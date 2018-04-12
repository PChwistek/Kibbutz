/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kibbutz.model.entity.Proof;
import kibbutz.model.entity.ProofPicture;
import kibbutz.model.entity.Survey;
import kibbutz.model.form.ProofForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Phil
 */
@Controller
public class ProofController {
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @PostMapping("/proof/post/{id}")
    public ModelAndView postProof(@RequestParam("file") MultipartFile file, @PathVariable("id") long imageId, 
            @ModelAttribute("comment") ProofForm proofForm){
        
        Survey theSurvey = surveyRepo.findOne(imageId);
        ProofPicture thePic = new ProofPicture(file);
        
        try {
            thePic.setPostPic(file.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ProofController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        thePic.setImageType(file.getContentType());
        theSurvey.setProof(new Proof(thePic));
        theSurvey.getProof().setComment(proofForm.getText());
        theSurvey.setActive(false);
        surveyRepo.save(theSurvey);
        
        return new ModelAndView("redirect:/posted_survey?id=" + imageId);
    }
    
}

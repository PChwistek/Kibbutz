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
import kibbutz.model.entity.User;
import kibbutz.model.form.ProofForm;
import kibbutz.model.form.SatisfiedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Phil
 */
@Controller
@SessionAttributes("user")
public class ProofController {
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    
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
    
    @PostMapping("/proof/post_vote/{id}")
    public ModelAndView vote(@PathVariable("id") long surveyId, 
            @ModelAttribute("satisfiedForm") SatisfiedForm satisfiedForm, @SessionAttribute("user") User user){
            
        Survey theSurvey = surveyRepo.findOne(surveyId);
        User viewedUser = userRepo.findUserByUsername(theSurvey.getAuthor());
        

        String level = satisfiedForm.getSatisfaction();

        if(level.equals("satisfied")){
            theSurvey.getProof().incrementNumSatisfied();
            viewedUser.setKarmaScore(viewedUser.getKarmaScore() + 1);
        } else {
            theSurvey.getProof().incrementNumDisatisfied();
            viewedUser.setKarmaScore(viewedUser.getKarmaScore() - 1);
        }

            
        User theUser = userRepo.findUserByUsername(user.getUsername());
        theUser.getSurveysReviewed().add(theSurvey);
        
        surveyRepo.save(theSurvey);
        userRepo.save(theUser);
        
        return new ModelAndView("redirect:/posted_survey?id=" + surveyId);
    }
    
    
}

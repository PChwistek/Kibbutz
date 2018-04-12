/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import kibbutz.model.entity.ProofPicture;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.SurveyPicture;
import kibbutz.model.entity.User;
import kibbutz.model.form.ProofForm;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Phil
 */

@Controller
@SessionAttributes("user")
public class SurveyDisplayController {
    
    @Autowired
    private SurveyPictureRepository surveyPicRepo;
    
    @Autowired
    private ProofPictureRepository proofPicRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/posted_survey")
    public String vote(@RequestParam("id") String id, Model model, @SessionAttribute("user") User user,
            @ModelAttribute("proofForm") ProofForm proofForm){
        
        Survey theSurvey = surveyRepo.findOne(Long.decode(id));
        model.addAttribute("proofForm", proofForm);
        model.addAttribute("survey", theSurvey);
        return "survey-detail";
    }
    
    @RequestMapping(value="/survey_pic/{id}")
    public void getSurveyImage(HttpServletResponse response , @PathVariable("id") long imageId) throws IOException{

      SurveyPicture thePic = surveyPicRepo.findSurveyPictureById(imageId);
      response.setContentType(thePic.getImageType());
      InputStream in1 = new ByteArrayInputStream(thePic.getPostPic());
      IOUtils.copy(in1, response.getOutputStream());        
    }
    
    @RequestMapping(value="/proof_pic/{id}")
    public void getProofImage(HttpServletResponse response , @PathVariable("id") long imageId) throws IOException{
      ProofPicture theProofPic = proofPicRepo.findProofPictureById(imageId);
      response.setContentType(theProofPic.getImageType());
      InputStream in1 = new ByteArrayInputStream(theProofPic.getPostPic());
      IOUtils.copy(in1, response.getOutputStream());        
    }
    
}

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
import kibbutz.model.form.SatisfiedForm;
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
    
    @Autowired
    private UserRepository userRepo;
    
    
    @GetMapping("/posted_survey")
    public String vote(@RequestParam("id") Long id, Model model, @SessionAttribute("user") User user){
        
        Survey theSurvey = surveyRepo.findOne(id);
        User theUser = userRepo.findUserByUsername(user.getUsername());
        
        model.addAttribute("proofForm", new ProofForm());
        model.addAttribute("satisfiedForm", new SatisfiedForm());
        model.addAttribute("survey", theSurvey);
        if(theSurvey.getProof() != null){
            model.addAttribute("numSatisfied", theSurvey.getProof().getNumSatisfied());
            model.addAttribute("numDisatisfied", theSurvey.getProof().getNumDisatisfied());
            Survey temp = theUser.getSurveysReviewed().stream().filter(aSurvey -> aSurvey.getSurveyId().equals(id))
            .findFirst().orElse(null);
            model.addAttribute("voted", temp != null);
        }
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

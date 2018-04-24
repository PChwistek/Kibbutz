/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import kibbutz.model.entity.Comment;
import kibbutz.model.entity.ProofPicture;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.SurveyPicture;
import kibbutz.model.entity.User;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.form.CommentForm;
import kibbutz.model.form.ProofForm;
import kibbutz.model.form.SatisfiedForm;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
        
        boolean voted = theSurvey.getAuthor().equalsIgnoreCase(user.getUsername());
        Survey tempSurvey = null;
        if(!voted){
            tempSurvey = theUser.getVotingHistory().stream().filter(survey -> survey.getSurveyId().equals(id)).findFirst().orElse(null);
            voted = tempSurvey != null;
        }
        
        model.addAttribute("proofForm", new ProofForm());
        model.addAttribute("satisfiedForm", new SatisfiedForm());
        model.addAttribute("survey", theSurvey);
        model.addAttribute("commentForm", new CommentForm());
        model.addAttribute("choiceForm", new ChoiceForm());
        model.addAttribute("voted", voted);
      
        
        if(theSurvey.getProof() != null){
            model.addAttribute("numSatisfied", theSurvey.getProof().getNumSatisfied());
            model.addAttribute("numDisatisfied", theSurvey.getProof().getNumDisatisfied());
        }
        
        return "survey-detail";
    }
    
    @PostMapping("survey/post_comment/{id}")
    public ModelAndView postComment(@PathVariable("id") Long id, Model model, @SessionAttribute("user") User user,
            @ModelAttribute("commentForm") CommentForm commentForm){
        
        User poster = userRepo.findUserByUsername(user.getUsername());
        Survey theSurvey = surveyRepo.findOne(id);
        theSurvey.getComments().add(new Comment(poster.getId(),
                commentForm.getText(),poster.getUsername() ));
        
        surveyRepo.save(theSurvey);
    
        return new ModelAndView("redirect:/posted_survey?id=" + id);
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

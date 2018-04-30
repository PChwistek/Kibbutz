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
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.SurveyPicture;
import kibbutz.model.entity.User;
import kibbutz.model.form.LoginForm;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.form.SignUpForm;
import kibbutz.model.form.SuggestedChoiceForm;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
//@RequestMapping("/kibbutz")
public class HomeController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyPictureRepository surveyPicRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/")
    public String index(ModelMap model) {
        
        User theUser;
        
        if(model.get("user") == null){
            model.addAttribute("loginForm", new LoginForm());
            model.addAttribute("signUpForm", new SignUpForm());
            return "index"; 
        } 
        
        User tempUser = (User)model.get("user");
        theUser = userRepo.findUserByUsername(tempUser.getUsername());
        
        
        System.out.println(theUser.getUsername());
        List<Survey> allActive = surveyRepo.findAllActive();
        List<Long> allActiveVoted = theUser.getVotingHistory().stream().
                filter(survey -> survey.isActive())
                .map(survey -> survey.getSurveyId())
                .collect(Collectors.toList());
        
        List<Long> votedChoiceSurveys = theUser.getVotedSuggestions().stream()
                .map(choice -> choice.getParentSurvey().getSurveyId())
                .collect(Collectors.toList());
        
        model.addAttribute("surveys", allActive);
        model.addAttribute("votedChoiceSurveys", votedChoiceSurveys);
        model.addAttribute("allActiveVoted", allActiveVoted);
        model.addAttribute("suggestionForm", new SuggestedChoiceForm());
        model.addAttribute("user", theUser);
        model.addAttribute("choiceForm", new ChoiceForm());

        return "index-signed-in";
    }
    
      
    @RequestMapping(value="/{id}")
    public void getSurveyImage(HttpServletResponse response , @PathVariable("id") long imageId) throws IOException{

      SurveyPicture thePic = surveyPicRepo.findSurveyPictureById(imageId);
      response.setContentType(thePic.getImageType());
      InputStream in1 = new ByteArrayInputStream(thePic.getPostPic());
      IOUtils.copy(in1, response.getOutputStream());        
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Phil
 */

@Controller
@RequestMapping("/survey")
public class PostController {
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("")
    public String survey(Model model){
        model.addAttribute("surveyForm", new SurveyForm());
        return "post-survey";
    }
    
    @PostMapping("/post")
    public String postSurvey(@Valid @ModelAttribute SurveyForm surveyForm, @SessionAttribute("user") User user){
        
        Survey theSurvey = new Survey(surveyForm);
        User someUser = userRepo.findOne(user.getId());
        someUser.getSurveys().add(theSurvey);
        userRepo.save(someUser);
        
        return "account-created";
    }
    
}

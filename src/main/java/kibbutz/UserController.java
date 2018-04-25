/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.User;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.form.SuggestedChoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Phil
 */
@Controller
@SessionAttributes("user")
public class UserController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/user")
    public RedirectView userInfo(@SessionAttribute("user") User user) {
        
        return new RedirectView("/profile?author=" + user.getUsername());
    }
    
    @GetMapping("/profile")
    public String userProfile(@RequestParam("author") String author, ModelMap model, @SessionAttribute("user") User user) {
        
        ArrayList<String> following = new ArrayList();
        ArrayList<String> followers = new ArrayList();
        
        User viewedUser = userRepo.findUserByUsername(author);
        User theUser = userRepo.findUserByUsername(user.getUsername());
        
        boolean isFollowing = author.equals(user.getUsername());
        
        model.addAttribute("posted", theUser.getSurveys());
        
        viewedUser.getFollowing().forEach((aUser) -> {
            following.add(aUser.getUsername());
        });        
        
        viewedUser.getFollowers().forEach((aUser) -> {
            followers.add(aUser.getUsername());
        });
        
        if(!isFollowing){
            isFollowing = followers.stream().anyMatch(username -> username.equalsIgnoreCase(user.getUsername()));
        }
        
        List<Long> allVoted = theUser.getVotingHistory().stream().filter(survey -> survey.getAuthor().equalsIgnoreCase(author)).map(survey -> survey.getSurveyId()).collect(Collectors.toList());
        
        model.addAttribute("allActiveVoted", allVoted);
        model.addAttribute("history", viewedUser.getVotingHistory());
        model.addAttribute("posted", viewedUser.getSurveys());
        model.addAttribute("account", viewedUser);
        model.addAttribute("followers", followers);
        model.addAttribute("choiceForm", new ChoiceForm());
        model.addAttribute("following", following);
        model.addAttribute("isFollowing", isFollowing);
        model.addAttribute("suggestionForm", new SuggestedChoiceForm());

        return "user-detail";
    }
    
}

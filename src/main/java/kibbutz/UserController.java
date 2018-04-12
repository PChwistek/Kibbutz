/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.ArrayList;
import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/user")
    public String userInfo(@SessionAttribute("user") User user, Model model) {
        
        ArrayList<String> following = new ArrayList();
        ArrayList<String> followers = new ArrayList();
        
        User theUser = userRepo.findUserByUsername(user.getUsername());
        
        model.addAttribute("posted", theUser.getSurveys());
        
        theUser.getFollowing().forEach((aUser) -> {
            following.add(aUser.getUsername());
        });        
        
        theUser.getFollowers().forEach((aUser) -> {
            followers.add(aUser.getUsername());
        });
        
        model.addAttribute("history", theUser.getVotingHistory());
        model.addAttribute("posted", theUser.getSurveys());
        model.addAttribute("followers", followers);
        model.addAttribute("following", following);
        return "user-info";
    }
    
    @GetMapping("/profile")
    public ModelAndView userProfile(@RequestParam("author") String author, ModelMap model, @SessionAttribute("user") User user) {
        
        user = userRepo.findUserByUsername(user.getUsername());
        
        User theUser = user.getFollowing().stream().filter(aUser -> aUser.getUsername().equals(author))
            .findFirst().orElse(null);
        
        boolean isFollowing = theUser != null;
        
        if(user.getUsername().equalsIgnoreCase(author)){
            return new ModelAndView("redirect:/user");
        }
        
        User temp = userRepo.findUserByUsername(author);
        
        model.addAttribute("posted", temp.getSurveys());
        model.addAttribute("isFollowing", isFollowing);
        model.addAttribute("user", user);
        model.addAttribute("author", userRepo.findUserByUsername(author));
        return new ModelAndView("user-detail", model);
    }
    
}

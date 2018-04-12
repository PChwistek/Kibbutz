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
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/user")
    public String userInfo(@SessionAttribute("user") User user, Model model) {
        
        model.addAttribute("posted", surveyRepo.findAll());
        
        ArrayList<String> following = new ArrayList();
        ArrayList<String> followers = new ArrayList();
        
        User theUser = userRepo.findUserByUsername(user.getUsername());
        System.out.println("In user controller!");
        
        System.out.println(theUser.getFollowing().size());
        
        theUser.getFollowing().forEach((aUser) -> {
            following.add(aUser.getUsername());
        });        
        
        theUser.getFollowers().forEach((aUser) -> {
            followers.add(aUser.getUsername());
        });
        
        model.addAttribute("followers", followers);
        model.addAttribute("following", following);
        return "user-info";
    }
    
    @GetMapping("/profile")
    public String userProfile(@RequestParam("author") String author, Model model) {
        
        model.addAttribute("author", userRepo.findUserByUsername(author));
        return "user-detail";
    }
    
}

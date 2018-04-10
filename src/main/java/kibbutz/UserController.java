/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author Phil
 */

@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/user")
    public String userInfo(@SessionAttribute("user") User user, Model model) {
       model.addAttribute("user", user);
       model.addAttribute("posted", surveyRepo.findAll());
       return "user-info";
    }
    
}

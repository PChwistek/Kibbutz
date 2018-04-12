/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.List;
import javax.persistence.EntityManager;
import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
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
public class FollowerController {
    
    @Autowired
    private UserRepository userRepo;   
    
    @PostMapping("/follow")
    public ModelAndView followUser(@RequestParam("id") String id, @SessionAttribute("user") User user, ModelMap model){
        
        User followed = userRepo.findOne(Long.decode(id));
        
        User theUser = userRepo.findUserByUsername(user.getUsername());
        System.out.println(theUser.getUsername());
        
        followed.getFollowers().add(theUser);
        theUser.getFollowing().add(followed);
        
        userRepo.save(theUser);
        userRepo.save(followed);
        
        followed = userRepo.findOne(Long.decode(id));
        theUser = userRepo.findUserByUsername(user.getUsername());
        
        System.out.println(followed.getFollowers().size());
        System.out.println(theUser.getFollowing().size());
        
        model.addAttribute("user", user);
        
        return new ModelAndView("redirect:/profile?author=" + followed.getUsername(), model);
    }
}
    


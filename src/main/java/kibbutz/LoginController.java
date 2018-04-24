/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import javax.validation.Valid;
import javax.websocket.Session;
import kibbutz.model.entity.User;
import kibbutz.model.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Phil
 */

@Controller
@SessionAttributes("user")
public class LoginController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PasswordHasher hasher;
    
         
    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, 
            SessionStatus status, ModelMap model) {
        
        User someUser = userRepo.findUserByUsername(loginForm.getUsername());
        
        boolean validLogin = someUser != null && hasher.passwordMatch(loginForm.getPassword(), someUser.getPassword());
        
        if(bindingResult.hasErrors() || !validLogin){
            status.setComplete();
            model.addAttribute("status", "Incorrect Login Credentials!");
            return new ModelAndView("redirect:/", model);
        }
        model.addAttribute("user", someUser);
        return new ModelAndView("redirect:/", model);
        
    }
   
    @GetMapping("/logout")
    public RedirectView logout2(@ModelAttribute("user") User user, SessionStatus status, 
            RedirectAttributes attributes) {
        
        user = null;
        status.setComplete();
        return new RedirectView("/");
    }
    
}

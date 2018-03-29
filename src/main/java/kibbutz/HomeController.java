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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("user")
public class HomeController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }
    
    @GetMapping("/")
    public String index(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("surveys", surveyRepo.findAll());
        return "index";
    }
    
    
    @PostMapping("/login")
    public RedirectView login(@Valid @ModelAttribute LoginForm loginForm, @ModelAttribute("user") User user, 
            SessionStatus status, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("loginForm", new LoginForm());
            model.addAttribute("status", "Has errors");
            return new RedirectView("/");
        }
        
        User someUser = userRepo.getUserByUsername(loginForm.getUsername());
        
        boolean validLogin = someUser != null && someUser.getPassword().equals(loginForm.getPassword());
        
        if(validLogin){
            user.setId(someUser.getId());
            model.addAttribute("status", "logged-in" + user.getUsername());
            return new RedirectView("/");
        } 
        
        status.setComplete();
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("status", "Wrong credentials!");
        return new RedirectView("/");
    }
    
    @PostMapping("/logout")
    public RedirectView logout(@ModelAttribute("user") User user, SessionStatus status, RedirectAttributes attributes) {
        user = null;
        status.setComplete();
        return new RedirectView("/");
    }
    
    
}

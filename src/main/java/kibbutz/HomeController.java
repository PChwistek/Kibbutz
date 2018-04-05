/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.form.LoginForm;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

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
        model.addAttribute("choiceForm", new ChoiceForm());
        model.addAttribute("surveys", surveyRepo.findAll());
        return "index";
    }
    
    
    
}

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
import org.springframework.validation.BindingResult;

/**
 *
 * @author Phil
 */

@Controller
public class SignUpController {
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign-up";
    }
    
    @PostMapping("/sign-up")
    public String createAccount(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult){
        
        if(!userForm.passwordIsValid() || bindingResult.hasErrors()){
            return "sign-up";
        }
        
        userRepo.save(new User(userForm));
        return "account-created";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.form.SignUpForm;
import kibbutz.model.entity.User;
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
    
    @Autowired
    private PasswordHasher hasher;
    
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute("message", "");
        return "sign-up";
    }
    
    @PostMapping("/sign-up")
    public String createAccount(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult bindingResult, Model model){
        
        boolean invalidPassword = !signUpForm.passwordIsValid();
        boolean hasErrors = bindingResult.hasErrors();
        boolean usernameNotUnique = userRepo.findUserByUsername(signUpForm.getUsername()) != null;
        
        if(invalidPassword || hasErrors || usernameNotUnique){
            String errorMsg = "";
            if(invalidPassword){
                errorMsg = "Passwords do not match";
            } else if (hasErrors){
                errorMsg = "****Make sure all fields are complete and fulfill requirements";
            } else {
                errorMsg = "Username not unique";
            }
            model.addAttribute("message", errorMsg);
            return "sign-up";
        }
        
        String hashedPass = hasher.hashPassword(signUpForm.getPassword());
        signUpForm.setPassword(hashedPass);
        
        userRepo.save(new User(signUpForm));
        return "account-created";
    }
    
    
}

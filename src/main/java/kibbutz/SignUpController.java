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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    
    @PostMapping("/sign-up")
    public RedirectView createAccount(@Valid @ModelAttribute SignUpForm signUpForm, 
            BindingResult bindingResult, Model model, RedirectAttributes attributes){
        
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
            attributes.addFlashAttribute("errorMsg", errorMsg);
            return new RedirectView("/");            
        }
        
        String hashedPass = hasher.hashPassword(signUpForm.getPassword());
        signUpForm.setPassword(hashedPass);
        
        User newUser = new User(signUpForm);
        userRepo.save(newUser);
        attributes.addFlashAttribute("user", newUser);
        return new RedirectView("/");
    }
    
    
}

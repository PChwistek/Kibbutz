/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.io.IOException;
import kibbutz.model.form.SurveyForm;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.User;
import java.util.Calendar;
import java.util.Date;
import javax.validation.Valid;
import kibbutz.model.entity.SurveyPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Phil
 */

@Controller
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("")
    public String survey(Model model){
        model.addAttribute("surveyForm", new SurveyForm());
        return "post-survey";
    }
    
    @PostMapping("/post")
    public RedirectView postSurvey(@Valid @ModelAttribute SurveyForm surveyForm,
            @SessionAttribute("user") User user, @RequestParam("file") MultipartFile file){
        
        Survey theSurvey = new Survey(surveyForm);
        theSurvey.setPoster(user);
        System.out.println(file.getContentType());
        
        Calendar cal = Calendar.getInstance(); // creates calendar
        theSurvey.setCreationTime(new Date());
        System.out.println(surveyForm.getMinutes());
        System.out.println(surveyForm.isCanSuggest());
        cal.add(Calendar.MINUTE, surveyForm.getMinutes()); // 2 minutes
        theSurvey.setTerminationTime(cal.getTime()); // returns new date object, one hour in the future
        theSurvey.setActive(true);
        SurveyPicture thePicture = new SurveyPicture(file);
        
        
        try{
            
            thePicture.setPostPic(file.getBytes());
            
        } catch(IOException ie){
            
        }
        theSurvey.setPicture(thePicture);

        User someUser = userRepo.findOne(user.getId());
        someUser.getSurveys().add(theSurvey);
        userRepo.save(someUser);
        return new RedirectView("/");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Phil
 */

@Controller
public class VoteController {
    
     @Autowired
    private SurveyRepository surveyRepo;
    
    
    @PostMapping("/vote")
    public RedirectView vote(@RequestParam("id") long id, @ModelAttribute ChoiceForm choice, Model model ){
        
        Survey theSurvey = surveyRepo.findOne(id);
        
        if(choice.equals(theSurvey.getChoices().get(0))){
            theSurvey.getChoices().get(0).incrementVote();
        } else {
            theSurvey.getChoices().get(1).incrementVote();
        }
        
        surveyRepo.save(theSurvey);
        model.addAttribute("selected", choice.getChoiceOne());
        return new RedirectView("/voted");
    }
    
    @GetMapping("/voted")
    public String voted(){
        return "voted";
    }
    
}

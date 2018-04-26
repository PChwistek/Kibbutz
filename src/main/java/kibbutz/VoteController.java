/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.Choice;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Phil
 */

@Controller
@SessionAttributes("user")
public class VoteController {
    
    @Autowired
    private SurveyRepository surveyRepo;
     
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ChoiceRepository choiceRepo;
    
    
    @PostMapping("/vote")
    public RedirectView vote(@RequestParam("id") Long id, @ModelAttribute ChoiceForm choice, @SessionAttribute("user") User user, 
            Model model ){
        
        Survey theSurvey = surveyRepo.findOne(id);
        User theUser = userRepo.findUserByUsername(user.getUsername());
        
        if(!theSurvey.getPoster().getId().equals(user.getId())){
         
            Choice votedChoice = theSurvey.getChoices().stream().
                    filter(temp -> temp.getName().equals(choice.getChoiceOne())).findFirst().orElse(null);
            
            if(votedChoice != null){
                votedChoice.incrementVote();
                choiceRepo.save(votedChoice);
            }
            
            theSurvey.incrementKarma();
            theUser.getVotingHistory().add(theSurvey);
            userRepo.save(theUser);
            surveyRepo.save(theSurvey);
        }
     
        return new RedirectView("/");
    }
}

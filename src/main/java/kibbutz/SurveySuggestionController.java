/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.List;
import kibbutz.model.entity.Choice;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.User;
import kibbutz.model.form.SuggestedChoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Philz zee Kill
 */

@Controller
@SessionAttributes("user")
public class SurveySuggestionController {
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ChoiceRepository choiceRepo;
    
    
    @PostMapping("survey/post_suggestion/{id}")
    public ModelAndView postComment(@PathVariable("id") Long id, Model model, @SessionAttribute("user") User user,
            @ModelAttribute("suggestionForm") SuggestedChoiceForm suggestedForm){
        
        User poster = userRepo.findUserByUsername(user.getUsername());
        Survey theSurvey = surveyRepo.findOne(id);
        theSurvey.getChoices().add(new Choice(suggestedForm.getText(), poster.getUsername()));
        findMaxSetMain(theSurvey.getChoices());
        surveyRepo.save(theSurvey);
        
        
        return new ModelAndView("redirect:/posted_survey?id=" + id);
    }
    
    
    
    private void findMaxSetMain(List<Choice> choiceList){
        
        Choice currentMax = choiceList.stream().filter(choice -> choice.isMainChoice() && choice.isSuggested()).findFirst().orElse(null);
        Choice nextMax = null;
        if(currentMax != null){
            for(Choice choice: choiceList){
                if(choice.getVotes() > currentMax.getVotes()){
                    currentMax.setMainChoice(false);
                    choiceRepo.save(currentMax);
                    nextMax = choice;
                }
            }
            if(nextMax != null){
               nextMax.setMainChoice(true);
               choiceRepo.save(nextMax);    
            }
        } else {
            
            Choice sugg = choiceList.stream().filter(choice -> choice.isSuggested()).findFirst().orElse(null);
            if(sugg != null){
                sugg.setMainChoice(true);
                choiceRepo.save(sugg);
            }
            
        }
        
    }
    
}

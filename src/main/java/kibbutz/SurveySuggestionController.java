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
    
    @PostMapping("survey/choice/vote_suggestion/{id}")
    public ModelAndView rank(@PathVariable("id") Long id, Model model, 
            @SessionAttribute("user") User user){
        
        Choice sugg = choiceRepo.findOne(id);
        Survey parentSurvey = sugg.getParentSurvey();
        
        
        if(!user.getVotedSuggestions().stream()
                .anyMatch(choice -> choice.getParentSurvey().getSurveyId().equals(parentSurvey.getSurveyId()))){
            
            User theUser = userRepo.findOne(user.getId());
            sugg.incrementRanking();
            choiceRepo.save(sugg);
            findMaxSetMain(sugg.getParentSurvey().getChoices());
            theUser.getVotedSuggestions().add(sugg);
            userRepo.save(theUser);
        
        }
        
        return new ModelAndView("redirect:/");
    }
    
    
    @PostMapping("survey/post_suggestion/{id}")
    public ModelAndView postSuggestion(@PathVariable("id") Long id, Model model, @SessionAttribute("user") User user,
            @ModelAttribute("suggestionForm") SuggestedChoiceForm suggestedForm){
        
        User poster = userRepo.findUserByUsername(user.getUsername());
        Survey theSurvey = surveyRepo.findOne(id);
        theSurvey.getChoices().add(new Choice(suggestedForm.getText(), false, poster));
        findMaxSetMain(theSurvey.getChoices());
        surveyRepo.save(theSurvey);
        
        
        return new ModelAndView("redirect:/posted_survey?id=" + id);
    }
    
    
    
    private void findMaxSetMain(List<Choice> choiceList){
        
        Choice currentMax = choiceList.stream().filter(choice -> choice.isMainChoice() && choice.isSuggested()).findFirst().orElse(null);
        Choice nextMax = null;
        if(currentMax != null){
            for(Choice choice: choiceList){
                if(choice.getRanking() > currentMax.getRanking()){
                    currentMax.setMainChoice(false);
                    nextMax = choice;
                }
            }
            if(nextMax != null){
               nextMax.setVotes(currentMax.getVotes());
               currentMax.setVotes(0);
               nextMax.setMainChoice(true);
               choiceRepo.save(currentMax);
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

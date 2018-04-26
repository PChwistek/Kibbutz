/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import kibbutz.model.entity.Choice;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Phil
 */
@Controller
public class ScheduledController {
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Scheduled(fixedDelay = 10000)
    public void checkActive(){
        
        System.out.println("Checking times...");
        
        List<Survey> theActiveSurveys = surveyRepo.findAllActive();
        
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date  
        
        for(Survey survey: theActiveSurveys){
                        
            if(cal.getTime().after(survey.getTerminationTime())){
                System.out.println("Out of time");
                survey.setActive(false);
                Choice winner = null;
                List<Choice> originalChoices = surveyRepo.findOne(survey.getSurveyId()).getOriginalChoices();
                for(Choice choice: originalChoices){
                    if(winner == null){
                        winner = choice;
                    } else if(choice.getVotes() > winner.getVotes()){
                        winner = choice;
                    }
                }
                
                Choice sugg = originalChoices.stream()
                        .filter(choice -> choice.isSuggested())
                        .findFirst()
                        .orElse(null);
                
                User sugger = null;
                if(sugg != null){
                    sugger = sugg.getSuggester();
                }
                
                if(sugger != null){
                    sugger.setKarmaScore(sugger.getKarmaScore() + sugg.getVotes());
                    userRepo.save(sugger);
                }
                
                survey.setWinning(winner);
                surveyRepo.save(survey);
                
            } else {
                long diff = survey.getTerminationTime().getTime() - cal.getTime().getTime();
                long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                survey.setMinutesLeft( (int) minutes);
                System.out.print("Time left: " + minutes);
                surveyRepo.save(survey);
            }
        }
        
    }
    
    
}

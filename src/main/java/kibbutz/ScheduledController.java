/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kibbutz.model.entity.Survey;
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
                surveyRepo.save(survey);
            }
        }
        
    }
    
    
}

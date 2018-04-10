/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Phil
 */

@Controller
public class SurveyDisplayController {
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @GetMapping("/posted_survey")
    public String vote(@RequestParam("id") String id, Model model ){
        
        Survey theSurvey = surveyRepo.findOne(Long.decode(id));
                
        model.addAttribute("survey", theSurvey);
        return "survey-detail";
    }
    
}

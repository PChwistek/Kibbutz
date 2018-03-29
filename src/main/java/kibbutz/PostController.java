/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Phil
 */

@Controller
public class PostController {
    
    @GetMapping("/post-survey")
    public String postSurvey(Model model){
        return "post-survey";
    }
    
}

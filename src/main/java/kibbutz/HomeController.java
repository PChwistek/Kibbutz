/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import kibbutz.model.entity.Survey;
import kibbutz.model.entity.SurveyPicture;
import kibbutz.model.form.LoginForm;
import kibbutz.model.form.ChoiceForm;
import kibbutz.model.entity.User;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class HomeController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private SurveyPictureRepository surveyPicRepo;
    
    @Autowired
    private SurveyRepository surveyRepo;
    
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }
    
    @GetMapping("/")
    public String index(@ModelAttribute("user") User user, Model model) {
        
        List<Survey> allActive = surveyRepo.findAllActive();
        if(!allActive.isEmpty()){
            long id = allActive.get(0).getPicture().getSurveyId();
            System.out.println(id);
        }
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("choiceForm", new ChoiceForm());        
        model.addAttribute("surveys", allActive);
        return "index";
    }
    
      
    @RequestMapping(value="/{id}")
    public void getSurveyImage(HttpServletResponse response , @PathVariable("id") long imageId) throws IOException{

      SurveyPicture thePic = surveyPicRepo.findSurveyPictureById(imageId);
      response.setContentType(thePic.getImageType());
      InputStream in1 = new ByteArrayInputStream(thePic.getPostPic());
      IOUtils.copy(in1, response.getOutputStream());        
    }
    
    
    
    
}

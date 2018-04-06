/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.SurveyPicture;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Phil
 */
public interface SurveyPictureRepository extends CrudRepository<SurveyPicture, Long> {
    
    SurveyPicture getSurveyPictureById(long id);

    
}    


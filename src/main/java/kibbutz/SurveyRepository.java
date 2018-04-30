/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Phil
 */
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    
        @Query("FROM Survey WHERE active = 1 ORDER BY creationTime DESC")
        List<Survey> findAllActive();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.List;
import kibbutz.model.entity.Choice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Phil
 */
public interface ChoiceRepository extends CrudRepository<Choice, Long> {
    
        @Query("FROM Choice WHERE suggested = 1")
        List<Choice> findAllOriginal();
    
}

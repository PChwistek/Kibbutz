/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import kibbutz.model.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Phil
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
    User findUserByUsername(String username);
    
    
}

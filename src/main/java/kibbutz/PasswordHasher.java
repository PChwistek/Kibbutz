/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Phil
 */
public class PasswordHasher {
    
    public PasswordHasher(){};
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public String hashPassword(String plainText){
        String hashedPassword = passwordEncoder.encode(plainText);
        return hashedPassword;
    }
    
    public boolean passwordMatch(String passwordOne, String passwordTwo){
        return passwordEncoder.matches(passwordOne, passwordTwo);
    }
    
}

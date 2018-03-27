/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Phil
 * 
 * https://spring.io/guides/gs/accessing-data-mysql/
 * 
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String first;
    private String last;
    private String username;
    private String password;    //should not be plaintext, but OK for now
    private String email;
  //  private Boolean emailConfirmed;
    private long karmaScore;
    
    protected User() {}
    
    protected User(UserForm userForm){
        this.username = userForm.getUsername();
        this.password = userForm.getPassword();
        this.first = userForm.getFirst();
        this.last = userForm.getLast();
        this.email = userForm.getEmail();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the karmaScore
     */
    public long getKarmaScore() {
        return karmaScore;
    }

    /**
     * @param karmaScore the karmaScore to set
     */
    public void setKarmaScore(long karmaScore) {
        this.karmaScore = karmaScore;
    }
    
}

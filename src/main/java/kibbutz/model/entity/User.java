/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.entity;

import kibbutz.model.entity.Survey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import kibbutz.model.form.SignUpForm;

/**
 *
 * @author Phil
 * 
 * https://spring.io/guides/gs/accessing-data-mysql/
 * https://gist.github.com/ffbit/3343910
 * 
 */

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    private String first;
    private String last;
    private String username;
    private String password;    //hashed
    private String email;
  //  private Boolean emailConfirmed;
    private long karmaScore;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "username")
    private List<Survey> surveys = new ArrayList();
    
    /* Not the best efficiency, but oh well 
    */
    
    @ManyToMany
    @JoinTable(name="followingTable")
    @JoinColumn(name="username")
    private Set<User> following;
    
    @ManyToMany
    @JoinTable(name="followerTable")
    @JoinColumn(name="username")
    private Set<User> followers;
    
    public User() {}
    
    public User(SignUpForm signUpForm){
        this.username = signUpForm.getUsername();
        this.password = signUpForm.getPassword();
        this.first = signUpForm.getFirst();
        this.last = signUpForm.getLast();
        this.email = signUpForm.getEmail();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return userId;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.userId = id;
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

    /**
     * @return the surveys
     */
    public List<Survey> getSurveys() {
        return surveys;
    }

    /**
     * @param surveys the surveys to set
     */
    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    /**
     * @return the following
     */
    public Set<User> getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    /**
     * @return the followers
     */
    public Set<User> getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

 
    

}

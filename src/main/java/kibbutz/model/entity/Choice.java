/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Phil
 */
@Entity
public class Choice {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private int votes;
    private String suggesterUsername;
    private boolean suggested;
    private boolean mainChoice;
    
    public Choice(){};
    
    public Choice(String name){
        this.name = name;
        this.votes = 0;
        this.suggested = false;
        this.suggesterUsername = "";
        this.mainChoice = true;
    }
    
    public Choice(String name, String suggesterUsername){
        this.name = name;
        this.suggesterUsername = suggesterUsername;
        this.votes = 0;
        this.suggested = true;
        this.mainChoice = false;
    }
    
    public void incrementVote(){
        this.votes++;
    }
    
    public void decrementVotes(){
        this.votes--;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(int votes) {
        this.votes = votes;
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
     * @return the suggesterUsername
     */
    public String getSuggesterUsername() {
        return suggesterUsername;
    }

    /**
     * @param suggesterUsername the suggesterUsername to set
     */
    public void setSuggesterUsername(String suggesterUsername) {
        this.suggesterUsername = suggesterUsername;
    }

    /**
     * @return the suggested
     */
    public boolean isSuggested() {
        return suggested;
    }

    /**
     * @param suggested the suggested to set
     */
    public void setSuggested(boolean suggested) {
        this.suggested = suggested;
    }

    /**
     * @return the mainChoice
     */
    public boolean isMainChoice() {
        return mainChoice;
    }

    /**
     * @param mainChoice the mainChoice to set
     */
    public void setMainChoice(boolean mainChoice) {
        this.mainChoice = mainChoice;
    }
    
}

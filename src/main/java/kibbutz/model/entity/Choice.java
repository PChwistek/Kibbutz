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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private int ranking;
    private boolean suggested;
    private boolean mainChoice;
    
    @ManyToOne
    @JoinColumn(name = "surveyId")
    private Survey parentSurvey;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private User suggester;
    
    public Choice(){};
    
    public Choice(String name){
        this.name = name;
        this.votes = 0;
        this.ranking = 0;
        this.suggested = false;
        this.mainChoice = true;
    }
    
    public Choice(String name, boolean mainChoice ){
        this.name = name;
        this.votes = 0;
        this.ranking = 0;
        this.suggested = true;
        this.mainChoice = mainChoice;
    }
    
    public Choice(String name, boolean mainChoice, User suggester ){
        this.name = name;
        this.votes = 0;
        this.ranking = 0;
        this.suggested = true;
        this.mainChoice = mainChoice;
        this.suggester = suggester;
    }
    
    public void incrementVote(){
        this.votes++;
    }
    
    public void decrementVotes(){
        this.votes--;
    }
    
    public void incrementRanking(){
        this.ranking++;
    }
    
    public void decrementRanking(){
        this.ranking--;
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

    /**
     * @return the parentSurvey
     */
    public Survey getParentSurvey() {
        return parentSurvey;
    }

    /**
     * @param parentSurvey the parentSurvey to set
     */
    public void setParentSurvey(Survey parentSurvey) {
        this.parentSurvey = parentSurvey;
    }

    /**
     * @return the ranking
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * @param ranking the ranking to set
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    /**
     * @return the suggester
     */
    public User getSuggester() {
        return suggester;
    }

    /**
     * @param suggester the suggester to set
     */
    public void setSuggester(User suggester) {
        this.suggester = suggester;
    }
    
}

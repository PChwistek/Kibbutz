/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.entity;

import kibbutz.model.entity.Choice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import kibbutz.model.form.SurveyForm;

/**
 *
 * @author Phil
 */
@Entity
public class Survey {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long surveyId;
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="CREATED_TIME")
    private Date creationTime;
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="TO_TERMINATE_TIME")
    private Date terminationTime;
    private String text;
    private String title;
    private String author;
    private boolean active;
    private int minutesLeft;
    private int karmaPotential = 0;
    private boolean canSuggest;
    
    @OneToMany(
            cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private List<Choice> choices = new ArrayList();
    
    @OneToMany(
        cascade = {CascadeType.MERGE, CascadeType.REFRESH},
        orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private List<Choice> suggestedChoices = new ArrayList();
    
    @OneToMany(
            cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private List<Comment> comments = new ArrayList();
    
    @OneToOne(
        cascade = {CascadeType.MERGE, CascadeType.REFRESH},
        orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private SurveyPicture picture;
    
    @OneToOne(
        cascade = {CascadeType.MERGE, CascadeType.REFRESH},
        orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private Proof proof;
    
    public Survey(){};
    
    public Survey(SurveyForm surveyForm){
        this.title = surveyForm.getTitle();
        this.text = surveyForm.getText();
        this.choices.add(new Choice(surveyForm.getChoiceOne()));
        this.choices.add(new Choice(surveyForm.getChoiceTwo()));
        
        if(surveyForm.getChoiceThree() != null && !surveyForm.getChoiceThree().equals("")){
            this.choices.add(new Choice(surveyForm.getChoiceThree()));
        }
        
        if(surveyForm.getChoiceFour() != null && !surveyForm.getChoiceFour().equals("")){
            this.choices.add(new Choice(surveyForm.getChoiceFour()));
        }
        
        this.canSuggest = surveyForm.isCanSuggest();
        
    }
    
    public void incrementKarma(){
        this.karmaPotential++;
    }

    /**
     * @return the id
     */
    public Long getSurveyId() {
        return surveyId;
    }

    /**
     * @param id the id to set
     */
    public void setSurveyId(Long id) {
        this.surveyId = id;
    }

  
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return the terminationTime
     */
    public Date getTerminationTime() {
        return terminationTime;
    }

    /**
     * @param terminationTime the terminationTime to set
     */
    public void setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the options
     */
    public List<Choice> getChoices() {
        return this.choices.stream().filter(choice -> choice.isSuggested() == false).collect(Collectors.toList());
    }

    /**
     * @param options the options to set
     */
    public void setOptions(List<Choice> choices) {
        this.choices = choices;
    }

    /**
     * @return the username
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param username the username to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the thePicture
     */
    public SurveyPicture getPicture() {
        return picture;
    }

    /**
     * @param thePicture the thePicture to set
     */
    public void setPicture(SurveyPicture thePicture) {
        this.picture = thePicture;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the proof
     */
    public Proof getProof() {
        return proof;
    }

    /**
     * @param proof the proof to set
     */
    public void setProof(Proof proof) {
        this.proof = proof;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the karmaPotential
     */
    public int getKarmaPotential() {
        return karmaPotential;
    }

    /**
     * @param karmaPotential the karmaPotential to set
     */
    public void setKarmaPotential(int karmaPotential) {
        this.karmaPotential = karmaPotential;
    }

    /**
     * @return the canSuggest
     */
    public boolean isCanSuggest() {
        return canSuggest;
    }

    /**
     * @param canSuggest the canSuggest to set
     */
    public void setCanSuggest(boolean canSuggest) {
        this.canSuggest = canSuggest;
    }

    /**
     * @return the minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * @param minutesLeft the minutesLeft to set
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * @return the suggestedChoices
     */
    public List<Choice> getSuggestedChoices() {
        return this.choices.stream().filter(choice -> choice.isSuggested() == true).collect(Collectors.toList());
    }

    /**
     * @param suggestedChoices the suggestedChoices to set
     */
    public void setSuggestedChoices(List<Choice> suggestedChoices) {
        this.suggestedChoices = suggestedChoices;
    }
  
    
}

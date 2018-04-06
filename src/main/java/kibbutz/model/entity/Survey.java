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
    private int upVotes;
    private int downVotes;
    private String author;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private List<Choice> choices = new ArrayList();
    
    @OneToOne(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "surveyId")
    private SurveyPicture picture;
    
    public Survey(){};
    
    public Survey(SurveyForm surveyForm){
        this.title = surveyForm.getTitle();
        this.text = surveyForm.getText();
        this.choices.add(new Choice(surveyForm.getChoiceOne()));
        this.choices.add(new Choice(surveyForm.getChoiceTwo()));
        //this.postPic = surveyForm.getPostPic();
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
     * @return the upVotes
     */
    public int getUpVotes() {
        return upVotes;
    }

    /**
     * @param upVotes the upVotes to set
     */
    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    /**
     * @return the downVotes
     */
    public int getDownVotes() {
        return downVotes;
    }

    /**
     * @param downVotes the downVotes to set
     */
    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    /**
     * @return the options
     */
    public List<Choice> getChoices() {
        return choices;
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
  
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.form;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Phil
 */
public class SurveyForm {
    
    private String text;
    @NotNull @Size(min=1, max=1000)
    private String title;
    private byte[] postPic;
    @NotNull @Size(min=1, max=100)
    private String choiceOne;
    @NotNull @Size(min=1, max=100)
    private String choiceTwo;
    @Size(max=100)
    private String choiceThree;
    @Size(max=100)
    private String choiceFour;
    @NotNull
    private int minutes;
    private boolean canSuggest = false;
    
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
     * @return the postPic
     */
    public byte[] getPostPic() {
        return postPic;
    }

    /**
     * @param postPic the postPic to set
     */
    public void setPostPic(byte[] postPic) {
        this.postPic = postPic;
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
     * @return the optionOne
     */
    public String getChoiceOne() {
        return choiceOne;
    }

    /**
     * @param optionOne the optionOne to set
     */
    public void setChoiceOne(String optionOne) {
        this.choiceOne = optionOne;
    }

    /**
     * @return the optionTwo
     */
    public String getChoiceTwo() {
        return choiceTwo;
    }

    /**
     * @param optionTwo the optionTwo to set
     */
    public void setChoiceTwo(String choiceTwo) {
        this.choiceTwo = choiceTwo;
    }

    /**
     * @return the terminationDate
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param terminationDate the terminationDate to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the choiceThree
     */
    public String getChoiceThree() {
        return choiceThree;
    }

    /**
     * @param choiceThree the choiceThree to set
     */
    public void setChoiceThree(String choiceThree) {
        this.choiceThree = choiceThree;
    }

    /**
     * @return the choiceFour
     */
    public String getChoiceFour() {
        return choiceFour;
    }

    /**
     * @param choiceFour the choiceFour to set
     */
    public void setChoiceFour(String choiceFour) {
        this.choiceFour = choiceFour;
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
    
}

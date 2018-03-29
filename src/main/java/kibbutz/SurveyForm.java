/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.Date;

/**
 *
 * @author Phil
 */
public class SurveyForm {
    
    private String text;
    private String title;
    private byte[] postPic;
    private String choiceOne;
    private String choiceTwo;
    private Date terminationDate;
    
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
    public Date getTerminationDate() {
        return terminationDate;
    }

    /**
     * @param terminationDate the terminationDate to set
     */
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
    
}

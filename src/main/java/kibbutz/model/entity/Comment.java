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
public class Comment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long commentId;
    private long authorId;
    private String text;
    private String username;

    public Comment(){}
    
    public Comment(Long authorId, String text, String username){
        this.authorId = authorId;
        this.text = text;
        this.username = username;
    }
    /**
     * @return the commentId
     */
    public long getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the author
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * @param author the author to set
     */
    public void setAuthorId(long author) {
        this.authorId = author;
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
    
}

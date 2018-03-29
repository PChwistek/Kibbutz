/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Phil
 */
@Entity
public class Survey {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="CREATED_TIME")
    private Date creationTime;
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="UPDATED_TIME")
    private Date terminationTime;
    private String text;
    
    @Column(name="POST_PIC")
    private byte[] postPic;
    //image
    
    protected Survey(){};

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
    
}

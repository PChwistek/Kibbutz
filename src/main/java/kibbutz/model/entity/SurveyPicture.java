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
import javax.persistence.Lob;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Phil
 */

@Entity
public class SurveyPicture {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    private String imageType;
    @Lob
    private byte[] postPic;
    
    public SurveyPicture(){};
    
    public SurveyPicture(MultipartFile file){
        this.imageType = file.getContentType();
    }

    /**
     * @return the surveyId
     */
    public Long getSurveyId() {
        return id;
    }

    /**
     * @param surveyId the surveyId to set
     */
    public void setSurveyId(Long id) {
        this.id = id;
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
     * @return the imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * @param imageType the imageType to set
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    
}

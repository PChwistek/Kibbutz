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
public class ProofPicture {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String imageType;
    @Lob
    private byte[] postPic;
    
    public ProofPicture(){};
    
    public ProofPicture(MultipartFile file){
        this.imageType = file.getContentType();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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

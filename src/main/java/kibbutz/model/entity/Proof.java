/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Phil
 */
@Entity
public class Proof {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long proofId;    
    private double karma;
    private int numSatisfied = 0;
    private int numDisatisfied = 0;
    private String comment;
    
    @OneToOne(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "proofId")
    private ProofPicture picture;
    
    public Proof(){}
    
    public Proof(ProofPicture picture){
        this.picture = picture;
    }
    
    public void incrementNumSatisfied(){
        this.setNumSatisfied(this.getNumSatisfied() + 1);
    }
    
    public void incrementNumDisatisfied(){
        this.setNumDisatisfied(this.getNumDisatisfied() - 1);
    }

    /**
     * @return the proofId
     */
    public Long getProofId() {
        return proofId;
    }

    /**
     * @param proofId the proofId to set
     */
    public void setProofId(Long proofId) {
        this.proofId = proofId;
    }

    /**
     * @return the karma
     */
    public double getKarma() {
        return karma;
    }

    /**
     * @param karma the karma to set
     */
    public void setKarma(double karma) {
        this.karma = karma;
    }

    /**
     * @return the picture
     */
    public ProofPicture getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(ProofPicture picture) {
        this.picture = picture;
    }

     /*
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the numSatisfied
     */
    public int getNumSatisfied() {
        return numSatisfied;
    }

    /**
     * @param numSatisfied the numSatisfied to set
     */
    public void setNumSatisfied(int numSatisfied) {
        this.numSatisfied = numSatisfied;
    }

    /**
     * @return the numDisatisfied
     */
    public int getNumDisatisfied() {
        return numDisatisfied;
    }

    /**
     * @param numDisatisfied the numDisatisfied to set
     */
    public void setNumDisatisfied(int numDisatisfied) {
        this.numDisatisfied = numDisatisfied;
    }
}

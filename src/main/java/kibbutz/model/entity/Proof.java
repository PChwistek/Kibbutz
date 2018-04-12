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
    private int satisfied;
    private int dissatisfied;
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

    /**
     * @return the satisfied
     */
    public int getSatisfied() {
        return satisfied;
    }

    /**
     * @param satisfied the satisfied to set
     */
    public void setSatisfied(int satisfied) {
        this.satisfied = satisfied;
    }

    /**
     * @return the dissatisfied
     */
    public int getDissatisfied() {
        return dissatisfied;
    }

    /**
     * @param dissatisfied the dissatisfied to set
     */
    public void setDissatisfied(int dissatisfied) {
        this.dissatisfied = dissatisfied;
    }

    /**
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
}

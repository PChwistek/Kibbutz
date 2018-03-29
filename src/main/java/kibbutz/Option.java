/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Phil
 */
@Entity
public class Option {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private int votes;
    
    public Option(String name, int votes){
        this.name = name;
        this.votes = votes;
    }
    
    public void incrementVote(){
        this.votes++;
    }
    
    public void decrementVotes(){
        this.votes--;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }
    
}

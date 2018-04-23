/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kibbutz.model.form;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Phil
 */
public class SignUpForm {
    
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String confirmUsername;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String confirmPassword;
    @NotNull @NotEmpty
    private String first;
    @NotNull @NotEmpty
    private String last;
    @NotNull @NotEmpty
    private String email;
    
    public SignUpForm(){};
    
    public boolean passwordIsValid(){
        
        return password.equals(confirmPassword) && username.equals(confirmUsername);
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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the confirmUsername
     */
    public String getConfirmUsername() {
        return confirmUsername;
    }

    /**
     * @param confirmUsername the confirmUsername to set
     */
    public void setConfirmUsername(String confirmUsername) {
        this.confirmUsername = confirmUsername;
    }
    
}

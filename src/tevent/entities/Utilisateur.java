/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

import java.util.Date;

/**
 *
 * @author hanto
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String cin;
    private String image;
    private Date dateNaissance;
    private String activation_token;
    private String reset_token;
    private String roles="[\"ROLE_USER\"]";

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email, String password, String cin, String image, Date dateNaissance, String activation_token, String reset_token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.image = image;
        this.dateNaissance = dateNaissance;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
    }

  

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", cin=" + cin + ", image=" + image + ", dateNaissance=" + dateNaissance + ", activation_token=" + activation_token + ", reset_token=" + reset_token + ", roles=" + roles + '}';
    }
    
    
}

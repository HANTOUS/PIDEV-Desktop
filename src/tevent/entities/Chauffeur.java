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
public class Chauffeur extends Utilisateur{
    
    private int numPermis;
    private Date dateDebut;
    private Date dateExpiration;
    private Date datePermis;
    private int idUser;

    public Chauffeur() {
    }

    public Chauffeur(int numPermis, Date dateDebut, Date dateExpiration, Date datePermis, int idUser) {
        this.numPermis = numPermis;
        this.dateDebut = dateDebut;
        this.dateExpiration = dateExpiration;
        this.datePermis = datePermis;
        this.idUser = idUser;
    }

        
    
    public int getNumPermis() {
        return numPermis;
    }

    public void setNumPermis(int numPermis) {
        this.numPermis = numPermis;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Date getDatePermis() {
        return datePermis;
    }

    public void setDatePermis(Date datePermis) {
        this.datePermis = datePermis;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    
    @Override
    public String toString() {
        return "Chauffeur{" + "numPermis=" + numPermis + ", dateDebut=" + dateDebut + ", dateExpiration=" + dateExpiration + ", datePermis=" + datePermis + '}';
    }

    
    

}

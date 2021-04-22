/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author hanto
 */
public class DemandeChauffeur {
    private int id ;
    private int utilisateur_id ;
    private int num_permis ;
    private LocalDate date_permis ; 
    private LocalDate date_expiration ;
    private String etat="encours";

    
    public DemandeChauffeur() {
    }

    //constructeur pour ajouter dans la base de données sans id 

    public DemandeChauffeur(int utilisateur_id, int num_permis, LocalDate date_permis, LocalDate date_expiration, String etat) {
        this.utilisateur_id = utilisateur_id;
        this.num_permis = num_permis;
        this.date_permis = date_permis;
        this.date_expiration = date_expiration;
        this.etat = etat;
    }
    //constructeur pour recuperer de la base de données  

    public DemandeChauffeur(int id, int utilisateur_id, int num_permis, LocalDate date_permis, LocalDate date_expiration, String etat) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.num_permis = num_permis;
        this.date_permis = date_permis;
        this.date_expiration = date_expiration;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public int getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(int num_permis) {
        this.num_permis = num_permis;
    }

    public LocalDate getDate_permis() {
        return date_permis;
    }

    public void setDate_permis(LocalDate date_permis) {
        this.date_permis = date_permis;
    }

    public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "DemandeChauffeur{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", num_permis=" + num_permis + ", date_permis=" + date_permis + ", date_expiration=" + date_expiration + ", etat=" + etat + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DemandeChauffeur other = (DemandeChauffeur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur_id != other.utilisateur_id) {
            return false;
        }
        if (this.num_permis != other.num_permis) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.date_permis, other.date_permis)) {
            return false;
        }
        if (!Objects.equals(this.date_expiration, other.date_expiration)) {
            return false;
        }
        return true;
    }
    

}

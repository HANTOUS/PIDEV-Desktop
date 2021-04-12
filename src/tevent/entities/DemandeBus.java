/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author hanto
 */
public class DemandeBus {

    private int id;
    private int utilisateur_id;
    private int nb_participant;
    private String ville_depart;
    private String ville_arrivee;
    private String heure_depart;
    private String heure_arrivee;
    private String etat="encours";
    private Date jour_location;

    public DemandeBus() {
    }

    //constructeur pour ajouter dans la base de données sans id 
    public DemandeBus(int utilisateur_id, int nb_participant, String ville_depart, String ville_arrivee, String heure_depart, String heure_arrivee, String etat, Date jour_location) {
        this.utilisateur_id = utilisateur_id;
        this.nb_participant = nb_participant;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
        this.etat = etat;
        this.jour_location = jour_location;
    }

    //constructeur pour recuperer de la base de données 
    public DemandeBus(int id, int utilisateur_id, int nb_participant, String ville_depart, String ville_arrivee, String heure_depart, String heure_arrivee, String etat, Date jour_location) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.nb_participant = nb_participant;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
        this.etat = etat;
        this.jour_location = jour_location;
    }

    public DemandeBus(int utilisateur_id, int nb_participant, String ville_depart, String ville_arrivee, String heure_depart, String heure_arrivee, String etat) {
        this.utilisateur_id = utilisateur_id;
        this.nb_participant = nb_participant;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
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

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public String getVille_depart() {
        return ville_depart;
    }

    public void setVille_depart(String ville_depart) {
        this.ville_depart = ville_depart;
    }

    public String getVille_arrivee() {
        return ville_arrivee;
    }

    public void setVille_arrivee(String ville_arrivee) {
        this.ville_arrivee = ville_arrivee;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public String getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(String heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getJour_location() {
        return jour_location;
    }

    public void setJour_location(Date jour_location) {
        this.jour_location = jour_location;
    }

    @Override
    public String toString() {
        return "DemandeBus{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", nb_participant=" + nb_participant + ", ville_depart=" + ville_depart + ", ville_arrivee=" + ville_arrivee + ", heure_depart=" + heure_depart + ", heure_arrivee=" + heure_arrivee + ", etat=" + etat + ", jour_location=" + jour_location + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final DemandeBus other = (DemandeBus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur_id != other.utilisateur_id) {
            return false;
        }
        if (this.nb_participant != other.nb_participant) {
            return false;
        }
        if (!Objects.equals(this.ville_depart, other.ville_depart)) {
            return false;
        }
        if (!Objects.equals(this.ville_arrivee, other.ville_arrivee)) {
            return false;
        }
        if (!Objects.equals(this.heure_depart, other.heure_depart)) {
            return false;
        }
        if (!Objects.equals(this.heure_arrivee, other.heure_arrivee)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.jour_location, other.jour_location)) {
            return false;
        }
        return true;
    }

}

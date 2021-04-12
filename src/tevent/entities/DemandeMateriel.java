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
public class DemandeMateriel {
      private int id ;
    private int utilisateur_id ;
    private int materiel_id ;
    private String qte ; 
    private String etat="encours"; 
    private Date date_debut ;
    private Date date_fin ;

    public DemandeMateriel() {
    }
    //constructeur pour recuperer de la base de données avec id 
    public DemandeMateriel(int id, int utilisateur_id, int materiel_id, String qte, String etat, Date date_debut, Date date_fin) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.materiel_id = materiel_id;
        this.qte = qte;
        this.etat = etat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    //constructeur pour ajouter dans la base de données sans id 

    public DemandeMateriel(int utilisateur_id, int materiel_id, String qte, String etat, Date date_debut, Date date_fin) {
        this.utilisateur_id = utilisateur_id;
        this.materiel_id = materiel_id;
        this.qte = qte;
        this.etat = etat;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
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

    public int getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(int materiel_id) {
        this.materiel_id = materiel_id;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "DemandeMateriel{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", materiel_id=" + materiel_id + ", qte=" + qte + ", etat=" + etat + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
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
        final DemandeMateriel other = (DemandeMateriel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.utilisateur_id != other.utilisateur_id) {
            return false;
        }
        if (this.materiel_id != other.materiel_id) {
            return false;
        }
        if (!Objects.equals(this.qte, other.qte)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
    
    
}

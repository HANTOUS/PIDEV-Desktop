/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

/**
 *
 * @author hanto
 */
public class Reclamation {
     private int id;
    private int user_id;
    private String sujet;
    private String contenu;
    private String etat="En cours";

    public Reclamation(int user_id, String sujet, String contenu) {
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
    }
    
    

    public Reclamation(int id, int user_id, String sujet, String contenu) {
        this.id = id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
    
    }
    
    public Reclamation(int id, int user_id, String sujet, String contenu, String etat) {
        this.id = id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.etat = etat;
    }
   
    
    
    public int getId() {
        return id;
    }

    public int getId_user() {
        return user_id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.user_id = id_user;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user=" + user_id + ", sujet=" + sujet + ", contenu=" + contenu + ", etat=" + etat + '}';
    }
    
}

package tevent.entities;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author al199
 */
public class Bus {
    
    
    private int id;
    private String fabriquant;
    private String modele;
    private int nbPlace;
    private Boolean panne;

    public Bus() {
    }

    public Bus(int id, String fabriquant, String modele, int nbPlace, Boolean panne) {
        this.id = id;
        this.fabriquant = fabriquant;
        this.modele = modele;
        this.nbPlace = nbPlace;
        this.panne = panne;
    }

    public Bus(String fabriquant, String modele, int nbPlace, Boolean panne) {
        this.fabriquant = fabriquant;
        this.modele = modele;
        this.nbPlace = nbPlace;
        this.panne = panne;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabriquant() {
        return fabriquant;
    }

    public void setFabriquant(String fabriquant) {
        this.fabriquant = fabriquant;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Boolean getPanne() {
        return panne;
    }

    public void setPanne(Boolean panne) {
        this.panne = panne;
    }

    @Override
    public String toString() {
        return "bus{" + "id=" + id + ", fabriquant=" + fabriquant + ", modele=" + modele + ", nbPlace=" + nbPlace + ", panne=" + panne + '}';
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
        final Bus other = (Bus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbPlace != other.nbPlace) {
            return false;
        }
        if (!Objects.equals(this.fabriquant, other.fabriquant)) {
            return false;
        }
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        if (!Objects.equals(this.panne, other.panne)) {
            return false;
        }
        return true;
    }
    
    
}

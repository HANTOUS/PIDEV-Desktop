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
public class Materiel {
    
    private int id;
    private String label;
    private int stock;
    private int qte_reserve;
    private Float prix;
    private Boolean dispo;

    public Materiel() {
    }

    public Materiel(int id, String label, int stock, int qte_reserve, Float prix, Boolean dispo) {
        this.id = id;
        this.label = label;
        this.stock = stock;
        this.qte_reserve = qte_reserve;
        this.prix = prix;
        this.dispo = dispo;
    }

    public Materiel(String label, int stock, int qte_reserve, Float prix, Boolean dispo) {
        this.label = label;
        this.stock = stock;
        this.qte_reserve = qte_reserve;
        this.prix = prix;
        this.dispo = dispo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQte_reserve() {
        return qte_reserve;
    }

    public void setQte_reserve(int qte_reserve) {
        this.qte_reserve = qte_reserve;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Boolean getDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", label=" + label + ", stock=" + stock + ", qte_reserve=" + qte_reserve + ", prix=" + prix + ", dispo=" + dispo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Materiel other = (Materiel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        if (this.qte_reserve != other.qte_reserve) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.dispo, other.dispo)) {
            return false;
        }
        return true;
    }
    
    
}

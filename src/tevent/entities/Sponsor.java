/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

/**
 *
 * @author skand
 */
public class Sponsor {
    private int id ;
    private int festival_id ;
    private String nom_sponsor;
    private String domaine_acivite;
    private String image;
    private String type_subvention;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFestival_id() {
        return festival_id;
    }

    public void setFestival_id(int festival_id) {
        this.festival_id = festival_id;
    }

    public String getNom_sponsor() {
        return nom_sponsor;
    }

    public void setNom_sponsor(String nom_sponsor) {
        this.nom_sponsor = nom_sponsor;
    }

    public String getDomaine_acivite() {
        return domaine_acivite;
    }

    public void setDomaine_acivite(String domaine_acivite) {
        this.domaine_acivite = domaine_acivite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType_subvention() {
        return type_subvention;
    }

    public void setType_subvention(String type_subvention) {
        this.type_subvention = type_subvention;
    }

    public Sponsor() {
    }

    public Sponsor(int id, int festival_id, String nom_sponsor, String domaine_acivite, String image, String type_subvention) {
        this.id = id;
        this.festival_id = festival_id;
        this.nom_sponsor = nom_sponsor;
        this.domaine_acivite = domaine_acivite;
        this.image = image;
        this.type_subvention = type_subvention;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id=" + id + ", festival_id=" + festival_id + ", nom_sponsor=" + nom_sponsor + ", domaine_acivite=" + domaine_acivite + ", image=" + image + ", type_subvention=" + type_subvention + '}';
    }
    
}

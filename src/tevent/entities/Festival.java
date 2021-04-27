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
public class Festival extends Event{
    
    private String type_fest ;
    private String artist;
    private String picture;
    private int nb_invit;

  

    public String getType_fest() {
        return type_fest;
    }

    public void setType_fest(String type_fest) {
        this.type_fest = type_fest;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getNb_invit() {
        return nb_invit;
    }

    public void setNb_invit(int nb_invit) {
        this.nb_invit = nb_invit;
    }

    public Festival(int id, String type_fest, String artist, String picture, int nb_invit) {
        super(id);
        this.type_fest = type_fest;
        this.artist = artist;
        this.picture = picture;
        this.nb_invit = nb_invit;
    }

    public Festival() {
    }

    
}

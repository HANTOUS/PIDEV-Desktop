/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

import java.util.Date;

/**
 *
 * @author maale
 */
public class Randonnee extends Event{
    private String typerand;

    public Randonnee(String typerand, int id, String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        super(id, nomevent, datedebut, datefin, heuredebut, heurefin, lieu, nbmaxparticipant, type, description, tarif, lat, lng);
        this.typerand = typerand;
    }

    public Randonnee() {
    }

    public Randonnee(String typerand, int id, String nomevent) {
        super(id, nomevent);
        this.typerand = typerand;
    }

    public Randonnee(String typerand, String nomevent) {
        super(nomevent);
        this.typerand = typerand;
    }

    public Randonnee(String typerand, String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        super(nomevent, datedebut, datefin, heuredebut, heurefin, lieu, nbmaxparticipant, type, description, tarif, lat, lng);
        this.typerand = typerand;
    }

   
    public String getTyperand() {
        return typerand;
    }

    public void setTyperand(String typerand) {
        this.typerand = typerand;
    }

    @Override
    public String toString() {
        return "Randonnee{" + "typerand=" + typerand + '}';
    }
    
}

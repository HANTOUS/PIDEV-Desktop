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
public class Event {
    private int id;
    private String nomevent;
    private Date datedebut,datefin;
    private String heuredebut,heurefin;
    private String lieu;
    private int nbmaxparticipant;
    private String type,description;
    private float tarif,lat,lng;

    public Event(int id, String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        this.id = id;
        this.nomevent = nomevent;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.lieu = lieu;
        this.nbmaxparticipant = nbmaxparticipant;
        this.type = type;
        this.description = description;
        this.tarif = tarif;
        this.lat = lat;
        this.lng = lng;
    }

    public Event(int id, String nomevent) {
        this.id = id;
        this.nomevent = nomevent;
    }

    public Event(int id) {
        this.id = id;
        
    }

    public Event(String nomevent) {
        this.nomevent = nomevent;
    }

       public Event( String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        
        this.nomevent = nomevent;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.lieu = lieu;
        this.nbmaxparticipant = nbmaxparticipant;
        this.type = type;
        this.description = description;
        this.tarif = tarif;
        this.lat = lat;
        this.lng = lng;
    }
    public Event() {
    }

    public int getId() {
        return id;
    }

    public String getNomevent() {
        return nomevent;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public String getHeuredebut() {
        return heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public String getLieu() {
        return lieu;
    }

    public int getNbmaxparticipant() {
        return nbmaxparticipant;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public float getTarif() {
        return tarif;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setHeuredebut(String heuredebut) {
        this.heuredebut = heuredebut;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setNbmaxparticipant(int nbmaxparticipant) {
        this.nbmaxparticipant = nbmaxparticipant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nomevent=" + nomevent + ", datedebut=" + datedebut + ", datefin=" + datefin + ", heuredebut=" + heuredebut + ", heurefin=" + heurefin + ", lieu=" + lieu + ", nbmaxparticipant=" + nbmaxparticipant + ", type=" + type + ", description=" + description + ", tarif=" + tarif + ", lat=" + lat + ", lng=" + lng + '}';
    }
    
    
}

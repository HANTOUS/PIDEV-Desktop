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
public class Camping extends Event {
    //private int id;
    private String localisation ;
    
     public Camping(String localisation,  String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        super( nomevent, datedebut, datefin, heuredebut, heurefin, lieu, nbmaxparticipant, type, description, tarif, lat, lng);
        this.localisation = localisation;
    } 
  
     
    public Camping(String localisation, int id, String nomevent, Date datedebut, Date datefin, String heuredebut, String heurefin, String lieu, int nbmaxparticipant, String type, String description, float tarif, float lat, float lng) {
        super(id, nomevent, datedebut, datefin, heuredebut, heurefin, lieu, nbmaxparticipant, type, description, tarif, lat, lng);
        this.localisation = localisation;
    }
    /*
    public Camping() {
    }*/

    public Camping(String localisation) {
        this.localisation = localisation;
    }

    public Camping(String localisation, int id, String nomevent) {
        super(id, nomevent);
        this.localisation = localisation;
    }

    public Camping(String localisation, String nomevent) {
        super(nomevent);
        this.localisation = localisation;
    }
   
      public Camping(int id,String localisation) {
          super();
        this.localisation = localisation;
    }
    public Camping() {
    }

   /* Camping(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "Camping{" + "localisation=" + localisation + '}';
    }
    
}

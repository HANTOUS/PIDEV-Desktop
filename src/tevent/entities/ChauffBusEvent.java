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
public class ChauffBusEvent {
    private int idUser;
    private int idBus;
    private int idEvent;
    private int heureDepart;
    private int heureArrive;
    private String villeDepart;
    private String villeArrive;

    public ChauffBusEvent() {
    }

    
    
    public ChauffBusEvent(int idUser, int idBus, int idEvent, int heureDepart, int heureArrive, String villeDepart, String villeArrive) {
        this.idUser = idUser;
        this.idBus = idBus;
        this.idEvent = idEvent;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(int heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(int heureArrive) {
        this.heureArrive = heureArrive;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    @Override
    public String toString() {
        return "ChauffBusEvent{" + "idUser=" + idUser + ", idBus=" + idBus + ", idEvent=" + idEvent + ", heureDepart=" + heureDepart + ", heureArrive=" + heureArrive + ", villeDepart=" + villeDepart + ", villeArrive=" + villeArrive + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idUser;
        hash = 79 * hash + this.idBus;
        hash = 79 * hash + this.idEvent;
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
        final ChauffBusEvent other = (ChauffBusEvent) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idBus != other.idBus) {
            return false;
        }
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.heureDepart != other.heureDepart) {
            return false;
        }
        if (this.heureArrive != other.heureArrive) {
            return false;
        }
        if (!Objects.equals(this.villeDepart, other.villeDepart)) {
            return false;
        }
        if (!Objects.equals(this.villeArrive, other.villeArrive)) {
            return false;
        }
        return true;
    }

    
}

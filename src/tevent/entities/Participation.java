/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.entities;

import java.util.Date;

/**
 *
 * @author Salim
 */
public class Participation {
    private int id;
    private int iduser_id ;
    private int idevent_id;
    private Date dateparticipation;

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public int getIduser_id() {
        return iduser_id;
    }

    public int getIdevent_id() {
        return idevent_id;
    }

    public Date getDateparticipation() {
        return dateparticipation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser_id(int iduser_id) {
        this.iduser_id = iduser_id;
    }

    public void setIdevent_id(int idevent_id) {
        this.idevent_id = idevent_id;
    }

    public void setDateparticipation(Date dateparticipation) {
        this.dateparticipation = dateparticipation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final Participation other = (Participation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", iduser_id=" + iduser_id + ", idevent_id=" + idevent_id + ", dateparticipation=" + dateparticipation + '}';
    }
    
    
    
}

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
public class Feedback {
      private int id;
    private int participf_id;
    private int note; 
    private String remarque;

    public Feedback(int id, int participf_id, int note, String remarque) {
        this.id = id;
        this.participf_id = participf_id;
        this.note = note;
        this.remarque = remarque;
    }

    public int getId() {
        return id;
    }

    public int getParticipf_id() {
        return participf_id;
    }

    public int getNote() {
        return note;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParticipf_id(int participf_id) {
        this.participf_id = participf_id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        final Feedback other = (Feedback) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", participf_id=" + participf_id + ", note=" + note + ", remarque=" + remarque + '}';
    }
    
}

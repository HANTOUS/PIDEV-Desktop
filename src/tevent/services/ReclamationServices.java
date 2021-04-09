/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import tevent.entities.Reclamation;
import tevent.tools.DataSource;


/**
 *
 * @author hanto
 */
public class ReclamationServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void addreclamation(Reclamation r){
        String req = "INSERT INTO reclamation (sujet, contenu, etat) VALUES ('"+r.getSujet()+"','"+r.getContenu()+"','"+r.getEtat()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delreclamation(Reclamation r){
        String req = "DELETE FROM Reclamation WHERE id="+ r.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updatereclamation(Reclamation r){
        String req = "UPDATE Reclamation SET sujet='"+r.getSujet()+"', contenu='"+r.getContenu()+"'WHERE id=" +r.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

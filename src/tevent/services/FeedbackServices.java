/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import tevent.entities.Feedback;
import tevent.tools.DataSource;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author hanto
 */
public class FeedbackServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void addFeedback(Feedback f){
        String req = "INSERT INTO feedback (note, remarque) VALUES ('"+f.getNote()+"','"+f.getRemarque()+"')";
        
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Feedback ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
    }
    
    public void delFeddback(Feedback f){
        
        String req ="DELETE FROM feedback WHERE id="+f.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeLargeUpdate(req);
            System.out.println("Feedback supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updatereclamation(Feedback f){
        String req = "UPDATE Feedback SET note='"+f.getNote()+"', remarque='"+f.getRemarque()+"'WHERE id=" +f.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Feedback modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

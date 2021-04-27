/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tevent.entities.Feedback;
import tevent.tools.DataSource;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tevent.entities.Event;


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
    
    public void delFeddback(int id){
        
        String req ="DELETE FROM feedback WHERE id="+id;
        try {
            Statement st = cnx.createStatement();
            st.executeLargeUpdate(req);
            System.out.println("Feedback supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateFeedback(Feedback f){
        String req = "UPDATE Feedback SET note='"+f.getNote()+"', remarque='"+f.getRemarque()+"'WHERE id=" +f.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Feedback modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String bestFeedback(){
        List<Feedback> list = new ArrayList<>();
        List<Event> list1 = new ArrayList<>();
        String  req="SELECT nomevent FROM event WHERE id IN(SELECT idevent_id FROM participation WHERE id IN (SELECT participf_id FROM feedback WHERE note > 3 )) ";
        try{
            /*Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);*/
            
            PreparedStatement ps = cnx.prepareStatement(req);
            //Statement st=cnx.createStatement();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                //list.add(new Feedback(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
            list1.add(new Event(rs.getString(1)));
            }
                

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1.get(0).getNomevent(); 
    }
    public ObservableList<Feedback> readFeedback(){
        List<Feedback> list = new ArrayList<>();
        ObservableList <Feedback> list1 = FXCollections.observableArrayList();
        String  req="SELECT * FROM feedback ";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Feedback(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1; 
    }
    
    public ObservableList<Feedback> readFeedbackByID(int id){
        List<Feedback> list = new ArrayList<>();
         ObservableList <Feedback> list1 = FXCollections.observableArrayList();

        String  req="SELECT * FROM Feedback  WHERE id="+id;
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Feedback(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list1; 
    }
    public ObservableList<Feedback> readFeedbackByNote(int note){
        List<Feedback> list = new ArrayList<>();
        ObservableList <Feedback> list1 = FXCollections.observableArrayList();

        String  req="SELECT * FROM Feedback  WHERE note="+note;
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Feedback(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list1; 
    }
    
    public float moyNoteEvent(int id){
        float moy=0;
        String req ="SELECT AVG(note) FROM feedback WHERE participf_id IN(SELECT id  FROM participation WHERE 	idevent_id  IN (SELECT id FROM event WHERE id= ?)) ";
    PreparedStatement ps; 
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                moy = rs.getFloat(1);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return (moy);
    }
    
    
    
}

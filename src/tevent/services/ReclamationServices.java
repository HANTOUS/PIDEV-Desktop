/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tevent.entities.Jmu;
import tevent.entities.Reclamation;
import tevent.tools.DataSource;


/**
 *
 * @author hanto
 */
public class ReclamationServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void addreclamation(Reclamation r) throws Exception{
        String req = "INSERT INTO reclamation (user_id,sujet, contenu, etat) VALUES ('"+r.getId_user()+"','"+r.getSujet()+"','"+r.getContenu()+"','"+r.getEtat()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation ajoutée !");
            Jmu.sendMail("mohamedsalim.jemai@esprit.tn", "reclamation", "bonjour votre réclamation a été bien reçue");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delreclamation(int id){
        String req = "DELETE FROM Reclamation WHERE id="+ id;
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
    
    public ObservableList<Reclamation> readReclamation(){
        List<Reclamation> list = new ArrayList<>();
        ObservableList<Reclamation> list1 = FXCollections.observableArrayList();
        String  req="SELECT * FROM reclamation ";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1; 
    }
    
    public ObservableList<Reclamation> readReclamationByID(int id){
        List<Reclamation> list = new ArrayList<>();
         ObservableList<Reclamation> list1 = FXCollections.observableArrayList();

        String  req="SELECT * FROM reclamation  WHERE id="+id;
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list1; 
    }
    
    public List<Reclamation> readReclamationBySubject(String s){
        List<Reclamation> list = new ArrayList<>();
        String  req="SELECT * FROM reclamation  WHERE sujet = ? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s);
            //Statement st=cnx.createStatement();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list; 
    }
    
    public ObservableList<Reclamation> reclamationTraité(){
        List<Reclamation> list = new ArrayList<>();
                ObservableList<Reclamation> list1 = FXCollections.observableArrayList();
        String  req="SELECT * FROM reclamation WHERE etat = 'traité' ";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1; 
    }
    
    public ObservableList<Reclamation> reclamationEncours(){
        List<Reclamation> list = new ArrayList<>();
        ObservableList<Reclamation> list1 = FXCollections.observableArrayList();
        String  req="SELECT * FROM reclamation WHERE etat = 'En cours' ";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Reclamation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1; 
    }
    
    public void traiteReclamation(int id) throws Exception{
        String req = "UPDATE reclamation SET etat= ? WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"traité");
           ps.setInt(2, id);
            ps.executeUpdate();
            Jmu.sendMail("mohamedsalim.jemai@esprit.tn", "reclamation", "Bonjour votre réclamation a été bien traité");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}

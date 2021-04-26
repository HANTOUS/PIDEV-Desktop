/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;
//import pidevzied.DataSource;
//import pidevzied.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tevent.entities.Event;
import tevent.tools.Connexion;

/**
 *
 * @author maale
 */

public class EventServices {
 Connection cnx;
 public static int InsertedId;

    public EventServices(Connection cnx) {
        this.cnx = Connexion.getInstance().getConnexion();
    }

    public EventServices() {
       this.cnx = Connexion.getInstance().getConnexion();
    }    
    public void AddEvent(Event e) throws SQLException{
      String  req="INSERT INTO event (nomevent,datedebut,datefin,heuredebut,heurefin,lieu,nbmaxparticipant,type,description,tarif,discr) VALUES ('"+e.getNomevent()+"','"+e.getDatedebut()+"','"+e.getDatefin()+"','"+e.getHeuredebut()+"','"+e.getHeurefin()+"','"+e.getLieu()+"','"+e.getNbmaxparticipant()+"','"+e.getType()+"','"+e.getDescription()+"','"+e.getTarif()+"','"+""+"')";
     PreparedStatement ps = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
     
     ps.executeUpdate(); 
     try(ResultSet generatedKeys = ps.getGeneratedKeys()){
            generatedKeys.next();               
            InsertedId = generatedKeys.getInt(1);
            
            System.out.println("event ajouté");

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
              
    }
    public void DeleteEvent(Event e){
      String  req="DELETE FROM event WHERE id="+e.getId();
      try{
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("event supprimé");

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
    }
    
    public List<Event> ReadEvent(){
        List<Event> list = new ArrayList<>();
        String  req="SELECT * FROM event ";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list.add(new Event(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getFloat(11),rs.getFloat(12),rs.getFloat(13)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list; 
    }
    public void UpdateEvent(Event e){
        String req = "UPDATE event SET nomevent='"+e.getNomevent()+"', datedebut='"+e.getDatedebut()+"', datefin='"+e.getDatefin()+"', heuredebut='"+e.getHeuredebut()+"',heurefin='"+e.getHeurefin()+"', lieu='"+e.getLieu()+"', nbmaxparticipant='"+e.getNbmaxparticipant()+"', type='"+e.getType()+"', description='"+e.getDescription()+"', tarif='"+e.getTarif()+"', lat='"+e.getLat()+"', lng='"+e.getLng()+"'WHERE id=" +e.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Event modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Event> ReadEventByID(int id){
        List<Event> list = new ArrayList<>();
        String  req="SELECT * FROM event  WHERE id="+id;
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list.add(new Event(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getFloat(11),rs.getFloat(12),rs.getFloat(13)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list; 
    }
    
        public List<Event> ReadEventByName(String nom){
        List<Event> list = new ArrayList<>();
        String  req="SELECT * FROM event  WHERE nomevent='?' ";
        try{
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            //Statement st=cnx.createStatement();
            ResultSet rs=ps.executeQuery(req);
            while(rs.next()){
                list.add(new Event(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getFloat(11),rs.getFloat(12),rs.getFloat(13)));
            
            }
            
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
             return list; 
    }
      


}

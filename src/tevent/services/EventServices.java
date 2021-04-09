/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;
import tevent.tools.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import tevent.entities.Event;

/**
 *
 * @author maale
 */
public class EventServices {
    private Connection cnx=DataSource.getInstance().getCnx();
    public void AddEvent(Event e){
      String  req="INSERT INTO event (nomevent,datedebut,datefin,heuredebut,heurefin,lieu,nbmaxparticipant,type,description,tarif,lat,lng,discr) VALUES ('"+e.getNomevent()+"','"+e.getDatedebut()+"','"+e.getDatefin()+"','"+e.getHeuredebut()+"','"+e.getHeurefin()+"','"+e.getLieu()+"','"+e.getNbmaxparticipant()+"','"+e.getType()+"','"+e.getDescription()+"','"+e.getTarif()+"','"+e.getLat()+"','"+e.getLng()+"','"+""+"')";
      try{
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
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
    
}

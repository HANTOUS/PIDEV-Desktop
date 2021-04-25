/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.ChauffBusEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tevent.tools.DataSource;

/**
 *
 * @author al199
 */
public class ChauffBusEventService {
    
    private int idUser;
    private int idBus;
    private int idEvent;
    private int heureDepart;
    private int heureArrive;
    private String villeDepart;
    private String villeArrive;
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<ChauffBusEvent> listChauffBusEvent (){
      List<ChauffBusEvent> list = new ArrayList<>() ;
         String req="SELECT * FROM materiel";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new ChauffBusEvent(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addChauffBusEvent (ChauffBusEvent m){
    String req ="INSERT INTO chauffbusevent (id_user, id_bus, id_event, heure_depart, heure_arrive, ville_depart, ville_arrive) VALUES ("+m.getIdUser()+","+m.getIdBus()+","+m.getIdEvent()+","+m.getHeureDepart()+","+m.getHeureArrive()+",'"+m.getVilleDepart()+"', '"+m.getVilleArrive()+"') ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("ChauffBusEvent Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteChauffBusEvent (ChauffBusEvent m){
    String req ="DELETE FROM chauffbusevent WHERE  id=" + m.getId();
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("ChauffBusEvent Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateChauffBusEvent (ChauffBusEvent m){
    String req ="UPDATE chauffbusevent SET id_user="+m.getIdUser()+",id_bus="+m.getIdUser()+",id_event="+m.getIdUser()+",heure_depart="+m.getHeureDepart()+",heure_arrive="+m.getHeureArrive()+",ville_depart"+m.getVilleDepart()+",ville_arrive"+m.getVilleArrive()+" WHERE id="+m.getId()  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("ChauffBusEvent modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
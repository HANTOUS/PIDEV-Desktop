/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.DemandeBus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeBusServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<DemandeBus> readDemandeBus (){
      List<DemandeBus> list = new ArrayList<>() ;
         String req="SELECT * FROM demande_bus";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new DemandeBus(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addDemandeBus (DemandeBus b){
    String req ="INSERT INTO demande_bus (utilisateur_id, nb_participant, ville_arrivee, ville_depart, heure_arrivee, heure_depart, etat, jour_location) VALUES ('"+b.getUtilisateur_id()+"','"+b.getNb_participant()+"','"+b.getVille_arrivee()+"','"+b.getVille_depart()+"','"+b.getHeure_arrivee()+"','"+b.getHeure_depart()+"','"+b.getEtat()+"','"+b.getJour_location()+"') ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeBus Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteDemandeBus (DemandeBus b){
    String req ="DELETE FROM demande_bus WHERE  id=" + b.getId();
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeBus Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateDemandeBus (DemandeBus b){
    String req ="UPDATE  demande_bus SET utilisateur_id='"+b.getUtilisateur_id()+"',nb_participant='"+b.getNb_participant()+"',ville_depart='"+b.getVille_depart()+"' ville_arrivee='"+b.getVille_arrivee()+"' heure_depart='"+b.getHeure_depart()+"' heure_arrivee='"+b.getHeure_arrivee()+"' etat='"+b.getEtat()+"' jour_location='"+b.getJour_location()+"' WHERE id='"+b.getId()+"' "  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeBus modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

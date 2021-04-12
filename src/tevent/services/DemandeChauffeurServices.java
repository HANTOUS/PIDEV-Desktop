/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.DemandeChauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tevent.entities.DemandeChauffeur;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeChauffeurServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<DemandeChauffeur> readDemandeChauffeur (){
      List<DemandeChauffeur> list = new ArrayList<>() ;
         String req="SELECT * FROM demande_chauffeur";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new DemandeChauffeur(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addDemandeChauffeur (DemandeChauffeur c){
    String req ="INSERT INTO demande_chauffeur (utilisateur_id, num_permis, date_permis, date_expiration, date_expiration) VALUES ('"+c.getUtilisateur_id()+"','"+c.getNum_permis()+"','"+c.getDate_permis()+"','"+c.getDate_expiration()+"') ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteDemandeChauffeur (int id){
    String req ="DELETE FROM demande_chauffeur WHERE  id=" + id;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateDemandeChauffeur (DemandeChauffeur c){
    String req ="UPDATE  demande_chauffeur SET utilisateur_id='"+c.getUtilisateur_id()+"',num_permis='"+c.getNum_permis()+"',date_permis='"+c.getDate_permis()+"' date_expiration='"+c.getDate_expiration()+"' WHERE id='"+c.getId()+"' "  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void AccepterDemande(int id){
                List<DemandeChauffeur> list = new ArrayList<>() ;
           String req = "update demande_chauffeur set  date_expiration= ? where id = ?";

    
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"accepter");
            ps.setInt(2,id);
            ps.executeUpdate();
                     
             

             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
}

public void RefuserDemande(int id){
           String req = "update demande_chauffeur set  date_expiration= ? where id = ?";

    
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"refuser");
            ps.setInt(2,id);
            ps.executeUpdate();
                     
             

             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
}
    
    public List<DemandeChauffeur> advancedSearchDemandeChauffeur(int num_permis, Date date_expiration){
            List<DemandeChauffeur> list = new ArrayList<>() ;

        try {
            String req="select * from demande_chauffeur where num_permis= ?";
            PreparedStatement ps = cnx.prepareStatement(req) ;
            ps.setInt(1,num_permis);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            list.add(new DemandeChauffeur(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req="select * from demande_chauffeur where date_expiration= ?";
            PreparedStatement ps = cnx.prepareStatement(req) ;
            ps.setDate(1, (java.sql.Date) date_expiration);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            list.add(new DemandeChauffeur(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list ;
        
}
    
}

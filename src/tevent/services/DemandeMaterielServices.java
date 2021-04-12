/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.DemandeMateriel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tevent.entities.DemandeMateriel;
import tevent.entities.DemandeMateriel;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeMaterielServices {
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<DemandeMateriel> readDemandeMateriel (){
      List<DemandeMateriel> list = new ArrayList<>() ;
         String req="SELECT * FROM demande_materiel";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new DemandeMateriel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addDemandeMateriel (DemandeMateriel c, int quantite,Date date_debut ,Date date_fin){
    String req ="INSERT INTO demande_materiel (utilisateur_id, materiel_id, qte, etat, date_debut, date_fin) VALUES ('"+c.getUtilisateur_id()+"','"+c.getMateriel_id()+"','"+quantite+"','"+c.getEtat()+"','"+date_debut+"','"+date_fin+"') ";
        try {
            
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeMateriel Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteDemandeMateriel (int id){
    String req ="DELETE FROM demande_materiel WHERE  id=" + id;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeMateriel Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateDemandeMateriel (DemandeMateriel c){
    String req ="UPDATE  demande_materiel SET utilisateur_id='"+c.getUtilisateur_id()+"',materiel_id='"+c.getMateriel_id()+"',qte='"+c.getQte()+"' etat='"+c.getEtat()+"' date_debut='"+c.getDate_fin()+"' date_fin='"+c.getDate_fin()+"' WHERE id='"+c.getId()+"' "  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeMateriel modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void AccepterDemande(int id){
                List<DemandeMateriel> list = new ArrayList<>() ;
           String req = "update demande_materiel set  etat= ? where id = ?";

    
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
           String req = "update demande_materiel set  etat= ? where id = ?";

    
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"refuser");
            ps.setInt(2,id);
            ps.executeUpdate();
                     
             

             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
}
    
    public List<DemandeMateriel> advancedSearchDemandeMateriel(int qte, String etat){
            List<DemandeMateriel> list = new ArrayList<>() ;

        try {
            String req="select * from demande_materiel where qte= ?";
            PreparedStatement ps = cnx.prepareStatement(req) ;
            ps.setInt(1,qte);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                        list.add(new DemandeMateriel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req="select * from demande_materiel where etat= ?";
            PreparedStatement ps = cnx.prepareStatement(req) ;
            ps.setString(1,etat);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                        list.add(new DemandeMateriel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list ;
        
}
    }


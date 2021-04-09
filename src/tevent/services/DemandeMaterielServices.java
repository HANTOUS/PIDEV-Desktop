/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.DemandeMateriel;
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
    
    public  void addDemandeMateriel (DemandeMateriel c){
    String req ="INSERT INTO demande_materiel (utilisateur_id, materiel_id, qte, etat, date_debut, date_fin) VALUES ('"+c.getUtilisateur_id()+"','"+c.getMateriel_id()+"','"+c.getQte()+"','"+c.getEtat()+"','"+c.getDate_debut()+"','"+c.getDate_fin()+"') ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeMateriel Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteDemandeMateriel (DemandeMateriel c){
    String req ="DELETE FROM demande_materiel WHERE  id=" + c.getId();
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
}

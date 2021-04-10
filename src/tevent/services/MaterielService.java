/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.Materiel;
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
public class MaterielService {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<Materiel> listMateriel (){
      List<Materiel> list = new ArrayList<>() ;
         String req="SELECT * FROM materiel";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5),rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addMateriel (Materiel m){
    String req ="INSERT INTO materiel (label, stock, qte_reserve, prix, dispo) VALUES ('"+m.getLabel()+"',"+m.getStock()+","+m.getQte_reserve()+","+m.getPrix()+","+m.getDispo()+") ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Materiel Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteMateriel (Materiel m){
    String req ="DELETE FROM materiel WHERE  id=" + m.getId();
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Materiel Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateMateriel (Materiel m){
    String req ="UPDATE materiel SET label='"+m.getLabel()+"',stock='"+m.getStock()+"',qte_reserve="+m.getQte_reserve()+" ,prix="+m.getPrix()+",dispo="+m.getDispo()+" WHERE id="+m.getId()  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Materiel modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

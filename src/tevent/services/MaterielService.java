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
    
    
    public List<Materiel> findDispo()
    {
        List<Materiel> list = new ArrayList<>() ;
        
        String sql = "SELECT * from materiel WHERE dispo != 0";
        try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
            list.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5),rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Materiel> searchLabel(Materiel m)
    {
        
        List<Materiel> list = new ArrayList<>() ;
        
        String sql = "SELECT * from materiel WHERE label like '"+m.getLabel()+"%'";
        try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
            list.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5),rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Materiel> almostUnavailable()
    {
        
        List<Materiel> list = new ArrayList<>() ;
        
        String sql = "SELECT *, (m.stock - m.qte_reserve) AS free from materiel WHERE (m.stock - m.qte_reserve) <= 15 ORDER By free DESC";
        try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
            list.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5),rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Materiel> filterMat(Materiel m)
    {
        
        List<Materiel> list = new ArrayList<>() ;
        
        String sql = "SELECT * from materiel WHERE label LIKE '"+m.getLabel()+"%' ";
        
        if ( (m.getDispo() == true) || (m.getDispo() == false) ) {
            sql = sql + "AND dispo = " +m.getDispo();
        }

        if (
            (m.getStock() != 0 ) ||
            (m.getQte_reserve() != 0 ) ||
            (m.getPrix() != 0)
        ) {
            sql = sql +" ORDER BY";
        }
        
        if (m.getStock() != 0 ) {
            if(m.getStock() == -1)
                sql = sql + " stock DESC";
            if(m.getStock() == 1)
                sql = sql + " stock ASC";
        }

        if (m.getQte_reserve() != 0 ) {
            if (m.getStock() != 0) {
                sql = sql + ", ";
            }
            if(m.getStock() == -1)
                sql = sql + " qte_reserve DESC";
            else
                sql = sql + " qte_reserve ASC";
        }

        if (m.getPrix() != Float.parseFloat("0") ) {
            if (m.getQte_reserve() !=  0 ) {
                sql = sql + ", ";
            }
             if(m.getStock() == Float.parseFloat("-1"))
                sql = sql + " prix DESC";
            else
                sql = sql + " prix ASC";
        }
        
        try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
            list.add(new Materiel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getFloat(5),rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
}

package tevent.services;

import java.sql.*;

import tevent.entities.Bus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tevent.tools.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author al199
 */
public class BusService {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
     public  List<Bus> listBus (){
      List<Bus> list = new ArrayList<>() ;
         String req="SELECT * FROM bus";
         try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
            list.add(new Bus(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  void addBus (Bus b){
    String req ="INSERT INTO bus (fabriquant, modele, nb_place, panne) VALUES ('"+b.getFabriquant()+"','"+b.getModele()+"',"+b.getNbPlace()+","+b.getPanne()+") ";
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Bus Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void deleteBus (Bus b){
    String req ="DELETE FROM bus WHERE  id=" + b.getId();
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Bus Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public  void updateBus (Bus b){
    String req ="UPDATE bus SET fabriquant='"+b.getFabriquant()+"',modele='"+b.getModele()+"',nb_place="+b.getNbPlace()+",panne="+b.getPanne()+" WHERE id="+b.getId()+" "  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("Bus modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

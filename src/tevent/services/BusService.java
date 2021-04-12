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
     
    public List<Bus> searchWithMultiParams(Bus b){
        List<Bus> list = new ArrayList<>() ;
        
        String sql = "SELECT * from bus WHERE fabriquant LIKE '" +b.getFabriquant() +"%' AND modele LIKE '" +b.getModele() +"%\'";

        if (b.getPanne() != null ) {
            if(b.getPanne() == true)
                sql = sql + " AND panne = 1";
            if(b.getPanne() == false)
                sql = sql + " AND panne = 0";
        }

        if (b.getNbPlace() != 0) {
            if(b.getNbPlace() == -1)
                sql = sql + " ORDER BY nb_place DESC";
            else
                sql = sql + " ORDER BY nb_place ASC";
        }

        try {
            Statement st= cnx.createStatement() ;
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
            list.add(new Bus(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public  Bus addBus (Bus b){
  
        try {
            
            if(b.getNbPlace()<1 || b.getFabriquant().equals("") || b.getModele().equals("")){
                System.out.println("Le Nombre de place doit etre supérieur ou egale à 1 et les champs sont obligatoires !");
                return b;
            }
            else{
            Statement st= cnx.createStatement() ;
            
            PreparedStatement statement = cnx.prepareStatement("INSERT INTO bus (fabriquant, modele, nb_place, panne) VALUES (?,?,?,?) ",
                                      Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getFabriquant());
            statement.setString(2, b.getModele());
            statement.setInt(3, b.getNbPlace());
            statement.setBoolean(4, b.getPanne());
            
            int affectedRows = statement.executeUpdate();
            //st.executeUpdate(req);
            if (affectedRows == 0) {
            throw new SQLException("Creating bus failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("Bus Ajoutée !");
                    b.setId(generatedKeys.getInt(1));
                    return b;
                }
                else {
                    throw new SQLException("Creating bus failed, no ID obtained.");
                }
            }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return b;
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
        if(b.getNbPlace()<1 || b.getFabriquant().equals("") || b.getModele().equals("")){
                System.out.println("Le Nombre de place doit etre supérieur ou egale à 1 et les champs sont obligatoires !");
        }
        else{
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
}

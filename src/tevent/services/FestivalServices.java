/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tevent.entities.Festival;
import tevent.interfaces.IserviceFestival;
import tevent.tools.DataSource;

/**
 *
 * @author skand
 */
public class FestivalServices implements IserviceFestival{
    Connection cnx;

    public FestivalServices(Connection cnx) {
        this.cnx = DataSource.getInstance().getCnx();
    }

    public FestivalServices() {
       this.cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public void AjouterFestival(Festival F) {
         int insertedID =0 ;
        String queryUe = "INSERT INTO `event`(`nomevent`,`tarif`)"
                + "VALUES(?,?)" ;
        try {
            PreparedStatement pse = cnx.prepareStatement(queryUe,Statement.RETURN_GENERATED_KEYS);
            pse.setString(1,F.getNomevent());
            pse.setFloat(2,F.getTarif());
            pse.executeUpdate();
            
            
           
            
            try (ResultSet generatedKeys = pse.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                insertedID = (generatedKeys.getInt(1));
                System.out.println(insertedID);
            }
            else {
                throw new SQLException("Creating fest failed, no ID obtained.");
            }
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
     String queryU = "INSERT INTO `festival`(id,`type_fest`, `artist`, `picture`, `nb_invit`) VALUES(?,?,?,?,?)";
            PreparedStatement ps;
        try {
           ps = cnx.prepareStatement(queryU);
           ps.setInt(1,insertedID );//ID EVENT
           ps.setString(2,F.getType_fest());
           ps.setString(3,F.getArtist());
           ps.setString(4, F.getPicture());
           ps.setInt(5, F.getNb_invit());
           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
            
               }

    @Override
    public ObservableList<Festival> AfficherFestival() throws SQLException {
     
            List <Festival> Festival = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select * from festival INNER JOIN event WHERE festival.id=event.id");
            
            ObservableList <Festival> oblist =  FXCollections.observableArrayList();
            
             while (rst.next())
            {
                
                Festival f= new Festival();
                f.setId(rst.getInt(1));
                f.setType_fest(rst.getString(2));
                f.setArtist(rst.getString(3));
                f.setPicture(rst.getString(4));
                f.setNb_invit(rst.getInt(5));
                f.setNomevent(rst.getString(7));
                 f.setTarif(rst.getFloat(16));
                oblist.add(f);   
            }
     
                     
        return oblist;
    }

    @Override
    public void SupprimerFestival(int id) {
       String queryU = "delete  from Event where id="+id;
        String queryP = "DELETE FROM Festival WHERE id = "+id;
        
        System.out.println(queryP);
        Statement stm;
        
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(queryP);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
         try {
            stm = cnx.createStatement();
            stm.executeUpdate(queryU);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Failed");
        }
    }

    @Override
    public void ModifierFestival(Festival F) {
     
      String queryU= " UPDATE `festival` SET `type_fest`=?,`artist`=?,`picture`=?,`nb_invit`=? WHERE id ="+F.getId();
            PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(queryU);
             ps.setString(1, F.getType_fest());
            ps.setString(2, F.getArtist());
            ps.setString(3, F.getPicture());
            ps.setInt(4, F.getNb_invit());
            
         
            
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
           
            String queryP = "UPDATE event SET `nomevent`=?,`tarif`=? WHERE id ="+F.getId();
            PreparedStatement psp;
        try {
            psp = cnx.prepareStatement(queryP);
            psp.setString(1,F.getNomevent());
            psp.setFloat(2,F.getTarif());
            psp.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
           
    }
    
}

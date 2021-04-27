/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Connection;
import java.sql.Date;
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
import tevent.entities.Camping;
import tevent.tools.DataSource;
/**
 *
 * @author maale
 */
public class CampingServices {
    private Connection cnx=DataSource.getInstance().getCnx();
    public int id;
  /*  public void AddCamping(Camping c){
     //EventServices es=new EventServices();
      String  req="INSERT INTO camping (localisation) VALUES ('"+c.getLocalisation()+"')";
      
      try{
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("camping ajouté");
            //es.AddEvent(new Event(100,c.getNomevent(),c.getDatedebut(),c.getDatefin(),c.getHeuredebut(),c.getHeurefin(),c.getLieu(),c.getNbmaxparticipant(),c.getType(),c.getDescription(),c.getTarif(),c.getLat(),c.getLng()));

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
    }*/
    
    
    
    
     /*public ObservableList<Camping> ReadCamping() throws SQLException{
        
    }*/
    public ObservableList<Camping> ReadCamping(){
         ObservableList<Camping> os = FXCollections.observableArrayList();
        try {
            String query = "Select localisation,nomevent,camping.id from camping INNER JOIN event WHERE camping.id=event.id";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // if(!"archived".equals(rs.getString("status"))) {
                Camping t = new Camping();
                
                t.setNomevent(rs.getString("nomevent"));
                t.setLocalisation(rs.getString("localisation"));
                t.setId(rs.getInt("id"));
               
                os.addAll(t);
                // }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
     
     public void AjouterCamping(Camping F) {
         
         
           int insertedID =0 ;
                   String queryUe = "INSERT INTO `event`(`nomevent`,`datedebut`,`datefin`,`heuredebut`,`heurefin`,`lieu`,`nbmaxparticipant`,`type`,`description`,`tarif`,`lat`,`lng`,`discr`)"
                           + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
                   try {
                       PreparedStatement pse = cnx.prepareStatement(queryUe,Statement.RETURN_GENERATED_KEYS);
                       pse.setString(1,F.getNomevent());
                       pse.setDate(2, (Date) F.getDatedebut());
                       pse.setDate(3, (Date) F.getDatefin());
                       pse.setString(4,F.getHeuredebut());
                       pse.setString(5,F.getHeurefin());
                       pse.setString(6,F.getLieu());
                       pse.setInt(7,F.getNbmaxparticipant());
                       pse.setString(8,F.getType());
                       pse.setString(9,F.getDescription());
                       pse.setFloat(10, F.getTarif());
                       pse.setFloat(11, F.getLat());
                       pse.setFloat(12, F.getLng());
                       pse.setString(13,"camping");


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
                       Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
                   }



                String queryU = "INSERT INTO `camping`(id,`localisation`) VALUES(?,?)";
                       PreparedStatement ps;
                   try {
                      ps = cnx.prepareStatement(queryU);
                      ps.setInt(1,insertedID );//ID EVENT
                      ps.setString(2,F.getLocalisation());

                      ps.executeUpdate();

                   } catch (SQLException ex) {
                       Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
                   }

        /*     
            String queryU = "INSERT INTO `camping`(id,`localisation`) VALUES(?,?)";
            PreparedStatement ps;
        try {
           ps = cnx.prepareStatement(queryU);
           ps.setInt(1,F.getId() );//ID EVENT
           ps.setString(2,F.getLocalisation());
           
           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
         */
         
         /*int insertedID =0 ;
        String queryUe = "INSERT INTO `event`(`nomevent`,`datedebut`,`datefin`,`heuredebut`,`heurefin`,`lieu`,`nbmaxparticipant`,`type`,`description`,`tarif`,`lat`,`lng`,`discr`)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
        try {
            PreparedStatement pse = cnx.prepareStatement(queryUe,Statement.RETURN_GENERATED_KEYS);
            pse.setString(1,F.getNomevent());
            pse.setDate(2, (Date) F.getDatedebut());
            pse.setDate(3, (Date) F.getDatefin());
            pse.setString(4,F.getHeuredebut());
            pse.setString(5,F.getHeurefin());
            pse.setString(6,F.getLieu());
            pse.setInt(7,F.getNbmaxparticipant());
            pse.setString(8,"camping");
            pse.setString(9,F.getDescription());
            pse.setFloat(10, F.getTarif());
            pse.setFloat(11, F.getLat());
            pse.setFloat(12, F.getLng());
            pse.setString(13,"camping");

            
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
            Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
     String queryU = "INSERT INTO `camping`(id,`localisation`) VALUES(?,?)";
            PreparedStatement ps;
        try {
           ps = cnx.prepareStatement(queryU);
           ps.setInt(1,insertedID);//ID EVENT
           ps.setString(2,F.getLocalisation());
         
           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            
          }
     
     
         public void SupprimerCamping(int id) {
       String queryU = "delete  from event where id="+id;
        String queryP = "DELETE FROM camping WHERE id = "+id;
        
        System.out.println(queryP);
        Statement stm,stm1;
        
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(queryP);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
         try {
            stm1 = cnx.createStatement();
            stm1.executeUpdate(queryU);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Failed");
        }
    }
         
             public void ModifierCamping(Camping F) {
     
      String queryU= " UPDATE `camping` SET `localisation`=?  WHERE id ="+F.getId();
            PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(queryU);
             ps.setString(1, F.getLocalisation());
            
            
         
            
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
           
            String queryP = "UPDATE event SET `nomevent`=?,`datedebut`=?,`datefin`=?,`heuredebut`=?,`heurefin`=?,`lieu`=?,`nbmaxparticipant`=?,`type`=?,`description`=?,`tarif`=?,`lat`=?,`lng`=? WHERE id ="+F.getId();
            PreparedStatement psp;
        try {
            psp = cnx.prepareStatement(queryP);
            psp.setString(1,F.getNomevent());
            psp.setDate(2, (Date) F.getDatedebut());
            psp.setDate(3, (Date) F.getDatefin());
            psp.setString(4,F.getHeuredebut());
            psp.setString(5,F.getHeurefin());
            psp.setString(6,F.getLieu());
            psp.setInt(7,F.getNbmaxparticipant());
            psp.setString(8,F.getType());
            psp.setString(9,F.getDescription());
            psp.setFloat(10, F.getTarif());
            psp.setFloat(11, F.getLat());
            psp.setFloat(12, F.getLng());
            psp.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
           
    }
    public ObservableList<Camping> ListAttestation() {
       ObservableList<Camping> os = FXCollections.observableArrayList();
        try {
            String query = "Select localisation,nomevent from camping INNER JOIN event WHERE camping.id=event.id";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // if(!"archived".equals(rs.getString("status"))) {
                Camping t = new Camping();
                
                
                t.setLocalisation(rs.getString("localisation"));
                t.setNomevent(rs.getString("nomevent"));
               // t.setId(rs.getInt("id"));
               
                os.addAll(t);
                // }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
}

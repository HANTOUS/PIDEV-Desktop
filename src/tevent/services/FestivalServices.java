/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import tevent.entities.Festival;
import tevent.interfaces.IserviceFestival;
import tevent.tools.Connexion;

/**
 *
 * @author skand
 */
public class FestivalServices implements IserviceFestival{
    Connection cnx;
    public int id;

    public FestivalServices(Connection cnx) {
        this.cnx = Connexion.getInstance().getConnexion();
    }

    public FestivalServices() {
       this.cnx = Connexion.getInstance().getConnexion();
    }

    @Override
    public void AjouterFestival(Festival F) {
         
      
        
        
     String queryU = "INSERT INTO `festival`(id,`type_fest`, `artist`, `picture`, `nb_invit`) VALUES(?,?,?,?,?)";
            PreparedStatement ps;
        try {
           ps = cnx.prepareStatement(queryU);
           ps.setInt(1,F.getId() );//ID EVENT
           ps.setString(2,F.getType_fest());
           ps.setString(3,F.getArtist());
           ps.setString(4, F.getPicture());
           ps.setInt(5, F.getNb_invit());
           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
            
               }
    
    
    public String image ;
    public String findimage(int id) {
        try {
            List <Festival> Festival = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select s.image from festival INNER JOIN sponsor s WHERE "+id+"=s.festival_id");
            
            while (rst.next())
            {
                image=rst.getString(1);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return image ;
    }
    
    public int id1 ;
    public int sponsor(int id) {
        try {
            List <Festival> Festival = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select s.id from festival INNER JOIN sponsor s WHERE festival.id=s.festival_id");
            
            while (rst.next())
            {
                id1=rst.getInt(1);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
          return id1 ;
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
                f.setDatedebut(rst.getDate(8));
                f.setDatefin(rst.getDate(9));
                f.setDescription(rst.getString(15));
                f.setNbmaxparticipant(rst.getInt(13));
               
                System.out.println(f);
                oblist.add(f);   
            }
     
                     
        return oblist;
    }
    
    public ArrayList<String> readFestEv() throws SQLException {
     
            ArrayList <String> oblist = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select * from festival INNER JOIN event WHERE festival.id=event.id");
            
            
             while (rst.next())
            {
                
                
                
                
                oblist.add(rst.getString(7));   
            }
     
                     
        return oblist;
    }
    public int getID(String nom) throws SQLException {
     
            Statement stm;
            
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select id from Event where nomevent = '"+nom+"'");
            
            
             while (rst.next())
            {
                
                
                
                
                id = rst.getInt(1);   
            }
     
                     
        return id;
    }
    
    
    
    public Festival find(int id) throws SQLException{
     List <Festival> Festival = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select * from festival INNER JOIN event WHERE festival.id="+id + " AND event.id="+id);
            
             Festival f= new Festival();
            
             while (rst.next())
            {
                
                
                f.setId(rst.getInt(1));
                f.setType_fest(rst.getString(2));
                f.setArtist(rst.getString(3));
                f.setPicture(rst.getString(4));
                f.setNb_invit(rst.getInt(5));
                f.setNomevent(rst.getString(7));
                f.setHeuredebut(rst.getString(10));
                f.setHeurefin(rst.getString(11));
                f.setNbmaxparticipant(rst.getInt(13));
                f.setDescription(rst.getString(15));
                f.setTarif(rst.getFloat(16));
                f.setDatedebut(rst.getDate(8));
                f.setDatefin(rst.getDate(9));
              
                  
            }
    
    return f ;
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
            
            
           
           
            String queryP = "UPDATE event SET `nomevent`=?,`datedebut`=?,`datefin`=?,`heuredebut`=?,`heurefin`=?," 
    +"`nbmaxparticipant`=?,`description`=?,`tarif`=? WHERE id ="+F.getId();
          
            PreparedStatement psp;
        try {
            psp = cnx.prepareStatement(queryP);
            psp.setString(1,F.getNomevent());
            
            psp.setDate(2,Date.valueOf(F.getDatedebut().toString()));
            psp.setDate(3,Date.valueOf(F.getDatefin().toString()));
            
            psp.setString(4,F.getHeuredebut());
             psp.setString(5,F.getHeurefin());
             
              psp.setInt(6,F.getNbmaxparticipant());
              psp.setString(7,F.getDescription());
            
            
            
            psp.setFloat(8,F.getTarif());
            psp.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
           
    }
    
}

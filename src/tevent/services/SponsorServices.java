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
import tevent.entities.Sponsor;
import tevent.entities.Sponsor;
import tevent.interfaces.IserviceFestival;
import tevent.interfaces.IserviceSponsor;
import tevent.tools.DataSource;

/**
 *
 * @author skand
 */
public class SponsorServices implements IserviceSponsor{
    Connection cnx;
    public int id;

    public SponsorServices(Connection cnx) {
        this.cnx = DataSource.getInstance().getCnx();
    }

    public SponsorServices() {
       this.cnx = DataSource.getInstance().getCnx();
    }


    @Override
    public void AjouterSponsor(Sponsor S) {
           String queryU = "INSERT INTO `sponsor`(`nom_sponsor`, `domaine_activite`, `image`, `type_subvention`,`festival_id`) VALUES(?,?,?,?,?)";
            PreparedStatement ps;
        try {
           ps = cnx.prepareStatement(queryU);
           ps.setString(1,S.getNom_sponsor());
           ps.setString(2, S.getDomaine_acivite());
           ps.setString(3, S.getImage());
           ps.setString(4,S.getType_subvention());
           ps.setInt(5,S.getFestival_id());
            
          ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SponsorServices.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
    }

    @Override
    public ObservableList<Sponsor> AfficherSponsor() throws SQLException {
        List <Sponsor> Sponsor = new ArrayList <> ();
            Statement stm;
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery("Select * from Sponsor ");
            
            ObservableList <Sponsor> oblist =  FXCollections.observableArrayList();
            
             while (rst.next())
            {
                
                Sponsor s = new Sponsor();
                s.setId(rst.getInt(1));
                s.setNom_sponsor(rst.getString(3));
                s.setDomaine_acivite(rst.getString(4));
                s.setImage(rst.getString(5));
                s.setType_subvention(rst.getString(6));
                
                oblist.add(s);   
            }          
             System.out.println(oblist);
        return oblist;
    }

    
    @Override
    public void SupprimerSponsor(int id) {
String queryU = "delete  from Sponsor where id="+id;
        
       
        Statement stm; 
         try {
            stm = cnx.createStatement();
            stm.executeUpdate(queryU);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Failed");
        }
    }


    @Override
    public void ModifierSponsor(Sponsor S) {
String queryU= " UPDATE sponsor SET `nom_sponsor`=?,`domaine_activite`=?,`image`=?,`type_subvention`=?  WHERE id ="+S.getId();
            PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(queryU);
          
           ps.setString(1,S.getNom_sponsor());
           ps.setString(2, S.getDomaine_acivite());
           ps.setString(3, S.getImage());
           ps.setString(4,S.getType_subvention());
         
            
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FestivalServices.class.getName()).log(Level.SEVERE, null, ex);
        }    
           
    }
    
}

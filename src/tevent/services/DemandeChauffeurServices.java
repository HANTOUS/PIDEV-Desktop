/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import tevent.entities.DemandeChauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeChauffeurServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<DemandeChauffeur> readDemandeChauffeur() {
        // List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_chauffeur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<DemandeChauffeur> getDemandeByUser(int id) {
        // List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_chauffeur where utilisateur_id='"+id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void addDemandeChauffeur(DemandeChauffeur c) {
        String req = "INSERT INTO demande_chauffeur (utilisateur_id, num_permis, date_permis, date_expiration, etat) VALUES ('" + c.getUtilisateur_id() + "','" + c.getNum_permis() + "','" + c.getDate_permis() + "','" + c.getDate_expiration() + "','" + c.getEtat() + "' ) ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteDemandeChauffeur(int id) {
        String req = "DELETE FROM demande_chauffeur WHERE  id=" + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateDemandeChauffeur(DemandeChauffeur c, int id) {
       /* String req = "UPDATE  demande_chauffeur SET utilisateur_id='" + c.getUtilisateur_id() + "',num_permis='" + c.getNum_permis() + "',date_permis='" + c.getDate_permis() + "' date_expiration='" + c.getDate_expiration() + "' WHERE id='" + id + "' ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    
    String req = "update demande_chauffeur set utilisateur_id=?, num_permis= ? , date_permis= ?, date_expiration= ?, etat=? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, c.getUtilisateur_id());
            ps.setInt(2, c.getNum_permis());
            ps.setString(5, c.getEtat());
            ps.setDate(3, java.sql.Date.valueOf(c.getDate_permis()));
            ps.setDate(4, java.sql.Date.valueOf(c.getDate_expiration()));
            ps.setInt(6, id);

            ps.executeUpdate();
            System.out.println("Demande Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    
    }

    public void AccepterDemande(int id) {
        List<DemandeChauffeur> list = new ArrayList<>();
        String req = "update demande_chauffeur set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "accepter");
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void RefuserDemande(int id) {
        String req = "update demande_chauffeur set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "refuser");
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<DemandeChauffeur> advancedSearchDemandeChauffeur(int num_permis, LocalDate date_expiration) {
        //List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        try {
            String req = "select * from demande_chauffeur where num_permis= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, num_permis);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select * from demande_chauffeur where date_expiration < ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, java.sql.Date.valueOf(date_expiration) );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

}

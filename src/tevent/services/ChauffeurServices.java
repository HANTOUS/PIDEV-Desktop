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
import java.util.ArrayList;
import java.util.List;
import tevent.entities.Chauffeur;
import tevent.tools.DataSource;

/**
 *
 * @author hanto
 */
public class ChauffeurServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterChauffeur(Chauffeur c) {
        String req = "insert into chauffeur(num_permis,date_debut,date_permis,date_expiration,id_user) VALUES (?,?,?,?,?)";
        String req1 = "update utilisateur set roles=? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps.setInt(1, c.getNumPermis());
            ps.setDate(2, (Date) c.getDateDebut());
            ps.setDate(3, (Date) c.getDatePermis());
            ps.setDate(4, (Date) c.getDateExpiration());
            ps.setInt(5, c.getIdUser());
            
            ps1.setString(1, "[\"ROLE_CHAUFFEUR\"]");
            ps1.setInt(2, c.getIdUser());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifierChauffeur(Chauffeur c) {
        String req = "update chauffeur set num_permis=?, date_debut= ? , date_permis= ?, date_expiration= ? where id_user = ?";
        String req1 = "update utilisateur set nom=?, prenom= ? , email= ?, cin= ?, date_naissance= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps1 = cnx.prepareStatement(req1);

            ps.setInt(1, c.getNumPermis());
            ps.setDate(2, (Date) c.getDateDebut());
            ps.setDate(3, (Date) c.getDatePermis());
            ps.setDate(4, (Date) c.getDateExpiration());
            ps.setInt(5, c.getIdUser());
            ps1.setString(1, c.getNom());
            ps1.setString(2, c.getPrenom());
            ps1.setString(3, c.getEmail());
            ps1.setString(4, c.getCin());
            ps1.setDate(5, (Date) c.getDateNaissance());
            ps1.setInt(6, c.getId());

            ps.executeUpdate();
            ps1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimerChauffeur(int id) {
        String req = "delete from chauffeur where id_user =?";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Chauffeur afficherChauffeur(int id) throws SQLException {
        String req = "select * from chauffeur where id_user =?";
        String req1 = "select * from utilisateur where id =?";

        PreparedStatement ps = cnx.prepareStatement(req);
        PreparedStatement ps1 = cnx.prepareStatement(req1);

        ps.setInt(1, id);
        ps1.setInt(1, id);
        Chauffeur c = new Chauffeur();
        ResultSet rs = ps.executeQuery();
        ResultSet rs1 = ps1.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            c.setId(rs.getInt("id"));
            c.setNumPermis(rs.getInt("num_permis"));
            c.setDateDebut((Date) rs.getDate("date_debut"));
            c.setDatePermis((Date) rs.getDate("date_permis"));
            c.setDateExpiration((Date) rs.getDate("date_expiration"));
            c.setIdUser(rs.getInt("id_c"));

        }

        while (rs1.next()) {
            check = true;
            c.setId(rs1.getInt("id"));
            c.setNom(rs1.getString("nom"));
            c.setPrenom(rs1.getString("prenom"));
            c.setEmail(rs1.getString("email"));
            c.setPassword(rs1.getString("password"));
            c.setCin(rs1.getString("cin"));
            c.setDateNaissance((Date) rs1.getDate("date_naissance"));
            c.setRoles(rs1.getString("roles"));
            c.setActivation_token(rs1.getString("activation_token"));
            c.setReset_token(rs1.getString("reset_token"));
            c.setImage(rs1.getString("image"));
        }
        if (check == true) {
            return c;
        } else {
            return null;
        }

    }

    public List<Chauffeur> afficherToutChauffeur() throws SQLException {
        String req = "select * from chauffeur";
        String req1 = "select * from utilisateur where id=?";

        PreparedStatement ps = cnx.prepareStatement(req);
        PreparedStatement ps1 = cnx.prepareStatement(req1);

        ResultSet rs = ps.executeQuery();
        List<Chauffeur> listc = new ArrayList();

        while (rs.next()) {
            Chauffeur c = new Chauffeur();

            c.setId(rs.getInt("id"));
            c.setNumPermis(rs.getInt("num_permis"));
            c.setDateDebut((Date) rs.getDate("date_debut"));
            c.setDatePermis((Date) rs.getDate("date_permis"));
            c.setDateExpiration((Date) rs.getDate("date_expiration"));
            c.setIdUser(rs.getInt("id_user"));

            ps1.setInt(1, rs.getInt("id_user"));
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                c.setId(rs1.getInt("id"));
                c.setNom(rs1.getString("nom"));
                c.setPrenom(rs1.getString("prenom"));
                c.setEmail(rs1.getString("email"));
                c.setPassword(rs1.getString("password"));
                c.setCin(rs1.getString("cin"));
                c.setDateNaissance((Date) rs1.getDate("date_naissance"));
                c.setRoles(rs1.getString("roles"));
                c.setActivation_token(rs1.getString("activation_token"));
                c.setReset_token(rs1.getString("reset_token"));
                c.setImage(rs1.getString("image"));
            }

            listc.add(c);

        }

        return listc;
    }
}

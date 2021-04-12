/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tuserlate file, choose Tools | Tuserlates
 * and open the tuserlate in the editor.
 */
package tevent.services;

import tevent.entities.Utilisateur;
import tevent.tools.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanto
 */
public class UtilisateurServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterUtilisateur(Utilisateur user) {
        String req = "insert into utilisateur(nom,prenom,email,password,cin,date_naissance,roles) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getCin());
            ps.setDate(6, (Date) user.getDateNaissance());
            ps.setString(7, user.getRoles());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifierUtilisateur(Utilisateur user) {
        String req = "update utilisateur set nom=?, prenom= ? , email= ?, cin= ?, date_naissance= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getCin());
            ps.setDate(5, (Date) user.getDateNaissance());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimerUtilisateur(int id) {
        String req = "delete from utilisateur where id =?";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public Utilisateur afficherUtilisateur(int id) throws SQLException {
        String req = "select * from utilisateur where id =?";

        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, id);
        Utilisateur user = new Utilisateur();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCin(rs.getString("cin"));
            user.setDateNaissance((Date) rs.getDate("date_naissance"));
            user.setRoles(rs.getString("roles"));
            user.setActivation_token(rs.getString("activation_token"));
            user.setReset_token(rs.getString("reset_token"));
            user.setImage(rs.getString("image"));
        }
        if (check == true) {
            return user;
        } else {
            return null;
        }

    }

    public List<Utilisateur> afficherToutUtilisateur() throws SQLException {
        String req = "select * from utilisateur";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();
        List<Utilisateur> listUser = new ArrayList();

        while (rs.next()) {
            Utilisateur user = new Utilisateur();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCin(rs.getString("cin"));
            user.setDateNaissance((Date) rs.getDate("date_naissance"));
            user.setRoles(rs.getString("roles"));
            user.setActivation_token(rs.getString("activation_token"));
            user.setReset_token(rs.getString("reset_token"));
            user.setImage(rs.getString("image"));
            listUser.add(user);
        }
        return listUser;
    }
    
     public boolean login(String email, String mdp) {

        try {
            PreparedStatement ps = cnx.prepareStatement("select * from utilisateur where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}

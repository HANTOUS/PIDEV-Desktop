/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import tevent.entities.Utilisateur;
import tevent.tools.DataSource;
//import org.mindrot.jbcrypt.BCrypt; 

/**
 *
 * @author hanto
 */
public class SecurityServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public String register(Utilisateur user) {
        String req = "insert into utilisateur(nom,prenom,email,password,cin,date_naissance,roles,activation_token) VALUES (?,?,?,?,?,?,?,?)";
        String token = UUID.randomUUID().toString();

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
           // String generatedSecuredPasswordHash = BCrypt.hashpw( user.getPassword(), BCrypt.gensalt(12));
            ps.setString(4,user.getPassword());
            ps.setString(5, user.getCin());
            ps.setDate(6, (Date) user.getDateNaissance());
            ps.setString(7, user.getRoles());
            ps.setString(8, token);
            ps.executeUpdate();
            return "Utilisateur ajouté avec succés";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public boolean login(String email, String mdp) {
        UtilisateurServices us = new UtilisateurServices();
        Utilisateur user = us.getUserByMail(email);
        if (user == null) {
            System.out.println("Cet Email n'existe pas!");
        } else {
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
        }
        return false;
    }

    public void resetPassword(int id, String pass) {
        try {
            String req = "UPDATE utilisateur SET password = '"+ pass +"', reset_token= null WHERE id = "+ id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void forgetPassword(int id, String token) {
        try {
            String req = "UPDATE utilisateur SET reset_token = '"+ token +"' WHERE id = "+ id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void activation(String token) {
        // PreparedStatement ps;
        try {
            String req = "UPDATE utilisateur SET activation_token = null WHERE activation_token = '" + token + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            /*ps = cnx.prepareStatement(req);
            ps.setString(1, token);
            ps.executeUpdate(req);*/
            // System.out.println(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void desactivation(int id) {
       // PreparedStatement ps;
        try {
            String req = "UPDATE utilisateur SET activation_token ='"+ UUID.randomUUID().toString() +"' where id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            /*ps = cnx.prepareStatement(req);
            String token = UUID.randomUUID().toString();

            ps.setString(1, token);*/
            //ps.setInt(2, id);
           // ps.executeUpdate(req);
           // System.out.println(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    


    public void changerPassword(int id, String pass ) {
        try {
            String req = "UPDATE utilisateur SET password = '"+ pass +"' WHERE id = "+ id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

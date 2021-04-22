/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tuserlate file, choose Tools | Tuserlates
 * and open the tuserlate in the editor.
 */
package tevent.services;

import tevent.entities.Utilisateur;
import tevent.tools.DataSource;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import tevent.interfaces.IserviceUtilisateur;

/**
 *
 * @author hanto
 */
public class UtilisateurServices implements IserviceUtilisateur {

    private Connection cnx = DataSource.getInstance().getCnx();

    public String ajouterUtilisateur(Utilisateur user) {
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
            return "Utilisateur ajouté avec succés";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String modifierUtilisateur(Utilisateur user) {
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
            return "Utilisateur modifié avec succés";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void supprimerUtilisateur(int id) {
        ChauffeurServices cs = new ChauffeurServices();
        String req1 = "select roles from utilisateur where id =?";
        String req = "delete from utilisateur where id =?";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps1.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("roles").equals("[\"ROLE_CHAUFFEUR\"]")) {
                    cs.supprimerChauffeur(id);
                }
            }

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

    public int calculAge(Date dateNaissance) throws SQLException {

        LocalDate l = dateNaissance.toLocalDate();
        LocalDate now = LocalDate.now(); //gets localDate
        Period diff = Period.between(l, now);
        return diff.getYears();
    }

    public String getRolebyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("roles");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Utilisateur n'existe pas";

    }

    public String getRolebyEmail(String email) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where email=?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("roles");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Utilisateur n'existe pas";
        
    }

    

    public String getNombyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return "Utilisateur n'existe pas";

    }

    public String getPrenombyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("prenom");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return "Utilisateur n'existe pas";

    }

    public String getDatebyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getDate("date_naissance").toString();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return "";
    }

    public String getPassbyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return "Utilisateur n'existe pas";
    }

    public String getMailbyId(int id) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return "Utilisateur n'existe pas";
    }

    public Utilisateur getUserByMail(String email) {
        try {
            PreparedStatement st = cnx.prepareStatement("select * from utilisateur where email=?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            Utilisateur user = new Utilisateur();
            if (rs.next()) {
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
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

}

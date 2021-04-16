/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.tests;

import tevent.entities.Utilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import tevent.entities.Chauffeur;
import tevent.services.ChauffeurServices;
import tevent.services.SecurityServices;
import tevent.services.UtilisateurServices;

/**
 *
 * @author hanto
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Utilisateur user1 = new Utilisateur(10, "Bellalouna", "Tarek", "tarek.bellalounkka@esprit.tn", "123456", "12345689", null, Date.valueOf("1998-12-04"), null, null);
        UtilisateurServices us = new UtilisateurServices();
        /*
        us.ajouterUtilisateur(user1);
        System.out.println("-----------------------------------------------------------------------------------------");

        //us.modifierUtilisateur(user);
        //us.supprimerUtilisateur(9);
        List<Utilisateur> users = us.afficherToutUtilisateur();
        for (Utilisateur user : users) {
            System.out.println(user);
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        Utilisateur u = us.afficherUtilisateur(3);
        if (u == null) {
            System.out.println("Cette utilisateur n'existe pas!");
        } else {
            System.out.println(u);
        }*/
 
        Chauffeur c = new Chauffeur(12385679,Date.valueOf("2021-04-08"),Date.valueOf("2026-11-16"),Date.valueOf("2026-11-15"),8);
        
        ChauffeurServices cs = new ChauffeurServices();
        
        cs.ajouterChauffeur(c);
        //cs.modifierChauffeur(c);

        System.out.println(us.calculAge( Date.valueOf("2022-01-04")));
        SecurityServices ss = new SecurityServices();
        System.out.println(ss.login("tarek.bellalounka@esprit.tn", "123456"));
        ss.resetPassword(13, "987654321");
        // ss.activation("dee45a71-7344-4ab4-8802-22ae5d8f8487");
        // ss.desactivation(14);
       
       
        List<Chauffeur> chauffeurs = cs.permisExpirer();
        for (Chauffeur chauf : chauffeurs) {
            System.out.println(chauf);
        }
    }

}

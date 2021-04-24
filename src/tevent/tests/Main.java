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
import tevent.entities.Feedback;
import tevent.entities.Reclamation;
import tevent.services.ChauffeurServices;
import tevent.services.FeedbackServices;
import tevent.services.ReclamationServices;
import tevent.services.UtilisateurServices;

/**
 *
 * @author hanto
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        
        ReclamationServices rs = new ReclamationServices();
        
        //rs.addreclamation(new Reclamation(2,"randonné ","Abcense de chef randonné"));
        //rs.delreclamation(new Reclamation(13,2, "", ""));
        //rs.updatereclamation(new Reclamation(8,2, "festivalll", "festival non completttt"));
        //System.out.println(rs.readReclamation());
        //System.out.println(rs.readReclamationByID(2));
        //System.out.println(rs.readReclamationBySubject("festival"));
        //System.out.println(rs.reclamationTraité());
        //System.out.println(rs.reclamationEncours());
        //rs.traiteReclamation(5);

        

        
        
              FeedbackServices fs = new FeedbackServices();
        //fs.addFeedback(new Feedback(5,5,4,"Bonne organisation de festival de sousse "));
              //fs.delFeddback(new Feedback(13, 0,0, ""));
           //System.out.println(fs.bestFeedback());
             //System.out.println(fs.readFeedback());
             //System.out.println(fs.readFeedbackByID(10));
             //System.out.println(fs.readFeedbackByNote(5));
             //System.out.println(fs.moyNoteEvent(2));     
             







       /* Utilisateur user1 = new Utilisateur(8, "Bellalouna", "Tarek", "tarek.bellalounkka@esprit.tn", "123456", "12345679", null, Date.valueOf("1998-12-04"), null, null);
        UtilisateurServices us = new UtilisateurServices();

        //us.ajouterUtilisateur(user);
        //us.modifierUtilisateur(user);
        //us.supprimerUtilisateur(9);
        List<Utilisateur> users = us.afficherToutUtilisateur();
        for (Utilisateur user : users) {
            System.out.println(user);
        }

        Utilisateur u = us.afficherUtilisateur(3);
        if (u==null)
            System.out.println("Cette utilisateur n'existe pas!");
        else
            System.out.println(u);*/
       
        //Chauffeur c = new Chauffeur(12345679,Date.valueOf("2021-04-08"),Date.valueOf("2017-11-16"),Date.valueOf("2026-11-15"),8);
        
        //ChauffeurServices cc = new ChauffeurServices();
        
        //cc.ajouterChauffeur(c);
        //cc.modifierChauffeur(c);
    }

}

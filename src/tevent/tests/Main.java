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
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.entities.DemandeMateriel;
import tevent.services.ChauffeurServices;
import tevent.services.UtilisateurServices;
import tevent.services.DemandeMaterielServices;
import tevent.services.DemandeChauffeurServices;
import tevent.services.DemandeBusServices;

/**
 *
 * @author hanto
 */
public class Main {

    public static void main(String[] args) throws SQLException {
    /*    Utilisateur user1 = new Utilisateur(8, "Bellalouna", "Tarek", "tarek.bellalounkka@esprit.tn", "123456", "12345679", null, Date.valueOf("1998-12-04"), null, null);
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
            System.out.println(u);
      */ 
       DemandeBusServices dbs = new DemandeBusServices();
       //DemandeChauffeurServices dcs = new DemandeChauffeurServices();
       //DemandeMaterielServices dms = new DemandeMaterielServices();
       
               //System.out.println(dms.readDemandeMateriel());
       
       // System.out.println(dbs.readDemandeBus());
        //System.out.println(dcs.deleteDemandeChauffeur(1));
  //      DemandeBus d= new DemandeBus(1,15,"sfaxxxxx","sousse","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16"));
//dbs.addDemandeBus(new DemandeBus(1,25,"sfax","aaaaaa","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16")));
//dbs.deleteDemandeBus(14);
//dbs.updateDemandeBus(new DemandeBus(15,1,3,"sssssfax","aaaaaa","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16")));
        //System.out.println(dbs.Pagination());
       // System.out.println(dbs.advancedSearch(0, "Tunis", ""));
       dbs.AccepterDemande(15);
       dbs.RefuserDemande(13);
        //Chauffeur c = new Chauffeur(12345679,Date.valueOf("2021-04-08"),Date.valueOf("2017-11-16"),Date.valueOf("2026-11-15"),8);
        
        //ChauffeurServices cc = new ChauffeurServices();
        
        //cc.ajouterChauffeur(c);
      //  cc.modifierChauffeur(c);
    }

}

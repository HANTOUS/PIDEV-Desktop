/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.tests;

import tevent.entities.Utilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
   String message="Vous êtes prié de bien vouloir vous présenter à l'agence pour l'évenement du  qui debute le et qui prendra fin le  pour la signature de la location du bus";

       DemandeBusServices dbs = new DemandeBusServices();
       DemandeChauffeurServices dcs = new DemandeChauffeurServices();
       DemandeMaterielServices dms = new DemandeMaterielServices();
        try {
                        System.out.println("amr");
            dbs.SendMail(message,"Bus","tarek.bellalouna@gmail.com");
            System.out.println("tarek");
        } catch (MessagingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

       //dcs.updateDemandeChauffeur(new DemandeChauffeur(1,11128163,LocalDate.of(2020, 1, 8),LocalDate.of(2021, 1, 8),"encours"), 2);
       System.out.println(dcs.advancedSearchDemandeChauffeur(0,LocalDate.of(2030,11,19)));
       //System.out.println(dms.MaterielName());

       /* List<DemandeBus> db = dbs.readDemandeBus();
        for (DemandeBus d : db) {
            System.out.println(d);
        }*/
               //System.out.println(dms.readDemandeMateriel());
               //System.out.println(dbs.readDemandeBus());
               //dcs.deleteDemandeChauffeur(1);
  //      DemandeBus d= new DemandeBus(1,15,"sfaxxxxx","sousse","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16"));
//dbs.addDemandeBus(new DemandeBus(1,50,"Monastir","Beja","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16")));
   //dcs.addDemandeChauffeur(new DemandeChauffeur(1,11128163,Date.valueOf("2017-11-16"),Date.valueOf("2017-11-16"),"encours"));
        //System.out.println(dms.addDemandeMateriel(new DemandeMateriel(1,2,"2","encours",LocalDate.of(2030,11,19),LocalDate.of(2030,11,22)),1));
   // System.out.println(dms.PrixTotale(3, 4));
    //dbs.deleteDemandeBus(15);
   // dbs.updateDemandeBus(new DemandeBus(16,1,3,"Tozeur","Tunis","10:00:00","19:00:00","encours",Date.valueOf("2017-11-16")));
        //System.out.println(dbs.Pagination());
     //  System.out.println(dbs.advancedSearchDemandeBus(100, "Sousse", ""));
     //dms.AccepterDemande(20,1);
       //dbs.RefuserDemande(11);
        //Chauffeur c = new Chauffeur(12345679,Date.valueOf("2021-04-08"),Date.valueOf("2017-11-16"),Date.valueOf("2026-11-15"),8);
        
        //ChauffeurServices cc = new ChauffeurServices();
        
        //cc.ajouterChauffeur(c);
      //  cc.modifierChauffeur(c);
    }
}

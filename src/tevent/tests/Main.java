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
import tevent.entities.Bus;
import tevent.entities.ChauffBusEvent;
import tevent.entities.Chauffeur;
import tevent.entities.Materiel;
import tevent.services.BusService;
import tevent.services.ChauffBusEventService;
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

        else
            System.out.println(u);*/
       
        Chauffeur c = new Chauffeur(12345679,Date.valueOf("2021-04-08"),Date.valueOf("2017-11-16"),Date.valueOf("2026-11-15"),8);
        
        
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
        
        
        ChauffBusEventService cbe= new ChauffBusEventService();
        
        BusService bs = new BusService();
        
        ChauffBusEvent cb = new ChauffBusEvent(2, 2, 1, 12, 14, "marsa", "tunis");
        System.out.println(cbe.listChauffBusEvent());
        cbe.addChauffBusEvent(cb);
        
        cb.setHeureDepart(13);
        cb.setHeureArrive(20);
        
        cbe.updateChauffBusEvent(cb);
        
        cbe.deleteChauffBusEvent(cb);
        
        /*Bus b1 = new Bus("Renauts", "C3", 1, Boolean.TRUE);
        
        Bus b2 = new Bus("Renauts X1", "C3", 0, Boolean.FALSE);
        
        Bus b4 = new Bus("Renauts X1", "C3", 20, Boolean.FALSE);
        
        Bus b3 = new Bus("Renauts X1", "C3", 27, Boolean.FALSE);
        
        b3 = bs.addBus(b3);
        
        b4 = bs.addBus(b4);
        
        b2 = bs.addBus(b2);
        b1 = bs.addBus(b1);
        
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b2);
        System.out.println(b1);
        
        b3.setFabriquant("Volvo");
        
        bs.updateBus(b3);
        
        bs.deleteBus(b4);*/
        
        /*
        Bus bSearch = new Bus("b","",0,null);
        
        System.out.println(bs.searchWithMultiParams(bSearch));
        */
        
        /*
        MaterielService ms = new MaterielService();
        
        System.out.println(ms.searchLabel(new Materiel("", 0, 0, Float.NaN, Boolean.TRUE)));
        
        
        System.out.println(ms.filterMat(new Materiel("", 0, 0, Float.parseFloat("-1"), Boolean.FALSE)));
        */
        
    }

}

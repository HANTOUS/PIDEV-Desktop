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
    }

}

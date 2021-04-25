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
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tevent.entities.Bus;
import tevent.entities.ChauffBusEvent;
import tevent.entities.Chauffeur;
import tevent.gui.FrontFestivalController;
import tevent.services.ChauffeurServices;

import tevent.services.SecurityServices;

import tevent.services.UtilisateurServices;

/**
 *
 * @author hanto
 */
public class Main extends Application {
    
     FrontFestivalController F = new FrontFestivalController();
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/tevent/gui/CrudSponsor.fxml"));
       Scene scene = new Scene(root);
       //Scene scene = new Scene(F.page());
        
        stage.setScene(scene);
        stage.show();
    }

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
     /*  
        Chauffeur c = new Chauffeur(12345679,Date.valueOf("2021-04-08"),Date.valueOf("2017-11-16"),Date.valueOf("2026-11-15"),8);
        
        ChauffeurServices cc = new ChauffeurServices();
        
        //cc.ajouterChauffeur(c);
cc.modifierChauffeur(c);

*/
launch(args);     
      
}


}

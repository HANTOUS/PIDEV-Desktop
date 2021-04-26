/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import tevent.entities.Utilisateur;
import tevent.services.UtilisateurServices;
import java.time.LocalDate;
import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

=======
package tevent.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.entities.DemandeMateriel;
import tevent.services.DemandeBusServices;
import tevent.services.DemandeChauffeurServices;
import tevent.services.DemandeMaterielServices;
import tevent.tools.DataSource;
>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96

/**
 * FXML Controller class
 *
<<<<<<< HEAD
 * @author hanto
 */
public class MesDemandesController implements Initializable {
   
    @FXML
    private Label lbUser;
@FXML
    private ImageView image;

    private Utilisateur user;
=======
 * @author DELL
 */
public class MesDemandesController implements Initializable {

    private Connection cnx = DataSource.getInstance().getCnx();
    DemandeBusServices dbs = new DemandeBusServices();
    DemandeChauffeurServices dcs = new DemandeChauffeurServices();
    DemandeMaterielServices dms = new DemandeMaterielServices();

>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
<<<<<<< HEAD
    }    
    @FXML
    private void profile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        loader.load();
        ProfileController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void password(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("ChangerPassword.fxml"));
        loader.load();
        ChangerPasswordController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        
    }

    @FXML
    private void demandes(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("MesDemandes.fxml"));
         loader.load();
        MesDemandesController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }   

    @FXML
    private void reclamations(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("MesReclamations.fxml"));
         loader.load();
        MesReclamationsController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
            lbUser.getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
    }

    public void setUser(Utilisateur u) {
        user = u;
        image.setImage(new Image(new File(user.getImage()).toURI().toString()));


    }


=======
        ObservableList<DemandeBus> list = dbs.getDemandeByUser(1);
        ObservableList<DemandeChauffeur> list1 = dcs.getDemandeByUser(1);
        ObservableList<DemandeMateriel> list2 = dms.getDemandeByUser(1);
        List<String> EtatBus = new ArrayList<>();
        for (DemandeBus db : list) {
            System.out.println(db.getEtat());

        }
        List<String> EtatChauffeur = new ArrayList<>();
        for (DemandeChauffeur dc: list1) {
            System.out.println(dc.getEtat());

        }
        List<String> EtatMateriel = new ArrayList<>();
        for (DemandeMateriel dm : list2) {
            System.out.println(dm.getEtat());

        }

    }

>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import tevent.entities.Utilisateur;
import tevent.services.UtilisateurServices;
import java.time.LocalDate;
import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

/**
 * FXML Controller class
 *
 * @author hanto
 */
public class MesReclamationsController implements Initializable {
   
    @FXML
    private Label lbUser;
@FXML
    private ImageView image;
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        lbUser.setText(u.getNom()+" "+u.getPrenom());
        image.setImage(new Image(new File(user.getImage()).toURI().toString()));

    } 

}

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
import tevent.entities.Utilisateur;
import tevent.services.UtilisateurServices;
import tevent.services.SecurityServices;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
public class ChangerPasswordController implements Initializable {
   
    @FXML
    private Label lbUser;
    @FXML
    private JFXPasswordField txtActuel;
    @FXML
    private JFXPasswordField txtPwd;
    @FXML
    private JFXPasswordField txtConf;
    @FXML
    private JFXButton btnValider;
     @FXML
    private ImageView image;

    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void changer(ActionEvent event) throws IOException {
         if (txtActuel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre mot de passe actuel !!!");
            alert.showAndWait();
        } 
        else if (txtPwd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre nouveau mot de passe !!!");
            alert.showAndWait();
        } else if (! txtPwd.getText().equals(txtConf.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer le meme nouveau mot de passe !!!");
            alert.showAndWait();
        }else{
            if(user.getPassword().equals(txtActuel.getText())){
                SecurityServices ss = new SecurityServices();
                ss.changerPassword(user.getId(),txtPwd.getText());
                txtActuel.setText("");
                txtPwd.setText("");
                txtConf.setText("");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText(null);
                alert.setContentText("!!!  Votre mot de passe a été changer !!!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("!!!  Votre mot de passe actuel n'est pas valid!!!");
                alert.showAndWait();
            }

        }

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

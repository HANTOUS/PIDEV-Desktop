/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListDemandesController implements Initializable {

    @FXML
    private Button retourbtn;
    @FXML
    private Button btnAdb;
    @FXML
    private Button btnAdc;
    @FXML
    private Button btnAdm;
     @FXML
    private Label lbUser;
    private Utilisateur user;
    public void setUser(Utilisateur u) {
        user = u;
       //  lbUser.setText(u.getNom()+" "+u.getPrenom());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        try { 
            
           FXMLLoader loader = new FXMLLoader();
        btnAdb.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Dashboard.fxml"));
        loader.load();
        DashboardController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

   @FXML
    private void AllerVersListDemandeMaterielAdmin(ActionEvent event) {
         try { 

           FXMLLoader loader = new FXMLLoader();
        btnAdb.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AdminListDemandeMateriel.fxml"));
        loader.load();
        AdminListDemandeMaterielController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeBusAdmin(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader();
        btnAdb.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AdminListDemandeBus.fxml"));
        loader.load();
        AdminListDemandeBusController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeChauffeurAdmin(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader();
        btnAdb.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AdminListDemandeChauffeur.fxml"));
        loader.load();
        AdminListDemandeChauffeurController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
}

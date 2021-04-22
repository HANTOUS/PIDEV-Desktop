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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL 
 */
public class HomeController implements Initializable {

    @FXML
    private Button btndb;
    @FXML
    private Button btndc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AllerVersDemandeBus(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addDemandeBus.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Ajouter Demande Bus");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersDemandeChauffeur(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addDemandeChauffeur.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Ajouter Demande Chauffeur");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersDemandeMateriel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addDemandeMateriel.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Ajouter Demande Materiel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeChauffeur(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listdmdchauffeur.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("List Demande Chauffeur");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeBus(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listdmdbus.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("List Demande Bus");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeMateriel(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listdmdmateriel.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("List Demande Materiel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeMaterielAdmin(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminListDemandeMateriel.fxml")) ;
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("List Demande Materiel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

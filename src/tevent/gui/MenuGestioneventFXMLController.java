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
import tevent.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author maale
 */
public class MenuGestioneventFXMLController implements Initializable {

    @FXML
    private Button Ajoutercamp;
    @FXML
    private Button ajouterrand;
    @FXML
    private Button affichercamp;
    @FXML
    private Button affichrand;
    private Utilisateur user;
 public void setUser(Utilisateur u) {
        user = u;

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutcampGNU(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("CampingAjoutFXML.fxml"));
            Stage window = (Stage) Ajoutercamp.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajoutrandGNU(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("RandonneeAjoutFXML.fxml"));
            Stage window = (Stage) ajouterrand.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affichcampGNU(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageCampingFXML.fxml"));
            Stage window = (Stage) affichercamp.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    

    @FXML
    private void affichrandGNU(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AffichageRandonneeFXML.fxml"));
            Stage window = (Stage) affichrand.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

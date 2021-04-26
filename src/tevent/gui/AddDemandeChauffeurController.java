/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tevent.entities.DemandeChauffeur;
import tevent.entities.Utilisateur;
import tevent.services.DemandeChauffeurServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddDemandeChauffeurController implements Initializable {

    @FXML
    private DatePicker datepermis;
    @FXML
    private DatePicker dateexpiration;
    @FXML
    private TextField numpermis;
    @FXML
    private Button btn;
    @FXML
    private Button Retourbtn;
    private Utilisateur user;

    public void setUser(Utilisateur u) {
        user = u;

    }
    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    @FXML
    private void addDemandeChauff(ActionEvent event) {
        if(isValidate()){ 
        int  num_permis = Integer.parseInt(numpermis.getText());
           LocalDate date_permis = datepermis.getValue();
           LocalDate date_expiration = dateexpiration.getValue();

        DemandeChauffeur DC = new DemandeChauffeur(1,num_permis,date_permis,date_expiration,"encours");
        DemandeChauffeurServices dcb = new DemandeChauffeurServices();
        dcb.addDemandeChauffeur(DC);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Demande Chauffeur ");
        alert.setContentText("Vous recevez un email dans quelques heures ");
        alert.show();
                        //dcs.SMS();

        try {
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("listdmdchauffeur.fxml"));
             ListdmdchauffeurController dc = loader.getController();
//               dc.setUser(user);
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btn.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
            

        }
    }
    
    private void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Attention");
        alert.setContentText(message);
        alert.show();
    }
    //Remplissage des champs obligatoire

    private boolean isValidate() {
        boolean isValid = true;
        String message = "";

        //System.out.println("gui.EventController.ajouter()" + comboType.getValue());
        if (numpermis.getText().isEmpty() ) {
            isValid = false;
            message = "Veuillez entrer un numero de permis valid";
        }else if (numpermis.getText().length() != 8) {
          isValid = false;
          message = "Le numero de permis doit etre exactement 8 chiffres";
        } else if (datepermis.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date permis";
        } else if (dateexpiration.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date d'expiration ";

        }else if (dateexpiration.getValue().isBefore(datepermis.getValue())) {
            isValid = false;
            message = "La date expiration doit être supérieur à la date permis ";
        }

        if (!isValid) {
            showDialog(message);
        }

        return isValid;

    }

    @FXML
    private void retour(ActionEvent event) {
         try {
              FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("Home.fxml"));
             HomeController dc = loader.getController();
//               dc.setUser(user);
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) Retourbtn.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
}

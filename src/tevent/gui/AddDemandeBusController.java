/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tevent.entities.DemandeBus;
import tevent.entities.Utilisateur;
import tevent.services.DemandeBusServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddDemandeBusController implements Initializable {

    @FXML
    private TextField nbparticipant;
    @FXML
    private DatePicker jourlocation;
    @FXML
    private TextField heuredepart;
    @FXML
    private ComboBox villedepart;
    @FXML
    private ComboBox villearrivee;
    @FXML
    private TextField heurearrivee;
    @FXML
    private Button addbtn;
    @FXML
    private Button btnretour;
    
    private Utilisateur user;

    public void setUser(Utilisateur u) {
        user = u;

    }
    int idUser = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> listville = FXCollections.observableArrayList("Ariana","Béja","Ben Arous","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Kef","Mahdia","Manouba","Mednine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");
               villedepart.setItems(listville);
               villearrivee.setItems(listville);
    }    
   
 

    @FXML
    private void addDemande(ActionEvent event) {
       if(isValidate()){
        int nb_participant =Integer.parseInt(nbparticipant.getText());
      String ville_depart = villedepart.getSelectionModel().getSelectedItem().toString();
      String ville_arrivee = villearrivee.getSelectionModel().getSelectedItem().toString();
      String heure_depart= heuredepart.getText();
      String heure_arrivee= heurearrivee.getText();
     
      LocalDate jour_location = jourlocation.getValue();
    Instant instant = Instant.from(jour_location.atStartOfDay(ZoneId.systemDefault()));
    Date date = Date.from(instant);
    System.out.println(jour_location + "\n" + instant + "\n" + date);
    idUser=user.getId();
      DemandeBus DB = new DemandeBus(idUser,nb_participant,ville_depart,ville_arrivee,heure_depart,heure_arrivee,"encours",jour_location);
      DemandeBusServices dbs = new DemandeBusServices();
      dbs.addDemandeBus(DB);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Demande Bus ");
        alert.setContentText("Vous recevez un email dans quelques heures ");
        alert.show();
    
         try {

             FXMLLoader loader = new FXMLLoader();
        addbtn.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("listdmdbus.fxml"));
        loader.load();
       
        ListdmdbusController dc = loader.getController();
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

    @FXML
    private void GoBack(ActionEvent event) {
        try {

             FXMLLoader loader = new FXMLLoader();
        btnretour.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("PasserDemandeFront.fxml"));
        loader.load();
       
         PasserDemandeFrontController dc = loader.getController();
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
        if (nbparticipant.getText().isEmpty()) {
            isValid = false;
            message = "Entrer un nombre de participants";
        } else if (villedepart.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une ville depart";
        } else if (villearrivee.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une ville arrivee ";

        } else if (villearrivee.getValue() == villedepart.getValue()) {
            isValid = false;
            message = "La ville depart doit être differente de la ville d'arrivée ";

        }else if (heuredepart.getText().isEmpty()) {
            isValid = false;
            message = "Heure Depart réquis";
        } else if (heurearrivee.getText().isEmpty()) {
            isValid = false;
            message = "Heure Arrivee requis";
        } else if (jourlocation.getValue()== null) {
            isValid = false;
            message = "Jour de location requis";
        }
        //else if (comboEtat.getValue() == null) {
//            isValid = false;
//            message = "Veuillez selectionnez type etat";
//        }

        if (!isValid) {
            showDialog(message);
        }

        return isValid;

    }
    
    }

    

    


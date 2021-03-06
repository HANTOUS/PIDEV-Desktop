/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tevent.entities.DemandeMateriel;
import tevent.entities.Utilisateur;
import tevent.services.DemandeMaterielServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddDemandeMaterielController implements Initializable {
    private Connection cnx = DataSource.getInstance().getCnx();

    @FXML
    private Button btnadd;
    @FXML
    private ComboBox materiel;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField quantite;
    
    DemandeMaterielServices dms= new DemandeMaterielServices();
    @FXML
    private Text message;
    @FXML
    private TextField prix;
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
        ObservableList<String> Materiel = FXCollections.observableArrayList(dms.MaterielName());
               materiel.setItems(Materiel);
    }    

    @FXML
    private void addDemande(ActionEvent event) {
     int idm=0;
     if(isValidate()){
     int qte = Integer.parseInt(quantite.getText());
           LocalDate date_debut = datedebut.getValue();
           LocalDate date_fin = datefin.getValue();
           String mat = materiel.getSelectionModel().getSelectedItem().toString();
           String req="SELECT id from materiel where label=?";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, mat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idm = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }   
            int idUser=user.getId();

           DemandeMateriel DM = new DemandeMateriel(idUser,idm,"6","encours",date_debut,date_fin);

           if(dms.addDemandeMateriel(DM,qte).equals("DemandeMateriel Ajout??e !")){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(dms.addDemandeMateriel(DM,qte));
        alert.setContentText("Vous recevez un email dans quelques heures ");
        alert.show();
               prix.setText(""+dms.PrixTotale(idm, qte)+"DT");

        
        
        
        //message.setText(dms.addDemandeMateriel(DM,qte));
        try {
             FXMLLoader loader = new FXMLLoader();
        btnadd.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("listdmdmateriel.fxml"));
        loader.load();
                             ListdmdmaterielController dc = loader.getController();
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
       
                  }else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Demande Mat??riel ");
        alert.setContentText(dms.addDemandeMateriel(DM,qte));
        alert.show();
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
        if (quantite.getText().isEmpty()) {
            isValid = false;
            message = "Veuillez entrer une quantit?? ?? reserver";
        } else if (materiel.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez un materiel";
        } else if (datedebut.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date de debut ";

        } else if (datefin.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date de fin ";
        }else if (datefin.getValue().isBefore(datedebut.getValue())) {
            isValid = false;
            message = "La date fin doit etre superieur ?? la date debut ";
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

    @FXML
    private void retour(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader();
        Retourbtn.getScene().getWindow().hide();
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

    @FXML
    private void calculPrix(KeyEvent event) {
        int idm=0;
    
     int qte = Integer.parseInt(quantite.getText());
           String mat = materiel.getSelectionModel().getSelectedItem().toString();
           String req="SELECT id from materiel where label=?";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, mat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idm = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        prix.setText(""+dms.PrixTotale(idm, qte)+"DT");
    }

  
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tevent.entities.DemandeMateriel;
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

        DemandeMateriel DM = new DemandeMateriel(1,idm,"6","encours",date_debut,date_fin);
        message.setText(dms.addDemandeMateriel(DM,qte));
        prix.setText(""+dms.PrixTotale(idm, qte)+"DT");
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
            message = "Veuillez entrer une quantité à reserver";
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
            message = "La date fin doit etre superieur à la date debut ";
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

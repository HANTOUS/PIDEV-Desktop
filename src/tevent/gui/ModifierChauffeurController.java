/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.sql.Date;
import tevent.entities.Chauffeur;
import tevent.services.ChauffeurServices;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author hanto
 */
public class ModifierChauffeurController implements Initializable {
   @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtCin;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXDatePicker dateN;
    @FXML
    private JFXTextField txtPermis;
    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXDatePicker dateE;
    @FXML
    private JFXButton btnAjouter;
    private int idU;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setFields( int id,String nom,String prenom, String cin, String email, Date DateN, Date DateP, Date DateE, int NumP ) {
        idU=id;
        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        dateN.setValue(DateN.toLocalDate());
        dateP.setValue(DateP.toLocalDate());
        dateE.setValue(DateE.toLocalDate());
        txtCin.setText(cin);
        txtPermis.setText(NumP+"");
        txtEmail.setText(email);

    }

    @FXML
    private void update(ActionEvent event) throws IOException {

       if (txtNom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre nom !!!");
            alert.showAndWait();
        } else if (txtPrenom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre prennom !!!");
            alert.showAndWait();
        } else if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre e-mail !!!");
            alert.showAndWait();
        } else if (txtCin.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre numéro telephonique !!!");
            alert.showAndWait();
        } else if (dateN.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre date de naissance !!!");
            alert.showAndWait();
        } else if (txtPermis.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre numéro de permis !!!");
            alert.showAndWait();
        } else if (dateP.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre date de permis !!!");
            alert.showAndWait();
        } else if (dateE.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre date d'expiratio  du permis !!!");
            alert.showAndWait();
        }else {
            Chauffeur u = new Chauffeur();
            u.setId(idU);
            u.setNom(txtNom.getText());
            u.setPrenom(txtPrenom.getText());
            u.setEmail(txtEmail.getText());
            u.setCin(txtCin.getText());
            u.setDateNaissance(Date.valueOf(dateN.getValue()));
            //u.setPhoto(file.toURI().toString());
            ChauffeurServices us = new ChauffeurServices();
            String res = us.modifierChauffeur(u);
            if(res.equals("Chauffeur modifier avec succés")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(res);
            alert.showAndWait();}
            else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(res);
            alert.showAndWait();
            }
/*
            FXMLLoader loader = new FXMLLoader();
            txtNom.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();*/
        }

    }
}

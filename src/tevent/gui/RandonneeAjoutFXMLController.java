/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tevent.entities.Randonnee;
import tevent.services.RandonneeServices;

/**
 * FXML Controller class
 *
 * @author maale
 */
public class RandonneeAjoutFXMLController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfheuredebut;
    @FXML
    private TextField tfheurefin;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfnbr;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tftarif;
    @FXML
    private TextField tflat;
    @FXML
    private TextField tflng;
    @FXML
    private DatePicker dpdebut;
    @FXML
    private DatePicker dpfin;
    @FXML
    private TextField tftype;
    @FXML
    private Button btnvalider;
    @FXML
    private Button menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveRandonnee(ActionEvent event) throws Exception {
            Randonnee F = new Randonnee();
            RandonneeServices  sf = new  RandonneeServices();
            if (tfnom.getText().trim().isEmpty() || tftarif.getText().trim().isEmpty() || tfheuredebut.getText().trim().isEmpty() /*|| tfidService.getText().trim().isEmpty() || tfidUser.getText().trim().isEmpty()*/) {
                    JOptionPane.showMessageDialog(null, "Veuillez inserer tous les champs");
                }
            else {
            F.setNomevent(tfnom.getText());
            F.setTarif(Float.parseFloat(tftarif.getText()));
            F.setDatedebut(Date.valueOf(dpdebut.getValue()));
            F.setDatefin(Date.valueOf(dpfin.getValue()));
            F.setHeuredebut(tfheuredebut.getText());
            F.setHeurefin(tfheurefin.getText());
            F.setNbmaxparticipant(Integer.parseInt(tfnbr.getText()));
            F.setDescription(tfdesc.getText());
            F.setTyperand(tftype.getText());
            F.setLat(Float.parseFloat(tflat.getText()));
            F.setLng(Float.parseFloat(tflng.getText()));
            F.setLieu(tflieu.getText());
            F.setType("randonnee");
            
            sf.AjouterRandonnee(F);
            try {
            Parent root = FXMLLoader.load(getClass().getResource("menuGestioneventFXML.fxml"));
            Stage window = (Stage) btnvalider.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }
                

    }

    @FXML
    private void gotomenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuGestioneventFXML.fxml"));
            Stage window = (Stage) menu.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

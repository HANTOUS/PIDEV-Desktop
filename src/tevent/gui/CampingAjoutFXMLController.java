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

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tevent.entities.Camping;
import tevent.services.CampingServices;


/**
 * FXML Controller class
 *
 * @author maale
 */
public class CampingAjoutFXMLController implements Initializable {

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
    private TextField tfloc;
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
        /*ObservableList<String> Type =
                FXCollections.observableArrayList(
                        "camping",
                        "randonnée"
                    );
        cbtype.setItems(Type);*/
    }    

    @FXML
    private void SaveCamping(ActionEvent event)  {
        Camping F = new Camping();
            CampingServices  sf = new  CampingServices();
            
            F.setNomevent(tfnom.getText());
            F.setTarif(Float.parseFloat(tftarif.getText()));
            F.setDatedebut(Date.valueOf(dpdebut.getValue()));
            F.setDatefin(Date.valueOf(dpfin.getValue()));
            F.setHeuredebut(tfheuredebut.getText());
            F.setHeurefin(tfheurefin.getText());
            F.setNbmaxparticipant(Integer.parseInt(tfnbr.getText()));
            F.setDescription(tfdesc.getText());
            F.setLocalisation(tfloc.getText());
            F.setLat(Float.parseFloat(tflat.getText()));
            F.setLng(Float.parseFloat(tflng.getText()));
            F.setLieu(tflieu.getText());
            F.setType("camping");
            
            sf.AjouterCamping(F);
        
                

        
        
        
        
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

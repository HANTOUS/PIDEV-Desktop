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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tevent.entities.Reclamation;
import tevent.services.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author Salim
 */
public class PasserReclamationController implements Initializable {

    @FXML
    private Button rcenvoyer;
    @FXML
    private TextField rcsujet;
    @FXML
    private TextField rccontenu;
    @FXML
    private TextField rcidu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveReclamation(ActionEvent event) throws Exception {
        
        
        String sujet = rcsujet.getText();
        String contenu = rccontenu.getText();
        int user_id = Integer.parseInt(rcidu.getText());
        
        Reclamation r = new Reclamation(user_id,sujet,contenu);
        ReclamationServices rc = new ReclamationServices();
        rc.addreclamation(r);
        
        rcsujet.setText("");
        rccontenu.setText("");
        rcidu.setText("");
        
       /*try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Stage window = (Stage) rcsujet.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
        
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
        if (rcsujet.getText().isEmpty()) {
            isValid = false;
            message = "Veuillez entrer un sujet a votre reclamation !";
        
        } else if (rccontenu.getText().isEmpty()) {
            isValid = false;
            message = "Veuillez ajouter un contenu a votre reclamation ! ";

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

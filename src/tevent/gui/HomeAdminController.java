/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Salim
 */
public class HomeAdminController implements Initializable {

    @FXML
    private Button gr;
    @FXML
    private Button gf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void gestReclamation(ActionEvent event) {
    
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeReclamation.fxml"));
            Stage window = (Stage) gr.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestFeedback(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeFeedback.fxml"));
            Stage window = (Stage) gf.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}

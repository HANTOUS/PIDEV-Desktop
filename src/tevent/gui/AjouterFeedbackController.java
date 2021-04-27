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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tevent.entities.Feedback;
import tevent.services.FeedbackServices;

/**
 * FXML Controller class
 *
 * @author Salim
 */
public class AjouterFeedbackController implements Initializable {

    @FXML
    private TextField pid;
    @FXML
    private TextField not;
    @FXML
    private TextField rem;
    @FXML
    private Button validfd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) {
        int participf_id = Integer.parseInt(pid.getText());
        int note = Integer.parseInt(not.getText());
        String remarque = rem.getText();
        
        Feedback f = new Feedback(participf_id, note, remarque);
        FeedbackServices fc = new FeedbackServices();
        fc.addFeedback(f);
        
        pid.setText("");
        not.setText("");
        rem.setText("");
       
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Stage window = (Stage) rem.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
    
}

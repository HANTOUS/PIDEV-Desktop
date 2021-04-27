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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Salim
 */
public class HomeAdminController implements Initializable {

    private Button gr;
    private Button gf;
    @FXML
    private Label lbUser;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnFeedback;
    
    
    private Utilisateur user;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setUser(Utilisateur u) {
        user = u;
        //lbUser.setText(u.getNom()+" "+u.getPrenom());

    }

    @FXML
    private void gestReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/ListeReclamation.fxml"));
                loader.load();
                
                ListeReclamationController auc = loader.getController();
                //auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void gestFeedback(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/ListeFeedback.fxml"));
                loader.load();
                
                ListeFeedbackController auc = loader.getController();
                //auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
        
    }

    @FXML
    private void profile(MouseEvent event) {
    }

    @FXML
    private void getDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/Dashboard.fxml"));
                loader.load();
                
                DashboardController auc = loader.getController();
                //auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
        
    }
    
}

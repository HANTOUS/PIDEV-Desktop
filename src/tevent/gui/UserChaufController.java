/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tevent.services.SecurityServices;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.sql.Date;
import tevent.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author hanto
 */
public class UserChaufController implements Initializable {
   @FXML
    private Label lbNom;
    
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void getUsersView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbNom.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AffichageUsers.fxml"));
                loader.load();
                
                AffichageUsersController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getChauffeurView(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
                lbNom.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AffichageChauffeurs.fxml"));
                loader.load();
                
                AffichageChauffeursController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

  
    
    public void setUser(Utilisateur u) {
        user = u;
        //lbUser.setText(u.getNom()+" "+u.getPrenom());

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader();
                lbNom.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("Dashboard.fxml"));
                loader.load();
                
                DashboardController dc = loader.getController();
                dc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
}

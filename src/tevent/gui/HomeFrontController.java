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
import academiccalendar.ui.main.FXMLDocumentController;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author hanto
 */
public class HomeFrontController implements Initializable {
   @FXML
    private Label lbUser;
    
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void getDemandesView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ListDemandes.fxml"));
                loader.load();
                
                ListDemandesController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    @FXML
    private void getFestivalView(ActionEvent event) throws IOException,SQLException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("FrontFestival.fxml"));
                loader.load();
                
                FrontFestivalController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                FrontFestivalController F = new FrontFestivalController();

                Scene scene = new Scene(F.page());
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getCampView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AffichageCampingFXML.fxml"));
                loader.load();
                
                AffichageCampingFXMLController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    @FXML
    private void getRandView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AffichageRandonneeFXML.fxml"));
                loader.load();
                
                AffichageRandonneeFXMLController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getRecView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
               // loader.setLocation(getClass().getResource("/academiccalendar/ui/main/FXMLDocument.fxml"));
                loader.load();
                
               // FXMLDocumentController auc = loader.getController();
                //auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    @FXML
    private void getLogView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ListDemandes.fxml"));
                loader.load();
                
                ListDemandesController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    @FXML
    private void exit(MouseEvent event) throws IOException {
            lbUser.getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
    }
    
    public void setUser(Utilisateur u) {
        user = u;
        lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    
   @FXML
    private void profile(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        loader.load();
        ProfileController dc = loader.getController();
        dc.setUser(user);
        dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
 
}
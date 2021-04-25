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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL 
 */
public class HomeController implements Initializable {

    @FXML
    private Button btndb;
    @FXML
    private Button btndc;
    @FXML
    private Button btndm;
    @FXML
    private Button btnlistdc;
    @FXML
    private Button btnlistdb;
    @FXML
    private Button btnlistdm;
    @FXML
    private Button btnAdm;
    @FXML
    private Button btnAdb;
    @FXML
    private Button btnAdc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AllerVersDemandeBus(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("addDemandeBus.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btndb.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersDemandeChauffeur(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("addDemandeChauffeur.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btndc.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersDemandeMateriel(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("addDemandeMateriel.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btndm.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeChauffeur(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("listdmdchauffeur.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnlistdc.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeBus(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("listdmdbus.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnlistdb.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeMateriel(ActionEvent event) {
       try {
            Parent homePage = FXMLLoader.load(getClass().getResource("listdmdmateriel.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnlistdm.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeMaterielAdmin(ActionEvent event) {
         try {
            Parent homePage = FXMLLoader.load(getClass().getResource("AdminListDemandeMateriel.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnAdm.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeBusAdmin(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("AdminListDemandeBus.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnAdb.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerVersListDemandeChauffeurAdmin(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("AdminListDemandeChauffeur.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnAdc.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
}

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
import tevent.entities.Utilisateur;

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
    
     private Utilisateur user;
    public void setUser(Utilisateur u) {
        user = u;

    }
    
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("addDemandeBus.fxml"));
             AddDemandeBusController dc = loader.getController();
//               dc.setUser(user);
            
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("addDemandeChauffeur.fxml"));
             AddDemandeChauffeurController dc = loader.getController();
//               dc.setUser(user);            
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
           FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("addDemandeMateriel.fxml"));
             AddDemandeMaterielController dc = loader.getController();
//               dc.setUser(user);   
            
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("listdmdchauffeur.fxml"));
             ListdmdchauffeurController dc = loader.getController();
//               dc.setUser(user);   
            
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("listdmdbus.fxml"));
             ListdmdbusController dc = loader.getController();
               //dc.setUser(user);   

            
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("listdmdmateriel.fxml"));
             ListdmdmaterielController dc = loader.getController();
//               dc.setUser(user);   

            
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
             FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("AdminListDemandeMateriel.fxml"));
             AdminListDemandeMaterielController dc = loader.getController();
//               dc.setUser(user);   

            
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
           FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("AdminListDemandeBus.fxml"));
             AdminListDemandeBusController dc = loader.getController();
//               dc.setUser(user);  
            
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
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("AdminListDemandeChauffeur.fxml"));
             AdminListDemandeChauffeurController dc = loader.getController();
//               dc.setUser(user);  
            
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

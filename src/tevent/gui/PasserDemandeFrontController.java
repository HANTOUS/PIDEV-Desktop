/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.stage.Stage;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PasserDemandeFrontController implements Initializable {

    @FXML
    private Button retourbtn;
    @FXML
    private Button passdm;
    @FXML
    private Button passdc;
    @FXML
    private Button passdb;
    @FXML
    private FontAwesomeIconView DB;
  private Utilisateur user;

 public void setUser(Utilisateur u) {
        user = u;

    }
    int idUser = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        retourbtn.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("HomeFront.fxml"));
        loader.load();
       
        HomeFrontController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void AllerDemandeMateriel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        passdm.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AddDemandeMateriel.fxml"));
        loader.load();
       
        AddDemandeMaterielController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void AllerDemandeChauffeur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        passdc.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AddDemandeChauffeur.fxml"));
        loader.load();
       
        AddDemandeChauffeurController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void AllerDemandeBus(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        passdb.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("AddDemandeBus.fxml"));
        loader.load();
       
        AddDemandeBusController dc = loader.getController();
        dc.setUser(user);
       // dc.setFields(user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance(),user.getImage());
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
    
}

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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tevent.entities.Utilisateur;
import tevent.gui.MaterielA.AdminSideMaterielController;
import tevent.gui.busA.AdminSideBusController;
import tevent.gui.chauffBusEvent.ChauffBusEventFXMLController;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class DashboardLogistiqueFXMLController implements Initializable {

    @FXML
    private Label lbUser;
    @FXML
    private Button btnBus;
    @FXML
    private Button btnMateriel;
    
    
    private Utilisateur user;
    @FXML
    private Button btnMateriel1;
    @FXML
    private Button btnRetour;

    public void setUser(Utilisateur u) {
        user = u;
        //lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profile(MouseEvent event) {
    }


    @FXML
    private void getMaterielView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/MaterielA/AdminSideMateriel.fxml"));
                loader.load();
                
                AdminSideMaterielController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getBusView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/busA/AdminSideBus.fxml"));
                loader.load();
                
                AdminSideBusController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getChauffBusEventView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/chauffBusEvent/chauffBusEventFXML.fxml"));
                loader.load();
                
                ChauffBusEventFXMLController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getDashboard(ActionEvent event) throws IOException{FXMLLoader loader = new FXMLLoader();
                //lbUser.getScene().getWindow().hide();
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

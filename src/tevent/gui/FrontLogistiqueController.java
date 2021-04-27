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
import javafx.stage.Stage;
import tevent.gui.MaterielA.AdminSideMaterielController;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class FrontLogistiqueController implements Initializable {

    @FXML
    private Button btnMateriel;
    @FXML
    private Button btnBus;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getMaterielView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/materiel/ClientSideMateriel.fxml"));
                loader.load();
                
                ClientSideMaterielController auc = loader.getController();
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void getBusView(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("/tevent/gui/bus/ClientSideBus.fxml"));
                loader.load();
                
                ClientSideBusController auc = loader.getController();
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("HomeFront.fxml"));
                loader.load();
                
                HomeFrontController auc = loader.getController();
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    
}

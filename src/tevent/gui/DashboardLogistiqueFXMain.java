/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author al199
 */
public class DashboardLogistiqueFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //DashboardLogistiqueFXML
        Parent root = FXMLLoader.load(getClass().getResource("DashboardLogistiqueFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Gestion Logistique");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

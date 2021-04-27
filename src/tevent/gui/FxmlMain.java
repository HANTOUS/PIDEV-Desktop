/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class FxmlMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListDemandes.fxml")) ;
            //Parent root1 = FXMLLoader.load(getClass().getResource("listdmdmateriel.fxml")) ;

            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Ajouter Demande Bus");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui.materiel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

/**
 *
 * @author al199
 */
public class MaterielFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientSideMateriel.fxml"));
        
        Scene scene = new Scene(root);
        
<<<<<<< HEAD
        primaryStage.setTitle("Hello World!");
=======
        primaryStage.setTitle("Materiel");
>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96
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

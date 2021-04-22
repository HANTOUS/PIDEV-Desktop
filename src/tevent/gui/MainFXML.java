/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 *
 * @author hanto
 */
public class MainFXML extends Application {
    //private double xOffset = 0; 
  //  private double yOffset = 0; 
    //BorderPane private p;
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
         primaryStage.show();
       /* primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        root.setOnMousePressed(((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getScreenY();
        }));
        
        root.setOnMouseDragged((MouseEvent event)->{
            primaryStage.setX(event.getSceneX() - xOffset);
            primaryStage.setY(event.getSceneY() - yOffset);

        });*/
        
                //new animatefx.animation.ZoomIn(root).play();

       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

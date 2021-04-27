/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import tevent.services.SecurityServices;
import tevent.entities.Utilisateur;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author hanto
 */
public class ResetPasswordController implements Initializable {
    @FXML
    private JFXPasswordField txtPwd;
    @FXML
    private JFXPasswordField txtPwd1;
    
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void confirmer(ActionEvent event) throws IOException {

        if (txtPwd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre mot de passe !!!");
            alert.showAndWait();
        } else if (! txtPwd.getText().equals(txtPwd1.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer le meme mot de passe !!!");
            alert.showAndWait();
        }else{
            SecurityServices ss = new SecurityServices();
            ss.resetPassword(user.getId(),txtPwd.getText());
           
                FXMLLoader loader = new FXMLLoader();
                txtPwd.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("Login.fxml"));
                loader.load();
                
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
        }

    }

    void setUser(Utilisateur u){
        user =u;
    }
    

}

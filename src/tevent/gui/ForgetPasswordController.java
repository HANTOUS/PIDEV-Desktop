/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tevent.services.UtilisateurServices;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author hanto
 */
public class ForgetPasswordController implements Initializable {
   @FXML
    private JFXTextField txtCode;
    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void valider(ActionEvent event) throws IOException {
        if (verifierCode(txtCode.getText(),user.getId())){
             System.out.println("code valid");
            FXMLLoader loader = new FXMLLoader();
                txtCode.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ResetPassword.fxml"));
                loader.load();
                
                ResetPasswordController rpc = loader.getController();
                rpc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();}
        else{
             System.out.println("code invalid");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Code Invalid");
                alert.showAndWait();
        }
}
    

    public void setUser(Utilisateur u) {
        user = u;

    }
    
    private boolean verifierCode(String code,int id){
        boolean valid =false;
        UtilisateurServices us = new UtilisateurServices();
        if (code.equals(us.getCodebyId(id)))
            valid = true;
            
        return valid;
        
    }

}

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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tevent.services.SecurityServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import tevent.services.UtilisateurServices;
import tevent.entities.Utilisateur;


/**
 * FXML Controller class
 *
 * @author hanto
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPwd;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnRegister;

    

    public static String email;
    public static String motpass;
    public static int codem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewPassword.fxml"));
        NewPasswordController ircc = loader.getController();
        String s = "a";
        if (!ircc.mail.equals(s)) {
            txtEmail.setText(ircc.mail);
        }*/
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        email = txtEmail.getText();
        motpass = txtPwd.getText();
        SecurityServices sc = new SecurityServices();
        if (sc.login(email, motpass)) {
            UtilisateurServices us = new UtilisateurServices();
            String role = us.getRolebyEmail(email);
            Utilisateur user = us.getUserByMail(email);
             System.out.println( user.getActivation_token()==  null);
            if( user.getActivation_token()==  null){
                if (role.equals("[\"ROLE_USER\"]")||role.equals("[\"ROLE_CHAUFFEUR\"]")) {

                    FXMLLoader loader = new FXMLLoader();
                    login.getScene().getWindow().hide();
                    Stage prStage = new Stage();
                    loader.setLocation(getClass().getResource("HomeFront.fxml"));
                    loader.load();

                    HomeFrontController dc = loader.getController();
                    dc.setUser(user);
                    Parent root = loader.getRoot();
                    Scene scene = new Scene(root);
                    prStage.setScene(scene);
                    prStage.setResizable(false);
                    prStage.show();

                } else if (role.equals("[\"ROLE_ADMIN\"]")){
                    FXMLLoader loader = new FXMLLoader();
                    login.getScene().getWindow().hide();
                    Stage prStage = new Stage();
                    loader.setLocation(getClass().getResource("Dashboard.fxml"));
                    loader.load();

                    DashboardController dc = loader.getController();
                    dc.setUser(user);
                    Parent root = loader.getRoot();
                    Scene scene = new Scene(root);
                    prStage.setScene(scene);
                    prStage.setResizable(false);
                    prStage.show();

                } 
           }
            else{
                FXMLLoader loader = new FXMLLoader();
                login.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ActivationCompte.fxml"));
                loader.load();
                
                ActivationCompteController ac = loader.getController();
                ac.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
                
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez Vos Coordonnees !!!");
            alert.showAndWait();
        }
    }

    @FXML
    private void MotpassOubliee(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        login.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Email.fxml"));
        loader.load();

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        

        /*Random r = new Random();
        codem = r.nextInt(9999 - 1000 + 1);
        System.out.println(codem);
        //sc.setCodepass(sc.getIdbymail(txtEmail.getText()), codem);

        //System.out.println(sc.getPassbyId(sc.getIdbymail(txtEmail.getText())));
        //=*if (isValidEmailAddress(txtEmail.getText())) {
            //     SendMail.send(txtEmail.getText(), sc.getPassbyId(sc.getIdbymail(txtEmail.getText())));
            SendMail.send(txtEmail.getText(), codem);
            FXMLLoader loader = new FXMLLoader();
            login.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("Motpasseoublie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Adresse Email Non Valide !!!");
            alert.showAndWait();
        }*/

    }

  

    @FXML
    private void Register(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        login.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }


}

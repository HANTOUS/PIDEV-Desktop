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
import tevent.services.SecurityServices;
import tevent.services.UtilisateurServices;
import tevent.entities.Utilisateur;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Random;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.UUID;


/**
 * FXML Controller class
 *
 * @author hanto
 */
public class EmailController implements Initializable {
    @FXML
    private JFXTextField txtEmail;
    
    private String username = "tevents98@gmail.com";
    private String password = "TEvents2021";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void envoyer(ActionEvent event) throws IOException {

       if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre email !!!");
            alert.showAndWait();
        }else{
            UtilisateurServices us = new UtilisateurServices();
            Utilisateur u = us.getUserByMail(txtEmail.getText());
            if(u == null ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Email n'existe pas");
                alert.showAndWait();
            }
            else{
                
                envoyerEmail(u);
                FXMLLoader loader = new FXMLLoader();
                txtEmail.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ForgetPassword.fxml"));
                loader.load();
                
                ForgetPasswordController fpc = loader.getController();
                fpc.setUser(u);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            }
        }
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        txtEmail.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    public static void envoyerEmail(Utilisateur u)  {
        SecurityServices us = new SecurityServices();
         String username = "tevents98@gmail.com";
         String password = "TEvents2021";
        //Random r = new Random();
        String codem = UUID.randomUUID().toString();
        
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
        });
        try {
        
        // Etape 2 : Création de l'objet Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
        message.setSubject("Mot de passe oublié");
        message.setText("Bonjour "+ u.getPrenom() +",Une demande de réinitialisation de mot de passe a été effectué pour le site Tunisia Events. veuillez copier le code ci-dessous pour continuer: "+codem);
        // Etape 3 : Envoyer le message
        Transport.send(message);
        us.forgetPassword(u.getId(),codem);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }
}

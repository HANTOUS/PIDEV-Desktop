/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tevent.entities.Utilisateur;
import tevent.services.SecurityServices;
import tevent.services.UtilisateurServices;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author hanto
 */
public class RegisterController implements Initializable {

    @FXML
    private AnchorPane register;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtCin;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPwd;
    @FXML
    private JFXPasswordField txtConfPwd;
    @FXML
    private JFXDatePicker dateN;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXButton btnLogin;

    public static String email;
    public static String motpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        
        if (txtNom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre nom !!!");
            alert.showAndWait();
        } else if (txtPrenom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre prennom !!!");
            alert.showAndWait();
        } else if (txtPwd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre mot de passe !!!");
            alert.showAndWait();
        } else if (! txtPwd.getText().equals(txtConfPwd.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer le meme mot de passe !!!");
            alert.showAndWait();
        }else if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre e-mail !!!");
            alert.showAndWait();
        } else if (txtCin.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre numéro telephonique !!!");
            alert.showAndWait();
        } else {
            Utilisateur u = new Utilisateur();
            u.setNom(txtNom.getText());
            u.setPrenom(txtPrenom.getText());
            u.setPassword(txtPwd.getText());
            u.setEmail(txtEmail.getText());
            u.setCin(txtCin.getText());
            u.setDateNaissance(Date.valueOf(dateN.getValue()));
            //u.setPhoto(file.toURI().toString());
            SecurityServices sc = new SecurityServices();
            String res = sc.register(u);
            if(res.equals("Utilisateur ajouté avec succés")){
                //sc.desactivation(u.getId());
                UtilisateurServices uc = new UtilisateurServices();
                envoyerEmail(uc.getUserByMail(u.getEmail()));
                FXMLLoader loader = new FXMLLoader();
                txtNom.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reesseyer");
                alert.setHeaderText(null);
                alert.setContentText(res);
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        register.getScene().getWindow().hide();
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
        //String codem = UUID.randomUUID().toString();
        
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
        message.setSubject("Activation Compte");
        message.setText("Bonjour "+ u.getPrenom() +",  Vous avez crée un compte dans notre site Tunisia Events. veuillez copier le code ci-dessous pour l'activer: "+u.getActivation_token());
        // Etape 3 : Envoyer le message
        Transport.send(message);
        //us.forgetPassword(u.getId(),codem);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }

}

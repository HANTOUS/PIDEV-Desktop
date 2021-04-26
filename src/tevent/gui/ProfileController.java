/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import tevent.entities.Utilisateur;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import java.sql.Date;
import tevent.entities.Utilisateur;
import tevent.services.UtilisateurServices;
import tevent.services.SecurityServices;
import javafx.event.ActionEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import java.net.MalformedURLException;
//import net.glxn.qrgen.QRCode;
//import net.glxn.qrgen.image.ImageType;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
//qr
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//mail
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
public class ProfileController implements Initializable {
    @FXML
    private Label lbUser;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtCin;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXDatePicker dateN;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private ImageView NewImage;
    @FXML
    private ImageView image;
     @FXML
    private Label pathpicture;

    private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
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
        } else if (txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre e-mail !!!");
            alert.showAndWait();
        } else if (txtCin.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Vous devez entrer votre numéro de carte d'identité !!!");
            alert.showAndWait();
        } else {
            Utilisateur u = new Utilisateur();
            u.setId(user.getId());
            u.setNom(txtNom.getText());
            u.setPrenom(txtPrenom.getText());
            u.setEmail(txtEmail.getText());
            u.setCin(txtCin.getText());
            u.setDateNaissance(Date.valueOf(dateN.getValue()));
            u.setImage(pathpicture.getText());
            UtilisateurServices us = new UtilisateurServices();
            String res = us.modifierUtilisateur(u);
            if(res.equals("Utilisateur modifier avec succés")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Vos données sont mis à jour");
            alert.showAndWait();}
            else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(res);
            alert.showAndWait();
            }

        }
    }
    
     public void setFields(String nom,String prenom, String cin, String email, Date toLocalDate ,String pic) throws FileNotFoundException{
        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        dateN.setValue(toLocalDate.toLocalDate());
        txtCin.setText(cin);
        txtEmail.setText(email);
        image.setImage(new Image(new File(pic).toURI().toString()));
        NewImage.setImage(new Image(new File(pic).toURI().toString()));
    }
    
    @FXML
    private void profile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        loader.load();
        ProfileController dc = loader.getController();
        UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();        
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void password(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("ChangerPassword.fxml"));
        loader.load();
        ChangerPasswordController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        
    }

    @FXML
    private void demandes(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("MesDemandes.fxml"));
         loader.load();
        MesDemandesController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }   

    @FXML
    private void reclamations(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lbUser.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("MesReclamations.fxml"));
        loader.load();
        MesReclamationsController dc = loader.getController();
         UtilisateurServices us = new UtilisateurServices();
        dc.setUser(us.getUserById(user.getId()));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
            lbUser.getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
    }

    public void setUser(Utilisateur u) {
        user = u;
        lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    
    @FXML
    private void handleDrag(DragEvent event) throws IOException {
        if(event.getDragboard().hasFiles())
        event.acceptTransferModes(TransferMode.ANY);
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
       // System.out.println(files.get(0));
        NewImage.setImage(img);
    }

     @FXML
    private void uploadpicture(ActionEvent event) throws FileNotFoundException,MalformedURLException{
         FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicture.setText(file.getAbsolutePath());
                      
                       file= new File("C:\\Users\\hanto\\Desktop\\Esprit\\3eme\\PI\\PIDEV-Desktop\\src\\tevent\\images\\"+file.getName());
                       File file1= new File(pathpicture.getText());

                      Image img = new Image(file.toURI().toString());
                      System.out.println(file.getAbsolutePath());
                      //saveToFile(img);
                       //newImage= new ImageView(img);
                       System.out.println(img);
                       NewImage.setImage(img);
           
                }
    }
    
     public static void saveToFile(Image image) {
        File outputFile = new File("C:\\Users\\hanto\\Desktop\\Esprit\\3eme\\PI\\PIDEV-Desktop\\src\\tevent\\images\\");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
       // System.out.println(bImage);
        try {
          ImageIO.write(bImage, "jpg", outputFile);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
  }
    
     @FXML
    private void QrCodeGenerate(ActionEvent event) throws Exception {
        
        try {
            String qrCodeData = "Détail Compte \n  Nom : "+user.getNom()+"\n Prenom : "+user.getPrenom()
            +"\n CIN : "+user.getCin()+"\n Email : "+user.getEmail()+"\n Date Naissance : "+user.getDateNaissance();
            String filePath = "C:/Users/hanto/Desktop/Esprit/3eme/PI/PIDEV-Desktop/src/tevent/images/"+user.getNom()+user.getPrenom()+Integer.toString(user.getId())+".png";
           /* String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            System.out.println(filePath);
            */
             
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("QRCode.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {

                               System.out.println(ex.getMessage());
                            }
                            QRCodeController qrc = loader.getController();
                            qrc.setImg(filePath);
                            System.out.println("icii2");
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    @FXML
    private void desactiver(ActionEvent event) throws IOException {
            SecurityServices ss = new SecurityServices();
            UtilisateurServices us = new UtilisateurServices();
            ss.desactivation(user.getId());
            envoyerEmail(us.getUserById(user.getId()));
            lbUser.getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
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
        message.setSubject("Compte désactiver");
        message.setText("Bonjour "+ u.getPrenom() +",  Vous avez désactiver votre compte. veuillez copier le code ci-dessous pour l'activer: "+u.getActivation_token());
        // Etape 3 : Envoyer le message
        Transport.send(message);
        //us.forgetPassword(u.getId(),codem);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }
    
}

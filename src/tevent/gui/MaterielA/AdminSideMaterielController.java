/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui.MaterielA;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import tevent.entities.Materiel;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.Bus;
import tevent.services.MaterielService;
import javax.mail.*;
import javax.activation.*;

import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tevent.entities.Utilisateur;
import tevent.gui.HomeController;


/**
 * FXML Controller class
 *
 * @author al199
 */
public class AdminSideMaterielController implements Initializable {

    MaterielService ms = new MaterielService();
    
    ObservableList<Materiel> listMateriel= FXCollections.observableArrayList();
    
    Materiel m = new Materiel();
    
    @FXML
    private TableView<Materiel> tableListMaterielId;
    @FXML
    private TextField lb_id;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_clear;

    @FXML
    private TableColumn<Materiel, String> col_table_id;
    @FXML
    private TableColumn<Materiel, String> col_table_label_id;
    @FXML
    private TableColumn<Materiel, String> col_table_stock_id;
    @FXML
    private TableColumn<Materiel, String> col_table_qte_reserve_id;
    @FXML
    private TableColumn<Materiel, String> col_table_prix_id;
    @FXML
    private TableColumn<Materiel, Boolean> col_table_dispo_id;

    @FXML
    private TextField lb_stock;
    @FXML
    private TextField lb_label;

    @FXML
    private ComboBox<Boolean> cb_dispo;
    @FXML
    private TextField lb_prix;
    @FXML
    private TextField lb_qte_reserve;
    @FXML
    private Button btnretour;
    @FXML
    private Label lbUser;
    
    
    public void loadMateriel(){
        List<Materiel> lsMateriel = ms.listMateriel();
        
        col_table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_label_id.setCellValueFactory(new PropertyValueFactory<>("label"));
        col_table_stock_id.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_table_qte_reserve_id.setCellValueFactory(new PropertyValueFactory<>("qte_reserve"));
        col_table_prix_id.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_table_dispo_id.setCellValueFactory(new PropertyValueFactory<>("dispo"));
        
        for(int i=0;i<lsMateriel.size();i++)
            listMateriel.add(lsMateriel.get(i));
        tableListMaterielId.setItems(listMateriel);
    }
    
    private Utilisateur user;

    public void setUser(Utilisateur u) {
        user = u;
        //lbUser.setText(u.getNom()+" "+u.getPrenom());

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb_id.setDisable(true);
        
        ObservableList<Boolean> listDispo= FXCollections.observableArrayList(false,true);
        cb_dispo.setItems(listDispo);
        
        loadMateriel();
        
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        
        tableListMaterielId.setRowFactory( tv -> {
            TableRow<Materiel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Materiel rowData = row.getItem();
                    this.m= rowData;
                    lb_id.setText(m.getId()+"");
                    lb_label.setText(m.getLabel());
                    lb_prix.setText(m.getPrix()+"");
                    lb_qte_reserve.setText(m.getQte_reserve()+"");
                    lb_stock.setText(m.getStock()+"");
                    cb_dispo.setValue(m.getDispo());
                    
                    btn_add.setDisable(true);
                    btn_update.setDisable(false);
                    btn_delete.setDisable(false);
                    System.out.println(rowData);
                }
            });
            return row ;
        });
    }    

    @FXML
    private void ajoutMateriel(ActionEvent event) {
        m.setLabel(lb_label.getText());
        m.setStock(Integer.parseInt(lb_stock.getText()));
        m.setQte_reserve(Integer.parseInt(lb_qte_reserve.getText()));
        m.setPrix(Float.parseFloat(lb_prix.getText()));
        m.setDispo(cb_dispo.getValue());
        ms.addMateriel(m);
        tableListMaterielId.getItems().clear();
        clear(event);
        loadMateriel();
    }
    
    @FXML
    private void deleteMateriel(ActionEvent event) {
        m.setId(Integer.parseInt(lb_id.getText()));
        
        tableListMaterielId.getItems().clear();
        
        ms.deleteMateriel(m);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        clear(event);
        loadMateriel();
    }
    
    @FXML
    private void updateMateriel(ActionEvent event) {
        System.out.println();
        m.setId(Integer.parseInt(lb_id.getText()));
        m.setLabel(lb_label.getText());
        m.setStock(Integer.parseInt(lb_stock.getText()));
        m.setQte_reserve(Integer.parseInt(lb_qte_reserve.getText()));
        m.setPrix(Float.parseFloat(lb_prix.getText()));
        m.setDispo(cb_dispo.getValue());
        ms.updateMateriel(m);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        tableListMaterielId.getItems().clear();
        
        if (m.getStock()- m.getQte_reserve() <= 15 && m.getStock()>15)
            this.senMailWarning(m);
        
        clear(event);
        loadMateriel();
    }
    
    @FXML
    private void clear(ActionEvent event) {
        lb_id.setText("");
        lb_label.setText("");
        lb_prix.setText("");
        lb_qte_reserve.setText("");
        lb_stock.setText("");
        cb_dispo.setValue(Boolean.FALSE);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
    }
    
    
    
    public void senMailWarning(Materiel m){
      
      // Mention the Recipient's email address
        String to = "alaaeddine.benali@esprit.tn";
        // Mention the Sender's email address
        String from = "tevents98@gmail.com";
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tevents98@gmail.com", "TEvents2021");
            }
        });
        System.out.println("session");
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            System.out.println("in sending");
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Warning Mailer stock!");
            // Now set the actual message
            message.setText(m.getStock()- m.getQte_reserve() +
                            " only are available for materiel: " +
                            m.getLabel() +
                            " with id: " +
                            m.getId() +
                            '!');
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("Home.fxml"));
            //HomeController dc = loader.getController();
//            dc.setUser(user);
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) btnretour.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
}

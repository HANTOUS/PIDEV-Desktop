/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import tevent.entities.Chauffeur;
import tevent.entities.DemandeChauffeur;
import tevent.entities.Utilisateur;
import tevent.services.ChauffeurServices;
import tevent.services.DemandeBusServices;
import tevent.services.DemandeChauffeurServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AdminListDemandeChauffeurController implements Initializable {

    @FXML
    private TextField utilisateur;
    @FXML
    private TextField dateexpiration;
    @FXML
    private TextField datepermis;
    @FXML
    private TextField email;
    @FXML
    private TextField numpermis;
    @FXML
    private TextField etat;
    @FXML
    private TableColumn<DemandeChauffeur, Integer> colID;
    @FXML
    private TableColumn<DemandeChauffeur, Integer> colUser;
    @FXML
    private TableColumn<DemandeChauffeur, Integer> colNumpermis;
    @FXML
    private TableColumn<DemandeChauffeur, LocalDate> colDatepermis;
    @FXML
    private TableColumn<DemandeChauffeur, LocalDate> colDateexpiration;
    @FXML
    private TableColumn<DemandeChauffeur, String> colEtat;
    @FXML
    private TableView<DemandeChauffeur> tableDemandeChauffeur;
    DemandeBusServices dbs= new DemandeBusServices();
    DemandeChauffeurServices dcs = new DemandeChauffeurServices();
    ChauffeurServices cs= new ChauffeurServices();
            private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    @FXML
    private TextField numkey;
    @FXML
    private TextField datekey;
    @FXML
    private Button retourbtn;
    @FXML
    private Button refreshbtn;
private Utilisateur user;

    public void setUser(Utilisateur u) {
        user = u;

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
               colNumpermis.setCellValueFactory(new PropertyValueFactory<>("num_permis"));
               colDatepermis.setCellValueFactory(new PropertyValueFactory<>("date_permis"));
               colDateexpiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
               colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
               
                ObservableList <DemandeChauffeur> DemandeChauffeurList = (ObservableList<DemandeChauffeur>) dcs.readDemandeChauffeur();
                tableDemandeChauffeur.setItems(DemandeChauffeurList);
    }    

    @FXML
    private void selectDemande(ActionEvent event) {
         if(tableDemandeChauffeur.getSelectionModel().getSelectedItem().getEtat().equals("encours")){
             accepter.setVisible(true);
        refuser.setVisible(true);
        }else{
            
        accepter.setVisible(false);
        refuser.setVisible(false);        }
        String prenom ="";
        String nom ="";
        String mail ="";       
        String req1 ="select prenom,nom , email from utilisateur where id=?";
                try {

                   
            int iduser = (tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getUtilisateur_id();
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps1.setInt(1, iduser);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                prenom=rs1.getString(1);
                nom=rs1.getString(2);
                mail=rs1.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      //  id = ((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getId());

            utilisateur.setText(prenom+nom);
            email.setText(mail);
            numpermis.setText(String.valueOf((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getNum_permis()));
            datepermis.setText(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_permis().toString()));
            dateexpiration.setText(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_expiration().toString()));
            etat.setText(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getEtat()));
           
    }

    @FXML
    private void supprimerDemande(ActionEvent event) {
           dcs.deleteDemandeChauffeur(tableDemandeChauffeur.getSelectionModel().getSelectedItem().getId());
        tableDemandeChauffeur.getItems().removeAll(tableDemandeChauffeur.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void accepterDemande(ActionEvent event) throws MessagingException, ParseException {
         String prenom ="";
        String nom ="";
        String mail ="";       
        String req1 ="select prenom,nom , email from utilisateur where id=?";
                try {

                   
            int iduser = (tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getUtilisateur_id();
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps1.setInt(1, iduser);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                prenom=rs1.getString(1);
                nom=rs1.getString(2);
                mail=rs1.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           String message="Mr "+nom+prenom+"Vous êtes prié de bien vouloir vous présenter à l'agence pour l'évenement du  qui debute le et qui prendra fin le  pour la signature de la location du bus";

         dcs.AccepterDemande(tableDemandeChauffeur.getSelectionModel().getSelectedItem().getId());
        tableDemandeChauffeur.setItems(dcs.readDemandeChauffeur());
                    etat.setText("accepter");
                            dbs.SendMail(message,"Chauffeur",mail);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                             Date dateex = (Date) formatter.parse(dateexpiration.getText());
                             Date datep = (Date) formatter.parse(datepermis.getText());
                                                         Date dateDebut = new Date();
  
                             java.sql.Date dateDep = new java.sql.Date(dateDebut.getTime());
                             java.sql.Date dateExp = new java.sql.Date(dateex.getTime());
                                java.sql.Date datePer = new java.sql.Date(datep.getTime());
           
                            int numPermis = Integer.parseInt(numpermis.getText());
                            Date dateExpiration = dateExp;
                            Date datePermis = datePer;
                            Chauffeur Chauff= new Chauffeur(numPermis,dateDep,dateExpiration, datePermis, 1) ;
                             
                            cs.ajouterChauffeur(Chauff);
                                        JOptionPane.showMessageDialog(null, " Le chauffeu a été ajouter , un email sera envoyé pour lui informé ");

                                                      
 

accepter.setVisible(false);
        refuser.setVisible(false);
        utilisateur.setText("");
            email.setText("");
            numpermis.setText("");
            datepermis.setText("");
            dateexpiration.setText("");
            etat.setText("");
    }

    @FXML
    private void refuserDemande(ActionEvent event) {
     dcs.RefuserDemande(tableDemandeChauffeur.getSelectionModel().getSelectedItem().getId());
        tableDemandeChauffeur.setItems(dcs.readDemandeChauffeur());
                    etat.setText("refuser");
                    
accepter.setVisible(false);
        refuser.setVisible(false);
utilisateur.setText("");
            email.setText("");
            numpermis.setText("");
            datepermis.setText("");
            dateexpiration.setText("");
            etat.setText("");
    }

    @FXML
    private void rechercher(ActionEvent event) {
      String date =datekey.getText()   ;
              LocalDate expiration = LocalDate.parse(date);
      int num = Integer.parseInt(numkey.getText());
      tableDemandeChauffeur.setItems(dcs.advancedSearchDemandeChauffeur(num, expiration));
    }

    @FXML
    private void retour(ActionEvent event) {
     try {
               FXMLLoader loader = new FXMLLoader();
            Parent homePage = loader.load(getClass().getResource("Home.fxml"));
             HomeController dc = loader.getController();
//               dc.setUser(user);
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) retourbtn.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Refresh(ActionEvent event) {
         tableDemandeChauffeur.setItems(dcs.readDemandeChauffeur());
        datekey.setText("");
       numkey.setText("");
    }
    
}

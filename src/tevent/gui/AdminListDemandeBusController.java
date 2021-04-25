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
import tevent.entities.DemandeBus;
import tevent.services.DemandeBusServices;
import static tevent.services.DemandeBusServices.SendMail;
import tevent.services.DemandeChauffeurServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AdminListDemandeBusController implements Initializable {

    @FXML
    private TextField utilisateur;
    @FXML
    private TextField email;
    @FXML
    private TextField villedepart;
    @FXML
    private TextField villearrivee;
    @FXML
    private TextField heuredebut;
    @FXML
    private TextField heurearrivee;
    @FXML
    private TextField etat;
    @FXML
    private TextField nb_participants;
    @FXML
    private Button accepter;
    @FXML
    private TableColumn<DemandeBus, Integer> colID;
    @FXML
    private TableColumn<DemandeBus, Integer> colUser;
    @FXML
    private TableColumn<DemandeBus, Integer> colNbparticipant;
    @FXML
    private TableColumn<DemandeBus, String> colVilledepart;
    @FXML
    private TableColumn<DemandeBus, String> colVillearrivee;
    @FXML
    private TableColumn<DemandeBus, String> colHeuredebut;
    @FXML
    private TableColumn<DemandeBus, String> colHeurearrivee;
    @FXML
    private TableColumn<DemandeBus, String> colEtat;
    @FXML
    private Button refuser;
    @FXML
    private TableView<DemandeBus> tableDemandeBus;
    @FXML
    private TableColumn<DemandeBus, Date> colJourloc;
    
    DemandeBusServices dbs = new DemandeBusServices();
            private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField jourloc;
    @FXML
    private TextField villekey;
    @FXML
    private TextField nbkey;
    @FXML
    private Button refreshbtn;
    @FXML
    private Button retourbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNbparticipant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        colVilledepart.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        colVillearrivee.setCellValueFactory(new PropertyValueFactory<>("ville_arrivee"));
        colHeuredebut.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        colHeurearrivee.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colJourloc.setCellValueFactory(new PropertyValueFactory<>("jour_location"));

        ObservableList<DemandeBus> DemandeBusList = dbs.readDemandeBus();
        tableDemandeBus.setItems(DemandeBusList);
    }    

    @FXML
    private void accepterDemande(ActionEvent event) throws MessagingException {
        String prenom ="";
        String nom ="";
        String mail ="";       
        String req1 ="select prenom,nom , email from utilisateur where id=?";
                try {

                   
            int iduser = (tableDemandeBus.getSelectionModel().getSelectedItem()).getUtilisateur_id();
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

        dbs.AccepterDemande(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
                                                JOptionPane.showMessageDialog(null, " La demande a été bien confirmer , un email sera envoyé pour lui informé ");

        tableDemandeBus.setItems(dbs.readDemandeBus());
        etat.setText("accepter");
        accepter.setVisible(false);
        refuser.setVisible(false);
        dbs.SendMail(message,"bus",mail);
    }

    @FXML
    private void refuserDemande(ActionEvent event) {
         dbs.RefuserDemande(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
        tableDemandeBus.setItems(dbs.readDemandeBus());
          etat.setText("refuser");
accepter.setVisible(false);
        refuser.setVisible(false);
    }

    @FXML
    private void supprimerDemande(ActionEvent event) {
          dbs.deleteDemandeBus(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
        tableDemandeBus.getItems().removeAll(tableDemandeBus.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void selectDemande(ActionEvent event) {
        if(tableDemandeBus.getSelectionModel().getSelectedItem().getEtat().equals("encours")){
             accepter.setVisible(true);
        refuser.setVisible(true);
        }else{
            
        accepter.setVisible(false);
        refuser.setVisible(false); 
        }
        String prenom ="";
        String nom ="";
        String mail ="";       
        String req1 ="select prenom,nom , email from utilisateur where id=?";
                try {

                   
            int iduser = (tableDemandeBus.getSelectionModel().getSelectedItem()).getUtilisateur_id();
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
            
            nb_participants.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getNb_participant()));
            villedepart.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getVille_depart()));
            villearrivee.setText(((tableDemandeBus.getSelectionModel().getSelectedItem()).getVille_arrivee()));
            etat.setText(((tableDemandeBus.getSelectionModel().getSelectedItem()).getEtat()));
            heurearrivee.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getHeure_arrivee()));
            heuredebut.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getHeure_depart()));
            jourloc.setText(((tableDemandeBus.getSelectionModel().getSelectedItem()).getJour_location().toString()));
    }

    @FXML
    private void rechercher(ActionEvent event) {
        String ville = villekey.getText();
        int nb=Integer.parseInt(nbkey.getText());
        tableDemandeBus.setItems(dbs.advancedSearchDemandeBus(nb, ville, ville));
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("Home.fxml"));
            
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
        tableDemandeBus.setItems(dbs.readDemandeBus());
        villekey.setText("");
       nbkey.setText("");
    }
    
}

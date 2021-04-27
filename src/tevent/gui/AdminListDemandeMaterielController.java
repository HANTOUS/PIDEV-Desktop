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
import tevent.entities.DemandeMateriel;
import tevent.entities.Utilisateur;
import tevent.services.DemandeBusServices;
import tevent.services.DemandeMaterielServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AdminListDemandeMaterielController implements Initializable {

    @FXML
    private TextField utilisateur;
    @FXML
    private TextField quantite;
    @FXML
    private TextField materiel;
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField datedebut;
    @FXML
    private TextField datefin;
    @FXML
    private TextField etat;
    
    @FXML
    private TableColumn<DemandeMateriel, Integer> colUser;
     @FXML
    private TableView<DemandeMateriel> tableDemandeMateriel;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colID;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colMateriel;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colQuantite;
    @FXML
    private TableColumn<DemandeMateriel, Date> colDateDebut;
    @FXML
    private TableColumn<DemandeMateriel, Date> colDateFin;
    @FXML
    private TableColumn<DemandeMateriel, String> colEtat;
        DemandeBusServices dbs= new DemandeBusServices();

    DemandeMaterielServices dms = new DemandeMaterielServices();
        private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    @FXML
    private TextField etatkey;
    @FXML
    private TextField qtekey;
    @FXML
    private TextField disponibilite;
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
        
        accepter.setVisible(false);
        refuser.setVisible(false);
                ObservableList <DemandeMateriel> DemandeMaterielList = (ObservableList<DemandeMateriel>) dms.readDemandeMateriel();

               colID.setCellValueFactory(new PropertyValueFactory<>("id"));
               colUser.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
               colMateriel.setCellValueFactory(new PropertyValueFactory<>("materiel_id"));
               colQuantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
               
               colDateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
               colDateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
               colEtat.setCellValueFactory(new PropertyValueFactory<>("etat")); 
               
                tableDemandeMateriel.setItems(DemandeMaterielList);
                tableDemandeMateriel.getColumns().addAll(colID,colMateriel,colQuantite,colDateDebut,colDateFin,colEtat) ;
    }    

    @FXML
    private void supprimerDemande(ActionEvent event) {
         dms.deleteDemandeMateriel(tableDemandeMateriel.getSelectionModel().getSelectedItem().getId());
        tableDemandeMateriel.getItems().removeAll(tableDemandeMateriel.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void selectDemande(ActionEvent event) {
        if(tableDemandeMateriel.getSelectionModel().getSelectedItem().getEtat().equals("encours")){
             accepter.setVisible(true);
        refuser.setVisible(true);
        }else{
            
        accepter.setVisible(false);
        refuser.setVisible(false);        }

        String label ="";
        String prenom ="";
        String nom ="";
        String mail ="";
        int stock=0;
        int diff=0;
        int qte_reserve=0;
        String req ="select label from materiel where id=?";
        String req1 ="select prenom,nom , email from utilisateur where id=?";
String req2 = "SELECT stock , qte_reserve FROM materiel where id=?";
       int idmateriel = tableDemandeMateriel.getSelectionModel().getSelectedItem().getMateriel_id();
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1,idmateriel );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt(1);
                qte_reserve = rs.getInt(2);
                diff = stock - qte_reserve;
                System.out.println(stock+""+qte_reserve+""+diff);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               try {

                   
            int idmat = (tableDemandeMateriel.getSelectionModel().getSelectedItem()).getMateriel_id();
            int iduser = (tableDemandeMateriel.getSelectionModel().getSelectedItem()).getUtilisateur_id();
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps.setInt(1, idmat);
            ps1.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            while (rs.next()) {
                label=rs.getString(1);
            }
            while (rs1.next()) {
                prenom=rs1.getString(1);
                nom=rs1.getString(2);
                mail=rs1.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
         

        int idtextfield = ((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getId());
        String iddem= ""+idtextfield;
            String Disponible =""+diff;
            disponibilite.setText(Disponible);
            utilisateur.setText(prenom + nom);
            email.setText(mail);
                       id.setText(iddem);

            quantite.setText(String.valueOf((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getQte()));
            materiel.setText(label);
            datedebut.setText(((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getDate_debut().toString()));
            datefin.setText(((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getDate_fin().toString()));
            etat.setText(((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getEtat()));
            
        
    }

    @FXML
    private void accepterDemande(ActionEvent event) throws MessagingException {
         int stock=0;
        int diff=0;
        int qte_reserve=0;
        int qte=Integer.parseInt(quantite.getText());
         String prenom ="";
        String nom ="";
        String mail ="";
        String req2 = "SELECT stock , qte_reserve FROM materiel where id=?";
       int idmateriel = tableDemandeMateriel.getSelectionModel().getSelectedItem().getMateriel_id();
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1,idmateriel );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt(1);
                qte_reserve = rs.getInt(2);
                diff = stock - qte_reserve;
                System.out.println(stock+""+qte_reserve+""+diff);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               
        String req1 ="select prenom,nom , email from utilisateur where id=?";
                try {

                   
            int iduser = (tableDemandeMateriel.getSelectionModel().getSelectedItem()).getUtilisateur_id();
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
           if(qte_reserve+qte<stock){
               
         dms.AccepterDemande(tableDemandeMateriel.getSelectionModel().getSelectedItem().getId(),tableDemandeMateriel.getSelectionModel().getSelectedItem().getMateriel_id());
        tableDemandeMateriel.setItems(dms.readDemandeMateriel());
                    etat.setText("accepter");
                    
                                                dbs.SendMail(message,"Matériel",mail);
            JOptionPane.showMessageDialog(null, " La demande a été accepter , un email sera envoyé à l'utilisateur");

           }else{
                       JOptionPane.showMessageDialog(null, " Il ne reste que"+diff+"vous ne pouvez pas l'accepter");

           }

accepter.setVisible(false);
        refuser.setVisible(false);
    }

    @FXML
    private void refuserDemande(ActionEvent event) {
         dms.RefuserDemande(tableDemandeMateriel.getSelectionModel().getSelectedItem().getId());
        tableDemandeMateriel.setItems(dms.readDemandeMateriel());
                            etat.setText("refuser");
accepter.setVisible(false);
        refuser.setVisible(false);
    }

    @FXML
    private void rechercher(ActionEvent event) {
        int qte =0+ Integer.parseInt(qtekey.getText());
        String etat = ""+etatkey.getText();
           tableDemandeMateriel.setItems(dms.advancedSearchDemandeMateriel(qte, etat));
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader();
                materiel.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("ListDemandes.fxml"));
                loader.load();
                
                ListDemandesController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Refresh(ActionEvent event) {
        tableDemandeMateriel.setItems(dms.readDemandeMateriel());
        etatkey.setText("");
       qtekey.setText("");
    }
    
}
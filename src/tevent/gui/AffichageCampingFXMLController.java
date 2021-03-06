/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tevent.entities.Camping;
import tevent.services.CampingServices;
import tevent.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author maale
 */
public class AffichageCampingFXMLController implements Initializable {

    @FXML
    private TableColumn<Camping, String> idcol;
    @FXML
    private TableColumn<Camping, String> columlocid;
    @FXML
    private TableView<Camping> tabviewid;
   
    CampingServices cs = new CampingServices();
    @FXML
    private Button btnsuppid;
    @FXML
    private TextField nom;
    @FXML
    private TextField localisation;
    @FXML
    private Button modbt;
    @FXML
    private Button menu;
    @FXML
    private Button annuler;
    @FXML
    private Button Confirmer;
    @FXML
    private TableColumn<?, ?> idc;
    private Utilisateur user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*idcol.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
        columlocid.setCellValueFactory(new PropertyValueFactory<>("localisation"));
       // idc.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        ObservableList <Camping> CampingList = null;
        CampingList = cs.ReadCamping();
        tabviewid.setItems(CampingList);*/
        //tabviewid.getColumns().addAll(tabID,tabNb,tabVilleDep,tabVilleArr,tabHeureDep,tabHeureArr,tabEtat,tabJourLoc) ;
       afficher();
    }    

    @FXML
    private void supprimercamping(ActionEvent event) {
        cs.SupprimerCamping(tabviewid.getSelectionModel().getSelectedItem().getId());
        tabviewid.getItems().removeAll(tabviewid.getSelectionModel().getSelectedItem());
    }
    public void setUser(Utilisateur u) {
           user = u;
          // lbUser.setText(u.getNom()+" "+u.getPrenom());

       }
      private void afficher(){
        
        tabviewid.setItems(cs.ListAttestation());
        //colid.setCellValueFactory(new PropertyValueFactory("id_esc"));
        //colidAtt.setCellValueFactory(new PropertyValueFactory("id"));
        columlocid.setCellValueFactory(new PropertyValueFactory("localisation"));
        idcol.setCellValueFactory(new PropertyValueFactory("nomevent"));
        idc.setCellValueFactory(new PropertyValueFactory("id"));
        
        
    
    }
    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void retourmenu(ActionEvent event) throws IOException{
           FXMLLoader loader = new FXMLLoader();
                Confirmer.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("HomeFront.fxml"));
                loader.load();
                
                HomeFrontController auc = loader.getController();
                auc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
        
    }

    @FXML
    private void annulerUpdate(ActionEvent event) {
    }

    @FXML
    private void confirmerUpdate(ActionEvent event) {
    }
    
}

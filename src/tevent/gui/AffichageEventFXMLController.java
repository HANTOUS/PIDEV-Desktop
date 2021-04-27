/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tevent.entities.Event;
import tevent.services.EventServices;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author maale
 */
public class AffichageEventFXMLController implements Initializable {

    @FXML
    private TableView<Event> tabviewid;
    @FXML
    private TableColumn<Event, Integer> id;
    @FXML
    private TableColumn<Event, String> nom;
    @FXML
    private TableColumn<Event, Date> datdebut;
    @FXML
    private TableColumn<Event, Date> datfin;
    @FXML
    private TableColumn<Event, String> heurdeb;
    @FXML
    private TableColumn<Event, String> heurfin;
    @FXML
    private TableColumn<Event, String> lieu;
    @FXML
    private TableColumn<Event, Integer> nbrpart;
    @FXML
    private TableColumn<Event, String> type;
    @FXML
    private TableColumn<Event, String> dsc;
    @FXML
    private TableColumn<Event, Float> tarif;
    @FXML
    private TableColumn<Event, Float> lat;
    @FXML
    private TableColumn<Event, Float> lng;
    EventServices es=new EventServices();
    @FXML
    private AnchorPane suppbutt;
    @FXML
    private Button supbut;
    @FXML
    private TextField tfrechnom;
    @FXML
    private Button entrerid;
    @FXML
    private Button dashboard;
    @FXML
    private Button retour;

 private Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
          nom.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
          datdebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
          datfin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
          heurdeb.setCellValueFactory(new PropertyValueFactory<>("heuredebut"));
          heurfin.setCellValueFactory(new PropertyValueFactory<>("heurefin"));
          lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
          nbrpart.setCellValueFactory(new PropertyValueFactory<>("nbmaxparticipant"));
          type.setCellValueFactory(new PropertyValueFactory<>("type"));
          dsc.setCellValueFactory(new PropertyValueFactory<>("description"));
          tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
          lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
        lng.setCellValueFactory(new PropertyValueFactory<>("lng"));
        
        ObservableList <Event> EventList = null;
        EventList = es.ReadEvent();
        tabviewid.setItems(EventList);
    }    

    @FXML
    private void suppevent(ActionEvent event) {
        es.DeleteEvent(tabviewid.getSelectionModel().getSelectedItem().getId());
        tabviewid.getItems().removeAll(tabviewid.getSelectionModel().getSelectedItem());
        
    }

    @FXML
    private void rechercheeventparnom(ActionEvent event) {
        ObservableList <Event> EventList1 = null;
        EventList1=es.ReadEventByName(tfrechnom.getText());
        tabviewid.setItems(EventList1);
        
        
    } 

    public void setUser(Utilisateur u) {
        user = u;

    }

    @FXML
     private void gotodashboard(ActionEvent event)throws IOException {
                
               FXMLLoader loader = new FXMLLoader();
                tfrechnom.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("DashboardeventFXML.fxml"));
                loader.load();
                
               DashboardeventFXMLController dc = loader.getController();
                dc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();

    }

    @FXML
    private void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
                tfrechnom.getScene().getWindow().hide();
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

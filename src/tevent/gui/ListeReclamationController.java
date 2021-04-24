/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import tevent.entities.Reclamation;
import tevent.services.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author Salim
 */
public class ListeReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, Integer> idr;
    @FXML
    private TableColumn<Reclamation, Integer> usidr;
    @FXML
    private TableColumn<Reclamation, String> sujetr;
    @FXML
    private TableColumn<Reclamation, String> contenur;
    @FXML
    private TableColumn<Reclamation, Integer> etatr;
    @FXML
    private TableView<Reclamation> tabler;
    
    ReclamationServices rs = new ReclamationServices();
    @FXML
    private Button supp;
    @FXML
    private Button trait;
    @FXML
    private Button hom;
    @FXML
    private Button tra;
    @FXML
    private Button enc;
    @FXML
    private Button lo;
    @FXML
    private TextField idtx;
    @FXML
    private Button schid;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void suprec(ActionEvent event) {
        rs.delreclamation(tabler.getSelectionModel().getSelectedItem().getId());
        tabler.getItems().removeAll(tabler.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void traitr(ActionEvent event) {
                ObservableList<Reclamation> list1 = FXCollections.observableArrayList();
                
         rs.traiteReclamation(tabler.getSelectionModel().getSelectedItem().getId());
                         
         tabler.setItems(rs.readReclamation()); 
          try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeReclamation.fxml"));
            Stage window = (Stage) hom.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
           
          
    }

    @FXML
    private void home(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Stage window = (Stage) hom.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rectraite(ActionEvent event) {
    tabler.setItems(rs.reclamationTraité());    
    }

    @FXML
    private void encours(ActionEvent event) {
           tabler.setItems(rs.reclamationEncours());
        
    }

    @FXML
    private void load(ActionEvent event) {
        idr.setCellValueFactory(new PropertyValueFactory<>("id"));
        usidr.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        sujetr.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenur.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        etatr.setCellValueFactory(new PropertyValueFactory<>("etat"));

        
         ObservableList <Reclamation> RecList =  rs.readReclamation();
         
         tabler.setItems(RecList);
    }

    @FXML
    private void searchbyid(ActionEvent event) {
        int id = Integer.parseInt(idtx.getText());
        tabler.setItems(rs.readReclamationByID(id));
    }
    
    
    
}

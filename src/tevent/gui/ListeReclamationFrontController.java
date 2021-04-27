/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.Reclamation;
import tevent.services.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class ListeReclamationFrontController implements Initializable {

    ReclamationServices rs = new ReclamationServices();
    @FXML
    private Button btnRetour;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
    }
    
}

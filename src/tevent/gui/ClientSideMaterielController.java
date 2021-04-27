/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.Materiel;
import tevent.services.MaterielService;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class ClientSideMaterielController implements Initializable {

   ObservableList<Materiel> listMateriel= FXCollections.observableArrayList();
    
    @FXML
    private TableView<Materiel> tableListBusId;
    @FXML
    private Button btn_search;
    @FXML
    private TableColumn<Materiel, String> col_table_id;
    @FXML
    private TableColumn<Materiel, String> col_table_stock_id;
    @FXML
    private TableColumn<Materiel, String> col_table_qtereserve_id;
    @FXML
    private TableColumn<Materiel, String> col_table_prix_id;
    @FXML
    private TableColumn<Materiel, String> col_table_dispo_id;
    @FXML
    private TextField label_materiel;
    
    private MaterielService ms = new MaterielService();
    @FXML
    private TableColumn<Materiel, String> col_table_label_id;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_label_id.setCellValueFactory(new PropertyValueFactory<>("label"));
        col_table_stock_id.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_table_qtereserve_id.setCellValueFactory(new PropertyValueFactory<>("qteReserve"));
        col_table_prix_id.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_table_dispo_id.setCellValueFactory(new PropertyValueFactory<>("dsipo"));
        
        List<Materiel> lsMateriel = ms.listMateriel();
        
        for(int i=0;i<lsMateriel.size();i++)
            listMateriel.add(lsMateriel.get(i));
        tableListBusId.setItems(listMateriel);
    }    

    @FXML
    private void search(ActionEvent event) {
        tableListBusId.getItems().clear();
        List<Materiel>lsMateriel=ms.searchLabel(new Materiel(label_materiel.getText(), 0, 0, Float.NaN, Boolean.TRUE));
        col_table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_label_id.setCellValueFactory(new PropertyValueFactory<>("label"));
        col_table_stock_id.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_table_qtereserve_id.setCellValueFactory(new PropertyValueFactory<>("qteReserve"));
        col_table_prix_id.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_table_dispo_id.setCellValueFactory(new PropertyValueFactory<>("dsipo"));
        
        
        for(int i=0;i<lsMateriel.size();i++)
            listMateriel.add(lsMateriel.get(i));
        tableListBusId.setItems(listMateriel);
    }

    @FXML
    private void retour(ActionEvent event) {
    }
    
}

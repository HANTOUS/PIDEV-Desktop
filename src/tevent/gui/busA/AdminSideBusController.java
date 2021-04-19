/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui.busA;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.Bus;
import tevent.services.BusService;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class AdminSideBusController implements Initializable {

    
    BusService bs = new BusService();
    
    ObservableList<Bus> listBus= FXCollections.observableArrayList();
    
    Bus b = new Bus();
    
    @FXML
    private TableView<Bus> tableListBusId;
    @FXML
    private TableColumn<Bus, String> col_table_nbre_id= new TableColumn<>("id");
    @FXML
    private TableColumn<Bus, String> col_table_fabriquant_id= new TableColumn<>("fabriquant");
    @FXML
    private TableColumn<Bus, String> col_table_modele_id= new TableColumn<>("modele");
    @FXML
    private TableColumn<Bus, String> col_table_nbreplace_id= new TableColumn<>("nbPlace");
    @FXML
    private TableColumn<Bus, String> col_table_panne_id= new TableColumn<>("panne");
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
    private TextField lb_fab;
    private TextField lb_modele;
    @FXML
    private TextField lb_nbre_place;
    @FXML
    private ComboBox<Boolean> cb_panne;
    @FXML
    private ComboBox<String> cb_modele;
    
    
    public void loadBus(){
        List<Bus> lsBus = bs.listBus();
        
        col_table_nbre_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_fabriquant_id.setCellValueFactory(new PropertyValueFactory<>("fabriquant"));
        col_table_modele_id.setCellValueFactory(new PropertyValueFactory<>("modele"));
        col_table_nbreplace_id.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
        col_table_panne_id.setCellValueFactory(new PropertyValueFactory<>("panne"));
        
        ObservableList<Boolean> listPanne= FXCollections.observableArrayList(false,true);
        cb_panne.setItems(listPanne);
        
        for(int i=0;i<lsBus.size();i++)
            listBus.add(lsBus.get(i));
        tableListBusId.setItems(listBus);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb_id.setDisable(true);
        
        ObservableList<String> listModele= FXCollections.observableArrayList("C1","C2","C3");
        cb_modele.setItems(listModele);
        
        loadBus();
        
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        
        tableListBusId.setRowFactory( tv -> {
            TableRow<Bus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Bus rowData = row.getItem();
                    this.b= rowData;
                    lb_id.setText(b.getId()+"");
                    lb_fab.setText(b.getFabriquant());
                    cb_modele.setValue(b.getModele());
                    lb_nbre_place.setText(b.getNbPlace()+"");
                    cb_panne.setValue(b.getPanne());
                    
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
    private void ajoutBus(ActionEvent event) {
        b.setFabriquant(lb_fab.getText());
        b.setModele(cb_modele.getValue());
        b.setNbPlace(Integer.parseInt(lb_nbre_place.getText()));
        b.setPanne(cb_panne.getValue());
        bs.addBus(b);
        tableListBusId.getItems().clear();
        clear(event);
        loadBus();
    }
    
    @FXML
    private void deleteBus(ActionEvent event) {
        b.setId(Integer.parseInt(lb_id.getText()));
        
        tableListBusId.getItems().clear();
        
        bs.deleteBus(b);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        clear(event);
        loadBus();
    }
    
    @FXML
    private void updateBus(ActionEvent event) {
        b.setId(Integer.parseInt(lb_id.getText()));
        b.setFabriquant(lb_fab.getText());
        b.setModele(cb_modele.getValue());
        b.setNbPlace(Integer.parseInt(lb_nbre_place.getText()));
        b.setPanne(cb_panne.getValue());
        bs.updateBus(b);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        tableListBusId.getItems().clear();
        
        clear(event);
        loadBus();
    }
    
    @FXML
    private void clear(ActionEvent event) {
        lb_id.setText("");
        lb_fab.setText("");
        cb_modele.setValue("C1");
        lb_nbre_place.setText("");
        cb_panne.setValue(Boolean.FALSE);
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui.MaterielA;

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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui.chauffBusEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.ChauffBusEvent;
import tevent.entities.Materiel;
import tevent.entities.Utilisateur;
import tevent.services.ChauffBusEventService;



/**
 * FXML Controller class
 *
 * @author al199
 */
public class ChauffBusEventFXMLController implements Initializable {
    
    
    ChauffBusEventService ms = new ChauffBusEventService();
    
    ObservableList<ChauffBusEvent> listChauffBusEvent = FXCollections.observableArrayList();
    
    ChauffBusEvent m = new ChauffBusEvent();

    @FXML
    private TextField lbIdUser;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private TextField lbIdBus;
    @FXML
    private TextField lbHeureDep;
    @FXML
    private Button btn_clear;
    @FXML
    private TextField lbIdEvent;
    @FXML
    private TextField lbHeureArr;
    @FXML
    private Button btnretour;
    @FXML
    private Label lbUser;
    @FXML
    private TextField lbVilleDep;
    @FXML
    private TextField lbVilleArr;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_idUser;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_idBus;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_idEvent;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_hdep;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_harr;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_vdep;
    @FXML
    private TableColumn<ChauffBusEvent, String>  col_table_varr;
    @FXML
    private TableView<ChauffBusEvent> tableListMaterielId;
    
    
    public void loadDate(){
        List<ChauffBusEvent> lsChauffBusEvent = ms.listChauffBusEvent();
        
        col_table_idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col_table_idBus.setCellValueFactory(new PropertyValueFactory<>("idBus"));
        col_table_idEvent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        col_table_hdep.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
        col_table_harr.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
        col_table_vdep.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
        col_table_varr.setCellValueFactory(new PropertyValueFactory<>("villeArrive"));
        
        for(int i=0;i<lsChauffBusEvent.size();i++)
            listChauffBusEvent.add(lsChauffBusEvent.get(i));
        tableListMaterielId.setItems(listChauffBusEvent);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadDate();
        
        lbIdBus.setDisable(true);
        lbIdUser.setDisable(true);
        lbIdEvent.setDisable(true);
        
        btn_add.setVisible(false);
        btn_delete.setVisible(false);
        
        tableListMaterielId.setRowFactory( tv -> {
            TableRow<ChauffBusEvent> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ChauffBusEvent rowData = row.getItem();
                    this.m= rowData;
                    lbIdBus.setText(m.getIdBus()+"");
                    lbIdEvent.setText(m.getIdEvent()+"");
                    lbIdUser.setText(m.getIdUser()+"");
                    lbHeureArr.setText(m.getHeureArrive()+"");
                    lbHeureDep.setText(m.getHeureDepart()+"");
                    lbVilleArr.setText(m.getVilleArrive());
                    lbVilleDep.setText(m.getVilleDepart());
                    
                    btn_add.setDisable(true);
                    btn_update.setDisable(false);
                    btn_delete.setDisable(false);
                    System.out.println(rowData);
                }
            });
            return row ;
        });
    }    

    private Utilisateur user;

    public void setUser(Utilisateur u) {
        user = u;
        //lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    
    @FXML
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        m.setIdUser(Integer.parseInt(lbIdUser.getText()));
        m.setIdBus(Integer.parseInt(lbIdBus.getText()));
        m.setIdEvent(Integer.parseInt(lbIdEvent.getText()));
        m.setHeureArrive(Integer.parseInt(lbHeureArr.getText()));
        m.setHeureDepart(Integer.parseInt(lbHeureDep.getText()));
        m.setVilleArrive(lbVilleArr.getText());
        m.setVilleDepart(lbVilleDep.getText());
        ms.updateChauffBusEvent(m);
        tableListMaterielId.getItems().clear();
        clear(event);
        loadDate();
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
        lbIdBus.setText("");
        lbIdEvent.setText("");
        lbIdUser.setText("");
        lbHeureArr.setText("");
        lbHeureDep.setText("");
        lbVilleArr.setText("");
        lbVilleDep.setText("");
        btn_add.setDisable(false);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
    }

    @FXML
    private void retour(ActionEvent event) {
    }
    
}

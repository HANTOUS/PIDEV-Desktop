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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.Bus;
import tevent.services.BusService;

/**
 * FXML Controller class
 *
 * @author al199
 */
public class ClientSideBusController implements Initializable {

    BusService bs = new BusService();
    
    ObservableList<Bus> listBus= FXCollections.observableArrayList();
    
    @FXML
    private TableView<Bus> tableListBusId;
    @FXML
    private ComboBox<String> ComboBoxFabriquantId;
    @FXML
    private ComboBox<String> ComboBoxPanneId;
    @FXML
    private ComboBox<String> ComboBoxModeleId;
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
    private Button btn_search;
    @FXML
    private Button btnRetour;


    void selectedModele(ActionEvent event){
        String s= ComboBoxModeleId.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
    }
    void selectedFabriquant(ActionEvent event){
        String s= ComboBoxFabriquantId.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
    }
    void selectedPanne(ActionEvent event){
        String s= ComboBoxPanneId.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
    }
    
    /*void btn_search(){
        String s= ComboBoxModeleId.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
    }*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listModele= FXCollections.observableArrayList("all","C1","C2","C3");
        ComboBoxModeleId.setItems(listModele);
        ObservableList<String> listMarque= FXCollections.observableArrayList("all","Bmw","Volvo","Renauts","Mercedes Benz");
        ComboBoxFabriquantId.setItems(listMarque);
        ObservableList<String> listPanne= FXCollections.observableArrayList("all","Oui","Non");
        ComboBoxPanneId.setItems(listPanne);
        List<Bus>lsBus = bs.listBus();
        System.out.println(lsBus);
        
        col_table_nbre_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_fabriquant_id.setCellValueFactory(new PropertyValueFactory<>("fabriquant"));
        col_table_modele_id.setCellValueFactory(new PropertyValueFactory<>("modele"));
        col_table_nbreplace_id.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
        col_table_panne_id.setCellValueFactory(new PropertyValueFactory<>("panne"));
        
        for(int i=0;i<lsBus.size();i++)
            listBus.add(lsBus.get(i));
        tableListBusId.setItems(listBus);
        
        //ComboBoxFabriquantId.setOnAction(e -> System.out.println(ComboBoxFabriquantId.getValue()));
    }

    @FXML
    private void search(ActionEvent event) {
        Boolean panne=null;
        if(ComboBoxPanneId.getValue() == "Oui")
            panne=true;
        else if(ComboBoxPanneId.getValue() == "Non")
            panne=false;
        
        String modele="";
        if(ComboBoxModeleId.getValue() != "all" && ComboBoxModeleId.getValue() != null)
            modele=ComboBoxModeleId.getValue();
        
        String fabriquant="";
        if(ComboBoxFabriquantId.getValue() != "all" && ComboBoxFabriquantId.getValue() != null)
            fabriquant=ComboBoxFabriquantId.getValue();

        Bus b = new Bus(fabriquant, modele, 0, panne);
        System.out.println(b);
        
        tableListBusId.getItems().clear();
        
        List<Bus>lsBus = bs.searchWithMultiParams(b);
        System.out.println(lsBus);
        for(int i=0;i<lsBus.size();i++)
            listBus.add(lsBus.get(i));
        
        col_table_nbre_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_table_fabriquant_id.setCellValueFactory(new PropertyValueFactory<>("fabriquant"));
        col_table_modele_id.setCellValueFactory(new PropertyValueFactory<>("modele"));
        col_table_nbreplace_id.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
        col_table_panne_id.setCellValueFactory(new PropertyValueFactory<>("panne"));
        
        tableListBusId.setItems(listBus);
    }

    @FXML
    private void retour(ActionEvent event) {
    }
    
}

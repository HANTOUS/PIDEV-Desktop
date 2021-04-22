/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.services.DemandeChauffeurServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListdmdchauffeurController implements Initializable {

    @FXML
    private TableColumn<DemandeChauffeur, Integer> colID;
    @FXML
    private TableColumn<DemandeChauffeur, Integer> colNumPermis;
    @FXML
    private TableColumn<DemandeChauffeur, Date> colDatePermis;
    @FXML
    private TableColumn<DemandeChauffeur, Date> colDateExpiration;
    @FXML
    private TableColumn<DemandeChauffeur, String> colEtat;
    @FXML
    private TableView<DemandeChauffeur> tableDemandeChauffeur;
    
    @FXML
    private TextField numpermis;
    @FXML
    private DatePicker datepermis;
    @FXML
    private DatePicker dateexpiration;
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;
    DemandeChauffeurServices dcs =  new DemandeChauffeurServices();
    int id=0;
    @FXML
    private TextField numpermiskey;
    @FXML
    private TextField dateexpirationkey;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
               colNumPermis.setCellValueFactory(new PropertyValueFactory<>("num_permis"));
               colDatePermis.setCellValueFactory(new PropertyValueFactory<>("date_permis"));
               colDateExpiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
               colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
               
                ObservableList <DemandeChauffeur> DemandeChauffeurList = (ObservableList<DemandeChauffeur>) dcs.getDemandeByUser(1);
                tableDemandeChauffeur.setItems(DemandeChauffeurList);
                tableDemandeChauffeur.getColumns().addAll(colID,colNumPermis,colDatePermis,colDateExpiration,colEtat) ;

              
    }    

    @FXML
    private void confirmerUpdate(ActionEvent event) {
       id = ((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getId());

        int num_permis = Integer.parseInt(numpermis.getText());
        LocalDate date_permis = datepermis.getValue();
        LocalDate date_expiration = dateexpiration.getValue();
        String etat =tableDemandeChauffeur.getSelectionModel().getSelectedItem().getEtat() ;


        DemandeChauffeur DC = new DemandeChauffeur(1, num_permis, date_permis, date_expiration, etat);
        dcs.updateDemandeChauffeur(DC,id);

        tableDemandeChauffeur.setItems(dcs.getDemandeByUser(1));
        
        numpermis.setText("");
        datepermis.setValue(null);
        dateexpiration.setValue(null);
            
        confirmer.setVisible(false);
        annuler.setVisible(false);
    }

    @FXML
    private void annulerUpdate(ActionEvent event) {
        
        numpermis.setText("");
        datepermis.setValue(null);
        dateexpiration.setValue(null);
            
    }

    @FXML
    private void modifierDemande(ActionEvent event) {
        confirmer.setVisible(true);
        annuler.setVisible(true);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (tableDemandeChauffeur.getSelectionModel().getSelectedItem() != null) {

            id = ((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getId());

            numpermis.setText(String.valueOf((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getNum_permis()));
            datepermis.setValue(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_permis()));
            dateexpiration.setValue(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_expiration()));
           

    }
    }


    @FXML
    private void rechercher(ActionEvent event) {
        
        tableDemandeChauffeur.setItems(dcs.advancedSearchDemandeChauffeur(0, LocalDate.of(2030,11,19)));

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tevent.entities.DemandeBus;
import tevent.entities.Festival;
import static tevent.gui.CrudFestivalController.ids;
import tevent.services.DemandeBusServices;
import tevent.services.FestivalServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListdmdbusController implements Initializable {

    @FXML
    private TableColumn<DemandeBus, Integer> tabID;
    @FXML
    private TableColumn<DemandeBus, Integer> tabNb;
    @FXML
    private TableColumn<DemandeBus, String> tabVilleDep;
    @FXML
    private TableColumn<DemandeBus, String> tabVilleArr;
    @FXML
    private TableColumn<DemandeBus, String> tabHeureDep;
    @FXML
    private TableColumn<DemandeBus, String> tabHeureArr;
    @FXML
    private TableColumn<DemandeBus, String> tabEtat;
    @FXML
    private TableColumn<DemandeBus, Date> tabJourLoc;
    @FXML
    private TableView<DemandeBus> tableDemandeBus;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnupdate;

    
    @FXML
    private TextField nbparticipants;
    @FXML
    private ComboBox villedepart;
    @FXML
    private ComboBox villearrivee;
    @FXML
    private DatePicker jourloc;
    @FXML
    private TextField heuredepart;
    @FXML
    private TextField heurearrivee;
    @FXML
    private Button annuler;
    @FXML
    private Button confirmer;
    
    DemandeBusServices dbs = new DemandeBusServices();
            int id = 0;
    @FXML
    private TextField recherchekey;
    @FXML
    private TextField recherchenb;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listville = FXCollections.observableArrayList("Ariana", "Béja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kébili", "Kef", "Mahdia", "Manouba", "Mednine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
        villedepart.setItems(listville);
        villearrivee.setItems(listville);

        confirmer.setVisible(false);
        annuler.setVisible(false);

        tabID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabNb.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        tabVilleDep.setCellValueFactory(new PropertyValueFactory<>("ville_depart"));
        tabVilleArr.setCellValueFactory(new PropertyValueFactory<>("ville_arrivee"));
        tabHeureDep.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        tabHeureArr.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tabJourLoc.setCellValueFactory(new PropertyValueFactory<>("jour_location"));

        ObservableList<DemandeBus> DemandeBusList = dbs.getDemandeByUser(1);
        tableDemandeBus.setItems(DemandeBusList);
        //tableDemandeBus.getColumns().addAll(tabID,tabNb,tabVilleDep,tabVilleArr,tabHeureDep,tabHeureArr,tabEtat,tabJourLoc) ;
    }

    @FXML
    private void supprimerDemande(ActionEvent event) {
        dbs.deleteDemandeBus(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
        tableDemandeBus.getItems().removeAll(tableDemandeBus.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void updateDemande(ActionEvent event) {
        confirmer.setVisible(true);
        annuler.setVisible(true);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (tableDemandeBus.getSelectionModel().getSelectedItem() != null) {

            id = ((tableDemandeBus.getSelectionModel().getSelectedItem()).getId());

            nbparticipants.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getNb_participant()));
            villedepart.setValue(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getVille_depart()));
            villearrivee.setValue(((tableDemandeBus.getSelectionModel().getSelectedItem()).getVille_arrivee()));
            heurearrivee.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getHeure_depart()));
            heuredepart.setText(String.valueOf((tableDemandeBus.getSelectionModel().getSelectedItem()).getHeure_arrivee()));
            jourloc.setValue(((tableDemandeBus.getSelectionModel().getSelectedItem()).getJour_location()));

        }
    }

    @FXML
    private void confirmUpdate(ActionEvent event) {
                    id = ((tableDemandeBus.getSelectionModel().getSelectedItem()).getId());

        int nb_participant = Integer.parseInt(nbparticipants.getText());
        String ville_depart = villedepart.getSelectionModel().getSelectedItem().toString();
        String ville_arrivee = villearrivee.getSelectionModel().getSelectedItem().toString();
        String heure_depart = heuredepart.getText();
        String heure_arrivee = heurearrivee.getText();
        String etat =tableDemandeBus.getSelectionModel().getSelectedItem().getEtat() ;
        LocalDate jour_location = jourloc.getValue();
        

        DemandeBus DB = new DemandeBus(1, nb_participant, ville_depart, ville_arrivee, heure_depart, heure_arrivee, etat, jour_location);
        dbs.updateDemandeBus(DB,id);

        tableDemandeBus.setItems(dbs.getDemandeByUser(1));
        
        nbparticipants.setText("");
            villedepart.setValue("");
            villearrivee.setValue("");
            heurearrivee.setText("");
            heuredepart.setText("");
            jourloc.setValue(null);
            
        confirmer.setVisible(false);
        annuler.setVisible(false);
    }

    @FXML
    private void annulerUpdate(ActionEvent event) {
        nbparticipants.setText("");
            villedepart.setValue("");
            villearrivee.setValue("");
            heurearrivee.setText("");
            heuredepart.setText("");
            jourloc.setValue(null);
    }

    @FXML
    private void refuserDemande(ActionEvent event) {
        dbs.RefuserDemande(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
        tableDemandeBus.setItems(dbs.getDemandeByUser(1));
    }

    @FXML
    private void accepterDemande(ActionEvent event) {
         dbs.AccepterDemande(tableDemandeBus.getSelectionModel().getSelectedItem().getId());
        tableDemandeBus.setItems(dbs.getDemandeByUser(1));
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        String searchkey = recherchekey.getText() ;
        int searchnb = Integer.parseInt(recherchenb.getText());

                tableDemandeBus.setItems(dbs.advancedSearchDemandeBus(searchnb, searchkey, searchkey));


        
        
    }
}

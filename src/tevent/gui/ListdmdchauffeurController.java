/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.entities.DemandeMateriel;
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
    @FXML
    private Button btnpdf;
    @FXML
    private Button retourbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            btnpdf.setVisible(false);
             confirmer.setVisible(false);
        annuler.setVisible(false);
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
        if(isValidate()) {
            
       id = ((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getId());

        int num_permis = Integer.parseInt(numpermis.getText());
        LocalDate date_permis = datepermis.getValue();
        LocalDate date_expiration = dateexpiration.getValue();
        String etat =tableDemandeChauffeur.getSelectionModel().getSelectedItem().getEtat() ;


        DemandeChauffeur DC = new DemandeChauffeur(1, num_permis, date_permis, date_expiration, etat);
        dcs.updateDemandeChauffeur(DC,id);
                    }

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
                confirmer.setVisible(false);
        annuler.setVisible(false);
    }

    @FXML
    private void modifierDemande(ActionEvent event) {
        confirmer.setVisible(true);
        annuler.setVisible(true);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(     "accepter".equals(tableDemandeChauffeur.getSelectionModel().getSelectedItem().getEtat())){
    System.out.println("Tarek");
    btnpdf.setVisible(true);
}else{
        btnpdf.setVisible(false);

}
        if(tableDemandeChauffeur.getSelectionModel().getSelectedItem() != null & (tableDemandeChauffeur.getSelectionModel().getSelectedItem().getEtat().equals("encours")))  {
  numpermis.setDisable(false);
            datepermis.setDisable(false);
            dateexpiration.setDisable(false);
            confirmer.setDisable(false);
            annuler.setDisable(false);
            id = ((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getId());

            numpermis.setText(String.valueOf((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getNum_permis()));
            datepermis.setValue(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_permis()));
            dateexpiration.setValue(((tableDemandeChauffeur.getSelectionModel().getSelectedItem()).getDate_expiration()));
            confirmer.setVisible(true);
        annuler.setVisible(true);

    }else{
              numpermis.setDisable(true);
            datepermis.setDisable(true);
            dateexpiration.setDisable(true);
            confirmer.setDisable(true);
            annuler.setDisable(true);
            
        }
    }

     private void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Attention");
        alert.setContentText(message);
        alert.show();
    }
    //Remplissage des champs obligatoire

    private boolean isValidate() {
        boolean isValid = true;
        String message = "";

        //System.out.println("gui.EventController.ajouter()" + comboType.getValue());
        if (numpermis.getText().isEmpty() ) {
            isValid = false;
            message = "Veuillez entrer un numero de permis valid";
        }else if (numpermis.getText().length() != 8) {
          isValid = false;
          message = "Le numero de permis doit etre exactement 8 chiffres";
        } else if (datepermis.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date permis";
        } else if (dateexpiration.getValue() == null) {
            isValid = false;
            message = "Veuillez selectionnez une date d'expiration ";

        }else if (dateexpiration.getValue().isBefore(datepermis.getValue())) {
            isValid = false;
            message = "La date expiration doit être supérieur à la date permis ";
        }

        if (!isValid) {
            showDialog(message);
        }

        return isValid;

    }

    @FXML
    private void rechercher(ActionEvent event) {
        
        tableDemandeChauffeur.setItems(dcs.advancedSearchDemandeChauffeur(0, LocalDate.of(2030,11,19)));

    }

    @FXML
    private void getPDF(ActionEvent event) throws DocumentException, BadElementException, IOException {
                           DemandeChauffeur dc = tableDemandeChauffeur.getSelectionModel().getSelectedItem();           

                       dcs.PDF(dc, "Bellalouna", "Tarek");

    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("Home.fxml"));
            
            Scene homePage_scene=new Scene(homePage);
            
            Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            app_stage.setScene(homePage_scene);
            
            app_stage.show();
            Stage stage = (Stage) retourbtn.getScene().getWindow(); 
           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
}

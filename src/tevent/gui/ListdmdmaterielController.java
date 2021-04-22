/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import tevent.entities.DemandeMateriel;
import tevent.entities.DemandeMateriel;
import tevent.services.DemandeMaterielServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListdmdmaterielController implements Initializable {

    @FXML
    private TableView<DemandeMateriel> tableDemandeMateriel;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colID;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colMateriel;
    @FXML
    private TableColumn<DemandeMateriel, Integer> colQuantite;
    @FXML
    private TableColumn<DemandeMateriel, Date> colDateDebut;
    @FXML
    private TableColumn<DemandeMateriel, Date> colDateFin;
    @FXML
    private TableColumn<DemandeMateriel, String> colEtat;
    private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField quantite;
    @FXML
    private ComboBox materiel;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Button annuler;
    @FXML
    private Button confirmer;
    /**
     * Initializes the controller class.
     */
    int id=0 ;
        DemandeMaterielServices dms= new DemandeMaterielServices();
    @FXML
    private TextField etatkey;
    @FXML
    private TextField qtekey;

        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> Materiel = FXCollections.observableArrayList(dms.MaterielName());
               materiel.setItems(Materiel);
        
        
        ObservableList <DemandeMateriel> DemandeMaterielList = (ObservableList<DemandeMateriel>) dms.readDemandeMateriel();
                List<String> label = new ArrayList<>();
               String req ="select label from materiel where id=?";
                               for (DemandeMateriel d : DemandeMaterielList) {
System.out.println(d.getMateriel_id());
               try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, d.getMateriel_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                label.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               
            
        }
        
         colID.setCellValueFactory(new PropertyValueFactory<>("id"));
               

         colMateriel.setCellValueFactory(new PropertyValueFactory<>("materiel_id"));
               colQuantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
               colDateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
               colDateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
               colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
               
               
       
                
                               System.out.println(label);
                tableDemandeMateriel.setItems(DemandeMaterielList);
                tableDemandeMateriel.getColumns().addAll(colID,colMateriel,colQuantite,colDateDebut,colDateFin,colEtat) ;
    }    

    @FXML
    private void annulerUpdate(ActionEvent event) {
         quantite.setText("");
            materiel.setValue("");
            datedebut.setValue(null);
            datefin.setValue(null);
            
    }
    @FXML
    private void modifierDemande(ActionEvent event) {
        String label ="";
        String req ="select label from materiel where id=?";
                              
               try {

                   
            int idmat = (tableDemandeMateriel.getSelectionModel().getSelectedItem()).getMateriel_id();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idmat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                label=rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
         confirmer.setVisible(true);
        annuler.setVisible(true);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (tableDemandeMateriel.getSelectionModel().getSelectedItem() != null) {

            id = ((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getId());

            quantite.setText(String.valueOf((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getQte()));
            materiel.setValue(String.valueOf((label)));
            datedebut.setValue(((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getDate_debut()));
            datefin.setValue(((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getDate_fin()));
            

        }
    }

    @FXML
    private void confirmerUpdate(ActionEvent event) {
         id = ((tableDemandeMateriel.getSelectionModel().getSelectedItem()).getId());

        int qte = Integer.parseInt(quantite.getText());
        String mat = materiel.getSelectionModel().getSelectedItem().toString();
        int idm=0;
        String etat =tableDemandeMateriel.getSelectionModel().getSelectedItem().getEtat() ;
        LocalDate date_debut = datedebut.getValue();
        LocalDate date_fin = datefin.getValue();
        String req="SELECT id from materiel where label=?";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, mat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idm = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        DemandeMateriel DM = new DemandeMateriel(1,idm,"6","encours",date_debut,date_fin);

        dms.updateDemandeMateriel(DM,qte,id);

        tableDemandeMateriel.setItems(dms.getDemandeByUser(1));
        
        quantite.setText("");
            materiel.setValue("");
            datedebut.setValue(null);
            datefin.setValue(null);
            
        confirmer.setVisible(false);
        annuler.setVisible(false);
    }

    @FXML
    private void rechercher(ActionEvent event) {
        
        int qte =0+ Integer.parseInt(qtekey.getText());
        String etat = ""+etatkey.getText();
           tableDemandeMateriel.setItems(dms.advancedSearchDemandeMateriel(qte, etat));
 
    }
}

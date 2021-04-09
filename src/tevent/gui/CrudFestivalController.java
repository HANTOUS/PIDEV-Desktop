/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import tevent.entities.Festival;
import tevent.services.FestivalServices;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class CrudFestivalController implements Initializable {

    @FXML
    private TextField artiste;
    @FXML
    private TextField nb_inv;
    @FXML
    private TextField pathpicture;
    @FXML
    private Button uploadpic;
    @FXML
    private ComboBox<String> typefest;
    @FXML
    private Button ajouterfest;
    @FXML
    private TableColumn<Festival,String> typefest1;
    @FXML
    private TableColumn<Festival,String> artiste1;
    @FXML
    private TableColumn<Festival,Integer> nb_inv1;
    @FXML
    private TableColumn<Festival, String> picc;
    @FXML
    private TextField nomevent;
    @FXML
    private TableColumn<Festival,Float> tarif;
    @FXML
    private TableView<Festival> tableaff;
    @FXML
    private TableColumn<Festival,String> nomfest;
    @FXML
    private TextField tarif1;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    @FXML
    private Button confirmer;
    public static  int ids;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typefest.getItems().addAll("concert","theatre","comedie");
        nomfest.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
        typefest1.setCellValueFactory(new PropertyValueFactory<>("type_fest"));
        artiste1.setCellValueFactory(new PropertyValueFactory<>("artist"));
        nb_inv1.setCellValueFactory(new PropertyValueFactory<>("nb_invit"));
        picc.setCellValueFactory(new PropertyValueFactory<>("picture"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         id.setVisible(false);
        
        ObservableList <Festival> L =  FXCollections.observableArrayList();
      
        FestivalServices f = new FestivalServices ();
        try {
            tableaff.setItems(f.AfficherFestival());
        } catch (SQLException ex) {
            Logger.getLogger(CrudFestivalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
    }    

    @FXML
    private void AjouterFestival(ActionEvent event) {
        Festival F =new Festival();
        FestivalServices  sf = new  FestivalServices();
        F.setId(1);
        F.setType_fest(typefest.getValue());
        F.setArtist(artiste.getText());
        F.setPicture(pathpicture.getText());
        F.setNb_invit(Integer.parseInt(nb_inv.getText()));
        F.setNomevent(nomevent.getText());
        F.setTarif(Float.parseFloat(tarif1.getText()));
        sf.AjouterFestival(F);
        
        
       ObservableList <Festival> L =  FXCollections.observableArrayList();
      
        FestivalServices f = new FestivalServices ();
        try {
            tableaff.setItems(f.AfficherFestival());
        } catch (SQLException ex) {
            Logger.getLogger(CrudFestivalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void uploadpicture(ActionEvent event) {
         FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicture.setText(file.getAbsolutePath());
                  
           
                }
    }

    @FXML
    private void supprimerfest(ActionEvent event) {
        
        
        FestivalServices sf = new FestivalServices(); 
        
        sf.SupprimerFestival(tableaff.getSelectionModel().getSelectedItem().getId());
        tableaff.getItems().removeAll(tableaff.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void modifierfest(ActionEvent event) {
         ObservableList <Festival> L =  FXCollections.observableArrayList();
        FestivalServices sf = new FestivalServices();
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        
        if (tableaff.getSelectionModel().getSelectedItem() != null)
        {
            confirmer.setVisible(true);
            annuler.setVisible(true);
            ids=((tableaff.getSelectionModel().getSelectedItem()).getId());
            
           nomevent.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getNomevent()));
            tarif1.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getTarif()));
            typefest.setValue(((tableaff.getSelectionModel().getSelectedItem()).getType_fest()));
            artiste.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getArtist()));
           nb_inv.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getNb_invit()));
           pathpicture.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getPicture()));
        }
    }

    @FXML
    private void confirmermodif(ActionEvent event) {
        Festival F =new Festival();
        FestivalServices  sf = new  FestivalServices();
        F.setId(ids);
        F.setType_fest(typefest.getValue());
        F.setArtist(artiste.getText());
        F.setPicture(pathpicture.getText());
        F.setNb_invit(Integer.parseInt(nb_inv.getText()));
        F.setNomevent(nomevent.getText());
        F.setTarif(Float.parseFloat(tarif1.getText()));
        sf.ModifierFestival(F);
        
        
        
       ObservableList <Festival> L =  FXCollections.observableArrayList();
      
        FestivalServices f = new FestivalServices ();
        try {
            tableaff.setItems(f.AfficherFestival());
        } catch (SQLException ex) {
            Logger.getLogger(CrudFestivalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
        
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import academiccalendar.ui.main.FXMLDocumentController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.F;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tevent.entities.Sponsor;
import tevent.entities.Sponsor;
import tevent.entities.Sponsor;
import tevent.entities.Sponsor;
import tevent.services.SponsorServices;


/**
 * FXML Controller class
 *
 * @author skand
 */
public class CrudSponsorController implements Initializable {

    @FXML
    private JFXTextField nomspons;
    @FXML
    private JFXTextField grantype;
    @FXML
    private JFXTextField actdomain;
    @FXML
    private JFXTextField pathpicc;
    @FXML
    private TableView<Sponsor> tableaff;
    @FXML
    private Button deletesponsor;
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;
    @FXML
    private TableColumn<Sponsor, String> namesp1;
    @FXML
    private TableColumn<Sponsor, String> grant1;
    @FXML
    private TableColumn<Sponsor, String> actd1;
    @FXML
    private TableColumn<Sponsor, String> picc1;
     public static  int ids;
    @FXML
    private VBox vboxmodifier;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        namesp1.setCellValueFactory(new PropertyValueFactory<>("nom_sponsor"));
        grant1.setCellValueFactory(new PropertyValueFactory<>("type_subvention"));
        actd1.setCellValueFactory(new PropertyValueFactory<>("domaine_acivite"));
        picc1.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        ObservableList <Sponsor> L =  FXCollections.observableArrayList();
      
        SponsorServices s = new SponsorServices ();
        try {
            tableaff.setItems(s.AfficherSponsor());
        } catch (SQLException ex) {
            Logger.getLogger(CrudSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
    }    


    @FXML
    private void deletesponsor(ActionEvent event) {
         SponsorServices sf = new SponsorServices(); 
        
        sf.SupprimerSponsor(tableaff.getSelectionModel().getSelectedItem().getId());
        tableaff.getItems().removeAll(tableaff.getSelectionModel().getSelectedItem());
    }

    
    @FXML
    private void confirmermodif(ActionEvent event) {
         Sponsor F =new Sponsor();
        SponsorServices  ss = new  SponsorServices();
        F.setId(ids);
        F.setNom_sponsor(nomspons.getText());
        F.setDomaine_acivite(actdomain.getText());
        F.setImage(pathpicc.getText());
        F.setType_subvention(grantype.getText());
      
        ss.ModifierSponsor(F);
        
        
        
       ObservableList <Sponsor> L =  FXCollections.observableArrayList();
      
        SponsorServices f = new SponsorServices ();
        try {
            tableaff.setItems(f.AfficherSponsor());
        } catch (SQLException ex) {
            Logger.getLogger(CrudSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
        
    }
    @FXML
    private void uploadpicture(ActionEvent event) {
        FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicc.setText(file.getAbsolutePath());
                  
           
                }
    }

    @FXML
    private void addSpon(ActionEvent event) {
          try {
               // Load root layout from fxml file.
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("addsponsor.fxml"));
               AnchorPane rootLayout = (AnchorPane) loader.load();
               Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.initModality(Modality.APPLICATION_MODAL); 

               // Pass main controller reference to view
               AddsponsorController eventController = loader.getController();
               eventController.setMainController(this);
               
               // Show the scene containing the root layout.
               
        namesp1.setCellValueFactory(new PropertyValueFactory<>("nom_sponsor"));
        grant1.setCellValueFactory(new PropertyValueFactory<>("type_subvention"));
        actd1.setCellValueFactory(new PropertyValueFactory<>("domaine_acivite"));
        picc1.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        ObservableList <Sponsor> L =  FXCollections.observableArrayList();
      
        SponsorServices s = new SponsorServices ();
        try {
            tableaff.setItems(s.AfficherSponsor());
        } catch (SQLException ex) {
            Logger.getLogger(CrudSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
               Scene scene = new Scene(rootLayout);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
    }

    @FXML
    private void modifysponsor(ActionEvent event) {
        ObservableList <Sponsor> L =  FXCollections.observableArrayList();
        SponsorServices sf = new SponsorServices();
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        vboxmodifier.setVisible(true);
        if (tableaff.getSelectionModel().getSelectedItem() != null)
        {
            confirmer.setVisible(true);
            annuler.setVisible(true);
            ids=((tableaff.getSelectionModel().getSelectedItem()).getId());
            
           nomspons.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getNom_sponsor()));
           grantype.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getType_subvention()));
           actdomain.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getDomaine_acivite()));
           pathpicc.setText(String.valueOf((tableaff.getSelectionModel().getSelectedItem()).getImage()));
        }

    }

    @FXML
    private void REFRESH(ActionEvent event) {
         namesp1.setCellValueFactory(new PropertyValueFactory<>("nom_sponsor"));
        grant1.setCellValueFactory(new PropertyValueFactory<>("type_subvention"));
        actd1.setCellValueFactory(new PropertyValueFactory<>("domaine_acivite"));
        picc1.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        ObservableList <Sponsor> L =  FXCollections.observableArrayList();
      
        SponsorServices s = new SponsorServices ();
        try {
            tableaff.setItems(s.AfficherSponsor());
        } catch (SQLException ex) {
            Logger.getLogger(CrudSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        confirmer.setVisible(false);
            annuler.setVisible(false);
    }


    }


   


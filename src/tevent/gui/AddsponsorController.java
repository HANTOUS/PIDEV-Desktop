/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tevent.entities.Event;
import tevent.entities.Festival;
import tevent.entities.Sponsor;
import tevent.services.EventServices;
import tevent.services.FestivalServices;
import tevent.services.SponsorServices;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AddsponsorController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox header;
    @FXML
    private Label topLabel;
    @FXML
    private JFXComboBox<String> typefest;
    @FXML
    private JFXTextField nomsponsor;
    @FXML
    private JFXTextField grant;
    @FXML
    private JFXTextField actd;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXTextField pathpicture;
    @FXML
    private JFXButton uploadpic;
        private CrudSponsorController mainController ;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FestivalServices sf = new FestivalServices();
        try {
            for(int i =0;i<sf.readFestEv().size();i++)
            typefest.getItems().add(sf.readFestEv().get(i));
        } catch (SQLException ex) {
            Logger.getLogger(AddsponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    



    @FXML
    private void save(ActionEvent event) throws SQLException {
            Sponsor S =new Sponsor();
       
        SponsorServices  ss = new  SponsorServices();
        FestivalServices sf = new FestivalServices();
        
        
        
        
        S.setDomaine_acivite(actd.getText());
        S.setImage(pathpicture.getText());
        S.setNom_sponsor(nomsponsor.getText());
        S.setType_subvention(grant.getText());
        
        S.setFestival_id(sf.getID(typefest.getValue()));
        ss.AjouterSponsor(S);
        
        
        
       
       
          
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }



    @FXML
    private void uploadpicture(ActionEvent event) {
         FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicture.setText(file.getAbsolutePath());
                  
           
                }
    }
     public void setMainController(CrudSponsorController mainController) {
        
        this.mainController = mainController ;
    }

    

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}


//Packages and Imports

package academiccalendar.ui.editfestival;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import academiccalendar.data.model.Model;
import academiccalendar.ui.main.FXMLDocumentController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tevent.entities.Event;
import tevent.entities.Festival;
import tevent.services.EventServices;
import tevent.services.FestivalServices;
import tevent.tools.Mask;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class EditfestivalController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox header;
    @FXML
    private Label topLabel;
    @FXML
    private JFXTextField nomevent;
    @FXML
    private JFXComboBox<String> typefest;
    @FXML
    private JFXDatePicker dateED;
    @FXML
    private JFXDatePicker dateEF;
    @FXML
    private JFXTimePicker timeED;
    @FXML
    private JFXTimePicker timeEF;
    @FXML
    private JFXTextField nbP;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXTextField tarif1;
    @FXML
    private JFXTextField artiste;
    @FXML
    private JFXTextField nb_inv;
    @FXML
    private JFXTextField pathpicture;
    @FXML
    private JFXButton uploadpic;
  //  private CrudFestivalController mainController ;
    private FXMLDocumentController mainController ;
    @FXML
    private JFXButton delete;
    
    
    
    

    @FXML
    private void exit(MouseEvent event) {
        
        
    }

    @FXML
    private void save(MouseEvent event) throws SQLException {
        
          Festival F =new Festival();
        FestivalServices  sf = new  FestivalServices();
        
        
      F.setId(Model.getInstance().event_term_id);
        F.setType_fest(typefest.getValue());
        F.setArtist(artiste.getText());
        F.setPicture(pathpicture.getText());
        F.setNb_invit(Integer.parseInt(nb_inv.getText()));
        F.setNomevent(nomevent.getText());
        F.setTarif(Float.parseFloat(tarif1.getText()));
        F.setDescription(desc.getText());
        
        
        F.setDatedebut(Date.valueOf(dateED.getValue()));
        F.setDatefin(Date.valueOf(dateEF.getValue()));
        F.setHeuredebut(String.valueOf(timeED.getValue()));
        F.setHeurefin(String.valueOf(timeEF.getValue()));
        F.setNbmaxparticipant(Integer.parseInt(nbP.getText()));
        sf.ModifierFestival(F);
       
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
 mainController.repaintView();
        
                

        
        
        

        
    }

    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void uploadpicture(ActionEvent event) {
         FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicture.setText(file.getAbsolutePath());
                  
           
                }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        Mask.noSymbolsAndNumbers(nomevent);
        
        setMainController(mainController);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
        
    typefest.getItems().addAll("concert","theatre","comedie");
    
         System.out.println("****"+Model.getInstance().event_term_id);
// Get selected day, month, and year and autofill date selection
        int day = Model.getInstance().event_day;
        int month = Model.getInstance().event_month + 1;
        int year = Model.getInstance().event_year;
        int termID = Model.getInstance().event_term_id;
        String descript = Model.getInstance().event_subject;
       

       
        
        Festival w = new Festival();
        FestivalServices wService = new FestivalServices(); 
        
        
        System.out.println("id "+termID);
        try {
            w = wService.find(termID);
        } catch (SQLException ex) {
            Logger.getLogger(EditfestivalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(w);
       // dateED.setValue(LocalDate.of(year, month, day));
        dateED.setValue(LocalDate.parse(w.getDatedebut().toString(),formatter));
        dateEF.setValue(LocalDate.parse(w.getDatefin().toString(),formatter));
        nomevent.setText(w.getNomevent());
        typefest.setValue(w.getType_fest());
        timeED.setValue(LocalTime.parse(w.getHeuredebut()));
        timeEF.setValue(LocalTime.parse(w.getHeurefin()));
        artiste.setText(w.getArtist());
        nb_inv.setText(String.valueOf(w.getNb_invit()));
        pathpicture.setText(w.getPicture());
        nbP.setText(String.valueOf(w.getNbmaxparticipant()));
        tarif1.setText(String.valueOf(w.getTarif()));
        desc.setText(w.getDescription());
    }
      
    
    
   public void setMainController(FXMLDocumentController mainController) {
        
        this.mainController = mainController ;
    }

    @FXML
    private void cancelaction(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void delete(ActionEvent event) {
        
        
        Festival w = new Festival();
        FestivalServices wService = new FestivalServices(); 
        
        wService.SupprimerFestival(Model.getInstance().event_term_id);
        
        
        
        
    }


 
}


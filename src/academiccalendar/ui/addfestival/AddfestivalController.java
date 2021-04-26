
//Packages and Imports

package academiccalendar.ui.addfestival;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import academiccalendar.ui.main.FXMLDocumentController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalTime;
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
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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
public class AddfestivalController implements Initializable {

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
    public String fullpath="" ;
    @FXML
    private void exit(MouseEvent event) {
    }

    @FXML
    private void save(MouseEvent event) throws SQLException {
         Festival F =new Festival();
         Event E = new Event();
        FestivalServices  sf = new  FestivalServices();
        EventServices se = new EventServices();
        
        
        
        F.setType_fest(typefest.getValue());
        F.setArtist(artiste.getText());
        F.setPicture(pathpicture.getText());
        F.setNb_invit(Integer.parseInt(nb_inv.getText()));
        
        
        E.setNomevent(nomevent.getText());
        E.setTarif(Float.parseFloat(tarif1.getText()));
        E.setDatedebut(Date.valueOf(dateED.getValue()));
        E.setDatefin(Date.valueOf(dateEF.getValue()));
        E.setHeuredebut(timeED.getValue().toString());
        E.setHeurefin(timeEF.getValue().toString());
        E.setNbmaxparticipant(Integer.parseInt(nbP.getText()));
        E.setDescription(desc.getText());
        
        
       
        
        se.AddEvent(E);
        F.setId(EventServices.InsertedId);
        sf.AjouterFestival(F);
        uploadtp(pathpicture.getText(), fullpath);
          
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
       
        
                

        
        
        

        
    }

    @FXML
    private void cancel(MouseEvent event) {
    }




//API upload download 
    
    
    
    @FXML
    private void uploadpicture(ActionEvent event) {
         FileChooser fil_chooser = new FileChooser();
        
        File file = fil_chooser.showOpenDialog(null); 
  
                if (file != null) { 
                      pathpicture.setText(file.getName());
                      fullpath=file.getAbsolutePath();
                  
           
                }
    }

   
    
   public void  uploadtp(String path,String fullpath)
    {
        String server = "127.0.0.1";
        int port = 21;
        String user = "skander";
        String pass = "123";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(fullpath);
 
            String firstRemoteFile = path;
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
 
            // APPROACH #2: uploads second file using an OutputStream

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    typefest.getItems().addAll("concert","theatre","comedie");
    Mask.noSymbolsAndNumbers(nomevent);
        Mask.noSymbolsAndNumbers(artiste);
        Mask.noLetters(nbP);
        Mask.noLetters(nb_inv);
        Mask.noLetters(tarif1);
    }
      
    
    
   public void setMainController(FXMLDocumentController mainController) {
        
        this.mainController = mainController ;
    }

    @FXML
    private void cancelaction(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


 
}


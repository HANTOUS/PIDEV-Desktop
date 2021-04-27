/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

//import com.teamdev.jxmaps.MouseEvent;
//import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL; 
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tevent.entities.Randonnee;
import tevent.services.RandonneeServices;
/**
 * FXML Controller class
 *
 * @author maale
 */
public class AffichageRandonneeFXMLController implements Initializable {

    @FXML
    private TableView<Randonnee> tabviewid;
    
    @FXML
    private TableColumn<Randonnee, String> idcol;
    @FXML
    private TableColumn<Randonnee, String> columlocid;
     RandonneeServices rs=new RandonneeServices();
    @FXML
    private Button btnsuppid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftyperand;
    @FXML
    private Button modifierbt;
    @FXML
    private Button menu;
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;
    @FXML
    private TableColumn<?, ?> idc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       /* idcol.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
        columlocid.setCellValueFactory(new PropertyValueFactory<>("typerand"));
        
        ObservableList <Randonnee> RandonneeList = null;
        RandonneeList = rs.ReadRandonnee();
        tabviewid.setItems(RandonneeList);*/
        
        afficher();
    }    

    @FXML
    private void supprandonnee(ActionEvent event) {
        rs.SupprimerRandonnee(tabviewid.getSelectionModel().getSelectedItem().getId());
        tabviewid.getItems().removeAll(tabviewid.getSelectionModel().getSelectedItem());
    }
    private void select(MouseEvent event) {
        Randonnee r= new Randonnee();
      //  r = tabviewid.getSelectionModel().getSelectedItem();
        //Format f = new SimpleDateFormat("yyyy-MM-dd");
        //String date = f.format(new java.util.Date());

           // tfnom.setText(tabviewid.getSelectionModel().getSelectedItem().getNomevent());
           // tftyperand.setText(tabviewid.getSelectionModel().getSelectedItem().getTyperand());

            //tfidService.setText(String.valueOf(r.getIdservice()));
            //tfidUser.setText(String.valueOf(r.getIduser()));
            /*ndelete.setDisable(false)
            btnupdate.setDisable(false);*/
    }
    private void afficher(){
        
        tabviewid.setItems(rs.ListAttestation());
        //colid.setCellValueFactory(new PropertyValueFactory("id_esc"));
        //colidAtt.setCellValueFactory(new PropertyValueFactory("id"));
        columlocid.setCellValueFactory(new PropertyValueFactory("typerand"));
        idcol.setCellValueFactory(new PropertyValueFactory("nomevent"));
        idc.setCellValueFactory(new PropertyValueFactory("id"));
        
        
    
    }

    @FXML
    private void modifrand(ActionEvent event) {

        tfnom.setText(tabviewid.getSelectionModel().getSelectedItem().getNomevent());
        tftyperand.setText(tabviewid.getSelectionModel().getSelectedItem().getTyperand());
       
        
    }

    @FXML
    private void retourmenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuGestioneventFXML.fxml"));
            Stage window = (Stage) menu.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void confirmerUpdate(ActionEvent event) {
        int id =tabviewid.getSelectionModel().getSelectedItem().getId();
        String type=tftyperand.getText();
        String nom=tfnom.getText();
         Randonnee r= new Randonnee(type,id,nom);
        r.setTyperand(type);
        r.setNomevent(nom);
        rs.ModifierRandonnee(r, id);
        tabviewid.setItems(rs.ReadRandonnee());
        
        
    }

    @FXML
    private void annulerUpdate(ActionEvent event) {
         tftyperand.setText("");
        tfnom.setText("");
        
    }

}

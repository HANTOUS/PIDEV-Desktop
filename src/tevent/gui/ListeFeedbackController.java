/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import tevent.entities.Feedback;
import tevent.services.FeedbackServices;

/**
 * FXML Controller class
 *
 * @author Salim
 */
public class ListeFeedbackController implements Initializable {

    @FXML
    private TableColumn<Feedback, Integer> idf;
    @FXML
    private TableColumn<Feedback, Integer> partf;
    @FXML
    private TableColumn<Feedback, Integer> notef;
    @FXML
    private TableColumn<Feedback, Integer> remarquef;
    
        FeedbackServices fs = new FeedbackServices();
    @FXML
    private TableView<Feedback> tabf;
    @FXML
    private Button sup;
    @FXML
    private Button hom;
    @FXML
    private Button lo;
    @FXML
    private TextField idt;
    @FXML
    private Button seaid;
    @FXML
    private TextField notet;
    @FXML
    private Button sean;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void delfeedback(ActionEvent event) {
        fs.delFeddback(tabf.getSelectionModel().getSelectedItem().getId());
        tabf.getItems().removeAll(tabf.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void home(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Stage window = (Stage) hom.getScene().getWindow();
            window.setScene(new Scene(root));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void load(ActionEvent event) {
                
        idf.setCellValueFactory(new PropertyValueFactory<>("id"));
        partf.setCellValueFactory(new PropertyValueFactory<>("participf_id"));
        notef.setCellValueFactory(new PropertyValueFactory<>("note"));
        remarquef.setCellValueFactory(new PropertyValueFactory<>("remarque"));
        
        ObservableList <Feedback> flist = fs.readFeedback();
        
        tabf.setItems(flist);

    }

    @FXML
    private void searchbyid(ActionEvent event) {
            int id = Integer.parseInt(idt.getText());
        tabf.setItems(fs.readFeedbackByID(id));
    }

    @FXML
    private void searchbn(ActionEvent event) {
        int nt = Integer.parseInt(notet.getText());
        tabf.setItems(fs.readFeedbackByNote(nt));
    }
    
}

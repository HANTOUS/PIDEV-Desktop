/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tevent.tools.DataSource;
import tevent.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author maale
 */
public class DashboardeventFXMLController implements Initializable {
 private Utilisateur user;
    @FXML
    private PieChart PieChart;
    private ObservableList<PieChart.Data> piechartdata;
    private Connection cnx=DataSource.getInstance().getCnx();
    @FXML
    private Button menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }  //To change body of generated methods, choose Tools | Templates.
        PieChart.setData(piechartdata);
    }    
    public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "SELECT type,COUNT(*) AS toBeUsed FROM event GROUP BY type";
                  

              PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("type"), rs.getInt("toBeUsed")));
            
        }
    }
 public void setUser(Utilisateur u) {
        user = u;
       // lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    @FXML
    private void retourmenuadmin(ActionEvent event) throws IOException{
        
 FXMLLoader loader = new FXMLLoader();
                PieChart.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AffichageEventFXML.fxml"));
                loader.load();
                
                AffichageEventFXMLController dc = loader.getController();
                dc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
}

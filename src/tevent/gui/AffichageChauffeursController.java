/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tevent.entities.Chauffeur;
import tevent.entities.Utilisateur;
import tevent.services.ChauffeurServices;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.sql.Date;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author hanto
 */
public class AffichageChauffeursController implements Initializable {
   @FXML
    private TableColumn<Chauffeur, String> colId;
    @FXML
    private TableColumn<Chauffeur, String> colNom;
    @FXML
    private TableColumn<Chauffeur, String> colPrenom;
    @FXML
    private TableColumn<Chauffeur, String> colCin;
    @FXML
    private TableColumn<Chauffeur, String> colEmail;
    @FXML
    private TableColumn<Chauffeur, String> colDate;
    @FXML
    private TableColumn<Chauffeur, String> colDateP;
    @FXML
    private TableColumn<Chauffeur, String> colDateE;
    @FXML
    private TableColumn<Chauffeur, String> colDateD;
    @FXML
    private TableColumn<Chauffeur, String> colPermis;
    @FXML
    private TableColumn<Chauffeur, String> colRole;
    @FXML
    private TableColumn<Chauffeur, String> colCompte;
    @FXML
    private TableColumn<Chauffeur, String> colAction;
    @FXML
    private TableView<Chauffeur> UserTable;
    @FXML
    private Label lbUser;
 private Utilisateur user;
    ObservableList<Chauffeur> chaffeursL = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       
        }  
    }    

    private void loadData() throws SQLException {
        refresh();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("num_permis"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_permis"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        if(new PropertyValueFactory<>("activation_token") == null)
            colCompte.setCellValueFactory(new PropertyValueFactory<>("Ativé"));
        else
            colCompte.setCellValueFactory(new PropertyValueFactory<>("Désactivé"));

        Callback<TableColumn<Chauffeur, String>, TableCell<Chauffeur, String>> cellFoctory = (TableColumn<Chauffeur, String> param) -> {
            // make cell containing buttons
            final TableCell<Chauffeur, String> cell = new TableCell<Chauffeur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Chauffeur user = UserTable.getSelectionModel().getSelectedItem();
                            ChauffeurServices us = new ChauffeurServices();
                            us.supprimerChauffeur(user.getId());
                            try{
                                refresh();}
                            catch(SQLException e){
                                System.out.println(e.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Chauffeur chauffeur = UserTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifierChauffeur.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                               System.out.println(ex.getMessage());
                            }
                            
                            ModifierChauffeurController mod = loader.getController();
                            mod.setFields(chauffeur.getId(),chauffeur.getNom(),chauffeur.getPrenom(),chauffeur.getCin(),chauffeur.getEmail(),(Date)chauffeur.getDateNaissance(),
                            (Date)chauffeur.getDatePermis(),(Date)chauffeur.getDateExpiration(),chauffeur.getNumPermis());
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         colAction.setCellFactory(cellFoctory);
         UserTable.setItems(chaffeursL);
    }
    
    @FXML
    private void refresh() throws SQLException {
        chaffeursL.clear();
        ChauffeurServices us = new ChauffeurServices();
        List<Chauffeur> chauffeurs = us.afficherToutChauffeur();
        for (Chauffeur chauffeur : chauffeurs) {
            chaffeursL.add(chauffeur);

        }

        UserTable.setItems(chaffeursL);
    }
    @FXML
    private void getAddView(MouseEvent event) throws IOException {
               
        Parent parent = FXMLLoader.load(getClass().getResource("AjoutUser.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

     public void setUser(Utilisateur u) {
        user = u;
       // lbUser.setText(u.getNom()+" "+u.getPrenom());

    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
            lbUser.getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader();
                lbUser.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("UserChauf.fxml"));
                loader.load();
                
                UserChaufController dc = loader.getController();
                dc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
}

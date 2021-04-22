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
import tevent.entities.Utilisateur;
import tevent.services.UtilisateurServices;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.sql.Date;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author hanto
 */
public class AffichageUsersController implements Initializable {

    @FXML
    private TableColumn<Utilisateur, String> colId;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colCin;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, String> colDate;
    @FXML
    private TableColumn<Utilisateur, String> colRole;
    @FXML
    private TableColumn<Utilisateur, String> colCompte;
    @FXML
    private TableColumn<Utilisateur, String> colAction;
    @FXML
    private TableView<Utilisateur> UserTable;
    @FXML
    private Label lbUser;

    ObservableList<Utilisateur> usersL = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }

    private void loadData() throws SQLException {
        refresh();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        if(new PropertyValueFactory<>("activation_token") == null)
            colCompte.setCellValueFactory(new PropertyValueFactory<>("Ativé"));
        else
            colCompte.setCellValueFactory(new PropertyValueFactory<>("Désactivé"));

        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
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
                            Utilisateur user = UserTable.getSelectionModel().getSelectedItem();
                            UtilisateurServices us = new UtilisateurServices();
                            us.supprimerUtilisateur(user.getId());
                            try{
                                refresh();}
                            catch(SQLException e){
                                System.out.println(e.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Utilisateur user = UserTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifierUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                               System.out.println(ex.getMessage());
                            }
                            
                            ModifierUserController mod = loader.getController();
                            mod.setFields(user.getId(),user.getNom(),user.getPrenom(),user.getCin(),user.getEmail(),(Date)user.getDateNaissance());
                            
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
         UserTable.setItems(usersL);
    }
    
    @FXML
    private void refresh() throws SQLException {
        usersL.clear();
        UtilisateurServices us = new UtilisateurServices();
        List<Utilisateur> users = us.afficherToutUtilisateur();
        for (Utilisateur user : users) {
            usersL.add(user);

        }

        UserTable.setItems(usersL);
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

    public void setUser(String nom,String prenom) {
        lbUser.setText(nom+" "+prenom);

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

}

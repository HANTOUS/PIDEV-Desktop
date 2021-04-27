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
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import tevent.services.SecurityServices;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


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

    private Utilisateur user;

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
                        FontAwesomeIconView blockIcon = new FontAwesomeIconView(FontAwesomeIcon.BAN);

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
                        blockIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#cc3300;"
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
                        
                        blockIcon.setOnMouseClicked((MouseEvent event) -> {
                            Utilisateur user = UserTable.getSelectionModel().getSelectedItem();
                            SecurityServices ss = new SecurityServices();
                            ss.desactivation(user.getId());
                            envoyerEmail(user);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Utilisateur a étè banni");
                            alert.showAndWait();
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon,blockIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(blockIcon, new Insets(2, 3, 0, 2));

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

    public void setUser(Utilisateur u) {
        user = u;
        lbUser.setText(u.getNom()+" "+u.getPrenom());

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
                loader.setLocation(getClass().getResource("Dashboard.fxml"));
                loader.load();
                
                DashboardController dc = loader.getController();
                dc.setUser(user);
                Parent root = loader.getRoot();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    
    public static void envoyerEmail(Utilisateur u)  {
        SecurityServices us = new SecurityServices();
         String username = "tevents98@gmail.com";
         String password = "TEvents2021";
        //Random r = new Random();
        //String codem = UUID.randomUUID().toString();
        
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
        });
        try {
        
        // Etape 2 : Création de l'objet Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
        message.setSubject("Banne");
        message.setText("Bonjour "+ u.getPrenom() +",  Vous avez ete banni par l'administrateur. veuillez copier le code ci-dessous pour activer: "+u.getActivation_token());
        // Etape 3 : Envoyer le message
        Transport.send(message);
        //us.forgetPassword(u.getId(),codem);
        System.out.println("Message_envoye");
        } catch (MessagingException e) {
        throw new RuntimeException(e);
        } }

}

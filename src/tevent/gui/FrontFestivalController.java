/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import tevent.entities.Festival;
import tevent.services.FestivalServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.StageStyle;
import tevent.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author skand
 */
public class FrontFestivalController implements Initializable {
  
    public static ObservableList<Festival> list ;
private Utilisateur user;

   // public static JFXComboBox<Integer> filtrePourc = new JFXComboBox<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FestivalServices pService = new FestivalServices();

        try {
            list = pService.AfficherFestival();
   
        } catch (SQLException ex) {
        
        }

    }    
    public void setUser(Utilisateur u) {
        user = u;

       // lbUser.setText(u.getNom()+" "+u.getPrenom());

    }
    public static AnchorPane createPromotion(Festival j,Festival b)  {
        
        FestivalServices pService = new FestivalServices();
        AnchorPane card2 = new AnchorPane();
        
        JFXButton btnBack = new JFXButton("Back");
        Pane promo = new Pane();
        Label t = new Label("Festival");
       //Label tfiltre = new Label("Filtrer :");
        
        //for (int i = 0; i<9;i++)
            //filtrePourc.getItems().add(i*10);
        
        String promolb =  "-fx-underline : true;"
                +"-fx-font-weight : bold;"
                +"-fx-text-fill: red;"
                +"-fx-font-size : 18;";
        
        String stylePromo = "-fx-text-fill: white;"
                + "-fx-font-size : 48;";
        promo.setPrefSize(1250, 139);
        
        t.setStyle(stylePromo);
        t.setLayoutX(100);
        t.setLayoutY(50);
        //tfiltre.setLayoutX(1000);
       // tfiltre.setLayoutY(150);
       // filtrePourc.setLayoutX(1100);
        //filtrePourc.setLayoutY(150);
        promo.setStyle("-fx-background-color :  #090031;");
        promo.getChildren().addAll(btnBack,t);
        card2.setPrefSize(1250, 650);
        card2.setStyle("-fx-background-color :  #FAFAFA;");
        card2.getChildren().add(promo);
        int xcard= 0;
        String styleCard = "-fx-background-raduis: 3;"
                +"-fx-background-color :  #D3D3D3;"
                + "-fx-border-raduis : 5;"
                + "-fx-border-color: #090031"; 
        String styleLabel = "-fx-text-fill: #738796;"
                + "-fx-font-weight : bold;"
                + "-fx-font-size : 15;"; 
        String styleTitre = "-fx-text-fill: #738796;"
                + "-fx-font-weight : italic;"
                + "-fx-font-size : 18;";
        String styleButton = "-fx-text-fill: white;"
                + "-fx-background-color : #fd5056;"
                + "-fx-font-size : 14;";
        String stylefooter = "-fx-border-raduis : 0 0 3 3;"
                + "-fx-background-color: #090031; "
                + "-fx-border-width : 1 0 0 0;"
                + "-fx-border-color: #C3C3C3"; 
        String styleBack = "-fx-text-fill: white;"
                + "-fx-font-weight : bold;"
                + "-fx-font-size : 18;"; 
        btnBack.setStyle(styleBack);
      
        for(int i=0;i<2;i++){
            
            AnchorPane card = new AnchorPane();
            
            VBox rootlb = new VBox();
            VBox root = new VBox();
            HBox rootf = new HBox();
            HBox test = new HBox();
            ImageView pour  = new ImageView();
            
            
            
            Pane footer = new Pane();
            JFXButton feed = new JFXButton("Feedback");
            feed.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifierUser.fxml"));
                            //j.getId()
                            try {
                                loader.load();
                            } catch (IOException ex) {
                               System.out.println(ex.getMessage());
                            }
                            
                           /* FeedbackController mod = loader.getController();
                            mod.setUser(user,j.getId());*/
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                }
            });

            Rating rating = new Rating(5);

            Label l1 = new Label(),l2 = new Label(),l3 = new Label(),l4 = new Label(),l5 = new Label(),l6 = new Label(),l7 = new Label();

            l1.setStyle(styleTitre);
            l2.setStyle(styleLabel);
            l3.setStyle(styleLabel);
            l4.setStyle(styleLabel);
            l5.setStyle(styleLabel);
            l6.setStyle(styleLabel);
            l7.setStyle(promolb);
            
            
            
           /* GlyphsDude.setIcon(l4, FontAwesomeIcon.CALENDAR_ALT);
            GlyphsDude.setIcon(l3, FontAwesomeIcon.CALENDAR_ALT);
            GlyphsDude.setIcon(l6, FontAwesomeIcon.MONEY);
            GlyphsDude.setIcon(l7, FontAwesomeIcon.MONEY);*/
           
           
           
            feed.setStyle(styleButton);
            footer.setStyle(stylefooter);
            card.setStyle(styleCard);
            
            
            footer.setPrefSize(555,50);        
            test.setSpacing(300); 
            
            rootlb.setSpacing(20);
            
            card.setPrefSize(555,300);
            card.setLayoutX(30+xcard);
            card.setLayoutY(40);        
       
                
                try {
                 InputStream stream = new FileInputStream("E:\\Users\\skand\\Desktop\\upload\\"+j.getPicture());
               
                 Image image = new Image(stream);
                 pour.setImage(image);
                 InputStream stream1;
                 
                 
             if (j.getId()==pService.sponsor(j.getId()))
            {
                         
                 stream1 = new FileInputStream(pService.findimage(j.getId()));
                 
                 Image image1 = new Image(stream1);
                 ImageView im = new ImageView(image1);
                feed.setGraphic(im);
                 
            }

            } catch (FileNotFoundException ex) {
              
            }
       
        
                l1.setText("NOM : "+j.getNomevent());
                l2.setText("ARTISTE :  "+j.getArtist());
                l3.setText("Date Debut : "+j.getDatedebut());
                l4.setText("Date Fin :  "+j.getDatefin());
                l5.setText("TARIF :  "+j.getTarif()+ " DT");
                
              
                //float resultat = j.getCout_f() * (100-j.getPrix())/100;
          
            //l6.setText(" "+j.getCout_f()+" ");
           // l7.setText(" "+resultat);
           

           

                
                
            
            if(i==1){
                try {
                 InputStream stream = new FileInputStream("E:\\Users\\skand\\Desktop\\upload\\"+b.getPicture());
                 Image image = new Image(stream) ;
                 pour.setImage(image);
                InputStream stream1;
                if (b.getId()==pService.sponsor(b.getId()))
                
                 
                 {
                stream1 = new FileInputStream(pService.findimage(b.getId()));
                Image image1 = new Image(stream1) ;
                ImageView im = new ImageView(image1);
               feed.setGraphic(im);
                 }
                
            } catch (FileNotFoundException ex) {
              
            }
                
                 l1.setText("NOM : "+b.getNomevent());
                l2.setText("ARTISTE  :  "+b.getArtist());
                l3.setText("Date Debut : "+b.getDatedebut());
                l4.setText("Date Fin :  "+b.getDatefin());
                  l5.setText("TARIF :  "+b.getTarif() + " DT");
               
                  //  float resultat2 = b.getCout_f() * (100-b.getPrix())/100;
                    //l6.setText(" "+b.getCout_f()+" ");
                  //  l7.setText(" " +resultat2);
                    //rootf.getChildren().addAll(l6,l7);
               
              /*
                    try {
                 InputStream stream = new FileInputStream("C:\\Users\\dell\\Desktop\\Game\\pourcentage\\"+Math.round(b.getPrix())+".png");
                 Image image = new Image(stream);
                 pour.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
       */
                
            }
           // rootf.getChildren().addAll(l6,l7);
            
        
        

            

            test.getChildren().addAll(rating,feed);
            
            footer.getChildren().add(test);
            test.setLayoutY(7);
            
            rootlb.getChildren().addAll(l1,l2,l3,l4,l5,rootf); 
            //rootlb.setLayoutX(40);
            //root.getChildren().add(rootlb);
            
           
            
            card.getChildren().addAll(rootlb,pour,footer);
            rootlb.setLayoutX(40);            
            footer.setLayoutY(250);
            footer.setLayoutX(2);
            pour.setLayoutX(339);
            pour.setLayoutY(44);
            
            
           

            card.setLayoutY(200);
            
            card2.getChildren().add(card);
            xcard+= 609;
      
      
        }
                     
        return card2;
    }
    
    public  VBox page() throws SQLException{
        
       FestivalServices pService = new FestivalServices();
        Pagination pagination = new Pagination();
        
       /* EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    if(filtrePourc.getValue()==0){
                        list = pService.readAllPromotion();
                        pagination.setPageCount(list.size());
                        pagination.setCurrentPageIndex(0);
                        pagination.setMaxPageIndicatorCount(3);
   
                    }else{
                        list = pService.filrePromotionPourcentage(filtrePourc.getValue());
                        pagination.setPageCount(list.size());
                        pagination.setCurrentPageIndex(0);
                        pagination.setMaxPageIndicatorCount(3);
                        System.out.println("list: "+list);
                        pagination.setPageFactory((pageIndex) -> {
                        if(list.size() == 1){
                            return new VBox(createPromotion(list.get(pageIndex),list.get(pageIndex)));

                        }
                        return new VBox(createPromotion(list.get(pageIndex),list.get(pageIndex+1)));
                    });
                        
                    }
                    
                   
                } catch (SQLException ex) {
                    Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(filtrePourc.getValue());
                
            }
            
        };
        */
        System.out.println("list: "+list);
        list = pService.AfficherFestival();
        //filtrePourc.setOnAction(event);
        pagination.setPageFactory((pageIndex) -> {

            return new VBox(createPromotion(list.get(pageIndex),list.get(pageIndex+1)));
        });
        VBox vBox = new VBox(pagination);
        
       
       return vBox;
    }

}

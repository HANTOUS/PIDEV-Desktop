/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tevent.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.io.File;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
/**
 * FXML Controller class
 *
 * @author hanto
 */
public class QRCodeController implements Initializable {
    @FXML
    private ImageView qr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setImg(String img){
        System.out.println("icii15");
        qr.setImage(new Image(new File(img).toURI().toString()));
    }

}

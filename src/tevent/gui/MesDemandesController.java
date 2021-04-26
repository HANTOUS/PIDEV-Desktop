/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.entities.DemandeMateriel;
import tevent.services.DemandeBusServices;
import tevent.services.DemandeChauffeurServices;
import tevent.services.DemandeMaterielServices;
import tevent.tools.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MesDemandesController implements Initializable {

    private Connection cnx = DataSource.getInstance().getCnx();
    DemandeBusServices dbs = new DemandeBusServices();
    DemandeChauffeurServices dcs = new DemandeChauffeurServices();
    DemandeMaterielServices dms = new DemandeMaterielServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<DemandeBus> list = dbs.getDemandeByUser(1);
        ObservableList<DemandeChauffeur> list1 = dcs.getDemandeByUser(1);
        ObservableList<DemandeMateriel> list2 = dms.getDemandeByUser(1);
        List<String> EtatBus = new ArrayList<>();
        for (DemandeBus db : list) {
            System.out.println(db.getEtat());

        }
        List<String> EtatChauffeur = new ArrayList<>();
        for (DemandeChauffeur dc: list1) {
            System.out.println(dc.getEtat());

        }
        List<String> EtatMateriel = new ArrayList<>();
        for (DemandeMateriel dm : list2) {
            System.out.println(dm.getEtat());

        }

    }

}

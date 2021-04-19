/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.tests;

import tevent.entities.Utilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import tevent.entities.Bus;
import tevent.entities.ChauffBusEvent;
import tevent.entities.Chauffeur;
import tevent.entities.Materiel;
import tevent.services.BusService;
import tevent.services.ChauffBusEventService;
import tevent.services.ChauffeurServices;
import tevent.services.MaterielService;
import tevent.services.UtilisateurServices;

/**
 *
 * @author hanto
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        
        ChauffBusEventService cbe= new ChauffBusEventService();
        
        BusService bs = new BusService();
        
        
        System.out.println(cbe.listChauffBusEvent());
        ChauffBusEvent cb = new ChauffBusEvent(2, 2, 1, 12, 14, "marsa", "tunis");
        /*cbe.addChauffBusEvent(cb);
        
        cb.setHeureDepart(13);
        cb.setHeureArrive(20);
        
        cbe.updateChauffBusEvent(cb);*/
        
        //cbe.deleteChauffBusEvent(cb);
        
        /*Bus b1 = new Bus("Renauts", "C3", 1, Boolean.TRUE);
        
        Bus b2 = new Bus("Renauts X1", "C3", 0, Boolean.FALSE);
        
        Bus b4 = new Bus("Renauts X1", "C3", 20, Boolean.FALSE);
        
        Bus b3 = new Bus("Renauts X1", "C3", 27, Boolean.FALSE);
        
        b3 = bs.addBus(b3);
        
        b4 = bs.addBus(b4);
        
        b2 = bs.addBus(b2);
        b1 = bs.addBus(b1);
        
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b2);
        System.out.println(b1);
        
        b3.setFabriquant("Volvo");
        
        bs.updateBus(b3);
        
        bs.deleteBus(b4);*/
        
        /*
        Bus bSearch = new Bus("b","",0,null);
        
        System.out.println(bs.searchWithMultiParams(bSearch));
        */
        
        
        MaterielService ms = new MaterielService();
        
        System.out.println(ms.searchLabel(new Materiel("v", 0, 0, Float.NaN, Boolean.TRUE)));
        
        
        System.out.println(ms.filterMat(new Materiel("v", -1, 0, Float.parseFloat("-1"), Boolean.TRUE)));
        
    }

}

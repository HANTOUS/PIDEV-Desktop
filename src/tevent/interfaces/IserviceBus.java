/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.interfaces;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import tevent.entities.Bus;

/**
 *
 * @author al199
 */
public interface IserviceBus {
    public ObservableList <Bus> AfficherBus()throws SQLException;
}

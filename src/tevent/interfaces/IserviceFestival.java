/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.interfaces;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import tevent.entities.Festival;

/**
 *
 * @author hanto
 */
public interface IserviceFestival {

    public void AjouterFestival(Festival F);

    public ObservableList<Festival> AfficherFestival() throws SQLException;

    public void SupprimerFestival(int id);

    public void ModifierFestival(Festival F);
}

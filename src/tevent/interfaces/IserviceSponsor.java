/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.interfaces;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import tevent.entities.Sponsor;



/**
 *
 * @author hanto
 */
public interface IserviceSponsor {
    
    public void AjouterSponsor(Sponsor S);
    public ObservableList <Sponsor > AfficherSponsor()throws SQLException;
    public void SupprimerSponsor(int id );
    public void ModifierSponsor(Sponsor  S);
    
}

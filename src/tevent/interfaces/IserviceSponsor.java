/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.interfaces;

<<<<<<< HEAD

=======
>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96
import java.sql.SQLException;
import javafx.collections.ObservableList;
import tevent.entities.Sponsor;

<<<<<<< HEAD

=======
>>>>>>> 1c0734a5f9d465baffc8611ee9dcd7af8dcdbe96

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

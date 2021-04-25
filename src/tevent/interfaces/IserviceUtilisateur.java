/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.interfaces;

import java.sql.SQLException;
import java.util.List;
import tevent.entities.Utilisateur;

/**
 *
 * @author hanto
 */
public interface IserviceUtilisateur {
    
    public void ajouterUtilisateur(Utilisateur user);

    public List<Utilisateur> afficherToutUtilisateur() throws SQLException;

    public Utilisateur afficherUtilisateur(int id) throws SQLException ;

    public void supprimerUtilisateur(int id);

    public void modifierUtilisateur(Utilisateur user);
}

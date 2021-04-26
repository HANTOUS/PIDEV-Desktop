/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import tevent.entities.DemandeMateriel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PrinterJob;
import javax.swing.JOptionPane;
import tevent.entities.DemandeChauffeur;
import tevent.entities.DemandeMateriel;
import tevent.entities.DemandeMateriel;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeMaterielServices {
 int stock = 0;
        int qte_reserve = 0;
        int diff = 0;
    private Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<DemandeMateriel> readDemandeMateriel() {
        //  List<DemandeMateriel> list = new ArrayList<>() ;
        ObservableList<DemandeMateriel> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_materiel";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeMateriel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<DemandeMateriel> getDemandeByUser(int id) {
        // List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeMateriel> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_materiel where utilisateur_id='" + id + "'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(new DemandeMateriel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public String addDemandeMateriel(DemandeMateriel c, int quantite) {
        String req = "INSERT INTO demande_materiel (utilisateur_id, materiel_id, qte, etat, date_debut, date_fin) VALUES ('" + c.getUtilisateur_id() + "','" + c.getMateriel_id() + "','" + quantite + "','" + c.getEtat() + "','" + c.getDate_debut() + "','" + c.getDate_fin() + "') ";
        String req1 = "SELECT stock , qte_reserve FROM materiel where id=?";
       
        try {
            PreparedStatement ps = cnx.prepareStatement(req1);
            ps.setInt(1, c.getMateriel_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt(1);
                qte_reserve = rs.getInt(2);
                diff = stock - qte_reserve;
                System.out.println(stock+""+qte_reserve+""+diff);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {

            Statement st = cnx.createStatement();
            if (diff > quantite) {
                st.executeUpdate(req);
                return ("DemandeMateriel Ajoutée !");
            } else {
                return ("il ne reste que" + "  " + diff);
            }
            /*
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeMateriel Ajoutée !");    
             */

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ("demande ajouté");
    }

    public int PrixTotale(int idMateriel, int qte) {
        int prixtot = 0;
        String req1 = "SELECT prix FROM materiel WHERE id IN (SELECT materiel_id FROM demande_materiel WHERE materiel_id=?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req1);
            ps.setInt(1, idMateriel);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                prixtot = rs.getInt(1) * qte;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return prixtot;
    }

    public void deleteDemandeMateriel(int id) {
        String req = "DELETE FROM demande_materiel WHERE  id=" + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeMateriel Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateDemandeMateriel(DemandeMateriel c, int qte, int id) {
        /*String req = "UPDATE  demande_materiel SET utilisateur_id='" + c.getUtilisateur_id() + "',materiel_id='" + c.getMateriel_id() + "',qte='" + c.getQte() + "' etat='" + c.getEtat() + "' date_debut='" + c.getDate_debut() + "' date_fin='" + c.getDate_fin() + "' WHERE id='" + c.getId() + "' ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeMateriel modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        String req = "update demande_materiel set utilisateur_id=?, materiel_id=? ,qte= ? , date_debut= ?, date_fin= ?, etat=? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, c.getUtilisateur_id());
            ps.setInt(2, c.getMateriel_id());
            ps.setInt(3, qte);
            ps.setDate(4, java.sql.Date.valueOf(c.getDate_debut()));
            ps.setDate(5, java.sql.Date.valueOf(c.getDate_fin()));
            ps.setString(6, c.getEtat());
            ps.setInt(7, id);

            ps.executeUpdate();
            System.out.println("Demande Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void AccepterDemande(int id, int idmat) {
        List<DemandeMateriel> list = new ArrayList<>();
        String req = "update demande_materiel set  etat= ? where id = ?";
        String req1 = "update materiel SET qte_reserve=(select qte_reserve from materiel where id=?)+(select qte from demande_materiel where id=?) where id=? ";

        try {

            PreparedStatement ps1 = cnx.prepareStatement(req1);
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "accepter");
            ps.setInt(2, id);
            ps.executeUpdate();
            ps1.setInt(1, idmat);
            ps1.setInt(2, id);
            ps1.setInt(3, idmat);
            ps1.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void RefuserDemande(int id) {
        String req = "update demande_materiel set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "refuser");
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<String> MaterielName() {
        String req1 = "SELECT label FROM materiel ";
        List<String> list = new ArrayList<>();

        try {
            PreparedStatement ps = cnx.prepareStatement(req1);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<DemandeMateriel> advancedSearchDemandeMateriel(int qte, String etat) {
        // List<DemandeMateriel> list = new ArrayList<>() ;
        ObservableList<DemandeMateriel> list = FXCollections.observableArrayList();

        try {
            String req = "select * from demande_materiel where qte= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, qte);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeMateriel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select * from demande_materiel where etat= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, etat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeMateriel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }
    
     public void PDF(DemandeMateriel db , String Materiel , String Nom , String Prenom) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        String file_name = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV-Desktop\\src\\demande.pdf";
        Document document = new Document();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        document.add(new Paragraph("Agence TEvent", FontFactory.getFont(FontFactory.TIMES)));
        document.add(new Paragraph("Demande Matériel", FontFactory.getFont(FontFactory.TIMES)));
        document.add(new Paragraph("-----------------------------------------------------------------"));
         Image image1 = Image.getInstance("C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV-Desktop\\src\\Logo-Off.png");
    //Fixed Positioning
    image1.setAbsolutePosition(495f,745f);
    //Scale to new height and new width of image
    image1.scaleAbsolute(100, 100);
    //Add to document
    document.add(image1);
        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("details de la demande"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cell);

        table.addCell("Nom");
        table.addCell(Nom);
        table.addCell("Prénom");
        table.addCell(Prenom);
        table.addCell("Materiel");
        table.addCell("" + db.getMateriel_id());
        table.addCell("Quantité reservé");
        table.addCell(db.getQte());
        table.addCell("Date de debut");
        table.addCell(db.getDate_debut().toString());
        table.addCell("Date de fin");
        table.addCell(db.getDate_fin().toString());
        

        //table.addCell(selectedUser.getNom());
        //System.out.println("*******************"+selectedUser.getNom().toString());
        document.add(table);
        document.add(new Paragraph("Tunis le   " + localDate.toString() + "   " + localTime.toString()));
        Paragraph sign = new Paragraph("Signature du client" + "   " + ".................");
        document.add(sign);



        PrinterJob job = PrinterJob.createPrinterJob();
        System.out.println(table);
        if (job != null) {

            //job.printPage();
            job.endJob();
        }
        document.close();

        JOptionPane.showMessageDialog(null, " données exportées en pdf evec succés ");

    }
}

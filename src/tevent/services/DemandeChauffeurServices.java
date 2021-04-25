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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import tevent.entities.DemandeChauffeur;
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
import tevent.entities.DemandeBus;
import tevent.entities.DemandeChauffeur;
import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeChauffeurServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<DemandeChauffeur> readDemandeChauffeur() {
        // List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_chauffeur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<DemandeChauffeur> getDemandeByUser(int id) {
        // List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_chauffeur where utilisateur_id='"+id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void addDemandeChauffeur(DemandeChauffeur c) {
        String req = "INSERT INTO demande_chauffeur (utilisateur_id, num_permis, date_permis, date_expiration, etat) VALUES ('" + c.getUtilisateur_id() + "','" + c.getNum_permis() + "','" + c.getDate_permis() + "','" + c.getDate_expiration() + "','" + c.getEtat() + "' ) ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteDemandeChauffeur(int id) {
        String req = "DELETE FROM demande_chauffeur WHERE  id=" + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateDemandeChauffeur(DemandeChauffeur c, int id) {
       /* String req = "UPDATE  demande_chauffeur SET utilisateur_id='" + c.getUtilisateur_id() + "',num_permis='" + c.getNum_permis() + "',date_permis='" + c.getDate_permis() + "' date_expiration='" + c.getDate_expiration() + "' WHERE id='" + id + "' ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeChauffeur modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    
    String req = "update demande_chauffeur set utilisateur_id=?, num_permis= ? , date_permis= ?, date_expiration= ?, etat=? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setInt(1, c.getUtilisateur_id());
            ps.setInt(2, c.getNum_permis());
            ps.setString(5, c.getEtat());
            ps.setDate(3, java.sql.Date.valueOf(c.getDate_permis()));
            ps.setDate(4, java.sql.Date.valueOf(c.getDate_expiration()));
            ps.setInt(6, id);

            ps.executeUpdate();
            System.out.println("Demande Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    
    }

    public void AccepterDemande(int id) {
        List<DemandeChauffeur> list = new ArrayList<>();
        String req = "update demande_chauffeur set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "accepter");
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void RefuserDemande(int id) {
        String req = "update demande_chauffeur set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "refuser");
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<DemandeChauffeur> advancedSearchDemandeChauffeur(int num_permis, LocalDate date_expiration) {
        //List<DemandeChauffeur> list = new ArrayList<>() ;
        ObservableList<DemandeChauffeur> list = FXCollections.observableArrayList();

        try {
            String req = "select * from demande_chauffeur where num_permis= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, num_permis);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select * from demande_chauffeur where date_expiration < ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, java.sql.Date.valueOf(date_expiration) );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeChauffeur(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }
    
    public void SMS(){
     
     String ACCOUNT_SID = "AC6300e57699b45af19a2c425e36ccea80";
    String AUTH_TOKEN = "20441b847ff2a1cfebcca4ad172fdd83";
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21655067708"),
                new PhoneNumber("+17325513213"),
                "Demande Chauffeur a été ajouté veuillez consulter votre liste des demandes")
                .setStatusCallback(URI.create("http://postb.in/1234abcd"))
                .create();

        System.out.println(message.getSid());
 }
 
   public void PDF(DemandeChauffeur db, String Nom , String Prenom) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        String file_name = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV-Desktop\\src\\demande.pdf";
        Document document = new Document();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        document.add(new Paragraph("Agence TEvent", FontFactory.getFont(FontFactory.TIMES)));
        document.add(new Paragraph("Demande Chauffeur", FontFactory.getFont(FontFactory.TIMES)));
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
        table.addCell("Numéro de permis");
        table.addCell("" + db.getNum_permis());
        table.addCell("Date de permis");
        table.addCell(db.getDate_permis().toString());
        table.addCell("Date d'expiration");
        table.addCell(db.getDate_expiration().toString());
       

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

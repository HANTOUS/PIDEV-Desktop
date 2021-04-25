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
import javax.mail.PasswordAuthentication;
import tevent.entities.DemandeBus;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PrinterJob;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
        
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import tevent.tools.DataSource;

/**
 *
 * @author DELL
 */
public class DemandeBusServices {

    private Connection cnx = DataSource.getInstance().getCnx();

    public ObservableList<DemandeBus> readDemandeBus() {
        //List<DemandeBus> list = new ArrayList<>() ;
        ObservableList<DemandeBus> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_bus";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeBus(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public ObservableList<DemandeBus> getDemandeByUser(int id) {
        //List<DemandeBus> list = new ArrayList<>() ;
        ObservableList<DemandeBus> list = FXCollections.observableArrayList();

        String req = "SELECT * FROM demande_bus where utilisateur_id='"+id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new DemandeBus(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public void addDemandeBus(DemandeBus b) {
        String req = "INSERT INTO demande_bus (utilisateur_id, nb_participant, ville_arrivee, ville_depart, heure_arrivee, heure_depart, etat, jour_location) VALUES ('" + b.getUtilisateur_id() + "','" + b.getNb_participant() + "','" + b.getVille_arrivee() + "','" + b.getVille_depart() + "','" + b.getHeure_arrivee() + "','" + b.getHeure_depart() + "','" + b.getEtat() + "','" + b.getJour_location() + "') ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeBus Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteDemandeBus(int id) {
        String req = "DELETE FROM demande_bus WHERE  id=" + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("DemandeBus Supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateDemandeBus(DemandeBus b , int id) {
        /*String req ="UPDATE  demande_bus SET utilisateur_id='"+b.getUtilisateur_id()+"',nb_participant='"+b.getNb_participant()+"',ville_depart='"+b.getVille_depart()+"' ville_arrivee='"+b.getVille_arrivee()+"' heure_depart='"+b.getHeure_depart()+"' heure_arrivee='"+b.getHeure_arrivee()+"' etat='"+b.getEtat()+"' jour_location='"+b.getJour_location()+"' WHERE id='"+b.getId()+"' "  ;
        try {
            Statement st= cnx.createStatement() ;
            st.executeUpdate(req);
            System.out.println("DemandeBus modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
        String req = "update demande_bus set utilisateur_id=?, nb_participant= ? , ville_depart= ?, ville_arrivee= ?, heure_depart= ?, heure_arrivee= ?, etat= ?, jour_location= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, b.getUtilisateur_id());
            ps.setInt(2, b.getNb_participant());
            ps.setString(3, b.getVille_depart());
            ps.setString(4, b.getVille_arrivee());
            ps.setString(5, b.getHeure_depart());
            ps.setString(6, b.getHeure_arrivee());
            ps.setString(7, b.getEtat());
            ps.setDate(8, Date.valueOf(b.getJour_location()));
            ps.setInt(9, id);

            ps.executeUpdate();
            System.out.println("Demande Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<DemandeBus> Pagination() {
        List list;
        list = readDemandeBus().stream()
                .map(DemandeBus::getId)
                .distinct()
                .limit(2)
                .collect(Collectors.toList());

        return list;
    }

    public void AccepterDemande(int id) {
        List<DemandeBus> list = new ArrayList<>();
        String req = "update demande_bus set  etat= ? where id = ?";

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
        String req = "update demande_bus set  etat= ? where id = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, "refuser");
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
public static void SendMail(String contenu ,String type, String email) throws AddressException, MessagingException{
Properties properties = new Properties();

properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttlps.enable", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

String username = "tevents98@gmail.com";
String password ="TEvents2021";
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
    @Override
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username,password) ;
//return new PasswordAuthentication(myAccountEmail,"a") ; 
    }
    
});
 
    Message message = new MimeMessage(session);
    
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
    message.setSubject("Agence TEvents : Acceptation de la demande"+type);
  
    message.setText(contenu);
    
    
     javax.mail.Transport.send(message);

}

    public ObservableList<DemandeBus> advancedSearchDemandeBus(int nb_participant, String ville_depart, String ville_arrivee) {
        // List<DemandeBus> list = new ArrayList<>() ;
        ObservableList<DemandeBus> list = FXCollections.observableArrayList();

        try {
            String req = "select * from demande_bus where nb_participant= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, nb_participant);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeBus(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select * from demande_bus where ville_depart= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ville_depart);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeBus(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            String req = "select * from demande_bus where ville_arrivee= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ville_arrivee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DemandeBus(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
    public void PDF(DemandeBus db, String Nom , String Prenom) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        String file_name = "C:\\Users\\DELL\\Documents\\NetBeansProjects\\PIDEV-Desktop\\src\\demande.pdf";
        Document document = new Document();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        document.add(new Paragraph("Agence TEvent", FontFactory.getFont(FontFactory.TIMES)));
        document.add(new Paragraph("Demande Bus", FontFactory.getFont(FontFactory.TIMES)));
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
        table.addCell("Nombre de particiapnts");
        table.addCell("" + db.getNb_participant());
        table.addCell("Ville de depart");
        table.addCell(db.getVille_depart());
        table.addCell("Ville d'arrivée");
        table.addCell(db.getVille_arrivee());
        table.addCell("Heure de depart");
        table.addCell(db.getHeure_depart());
        table.addCell("Heure d'arrivée");
        table.addCell(db.getHeure_arrivee());
        table.addCell("Jour de location");
        table.addCell(db.getJour_location().toString());

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

        JOptionPane.showMessageDialog(null, " données exportées en pdf avec succés ");

    }

}

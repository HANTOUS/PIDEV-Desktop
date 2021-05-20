/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.services;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tevent.entities.Randonnee;
import tevent.entities.JavamailUtil;
import tevent.tools.DataSource;
/**
 *
 * @author maale
 */
public class RandonneeServices {
     private Connection cnx=DataSource.getInstance().getCnx();
     
       public ObservableList<Randonnee> ReadRandonnee(){
        List<Randonnee> list = new ArrayList<>();
        ObservableList <Randonnee> list1 = FXCollections.observableArrayList();
        String  req="Select typerand,nomevent from randonnee INNER JOIN event WHERE randonnee.id=event.id";
        try{
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                list1.add(new Randonnee(rs.getString(1),rs.getString(2)));
            
            }

      }catch(SQLException ex){
            System.out.println(ex.getMessage());
       }
       return list1; 
    }
      public void AjouterRandonnee(Randonnee F) throws Exception {
                    int insertedID =0 ;
                   String queryUe = "INSERT INTO `event`(`nomevent`,`datedebut`,`datefin`,`heuredebut`,`heurefin`,`lieu`,`nbmaxparticipant`,`type`,`description`,`tarif`,`lat`,`lng`,`discr`)"
                           + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
                   try {
                       PreparedStatement pse = cnx.prepareStatement(queryUe,Statement.RETURN_GENERATED_KEYS);
                       pse.setString(1,F.getNomevent());
                       pse.setDate(2, (Date) F.getDatedebut());
                       pse.setDate(3, (Date) F.getDatefin());
                       pse.setString(4,F.getHeuredebut());
                       pse.setString(5,F.getHeurefin());
                       pse.setString(6,F.getLieu());
                       pse.setInt(7,F.getNbmaxparticipant());
                       pse.setString(8,F.getType());
                       pse.setString(9,F.getDescription());
                       pse.setFloat(10, F.getTarif());
                       pse.setFloat(11, F.getLat());
                       pse.setFloat(12, F.getLng());
                       pse.setString(13,"randonnee");


                       pse.executeUpdate();




                       try (ResultSet generatedKeys = pse.getGeneratedKeys()) {
                       if (generatedKeys.next()) {
                           insertedID = (generatedKeys.getInt(1));
                           System.out.println(insertedID);
                       }
                       else {
                           throw new SQLException("Creating fest failed, no ID obtained.");
                       }
                       }



                   } catch (SQLException ex) {
                       Logger.getLogger(RandonneeServices.class.getName()).log(Level.SEVERE, null, ex);
                   }



                String queryU = "INSERT INTO `randonnee`(id,`typerand`) VALUES(?,?)";
                       PreparedStatement ps;
                   try {
                      ps = cnx.prepareStatement(queryU);
                      ps.setInt(1,insertedID );//ID EVENT
                      ps.setString(2,F.getTyperand());

                      ps.executeUpdate();
                      JavamailUtil.sendMail("hantous.mehdi2@gmail.com", "API", "bonsoir zied un évenement a été ajouté ");


                   } catch (SQLException ex) {
                       Logger.getLogger(RandonneeServices.class.getName()).log(Level.SEVERE, null, ex);
                   }


              

                          }

     
         public void SupprimerRandonnee(int id) {
                String queryU = "delete  from event where event.id="+id;
                 String queryP = "DELETE FROM randonnee WHERE randonnee.id = "+id;

                 System.out.println(queryP);
                 Statement stm,stm1;

                 try {
                     stm = cnx.createStatement();
                     stm.executeUpdate(queryP);
                     System.out.println("Done");
                 } catch (SQLException ex) {
                     System.out.println(ex);
                 }

                  try {
                     stm1 = cnx.createStatement();
                     stm1.executeUpdate(queryU);
                     System.out.println("Done");
                 } catch (SQLException ex) {
                     System.out.println("Failed");
                 }
             }
         
     public void ModifierRandonnee(Randonnee F,int id) {
     
                  String queryU= " UPDATE `randonnee` SET `typerand`=?  WHERE id ="+id;
                  PreparedStatement ps;
              try {
                  ps = cnx.prepareStatement(queryU);
                   ps.setString(1, F.getTyperand());




                 ps.executeUpdate();
              } catch (SQLException ex) {
                  Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
              }




                  String queryP = "UPDATE event SET `nomevent`=? WHERE id ="+F.getId();
                  PreparedStatement psp;
              try {
                  psp = cnx.prepareStatement(queryP);
                  psp.setString(1,F.getNomevent());
                  /*psp.setDate(2, (Date) F.getDatedebut());
                  psp.setDate(3, (Date) F.getDatefin());
                  psp.setString(4,F.getHeuredebut());
                  psp.setString(5,F.getHeurefin());
                  psp.setString(6,F.getLieu());
                  psp.setInt(7,F.getNbmaxparticipant());
                  psp.setString(8,F.getType());
                  psp.setString(9,F.getDescription());
                  psp.setFloat(10, F.getTarif());
                  psp.setFloat(11, F.getLat());
                  psp.setFloat(12, F.getLng());*/
                  psp.executeUpdate();
              } catch (SQLException ex) {
                  Logger.getLogger(CampingServices.class.getName()).log(Level.SEVERE, null, ex);
              }




          }
     
     public ObservableList<Randonnee> ListAttestation() {
        ObservableList<Randonnee> os = FXCollections.observableArrayList();
        try {
            String query = "Select typerand,nomevent,randonnee.id from randonnee INNER JOIN event WHERE randonnee.id=event.id";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // if(!"archived".equals(rs.getString("status"))) {
                Randonnee t = new Randonnee();
                
                t.setNomevent(rs.getString("nomevent"));
                t.setTyperand(rs.getString("typerand"));
                t.setId(rs.getInt("id"));
               
                os.addAll(t);
                // }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    }
}

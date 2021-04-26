/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tevent.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hanto
 */
public class DataSource {
     
    public String url="jdbc:mysql://localhost:3306/teventdb1";
    public String user="root";
    public String pwd="root";
    private Connection cnx;
    public static DataSource ct;
    
    private DataSource(){
       try{
           
        cnx = DriverManager.getConnection(url,user,pwd);
        System.out.println("Connexion établieeee");
        
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }
    public static DataSource getInstance(){
        if (ct == null)
            ct = new DataSource();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
   
    
}

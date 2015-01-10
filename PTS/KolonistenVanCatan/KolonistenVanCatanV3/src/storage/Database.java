/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storage;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Daan
 */
public class Database {
    
    private Properties props;
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private String query;
    private ResultSet rs;
    
    private void initConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@emielmuit.asuscomm.com:1521:XE", "System", "12345");  
    }
    
    public String Login(String gebruikersnaam) {
        String ww = null;
        
        query = "SELECT WACHTWOORD FROM GEBRUIKER WHERE GEBRUIKERSNAAM = ?";
        try {
            initConnection();
            pstat = conn.prepareStatement(query);
            pstat.setString(1, gebruikersnaam);
            rs = pstat.executeQuery();
            while (rs.next())
            {
                ww = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ww;
    }
    
    /**
    public Boolean Register(String gebruikersnaam, String wachtwoord) {
        Boolean isSuccess = false;
        int maxID;
        
        if (Login(gebruikersnaam) == null) {
            query = "SELECT MAX(ID) FROM GEBRUIKERS";
            try {
                initConnection();
                stat = conn.createStatement();
                rs = stat.executeQuery(query);
                while (rs.next())
                {
                    maxID = rs.getInt(1);
                }
                
                query = "INSERT INTO GEBRUIKERS VALUES(?,?)";
                pstat = conn.prepareStatement(query);
                pstat.setString(1, gebruikersnaam);
                pstat.setString(2, wachtwoord);
                pstat.execute();
                
                isSuccess = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return isSuccess;
    }
    */
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Maiko
 */
public class DBconnect {

    private Properties props;
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void Connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://85.113.237.162:3306/PTS", "PTS", "YvXVhN2xWvCSdbMs");
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public String getPassword(String username) throws SQLException, Exception
    {
        String Password = null;
        try
        {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           conn = DriverManager.getConnection("jdbc:mysql://85.113.237.162:3306/PTS", "PTS", "YvXVhN2xWvCSdbMs");
           
           preparedStatement = conn.prepareStatement("SELECT Password FROM User WHERE Username = ?");
           preparedStatement.setString(1, username);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next())
           {
               Password = resultSet.getString("Password");
           }
           return Password;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            Close();
        }
    }
    
    public boolean CheckUsername(String username) throws Exception
    {
        try
        {
            Connect();
            
            preparedStatement = conn.prepareStatement("SELECT Username FROM User WHERE Username =?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                if(resultSet.getString("Username").equals(username))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            Close();
        }
    }
    
    public void CreateUser(String username, String email, String password) throws Exception
    {
        try
        {
            Connect();
            
            preparedStatement = conn.prepareStatement("INSERT INTO User(Username, Email, Password) VALUES(?, ?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            Close();
        }
    }

    private void Close() {
        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

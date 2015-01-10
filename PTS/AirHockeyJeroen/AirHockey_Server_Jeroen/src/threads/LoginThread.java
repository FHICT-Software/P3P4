//<editor-fold defaultstate="collapsed" desc="Jibberish">
package threads;

import components.lobby.User;
import interfaces.IThread;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 * In this class you can find all properties and operations for WelcomeThread. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/26
 */
public class LoginThread extends ClientConnector implements IThread {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static Connection conn;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for OpenThread.
     *
     * @param clientSocket is the open socket to the client.
     */
    public LoginThread(Socket clientSocket, int id) {
        super(clientSocket, id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="run()">
    @Override
    public void run() {
        boolean loginCorrect = false;
        int loginTry = 0;
        String message = null;

        while (!loginCorrect) {
            message = readMessage();
            if (message.equals("Login")) {
                loginCorrect = login(loginTry);
            } else if (message.equals("Register")) {
                register();
            }

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login()">
    private boolean login(int loginTry) {
        boolean loginCorrect = false;
        String message = "";
        String username = readMessage();
        String passwordGiven = readMessage();
        String passwordDatabase = null;
        String email = null;
        String firstName = null;
        String lastName = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://85.113.237.162:3306/PTS", "PTS", "YvXVhN2xWvCSdbMs");

            preparedStatement = conn.prepareStatement("SELECT * FROM User WHERE Username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passwordDatabase = resultSet.getString("Password");
                email = resultSet.getString("Email");
                firstName = resultSet.getString("FirstName");
                lastName = resultSet.getString("LastName");
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LoginThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginCorrect = passwordGiven.equals(passwordDatabase);

        if (loginCorrect) {
            message = "Access Granted";
            sendObject(message);
            User user = null;
            if (firstName != null && lastName != null) {
                user = new User(username, email, firstName, lastName);
            } else {
                user = new User(username, email);
            }
            sendObject(user);
            airhockey_server_jeroen.AirHockey_Server_Jeroen.addUser(user);
            new LobbyThread(clientSocket, id).start();
        } else {
            loginTry++;
            sendObject("Access Denied (" + loginTry + ")");
        }
        return loginCorrect;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="register()">
    private void register() {
        String message = "";
        String username = readMessage();
        String password = readMessage();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://85.113.237.162:3306/PTS", "PTS", "YvXVhN2xWvCSdbMs");
            preparedStatement = conn.prepareStatement("INSERT INTO User (Username, Password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            message = "Access Granted";
            sendObject(message);
            User user = null;
            user = new User(username, "");
            sendObject(user);
            airhockey_server_jeroen.AirHockey_Server_Jeroen.addUser(user);
            new LobbyThread(clientSocket, id).start();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            message = "Access Denied";
            sendObject(message);
            Logger.getLogger(LoginThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
//</editor-fold>

}

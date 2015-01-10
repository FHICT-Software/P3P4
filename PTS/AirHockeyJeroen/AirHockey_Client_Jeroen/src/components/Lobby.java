//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

import components.lobby.User;
import enums.GameType;
import gui.Login;
import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import interfaces.game.IGame;
import interfaces.game.IGameWaiting;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class Lobby implements ILobbyNotLoggedIn, ILobbyLoggedIn {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Static Finnal">
    private static final int PORT = 8080;
    private static final String SERVERNAME = "192.168.1.110";
    //</editor-fold>
    private User user;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    private Lobby() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login(username, password)">
    /**
     * This operation connects to the server and tries to login with the given parameters. The server than returns if it is
     * "Success" or "Access Denied". Depending on the server return, the Lobby will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     *
     * @return the interface to control the Lobby when logged in.
     */
    @Override
    public ILobbyLoggedIn login(String username, String password) {
        ILobbyLoggedIn returner = null;
        try {
            System.out.println("Connecting to " + SERVERNAME + " on port " + PORT);
            Socket client = new Socket(SERVERNAME, PORT);
            System.out.println("Connection Successful");
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF("login(" + username + "," + password + ")");
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String response = dataInputStream.readUTF();
            System.out.println(response);
            if (response.equals("Access Granted")) {
                this.user = new User(username);
                returner = this;
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="register(username, password)">
    /**
     * This operation connects to the server and tries to create a new user with the given parameters. The server than returns if
     * it is "Access Granted" or "Access Denied". Depending on the server return, the Lobby will be opened. (The new user is
     * automatically logged in).
     *
     * @param username the new username (this has to be unique).
     * @param password the password linked to this user.
     *
     * @return the interface to control the Lobby when logged in.
     */
    @Override
    public ILobbyLoggedIn register(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createGame(host, name, gameType)">
    /**
     * This operation creates and returns a new Game.
     *
     * @param name     is the name of the game (does not have to be unique).
     * @param gameType is the type of game that will be created.
     *
     * @return the interface of the game that has been created.
     */
    @Override
    public IGameWaiting createGame(String name, GameType gameType) {
        IGameWaiting gameWaiting = new Game(user, name, gameType);
        try {
            System.out.println("Connecting to " + SERVERNAME + " on port " + PORT);
            Socket client = new Socket(SERVERNAME, PORT);
            System.out.println("Connection Successful");
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("createGame");
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String response = dataInputStream.readUTF();
            System.out.println(response);
            if (response.equals("Send Game")) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(gameWaiting);
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameWaiting;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="joinGame(joiner, game)">
    /**
     * This operation lets you join a game.
     *
     * @param joiner is the user that wants to join (he will be added to the next available player).
     * @param game   is the game the game he wants to join.
     *
     */
    @Override
    public void joinGame(IGameWaiting game) {
        game.join(user);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finding">
    //<editor-fold defaultstate="collapsed" desc="findGame(userPlaying)">
    /**
     * This operation will return the game that fit the search.
     *
     * @param userPlaying is one of the players in the game.
     *
     * @return the game where the user is playing in.
     */
    @Override
    public IGame findGame(User userPlaying) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameName)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(String gameName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameType)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(GameType gameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameName, GameType)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(String gameName, GameType gameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="logout()">
    /**
     * This operation connects to the server and let the user logout. The Lobby GUI will be closed, and the Login GUI will be
     * shown.
     *
     * @return the interface to control the Lobby when not logged in.
     */
    @Override
    public ILobbyNotLoggedIn logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    public static Lobby getInstance() {
        return LobbyHolder.INSTANCE;
    }

    private static class LobbyHolder {

        private static final Lobby INSTANCE = new Lobby();
    }
    //</editor-fold>

}

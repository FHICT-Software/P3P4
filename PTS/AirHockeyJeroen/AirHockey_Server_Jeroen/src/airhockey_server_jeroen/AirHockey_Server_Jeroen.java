//<editor-fold defaultstate="collapsed" desc="Jibberish">
package airhockey_server_jeroen;

import components.Game;
import components.lobby.User;
import interfaces.IGame;
import interfaces.IThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientConnector;
import threads.ClientListenerThread;
import threads.WelcomeThread;
//</editor-fold>

/**
 * In this class you can find all properties and operations for AirHockey_Server_Jeroen. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/24
 */
public class AirHockey_Server_Jeroen {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static ServerSocket serverSocket;
    private static final List<IGame> games = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();
    private static final List<ClientListenerThread> listeners = new ArrayList<>();
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="addGame(game)">
    public static boolean addGame(IGame game) {
        if (!games.contains(game)) {
            games.add(game);
            return true;
        } else {
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addUser(user)">
    public static void addUser(User user) {
        users.add(user);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeUser(user)">
    public static void removeUser(String username, ClientConnector clientConnector) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
            }
        }
        for (int i = 0; i < listeners.size(); i++) {
            if (listeners.get(i).equals(clientConnector)) {
                listeners.remove(i);
            }
        }
        removeGame(username);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addListener(listner)">
    public static void addListener(ClientListenerThread listener) {
        listeners.add(listener);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="sendMessageToAll(sender, message)">
    public static void sendMessageToAll(IThread sender, String message) {
        for (ClientConnector receiver : listeners) {
            if (!receiver.equals(sender)) {
                receiver.sendObject(message);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="sendMessageToAll(sender, message)">
    public static void sendMessageToAll(String message) {
        if (message != null) {
            for (int i = 0; i < listeners.size(); i++) {
                try {
                    listeners.get(i).sendObject(message);
                } catch (Exception exception) {
                    listeners.remove(i);
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="sendObjectToAll(object)">
    public static void sendObjectToAll(Object object) {
        for (ClientConnector receiver : listeners) {
            receiver.sendObject(object);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getGames()">
    public static List<IGame> getGames() {
        return games;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="updateGame(game)">
    public static void updateGame(IGame game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).equals(game)) {
                games.get(i).matchTo((Game) game);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeGame(username)">
    public static void removeGame(String username) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getHost().getUsername().equals(username)) {
                games.remove(i);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeGame(game)">
    public static void removeGame(IGame game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).equals(game)) {
                games.remove(i);
            }
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="static main">
    public static void main(String[] args) {
        int port;
        if (args.length < 1) {
            port = 6752;
        } else {
            port = Integer.parseInt(args[0]);
        }

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(AirHockey_Server_Jeroen.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
        while (true) {
            try {
                Socket currentConnection = serverSocket.accept();
                WelcomeThread currentThread = new WelcomeThread(currentConnection);
                currentThread.start();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
        //</editor-fold>
    }
}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

import components.chat.Chatter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Chat. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/27
 */
public class Chat extends ServerConnecter {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Static Finnal">
    private static final int PORT = 8080;
    private static final String SERVERNAME = "localhost";
    //</editor-fold>
    private final List<Chatter> chatters;
    private Socket clientSocket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //</editor-fold>
    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for Chat.
     */
    public Chat() {
        chatters = new ArrayList<>();
        connect();
        sendObjectToServer("Setup Chat");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="sendMessage(message)">
    /**
     * This Operation opens
     *
     * @param message
     */
    public void sendMessage(String message) {
        sendObjectToServer(message);
    }
    //</editor-fold>
    //</editor-fold>
}

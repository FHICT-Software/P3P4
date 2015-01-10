//<editor-fold defaultstate="collapsed" desc="Jibberish">
package threads;

import interfaces.IThread;
import java.net.Socket;
//</editor-fold>

/**
 * In this class you can find all properties and operations for WelcomeThread. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/26
 */
public class ChatThread extends ClientConnector implements IThread {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //</editor-fold>
    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for OpenThread.
     *
     * @param clientSocket is the open socket to the client.
     */
    public ChatThread(Socket clientSocket, int id) {
        super(clientSocket, id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="run()">
    @Override
    public void run() {
        while (true) {
            String message = readMessage();
            airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
        }
    }
    //</editor-fold>
    //</editor-fold>
}

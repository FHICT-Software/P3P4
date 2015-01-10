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
public class ClientListenerThread extends ClientConnector implements IThread {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //</editor-fold>
    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for OpenThread.
     *
     * @param clientSocket is the open socket to the client.
     */
    public ClientListenerThread(Socket clientSocket, int id) {
        super(clientSocket, id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="run()">
    @Override
    public void run() {
        while (true){
            
        }
        //I think this doesn't have to do anything...
        //This thread keeps the socket open, and if a client sends an update (for example in the lobby),
        //this will be handled in the AirHockey_Server_Jeroen Thread.
    }
    //</editor-fold>
    //</editor-fold>
}

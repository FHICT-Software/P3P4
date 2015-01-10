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
public class WelcomeThread extends ClientConnector implements IThread {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static int nextID = 1000;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for OpenThread.
     *
     * @param clientSocket is the open socket to the client.
     */
    public WelcomeThread(Socket clientSocket) {
        super(clientSocket, 111);
    }
    //</editor-fold>

    //<editor-fold desc="run()">
    @Override
    public void run() {
        boolean loginCorrect = false;
        String message = null;
        int loginTry = 0;

        message = "Welcome to the MP-AirHockey Server.";
        sendObject(message);
        message = readMessage();
        if (message.equals("Setup Login")) {
            message = "You're ID is:" + nextID;
            sendObject(message);
            new LoginThread(clientSocket, nextID).start();
            nextID++;
            System.out.println("Listening to: " + clientSocket.getRemoteSocketAddress().toString().split(":")[0]);
        } else if (message.contains("Setup Listener:")) {
            ClientListenerThread listener = new ClientListenerThread(clientSocket, Integer.parseInt(message.split(":")[1]));
            listener.start();
            airhockey_server_jeroen.AirHockey_Server_Jeroen.addListener(listener);
            System.out.println("Open Connection to: " + clientSocket.getRemoteSocketAddress().toString().split(":")[0]);
        } else if (message.contains("Setup Chat:")) {
            new ChatThread(clientSocket, Integer.parseInt(message.split(":")[1])).start();
        } else if (message.contains("Setup IngameConnection")) {
            new InGameThread(clientSocket, id).start();
        }
    }
    //</editor-fold>
}

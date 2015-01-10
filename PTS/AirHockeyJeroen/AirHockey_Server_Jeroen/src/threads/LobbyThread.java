//<editor-fold defaultstate="collapsed" desc="Jibberish">
package threads;

import components.Game;
import components.game.Player;
import components.lobby.User;
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
public class LobbyThread extends ClientConnector implements IThread {

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    /**
     * This is the constructor for OpenThread.
     *
     * @param clientSocket is the open socket to the client.
     */
    public LobbyThread(Socket clientSocket, int id) {
        super(clientSocket, id);

    }
    //</editor-fold>

    //<editor-fold desc="run()">
    @Override
    public void run() {
        sendObject(airhockey_server_jeroen.AirHockey_Server_Jeroen.getGames());
        while (true) {
            String message = readMessage();
            if (message != null) {
                if (message.equals("Create Game")) {
                    Game game = (Game) readObject();
                    if (airhockey_server_jeroen.AirHockey_Server_Jeroen.addGame(game)) {
                        message = "New Game Created.";
                        sendObject(message);
                        message = "New Game Added";
                        airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                        airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(game);
                        System.out.println("New Game Created (" + game + ").");
                    } else {
                        message = "You already have a game. Game not created.";
                        sendObject(message);
                        System.out.println("New Game Not Created (" + game + ").");
                    }
                } else if (message.equals("Remove Game")) {
                    Game game = (Game) readObject();
                    Player.serverPlusOne();
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.removeGame(game);
                    message = "Game Removed";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(game);
                    System.out.println("Game Removed (" + game + ").");
                } else if (message.equals("Join Game")) {
                    User user = (User) readObject();
                    Game game = (Game) readObject();
                    Player.serverPlusOne();
                    game.join(user);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.updateGame(game);
                    message = "Game Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(game);
                    System.out.println("Game Updated (" + game + ").");
                }  else if (message.equals("Leave Game")) {
                    User user = (User) readObject();
                    Game game = (Game) readObject();
                    Player.serverPlusOne();
                    game.leave(user);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.updateGame(game);
                    message = "Game Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(game);
                    System.out.println("Game Updated (" + game + ").");
                } else if (message.contains("Start")) {
                    Game game = (Game) readObject();
                    game.start();
                    message = "Game Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(game);
                    System.out.println("Game Started (" + game + ").");
                } else if (message.contains("Logout")) {
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.removeUser(message.split(":")[1], this);
                    System.out.println("Connections to " + clientSocket.getRemoteSocketAddress().toString().split(":")[0] + " Closed.");
                }
            } else {
                break;
            }
        }
    }
    //</editor-fold>
    //</editor-fold>
}

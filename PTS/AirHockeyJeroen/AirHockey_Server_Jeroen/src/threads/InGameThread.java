/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import components.game.Bat;
import components.game.Point;
import components.game.Puck;
import interfaces.IThread;
import java.net.Socket;

/**
 *
 * @author JMAGPeeters
 */
public class InGameThread extends ClientConnector implements IThread {

    public InGameThread(Socket clientSocket, int id) {
        super(clientSocket, id);
    }

    @Override
    public void run() {
        while (true) {
            String message = readMessage();
            if (message != null) {
                if (message.equals("Bat Position P1")) {
                    Point pbat = (Point) readObject();
                    message = "Bat P1 Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(pbat);
                    System.out.println("bat position p1 updated");
                } else if (message.equals("Bat Position P2")) {
                    Point pbat = (Point) readObject();
                    message = "Bat P2 Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(pbat);
                    System.out.println("bat position p2 updated");
                } else if (message.equals("Bat Position P3")) {
                    Point pbat = (Point) readObject();
                    message = "Bat P3 Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(pbat);
                    System.out.println("bat position p2 updated");
                } else if (message.equals("Puck Position")) {
                    Point ppuck = (Point) readObject();
                    message = "Puck Position Updated";
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendMessageToAll(message);
                    airhockey_server_jeroen.AirHockey_Server_Jeroen.sendObjectToAll(ppuck);
                    System.out.println("Puck position updated");
                }
            } else {
                break;
            }
        }
    }
}

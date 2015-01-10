/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javax.swing.text.html.ListView;

/**
 *
 * @author Miquel
 */
public class SpelController extends UnicastRemoteObject implements Initializable {

    @FXML
    ImageView imgGraan;
    @FXML
    ImageView imgHout;
    @FXML
    ImageView imgSteen;
    @FXML
    ImageView imgWol;
    @FXML
    ImageView imgErts;
    @FXML
    ImageView imgHandelsroute;
    @FXML
    ImageView imgRiddermacht;
    @FXML
    ImageView imgRidder;
    @FXML
    ImageView imgMonopolie;
    @FXML
    ImageView imgUitvinding;
    @FXML
    ImageView imgOverwinningspunt;
    @FXML
    ImageView imgStratenbouw;
    public static Spel spel;

    /**
     *
     */
    public SpelController() throws RemoteException {
        String ipAddress = "localhost";
        int portNumber = 1099;
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                //Todo
            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void testConnectie() {
        try {
            spel.lobby.plaatsBericht("TEST", "TEST");
        } catch (RemoteException ex) {
            Logger.getLogger(KVCSpelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

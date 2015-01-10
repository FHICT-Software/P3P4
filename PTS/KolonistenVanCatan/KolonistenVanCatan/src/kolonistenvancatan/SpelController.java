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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.text.html.ListView;

/**
 *
 * @author Miquel
 */
public class SpelController extends UnicastRemoteObject implements Initializable {
    
    @FXML ImageView imgGraan; 
    @FXML ImageView imgHout; 
    @FXML ImageView imgSteen; 
    @FXML ImageView imgWol; 
    @FXML ImageView imgErts;
    @FXML ImageView imgHandelsroute; 
    @FXML ImageView imgRiddermacht; 
    @FXML ImageView imgRidder; 
    @FXML ImageView imgMonopolie; 
    @FXML ImageView imgUitvinding; 
    @FXML ImageView imgOverwinningspunt; 
    @FXML ImageView imgStratenbouw;
    @FXML Button btnHandelen;
    @FXML Button btnHandelenbank;
    @FXML Button btnBeurt;
    @FXML ListView lvSpelers;
    @FXML TextArea taChatbox;
    @FXML TextField tfChatbox;
    
    /**
     *
     */
    public SpelController() throws RemoteException
    {
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
    
}

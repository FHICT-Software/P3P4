/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.Spel;
import domein.Speler;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author E.Muit-Laptop
 */
public class LobbyController extends UnicastRemoteObject implements Initializable {

    
    @FXML ListView lvSpelers;
    @FXML Button btLogin;
    @FXML TextField tfSpelernaam;
    @FXML TextArea taChat;
    @FXML TextField taChatMessage;
    @FXML ListView lvSpellen;
    @FXML Button btHost;
    // Set flag locateRegistry when binding using registry
    // Reset flag locateRegistry when binding using Naming
    private static boolean locateRegistry = true;
    // Set binding name for IEffectenbeurs
    private static String bindingName = "CvcServer";
    // References to registry and IEffectenbeurs
    private Registry registry = null;
    private ILobby lobby = null;
    private Timer t = new Timer();
    private int aantalSpelers = 0;
    private int aantalSpellen = 0;
    private String naam;
    
    // Constructor
    //public LobbyController(String ipAddress, int portNumber) throws RemoteException {
    public LobbyController() throws RemoteException {
        String ipAddress = "localhost";
        int portNumber = 1099;
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

                t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (lobby.getAantalSpelers() > aantalSpelers)
                    {
                        aantalSpelers = lobby.getAantalSpelers();
                        Platform.runLater(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    lvSpelers.setItems(FXCollections.observableList(lobby.getSpelers()));
                                } catch (RemoteException ex) {
                                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });    
                    }
                    if (lobby.getAantalSpellen() > aantalSpellen)
                    {
                        aantalSpellen = lobby.getAantalSpellen();
                        Platform.runLater(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    lvSpellen.setItems(FXCollections.observableList(lobby.getSpellen()));
                                } catch (RemoteException ex) {
                                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                    taChat.setText(lobby.getChatTekst());
                } catch (RemoteException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 1000, 1000);
        
        // Bind IEffectenbeurs
        if (locateRegistry) {
            // Locate registry at IP address and port number
            registry = locateRegistry(ipAddress, portNumber);

            // Print result locating registry
            if (registry != null) {
                System.out.println("Client: Registry located");
            } else {
                System.out.println("Client: Cannot locate registry");
                System.out.println("Client: Registry is null pointer");
            }

            // Print contents of registry
            if (registry != null) {
                printContentsRegistry();
            }

            // Bind IEffectenbeurs using registry
            if (registry != null) {
                lobby = bindMockEffectenbeursUsingRegistry();
            }
        } else {
            // Bind IEffectenbeurs using Naming
            lobby = bindMockEffectenbeursUsingNaming(ipAddress, portNumber);
        }

        // Print result binding IEffectenbeurs
        if (lobby != null) {

            System.out.println("Client: ILobby bound");
        } else {
            System.out.println("Client: ILobby is null pointer");
        }
    }

    // Locate registry
    private Registry locateRegistry(String ipAddress, int portNumber) {

        // Locate registry at IP address and port number
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        return registry;
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    // Bind studenIEffectenbeurst administration using registry
    private ILobby bindMockEffectenbeursUsingRegistry() {

        // Bind MockEffectenbeurs
        ILobby lobbyAdmin = null;
        try {
            lobbyAdmin = (ILobby) registry.lookup(bindingName);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot bind lobby, remote exception");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            lobbyAdmin = null;
        } catch (NotBoundException ex) {
            System.out.println("Client: Cannot bind lobby, not bound exception");
            System.out.println("Client: NotBoundException: " + ex.getMessage());
            lobbyAdmin = null;
        }
        return lobbyAdmin;
    }

    // Bind MockEffectenbeurs using Naming
    private ILobby bindMockEffectenbeursUsingNaming(String ipAddress, int portNumber) {

        // Bind IEffectenbeurs
        ILobby lobby = null;
        try {
            lobby = (ILobby) Naming.lookup("rmi://" + ipAddress + ":" + portNumber + "/" + bindingName);
        } catch (MalformedURLException ex) {
            System.out.println("Client: Cannot bind lobby, malformed url exception");
            System.out.println("Client: MalformedURLException: " + ex.getMessage());
            lobby = null;
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot bind lobby, remote exception");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            lobby = null;
        } catch (NotBoundException ex) {
            System.out.println("Client: Cannot bind lobby, not bound exception");
            System.out.println("Client: NotBoundException: " + ex.getMessage());
            lobby = null;
        }
        return lobby;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void login()
    {
        try {
            lobby.addSpeler(tfSpelernaam.getText());
            naam = tfSpelernaam.getText();
            tfSpelernaam.setVisible(false);
            btLogin.setVisible(false);
            taChat.setVisible(true);
            taChatMessage.setVisible(true);
            btHost.setDisable(false);
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        taChatMessage.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                try {
                    lobby.plaatsBericht(taChatMessage.getText(),naam);
                    taChatMessage.setText("");
                } catch (RemoteException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void hostGame()
    {
        try {
            lobby.addSpel(naam + "'s Game");
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.Spel;
import domein.Speler;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author E.Muit-Laptop
 */
public interface ILobby extends Remote {
    
    public void plaatsBericht(String bericht, String speler) throws RemoteException;
    public ArrayList<String> getSpelers() throws RemoteException;
    public void addSpeler(String naam) throws RemoteException;
    public void removeSpeler(Speler speler) throws RemoteException;
    public int getAantalSpelers() throws RemoteException;
    public String getChatTekst() throws RemoteException;
    public void addSpel(String s) throws RemoteException;
    public ArrayList<String> getSpellen() throws RemoteException;
    public int getAantalSpellen() throws RemoteException;
    public Spel getSpel(String naam) throws RemoteException;
    public void voegSpelerToeAanSpel(Speler speler, String spel) throws RemoteException;
}

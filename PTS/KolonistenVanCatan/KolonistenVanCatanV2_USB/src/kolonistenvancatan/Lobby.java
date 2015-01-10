/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.Spel;
import domein.Speler;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author E.Muit-Laptop
 */
public class Lobby extends UnicastRemoteObject implements ILobby {
    
    public ArrayList<Speler> spelers = new ArrayList<Speler>();
    public ArrayList<Spel> spellen = new ArrayList<Spel>();
    public String chatTekst = "";
    
    public Lobby() throws RemoteException
    {
        
    }
    
    public String getChatTekst()
    {
        return chatTekst;
    }
    
    @Override
    public void plaatsBericht(String bericht, String speler) throws RemoteException {
        chatTekst += speler + ": " + bericht + "\n";
    }

    @Override
    public void addSpeler(String naam) throws RemoteException {
        Speler s = new Speler(naam, null);
        spelers.add(s);
    }

    @Override
    public void removeSpeler(Speler speler) throws RemoteException {
        spelers.remove(speler);
    }

    @Override
    public ArrayList<String> getSpelers() {
        ArrayList<String> returnwaarde = new ArrayList<String>();
        for (Speler s : spelers)
        {
            returnwaarde.add(s.getNaam());
        }
        return returnwaarde;
    }

    @Override
    public int getAantalSpelers() throws RemoteException {
        return spelers.size();
    }

    @Override
    public void addSpel(String s) throws RemoteException {
        boolean spelBestaatAl = false;
        for (Spel spel : spellen)
        {
            if (spel.getNaam().equals(s))
                spelBestaatAl=true;
        }
        if (!spelBestaatAl)
            spellen.add(new Spel(s,null));
    }

    @Override
    public ArrayList<String> getSpellen() throws RemoteException {
        ArrayList<String> returnwaarde = new ArrayList<String>();
        for (Spel s : spellen)
        {
            returnwaarde.add(s.getNaam());
        }
        return returnwaarde;
    }

    @Override
    public int getAantalSpellen() throws RemoteException {
        return spellen.size();
    }
    
    
    
}

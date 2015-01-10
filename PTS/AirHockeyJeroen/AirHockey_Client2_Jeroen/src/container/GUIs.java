//<editor-fold defaultstate="collapsed" desc="Jibberish">

package container;

//</editor-fold>

import gui.GUI;
import gui.GameGUI;
import gui.GameLobbyGUI;
import gui.LobbyGUI;


/**
 * In this class you can find all properties and operations for GUIs.
 * //CHECK
 *
 * @organization: Moridrin
 * @author        J.B.A.J. Berkvens
 * @date          2014/06/19
 */
public class GUIs {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static GUI game;
    private static GUI gameLobby;
    private static GUI lobby;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="Game">
    public static GUI getGame(){
        return game;
    }

    public static void setGame(GameGUI game) {
        GUIs.game = game;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Game LobbyGUI">
    public static GUI getGameLobby() {
        return gameLobby;
    }

    public static void setGameLobby(GameLobbyGUI gameLobby) {
        GUIs.gameLobby = gameLobby;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LobbyGUI">
    public static GUI getLobby() {
        return lobby;
    }

    public static void setLobby(LobbyGUI lobby) {
        GUIs.lobby = lobby;
    }
    //</editor-fold>
    //</editor-fold>
}

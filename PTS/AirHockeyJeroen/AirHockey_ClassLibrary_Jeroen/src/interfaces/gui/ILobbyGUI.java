//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces.gui;

//</editor-fold>

import interfaces.IGame;

/**
 * In this class you can find all types and operations for ILobbyGUI.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/28
 */
public interface ILobbyGUI {

    /**
     * This is the adder for the GameList (items).
     *
     * @param item this is the item that needs to be added.
     */
    public void addGameListItem(IGame item);
    
    public void updateGameListItem(IGame item);
    
    public void removeGameListItem(IGame item);

    public void addMessage(String message);
    
}

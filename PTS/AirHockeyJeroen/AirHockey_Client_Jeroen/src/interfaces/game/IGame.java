//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces.game;

import components.game.Player;
import components.lobby.User;
import enums.GameState;
import enums.GameType;
//</editor-fold>

/**
 * In this class you can find all types and operations for IGame.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface IGame {

    public String getName();

    public User getHost();

    public GameType getGameType();

    public GameState getGameState();

    public Player getPlayer(int id);
}

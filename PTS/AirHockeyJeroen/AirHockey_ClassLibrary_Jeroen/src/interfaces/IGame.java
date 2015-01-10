//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces;

import components.Game;
import components.game.Player;
import components.lobby.User;
import enums.GameState;
import enums.GameType;
import java.io.Serializable;
import java.util.List;
//</editor-fold>

/**
 * In this class you can find all types and operations for IGame.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface IGame extends Serializable{

    /**
     * Getter for name.
     *
     * @return the name of the game.
     */
    public String getName();

    /**
     * Getter for host.
     *
     * @return the host player (player 0).
     */
    public User getHost();

    /**
     * Getter for gameType.
     *
     * @return the type of the game.
     */
    public GameType getGameType();

    /**
     * Getter for gameState.
     *
     * @return the state of the game.
     */
    public GameState getGameState();

    /**
     * Getter for Players.
     *
     * @return the full list of players.
     */
    public List<Player> getPlayers();

    /**
     * Getter for Player.
     *
     * @param id is the id of the player (nr of when he joined the game).
     *
     * @return the player with the given id.
     */
    public Player getPlayer(int id);

    /**
     * This operation updates the game to match the game given.
     *
     * @param game is the game this should match to.
     */
    public void matchTo(Game game);
}

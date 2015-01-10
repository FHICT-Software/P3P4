//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces.game;

import components.lobby.User;
import interfaces.IGame;
import java.io.Serializable;
//</editor-fold>

/**
 * In this class you can find all types and operations for IGameWaiting.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface IGameWaiting extends IGame, Serializable {

    /**
     * This operation lets a user join the game.
     *
     * @param user is the user that wants to join.
     */
    public void join(User user);

    /**
     * @return the game that is going to be played.
     */
    public IGamePlaying start();

    /**
     * This operation lets a user leave the game.
     *
     * @param user is the user that wants to leave.
     */
    public void leave(User user);

}

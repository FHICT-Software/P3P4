//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces.game;

import components.game.Player;
import components.lobby.User;
import interfaces.gui.IGameGUI;
import java.awt.Point;
import java.io.Serializable;
import javafx.scene.control.Label;
//</editor-fold>

/**
 * In this class you can find all types and operations for IGamePlaying.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface IGamePlaying extends IGameViewing, Serializable {

    /**
     * This operation calls the moveBat() operation in Player.
     *
     * @param direction The direction in which the bat it moving.
     */
    public void moveBat(double direction);

    /**
     * This operation lets the game setup the labels (player names, etc).
     * @param label1
     * @param label2
     * @param label3
     */
    public void setLabels(Label label1, Label label2, Label label3);
    
    public Player getPlayer(User user);
    
    public void setGameGUI(IGameGUI gameGUI);

    public void stop();
    
    public void positionUpdater();
}

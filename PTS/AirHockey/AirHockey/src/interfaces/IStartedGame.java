package interfaces;

import components.Player;
import java.util.ArrayList;

public interface IStartedGame extends Runnable {

    /**
     * This operation returns the list of players currently playing in this game.
     *
     * @return
     */
    public ArrayList<Player> getPlayers();

    /**
     * This operation returns the amount of users currently viewing this game.
     *
     * @return
     */
    public int getViewerCount();
}

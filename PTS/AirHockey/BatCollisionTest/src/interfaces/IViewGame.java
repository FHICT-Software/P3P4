package interfaces;

import java.awt.Graphics;

public interface IViewGame {

    /**
     * This returns the graphics component on which the game is drawn.
     *
     * @param userName This name defines from what angle the game is viewed (if the name is unknown, it views from the Host
     *                 Player)
     */
    public Graphics view(String userName);
}

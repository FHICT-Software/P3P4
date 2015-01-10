package interfaces;

import javafx.scene.control.Label;

public interface IPlayingGame extends IViewGame {

    /**
     * This operation calls the moveBat() operation in Player.
     *
     * @param direction The direction in which the bat it moving.
     */
    public void moveBat(double direction);

    /**
     * This operation lets the game setup the labels (player names, etc).
     */
    public void setLabels(Label label1, Label label2, Label label3);
}

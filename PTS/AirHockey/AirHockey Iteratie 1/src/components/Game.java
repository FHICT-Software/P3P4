//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

import calculations.Colission;
import gui.GameGUI;
import interfaces.IDrawable;
import interfaces.ILobbyLoggedIn;
import interfaces.IPlayingGame;
import interfaces.IStartedGame;
import interfaces.IWaitingGame;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

//</editor-fold>
/**
 * In this class you can find all properties and operations for Game. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Game implements IWaitingGame, IStartedGame, IPlayingGame, Runnable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final Lobby lobby;
    private final Player player1;
    private Player player2;
    private Player player3;
    private Puck puck;
    private final Colission colission;
    private final List<Border> borders;
    private GameGUI gameGUI;
    private final List<IDrawable> drawables;
    private int speed;
    private boolean waiting;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(lobby, user)">
    public Game(Lobby lobby, User user, String gameType) {
        this.lobby = lobby;
        player1 = new Player(user, 0);
        puck = new Puck(speed);
        if (gameType.toLowerCase().contains("single")) {
            player2 = new Bot(1);
            player3 = new Bot(2);
        }
        borders = new ArrayList<>();
        generateBorders();
        List<Bat> bats = new ArrayList<>();
        bats.add(player1.getBat());
        bats.add(player2.getBat());
        bats.add(player3.getBat());
        colission = new Colission(puck, bats, borders);
        drawables = new ArrayList<>();
        drawables.add(player1);
        drawables.add(player2);
        drawables.add(player3);
        drawables.add(puck);
        drawables.addAll(borders);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="start">
    /**
     * This method will start a game and return the IStartedGame interface.
     *
     * @return
     */
    @Override
    public IStartedGame start() {
        int averageRating = (player1.getUser().getRating() + player2.getUser().getRating() + player3.getUser().getRating()) / 3;
        puck = new Puck((int) (averageRating));
        gameGUI = new GameGUI(this);
        gameGUI.start();
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SetLabels">
    @Override
    public void setLabels(Label label1, Label label2, Label label3) {
        label1.setText(player1.getScore() + " : " + player1.getUser().getName());
        label2.setText(player2.getScore() + " : " + player2.getUser().getName());
        label3.setText(player3.getScore() + " : " + player3.getUser().getName());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="cancel">
    /**
     * This method will stop the game (nullify the component) and return the ILobbyLoggedIn interface.
     *
     * @return
     */
    @Override
    public ILobbyLoggedIn cancel() {
        throw new UnsupportedOperationException();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlayers">
    /**
     * This operation returns the list of players currently playing in this game.
     *
     * @return
     */
    @Override
    public ArrayList<Player> getPlayers() {
        throw new UnsupportedOperationException();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getViewerCount">
    /**
     * This operation returns the amount of users currently viewing this game.
     *
     * @return
     */
    @Override
    public int getViewerCount() {
        throw new UnsupportedOperationException();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Geberate Borders">
    private void generateBorders() {
        double edgeLength = Math.sqrt((Math.pow(calculations.Drawable.getFieldHeight(), 2)) + (Math.pow((calculations.Drawable.getFieldWidth() / 2), 2))) * 0.3;
        int Ax = 0;
        int Ay = (int) calculations.Drawable.getFieldHeight();
        int Bx = (int) (0 + (Math.cos(Math.toRadians(60)) * edgeLength));
        int By = (int) (calculations.Drawable.getFieldHeight() - (Math.sin(Math.toRadians(60)) * edgeLength));
        int Cx = (int) (calculations.Drawable.getFieldWidth() / 2 - (Math.cos(Math.toRadians(60)) * edgeLength));
        int Cy = (int) (0 + (Math.sin(Math.toRadians(60)) * edgeLength));
        int Dx = calculations.Drawable.getFieldWidth() / 2;
        int Dy = 0;
        int Ex = (int) (calculations.Drawable.getFieldWidth() / 2 + (Math.cos(Math.toRadians(60)) * edgeLength));
        int Ey = (int) (0 + (Math.sin(Math.toRadians(60)) * edgeLength));
        int Fx = (int) (calculations.Drawable.getFieldWidth() - (Math.cos(Math.toRadians(60)) * edgeLength));
        int Fy = (int) (calculations.Drawable.getFieldHeight() - (Math.sin(Math.toRadians(60)) * edgeLength));
        int Gx = calculations.Drawable.getFieldWidth();
        int Gy = (int) calculations.Drawable.getFieldHeight();
        int Hx = (int) (calculations.Drawable.getFieldWidth() / 2 + calculations.Drawable.getFieldWidth() / 5);
        int Hy = (int) calculations.Drawable.getFieldHeight();
        int Ix = (int) (calculations.Drawable.getFieldWidth() / 2 - calculations.Drawable.getFieldWidth() / 5);
        int Iy = (int) calculations.Drawable.getFieldHeight();
        borders.add(new Border(new Point(Ax, Ay), new Point(Bx, By), "Black", false));
        borders.add(new Border(new Point(Cx, Cy), new Point(Dx, Dy), "Black", false));
        borders.add(new Border(new Point(Dx, Dy), new Point(Ex, Ey), "Black", false));
        borders.add(new Border(new Point(Fx, Fy), new Point(Gx, Gy), "Black", false));
        borders.add(new Border(new Point(Gx, Gy), new Point(Hx, Hy), "Black", false));
        borders.add(new Border(new Point(Ix, Iy), new Point(Ax, Ay), "Black", false));
        borders.add(new Border(new Point(Hx, Hy), new Point(Ix, Iy), "Red", true));
        borders.add(new Border(new Point(Ex, Ey), new Point(Fx, Fy), "Green", true));
        borders.add(new Border(new Point(Bx, By), new Point(Cx, Cy), "Blue", true));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="view">
    /**
     * This returns the graphics component on which the game is drawn.
     *
     * @param userName This number defines from what angle the game is viewed (0 for hostPlayer and viewers)
     *
     * @return
     */
    @Override
    public Graphics view(String userName) {
        throw new UnsupportedOperationException();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setDirection">
    /**
     * This operation calls the setDirection() operation in Player.
     *
     * @param direction The direction in which the bat it moving.
     */
    @Override
    public void moveBat(double direction) {
        if (!player1.getBat().isMoving() || direction == 0) {
            if (direction == -1) {
                player1.getBat().setDirection(180);
                player1.getBat().startMoving();
            } else if (direction == 0) {
                player1.getBat().stopMoving();
            } else if (direction == 1) {
                player1.getBat().setDirection(0);
                player1.getBat().startMoving();
            }
        }
    }

    /**
     * This operation calls the setDirection() operation in Player.
     *
     */
    public void moveBat() {
        player1.getBat().moveBat();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="requestQuit()">
    @Override
    public void requestQuit() {
        gameGUI.requestQuit(lobby);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="run()">
    @Override
    public void run() {
        waiting = false;
        int waitingCount = 0;
        int round = 0;
        while (round < 10) {
            try {
                //<editor-fold defaultstate="collapsed" desc="Waiting">
                if (waiting) {
                    //Interval
                    Thread.sleep(10);
                    if (waitingCount < 300) {
                        waitingCount++;
                    } else {
                        waitingCount = 0;
                        round++;
                        if (round > 9) {

                        }
                        waiting = false;
                    }
                } //</editor-fold>
                //<editor-fold desc="Playing">
                else {
                    Thread.sleep(20);
                    puck.move();
                    moveBat();
                    if (player2 instanceof Bot) {
                        ((Bot) player2).preformAction(puck);
                        ((Bot) player3).preformAction(puck);
                    }
                    waiting = Colission.intersects();
                }
                //</editor-fold>
                gameGUI.requestDraw(drawables, waiting, waitingCount, round);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //lobby.Open();
        gameGUI.requestQuit(lobby);
    }
    //</editor-fold>
    //</editor-fold>

    @Override
    public String getWinner() {
        if (player1.getScore() > player2.getScore()){
            if (player1.getScore() > player3.getScore()){
                return player1.toString();
            }else{
                return player3.toString();
            }
        }else{
            if (player2.getScore() > player3.getScore()){
                return player2.toString();
            }else{
                return player3.toString();
            }
        }
    }
}

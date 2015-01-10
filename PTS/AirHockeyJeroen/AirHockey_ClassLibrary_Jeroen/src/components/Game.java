//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

import calculations.Colission;
import components.game.Bat;
import components.game.Border;
import components.game.Bot;
import components.game.Player;
import components.game.Puck;
import components.lobby.User;
import enums.GameState;
import enums.GameType;
import interfaces.IDrawable;
import interfaces.game.IGamePlaying;
import interfaces.game.IGameStarted;
import interfaces.game.IGameWaiting;
import interfaces.gui.IGameGUI;
import java.awt.Point;
import java.io.Serializable;
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
 * @date 2014/05/25
 */
public class Game implements IGamePlaying, IGameStarted, IGameWaiting, Serializable, Runnable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    public static final long serialVersionUID = 42L;
    //<editor-fold defaultstate="collapsed" desc="final">
    private final String name;
    private final GameType gameType;
    private List<Player> players;
    private final List<Border> borders;
    private Player hostPlayer;
    private Player player2;
    private Player player3;
    //</editor-fold>
    private GameState gameState;
    private Puck puck;
    private Colission colission;
    private List<IDrawable> drawables;
    private List<IDrawable> drawablesPuck;
    private List<IDrawable> drawablesBats;
    private int speed;
    private IGameGUI gameGUI;

    private ServerConnecter InGameReaderConnecter;
    private ServerConnecter InGameWriterConnecter;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="gameState">
    @Override
    public GameState getGameState() {
        return gameState;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Players">
    @Override
    public User getHost() {
        return players.get(0).getUser();
    }

    @Override
    public Player getPlayer(int id) {
        if (players.size() > id) {
            return players.get(id);
        } else {
            return null;
        }
    }

    @Override
    public Player getPlayer(User user) {
        Player returner = null;
        for (Player player : players) {
            if (player.getUser().equals(user)) {
                returner = player;
            }
        }
        return returner;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Name">
    @Override
    public String getName() {
        return name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GameType">
    @Override
    public GameType getGameType() {
        return gameType;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor(host, gameName, gameType)">
    /**
     * This is the constructor for Game. This sets the Name, Type, and State of
     * the game.
     *
     * @param host is the host player for this game.
     * @param gameName is the name of the game.
     * @param gameType is the type of game.
     */
    public Game(Player host, String gameName, GameType gameType) {
        this.name = gameName;
        this.gameType = gameType;
        this.gameState = GameState.Waiting;
        this.InGameReaderConnecter = Lobby.getInstance().getGameConnecter();

        borders = new ArrayList<>();
        players = new ArrayList<>();
        this.hostPlayer = host;
        this.players.add(hostPlayer);
        if (gameType == GameType.SingleCompetition || gameType == GameType.SingleFun) {
            this.players.add(new Bot());
            this.players.add(new Bot());
        }
        speed = 10;
        puck = new Puck(speed);
        drawables = new ArrayList<>();
        drawablesBats = new ArrayList<>();
        drawablesPuck = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="join(user)">
    /**
     * This operation lets a user join the game.
     *
     * @param user is the user that wants to join.
     */
    @Override
    public void join(User user) {
        Player joinedPlayer = new Player(user);
        if (players.size() == 1) {
            this.player2 = joinedPlayer;
            players.add(player2);
        } else if (players.size() == 2) {
            this.player3 = joinedPlayer;
            players.add(player3);
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="matchTo(game)">
    @Override
    public void matchTo(Game game) {
        this.gameState = game.gameState;
        this.players = game.players;
        this.colission = game.colission;
        this.drawables = game.drawables;
        this.puck = game.puck;
        this.speed = game.speed;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="generateBorders()">
    private void generateBorders() {

        double edgeLength = Math.sqrt((Math.pow(calculations.Drawable.getFieldHeight(), 2)) + (Math.pow((calculations.Drawable.getFieldWidth() / 2), 2))) * 0.3;
        int edgePart = (int) edgeLength / 8;
        int yHight = (int) edgeLength / 3;

        int Bx = (int) calculations.Drawable.getFieldWidth() / 6;
        int By = (int) (calculations.Drawable.getFieldHeight() - calculations.Drawable.getFieldHeight() / 3 - yHight);

        int B2x = (int) ((calculations.Drawable.getFieldWidth() / 6) + (Math.cos(Math.toRadians(30)) * edgePart));
        int B2y = (int) ((calculations.Drawable.getFieldHeight() - calculations.Drawable.getFieldHeight() / 3) + (Math.sin(Math.toRadians(30)) * edgePart)) - yHight;

        int Cx = (int) (calculations.Drawable.getFieldWidth() / 3);
        int Cy = (int) (calculations.Drawable.getFieldHeight() / 3) - yHight;

        int C2x = (int) ((calculations.Drawable.getFieldWidth() / 3) + (Math.cos(Math.toRadians(30)) * edgePart));
        int C2y = (int) ((calculations.Drawable.getFieldHeight() / 3) + (Math.sin(Math.toRadians(30)) * edgePart)) - yHight;

        int Ex = (int) (calculations.Drawable.getFieldWidth() - calculations.Drawable.getFieldWidth() / 3);
        int Ey = (int) (calculations.Drawable.getFieldHeight() / 3) - yHight;

        int E2x = (int) ((calculations.Drawable.getFieldWidth() - calculations.Drawable.getFieldWidth() / 3) - (Math.cos(Math.toRadians(30)) * edgePart));
        int E2y = (int) ((calculations.Drawable.getFieldHeight() / 3) + (Math.sin(Math.toRadians(30)) * edgePart)) - yHight;

        int Fx = (int) calculations.Drawable.getFieldWidth() - (calculations.Drawable.getFieldWidth() / 6);
        int Fy = (int) (calculations.Drawable.getFieldHeight() - calculations.Drawable.getFieldHeight() / 3) - yHight;

        int F2x = (int) (calculations.Drawable.getFieldWidth() - (calculations.Drawable.getFieldWidth() / 6) - (Math.cos(Math.toRadians(30)) * edgePart));
        int F2y = (int) ((calculations.Drawable.getFieldHeight() - calculations.Drawable.getFieldHeight() / 3) + (Math.sin(Math.toRadians(30)) * edgePart)) - yHight;

        int Hx = (int) (calculations.Drawable.getFieldWidth() - calculations.Drawable.getFieldWidth() / 3);
        int Hy = (int) calculations.Drawable.getFieldHeight() - yHight;

        int H2x = (int) (calculations.Drawable.getFieldWidth() - calculations.Drawable.getFieldWidth() / 3);
        int H2y = (int) calculations.Drawable.getFieldHeight() - edgePart - yHight;

        int Ix = (int) (calculations.Drawable.getFieldWidth() / 3);
        int Iy = (int) calculations.Drawable.getFieldHeight() - yHight;

        int I2x = (int) (calculations.Drawable.getFieldWidth() / 3);
        int I2y = (int) calculations.Drawable.getFieldHeight() - edgePart - yHight;

        //borders.add(new Border(new Point(Ix, Iy), new Point(Hx, Hy), "Red", true));
        borders.add(new Border(new Point(Ix, Iy), new Point(I2x, I2y), "Black", false));
        borders.add(new Border(new Point(I2x, I2y), new Point(B2x, B2y), "Black", false));
        borders.add(new Border(new Point(Bx, By), new Point(B2x, B2y), "Black", false));

        //borders.add(new Border(new Point(Bx, By), new Point(Cx, Cy), "Green", true));
        borders.add(new Border(new Point(Cx, Cy), new Point(C2x, C2y), "Black", false));
        borders.add(new Border(new Point(C2x, C2y), new Point(E2x, E2y), "Black", false));
        borders.add(new Border(new Point(Ex, Ey), new Point(E2x, E2y), "Black", false));

        //borders.add(new Border(new Point(Ex, Ey), new Point(Fx, Fy), "Blue", true));
        borders.add(new Border(new Point(Fx, Fy), new Point(F2x, F2y), "Black", false));
        borders.add(new Border(new Point(F2x, F2y), new Point(H2x, H2y), "Black", false));
        borders.add(new Border(new Point(Hx, Hy), new Point(H2x, H2y), "Black", false));

        if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
            borders.add(new Border(new Point(Ix, Iy), new Point(Hx, Hy), "Red", true));
            borders.add(new Border(new Point(Bx, By), new Point(Cx, Cy), "Green", true));
            borders.add(new Border(new Point(Ex, Ey), new Point(Fx, Fy), "Blue", true));
            hostPlayer.getBat().SetColor("Red");
            if (gameType == GameType.MultiCompetition || gameType == GameType.MultiFun) {
                player3.getBat().SetColor("Blue");
                player2.getBat().SetColor("Green");
            }
        } else if (Lobby.getInstance().getUser().equals(player2.getUser())) {
            borders.add(new Border(new Point(Ix, Iy), new Point(Hx, Hy), "Green", true));
            borders.add(new Border(new Point(Bx, By), new Point(Cx, Cy), "Blue", true));
            borders.add(new Border(new Point(Ex, Ey), new Point(Fx, Fy), "Red", true));
            hostPlayer.getBat().SetColor("Red");
            player3.getBat().SetColor("Blue");
            player2.getBat().SetColor("Green");
        } else if (Lobby.getInstance().getUser().equals(player3.getUser())) {
            borders.add(new Border(new Point(Ix, Iy), new Point(Hx, Hy), "Blue", true));
            borders.add(new Border(new Point(Bx, By), new Point(Cx, Cy), "Red", true));
            borders.add(new Border(new Point(Ex, Ey), new Point(Fx, Fy), "Green", true));
            hostPlayer.getBat().SetColor("Red");
            player3.getBat().SetColor("Blue");
            player2.getBat().SetColor("Green");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="rotatePoint(p, player)">
    public Point rotatePoint(Point p, int player) {
        double edgeLength = Math.sqrt((Math.pow(calculations.Drawable.getFieldHeight(), 2)) + (Math.pow((calculations.Drawable.getFieldWidth() / 2), 2))) * 0.3;
        int edgePart = (int) edgeLength / 8;
        int yHight = (int) edgeLength / 3;
        double centerX = calculations.Drawable.getFieldWidth() / 2;
        double centerY = calculations.Drawable.getFieldHeight() / 2 - yHight;
        Point returnPoint = null;

        if (player == 1) {
            returnPoint = p;
        } else if (player == 2) {
            Point newPoint = new Point(p);
            double rad = (120 * Math.PI) / 180;
            int newX = (int) (Math.cos(rad) * (p.x - centerX) - Math.sin(rad) * (p.y - centerY) + centerX);
            int newY = (int) (Math.sin(rad) * (p.x - centerX) + Math.cos(rad) * (p.y - centerY) + centerY);
            newPoint.x = newX;
            newPoint.y = newY;
            returnPoint = newPoint;
        } else if (player == 3) {
            Point newPoint = new Point(p);
            double rad = (240 * Math.PI) / 180;
            int newX = (int) (Math.cos(rad) * (p.x - centerX) - Math.sin(rad) * (p.y - centerY) + centerX);
            int newY = (int) (Math.sin(rad) * (p.x - centerX) + Math.cos(rad) * (p.y - centerY) + centerY);
            newPoint.x = newX;
            newPoint.y = newY;
            returnPoint = newPoint;
        }
        return returnPoint;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="moveBat(direction)">
    @Override
    public void moveBat(double direction) {
        if (!players.get(0).getBat().isMoving() || direction == 0) {
            if (direction == -1) {
                players.get(0).getBat().setDirection(180);
                players.get(0).getBat().startMoving();
            } else if (direction == 0) {
                players.get(0).getBat().stopMoving();
            } else if (direction == 1) {
                players.get(0).getBat().setDirection(0);
                players.get(0).getBat().startMoving();
            }
        }
    }

    public void moveBat() {
        players.get(0).getBat().moveBat();
//        String message = "Bat P1 Updated";
//        InGameConnecter.sendObjectToServer(message);
//        InGameConnecter.sendObjectToServer(players.get(0).getBat().getPosition());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setLabels(label1, label2, label3)">
    @Override
    public void setLabels(Label label1, Label label2, Label label3) {
        return;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="start()">
    @Override
    public IGamePlaying start() {
        if (gameState != GameState.Started) {
            List<Bat> bats = new ArrayList<>();
            bats.add(players.get(0).getBat());
            bats.add(players.get(1).getBat());
            bats.add(players.get(2).getBat());
            colission = new Colission(puck, bats, borders);
            drawables.add(players.get(0));
            drawables.add(players.get(1));
            drawables.add(players.get(2));

            this.gameState = GameState.Started;
            String message = "Start Game";
        }
        return this;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Class">
    @Override
    public boolean equals(Object o) {
        if (o instanceof Game) {
            return this.equals((Game) o);
        } else {
            return false;
        }
    }

    public boolean equals(Game game) {
        return this.getHost().equals(game.getHost());
    }

    @Override
    public String toString() {
        return this.name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="run()">
    @Override
    public void run() {
//        this.InGameConnecter = Lobby.getInstance().getGameConnecter();
//        startGameListener(gameGUI);
//        

        setOtherPlayers();
        while (true) {
            try {
                //Interval
                Thread.sleep(10);

                //Move Bat
                moveBat();

                //Move Bots
                if (players.get(1) instanceof Bot) {
                    ((Bot) players.get(1)).preformAction(puck);
                    ((Bot) players.get(2)).preformAction(puck);
                }

                //Move Puck      
                if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
                    puck.move();
                    gameGUI.requestDraw(drawables);
                    if (Colission.intersects()) {
                        puck.reset();
                    }
                } else if (Lobby.getInstance().getUser().equals(player2.getUser())) {

                    puck.move();
                    gameGUI.requestDraw(drawables);
                    if (Colission.intersects()) {
                        puck.reset();
                    }
                } else if (Lobby.getInstance().getUser().equals(player3.getUser())) {

                    puck.move();
                    gameGUI.requestDraw(drawables);
                    if (Colission.intersects()) {
                        puck.reset();
                    }
                }

                //Check Collision
                //Redraw
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //</editor-fold>

    @Override
    public void setGameGUI(IGameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    @Override
    public void stop() {
        setGameGUI(null);
        Lobby.getInstance().removeGame(this);
    }

    @Override
    public void leave(User user) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUser().equals(user)) {
                players.remove(i);
            }
        }
    }

    public void setOtherPlayers() {
        List<Bat> bats = new ArrayList<>();
        if (gameType == GameType.SingleCompetition || gameType == GameType.SingleFun) {

        } else {
            this.hostPlayer = players.get(0);
            this.player2 = players.get(1);
            this.player3 = players.get(2);
            puck = new Puck(speed);
//            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
//                hostPlayer.getBat().setPosition(rotatePoint(hostPlayer.getBat().getPosition(), 2));
//                player2.getBat().setPosition(rotatePoint(player2.getBat().getPosition(), 2));
//                player3.getBat().setPosition(rotatePoint(player3.getBat().getPosition(), 2));
//            } else if (Lobby.getInstance().getUser().equals(player3.getUser())) {
//                hostPlayer.getBat().setPosition(rotatePoint(hostPlayer.getBat().getPosition(), 3));
//                player2.getBat().setPosition(rotatePoint(player2.getBat().getPosition(), 2));
//                player3.getBat().setPosition(rotatePoint(player3.getBat().getPosition(), 2));
//            }
        }
        bats.add(players.get(0).getBat());
        bats.add(players.get(1).getBat());
        bats.add(players.get(2).getBat());
        borders.clear();
        generateBorders();
        colission = new Colission(puck, bats, borders);
        drawables.clear();
        drawables.add(puck);
        drawables.addAll(borders);
        drawables.add(players.get(0));
        drawables.add(players.get(1));
        drawables.add(players.get(2));

    }

    public boolean inGameConnect() {
        boolean returner = false;
        returner = InGameReaderConnecter.connect();
        returner = InGameWriterConnecter.connect();
        if (returner) {
            String message = "Setup IngameConnection";
            InGameReaderConnecter.sendObjectToServer(message);
            InGameWriterConnecter.sendObjectToServer(message);
        }
        return returner;
    }

    public void startGameListener(IGameGUI gUIgame) {
        new Thread(() -> {
            inGameConnect();
            while (true) {
                String message = InGameReaderConnecter.readMessageFromServer();
                switch (message) {
                    case "Bat P1 Updated": {
                        Point pbat = (Point) InGameReaderConnecter.readObjectFromServer();
                        for (Player p : players) {
                            if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
                                p.getBat().setPosition(pbat);
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 2));
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 3));
                            }
                        }
                        drawablesBats.clear();
                        drawablesBats.add(players.get(0));
                        drawablesBats.add(players.get(1));
                        drawablesBats.add(players.get(2));
                        gUIgame.requestDraw(drawablesBats);
                        break;
                    }
                    case "Bat P2 Updated": {
                        Point pbat = (Point) InGameReaderConnecter.readObjectFromServer();
                        for (Player p : players) {
                            if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 3));
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(pbat);
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 2));
                            }
                        }
                        drawablesBats.clear();
                        drawablesBats.add(players.get(0));
                        drawablesBats.add(players.get(1));
                        drawablesBats.add(players.get(2));
                        gUIgame.requestDraw(drawablesBats);
                        break;
                    }
                    case "Bat P3 Updated": {
                        Point pbat = (Point) InGameReaderConnecter.readObjectFromServer();
                        for (Player p : players) {
                            if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 2));
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(pbat, 3));
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(pbat);
                            }
                        }
                        drawablesBats.clear();
                        drawablesBats.add(players.get(0));
                        drawablesBats.add(players.get(1));
                        drawablesBats.add(players.get(2));
                        gUIgame.requestDraw(drawablesBats);
                        break;
                    }
                    case "Puck Position Updated": {
                        Point ppuck = (Point) InGameReaderConnecter.readObjectFromServer();
                        for (Player p : players) {
                            if (Lobby.getInstance().getUser().equals(hostPlayer.getUser())) {
                                p.getBat().setPosition(ppuck);
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(ppuck, 2));
                            }
                            if (Lobby.getInstance().getUser().equals(player2.getUser())) {
                                p.getBat().setPosition(rotatePoint(ppuck, 3));
                            }
                        }
                        drawablesPuck.clear();
                        drawablesPuck.add(players.get(0));
                        drawablesPuck.add(players.get(1));
                        drawablesPuck.add(players.get(2));
                        gUIgame.requestDraw(drawablesPuck);
                        break;
                    }
                }
            }
        }).start();
    }

    public void sendBat1Position(Point p) {
        String message = "Bat Position P1";
        InGameWriterConnecter.sendObjectToServer(message);
        InGameWriterConnecter.sendObjectToServer(p);
    }

    public void sendBat2Position(Point p) {
        String message = "Bat Position P2";
        InGameWriterConnecter.sendObjectToServer(message);
        InGameWriterConnecter.sendObjectToServer(p);
    }

    public void sendBat3Position(Point p) {
        String message = "Bat Position P3";
        InGameWriterConnecter.sendObjectToServer(message);
        InGameWriterConnecter.sendObjectToServer(p);
    }

    public void sendPuckPosition(Point p) {
        String message = "Puck Position";
        InGameReaderConnecter.sendObjectToServer(message);
        InGameReaderConnecter.sendObjectToServer(p);
    }

    @Override
    public void positionUpdater() {
        if (gameType == GameType.MultiCompetition || gameType == GameType.MultiFun) {
            sendBat1Position(hostPlayer.getBat().getPosition());
            sendBat2Position(player2.getBat().getPosition());
            sendBat3Position(player3.getBat().getPosition());
            sendPuckPosition(puck.getPosition());
        } else {
        }
    }

}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import container.GUIs;
import interfaces.game.IGamePlaying;
import interfaces.gui.IGameGUI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//</editor-fold>

/**
 * In this class you can find all properties and operations for GameGUI. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/03/26
 */
public class GameGUI extends GUI implements IGameGUI {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private int width = 500;
    private int height = 500;
    Label player1Label;
    Label player2Label;
    Label player3Label;
    private Canvas canvas;
    private final IGamePlaying game;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(game)">
    public GameGUI(IGamePlaying game) {
        super(new Stage());
        GUIs.setGame(this);
        game.setGameGUI(this);
        this.game = game;
    }

    public void start() {
        //<editor-fold defaultstate="collapsed" desc="Grid">
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Group">
        Group group = new Group();
        group.getChildren().add(grid);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Canvas">
        canvas = new Canvas(width, height);
        grid.add(canvas, 0, 0, 25, 25);

        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Labels">
        player1Label = new Label("");
        player2Label = new Label("");
        player3Label = new Label("");
        grid.add(player1Label, 0, 0);
        grid.add(player2Label, 0, 1);
        grid.add(player3Label, 0, 2);
        //game.setLabels(player1Label, player2Label, player3Label);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Scene">
        Scene scene = new Scene(group, width + 50, height + 50);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Stage">
        STAGE.setTitle("GameGUI");
        STAGE.setScene(scene);
        STAGE.setOnCloseRequest((WindowEvent t) -> {
            GUIs.getLobby().show();
            game.stop();
        });
        STAGE.show();
        addKeyEvents(STAGE);
        //primaryStage.setFullScreen(true);
        //</editor-fold>

        new Thread(() -> {
            try {
                Thread.sleep(500);
                game.positionUpdater();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Keyboard">
    private void addKeyEvents(final Stage stage) {
        //<editor-fold defaultstate="collapsed" desc="Key Presses">
        final EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) {
                    game.moveBat(-1);
                } else if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) {
                    game.moveBat(1);
                }
            }
        };
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Key Released">
        final EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                game.moveBat(0);
            }
        };
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="addEvents">
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyPressed);
        stage.addEventFilter(KeyEvent.KEY_RELEASED, keyReleased);
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="requestDraw()">
    @Override
    public void requestDraw(final List<interfaces.IDrawable> drawables) {
        //<editor-fold defaultstate="collapsed" desc="Initialize">
        width = (int) STAGE.getWidth() - 50;
        height = (int) STAGE.getHeight() - 78;
        calculations.Drawable.updateCalculations(width, height);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Run Later">
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                draw(drawables);
            }
        });
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(drawables)">
    public void draw(List<interfaces.IDrawable> drawables) {
        //<editor-fold defaultstate="collapsed" desc="Initialize">
        canvas.setWidth(width);
        canvas.setHeight(height);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Draw Components">
        for (interfaces.IDrawable drawable : drawables) {
            drawable.draw(graphicsContext);
        }
        //</editor-fold>

        //graphicsContext.drawImage(new Image("tribune.png"), 0, 0, 50, 50);
        //game.setLabels(player1Label, player2Label, player3Label);
    }
    //</editor-fold>
    //</editor-fold>
}

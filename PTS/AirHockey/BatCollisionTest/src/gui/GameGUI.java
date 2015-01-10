//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import components.Game;
import interfaces.IPlayingGame;
import java.util.List;
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
public class GameGUI {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private int width = 500;
    private int height = 500;
    Label player1Label;
    Label player2Label;
    Label player3Label;
    private Stage primaryStage;
    private Canvas canvas;
    private final IPlayingGame game;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public GameGUI(Game game) {
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

        //<editor-fold defaultstate="collapsed" desc="Scene">
        Scene scene = new Scene(group, width + 50, height + 50);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Stage">
        primaryStage = new Stage();
        primaryStage.setTitle("GameGUI");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(1);
            }
        });
        primaryStage.show();
        addKeyEvents(primaryStage);
        //primaryStage.setFullScreen(true);
        //</editor-fold>
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
    public void requestDraw(final List<interfaces.IDrawable> drawables) {
        //<editor-fold defaultstate="collapsed" desc="Initialize">
        width = (int) primaryStage.getWidth() - 50;
        height = (int) primaryStage.getHeight() - 78;
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
        game.setLabels(player1Label, player2Label, player3Label);
    }
    //</editor-fold>
    //</editor-fold>
}

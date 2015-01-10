package gui;

import components.Game;
import components.Lobby;
import components.User;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class AirHockey extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(new Lobby(), new User("Jeroen", 15), "SingleFun");
        game.start();
        Thread thread = new Thread(game);
        thread.start();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

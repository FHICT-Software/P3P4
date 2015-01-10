//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//</editor-fold>

/**
 * This is the main Login interface.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/24
 */
public class Login extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="FINALS">
    private Stage STAGE;
    private final ILobbyNotLoggedIn LOBBY = components.Lobby.getInstance();
    //</editor-fold>
    private TextField usernameTextField;
    private TextField passwordTextField;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="start(stage)">
    @Override
    public void start(Stage stage) {

        //<editor-fold defaultstate="collapsed" desc="GUI">
        //<editor-fold defaultstate="collapsed" desc="Main Pane">
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //<editor-fold defaultstate="collapsed" desc="Username Label">
        Label usernameLabel = new Label();
        usernameLabel.setText("Username");
        gridPane.add(usernameLabel, 3, 9);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="password Label">
        Label passwordLabel = new Label();
        passwordLabel.setText("Password");
        gridPane.add(passwordLabel, 3, 10);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Username TextField">
        usernameTextField = new TextField();
        gridPane.add(usernameTextField, 5, 9);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Password TextField">
        passwordTextField = new TextField();
        gridPane.add(passwordTextField, 5, 10);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Login Button">
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                login(usernameTextField.getText(), passwordTextField.getText());
            }
        });
        gridPane.add(loginButton, 5, 11);
        //</editor-fold>

        borderPane.setCenter(gridPane);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Scene / Stage">
        Scene scene = new Scene(borderPane, 300, 250);
        stage.setTitle("Login - MP-Hockey");
        stage.setScene(scene);
        stage.show();
        this.STAGE = stage;
        //</editor-fold>
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login(username, password)">
    /**
     * This operation checks if it can login to the Lobby. Depending on the server return, the Lobby will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     */
    private void login(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            ILobbyLoggedIn lobbyLoggedIn = LOBBY.login(username, password);
            if (lobbyLoggedIn != null) {
                gui.Lobby lobbyGUI = new gui.Lobby(lobbyLoggedIn);
                lobbyGUI.start(new Stage());
                STAGE.close();
            } else {
                //TODO give notice of invalid login.
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="main(args)">
    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
     * main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    //</editor-fold>
    //</editor-fold>
}

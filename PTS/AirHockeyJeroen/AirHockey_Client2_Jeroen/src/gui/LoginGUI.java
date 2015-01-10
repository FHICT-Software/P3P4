//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//</editor-fold>

/**
 * In this class you can find all properties and operations for LoginGUI. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/27
 */
public class LoginGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Stage STAGE;
    private static final ILobbyNotLoggedIn LOBBY = components.Lobby.getInstance();
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label errorLabel;
    private int tryCount = 0;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    @Override
    public void start(Stage stage) {
        //<editor-fold defaultstate="collapsed" desc="GUI">
        //<editor-fold defaultstate="collapsed" desc="Main Pane">
        BorderPane borderPane = new BorderPane();
        errorLabel = new Label();
        errorLabel.setVisible(false);
        borderPane.setTop(errorLabel);
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
        gridPane.add(usernameTextField, 5, 9, 2, 1);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Password TextField">
        passwordTextField = new PasswordField();
        gridPane.add(passwordTextField, 5, 10, 2, 1);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Login Button">
        Button loginButton = new Button();
        loginButton.setDefaultButton(true);
        loginButton.setText("Login");
        loginButton.setOnAction((ActionEvent event) -> {
            login(usernameTextField.getText(), passwordTextField.getText());
        });
        gridPane.add(loginButton, 5, 11);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Register Button">
        Button registerButton = new Button();
        registerButton.setText("Register");
        registerButton.setOnAction((ActionEvent event) -> {
            register(usernameTextField.getText(), passwordTextField.getText());
        });
        gridPane.add(registerButton, 6, 11);
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

        //<editor-fold defaultstate="collapsed" desc="Server Connection">
        Thread thread = new Thread(() -> {
            boolean connected = false;
            while (!connected) {
                connected = LOBBY.loginConnect();
                if (!connected) {
                    registerButton.setDisable(true);
                    tryCount++;
                    Platform.runLater(() -> {
                        errorLabel.setText("Could not connect... Retry in " + tryCount * 5 + " seconds");
                        errorLabel.setVisible(true);
                    });
                }
            }
            Platform.runLater(() -> {
                registerButton.setDisable(false);
                errorLabel.setVisible(false);
            });
        });
        thread.start();
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login(username, password)">
    /**
     * This operation checks if it can login to the LobbyGUI. Depending on the server return, the LobbyGUI will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     */
    private void login(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            ILobbyLoggedIn lobbyLoggedIn = LOBBY.login(username, password);
            if (lobbyLoggedIn != null) {
                gui.LobbyGUI lobbyGUI = new gui.LobbyGUI();
                lobbyGUI.start();
                STAGE.close();
            } else {
                //TODO give notice of invalid login.
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="register(username, password)">
    /**
     * This operation checks if it can login to the LobbyGUI. Depending on the server return, the LobbyGUI will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     */
    private void register(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            ILobbyLoggedIn lobbyLoggedIn = LOBBY.register(username, password);
            if (lobbyLoggedIn != null) {
                gui.LobbyGUI lobbyGUI = new gui.LobbyGUI();
                lobbyGUI.start();
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
}

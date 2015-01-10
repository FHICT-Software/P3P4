//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import components.Game;
import container.GUIs;
import enums.GameState;
import interfaces.IGame;
import interfaces.ILobbyLoggedIn;
import interfaces.game.IGameWaiting;
import interfaces.gui.ILobbyGUI;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//</editor-fold>

/**
 * This is the main Login interface.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/24
 */
public class LobbyGUI extends GUI implements ILobbyGUI {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="FINALS">
    private final ILobbyLoggedIn LOBBY;
    //</editor-fold>
    private static Socket clientSocket = null;
    private static ObjectInputStream is = null;
    private static ObjectOutputStream os = null;

    //<editor-fold defaultstate="collapsed" desc="GUI Components">
    public ObservableList<IGame> gameListItems;
    public ListView<IGame> gameList = new ListView<>();
    private Label gameNameLabel = new Label();
    private Label creatorLabel = new Label();
    private Label gameTypeLabel = new Label();
    private Label gameStateLabel = new Label();
    private Label player1Label = new Label();
    private Label player2Label = new Label();
    private Label player3Label = new Label();
    private TextField messageField = new TextField();
    private TextArea messagesArea = new TextArea();
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters, Setters, Adders, Removers">
    //<editor-fold defaultstate="collapsed" desc="GameList">
    /**
     * This is the getter for the GameList (items).
     *
     * @return the items in the GameList.
     */
    public ObservableList<IGame> getGameListItems() {
        return gameListItems;
    }

    /**
     * This is the setter for the GameList (items).
     *
     * @param items is the new list of items you want to set.
     */
    public void setGameListItems(ObservableList<IGame> items) {
        this.gameListItems = items;
    }

    /**
     * This is the getter for the GameList (items).
     *
     * @param index is the position of the item in the list.
     *
     * @return a single item in the list.
     */
    public IGame getGameListItem(int index) {
        return this.gameListItems.get(index);
    }

    /**
     * This is the adder for the GameList (items).
     *
     * @param item this is the item that needs to be added.
     */
    @Override
    public void addGameListItem(IGame item) {
        this.gameListItems.add(item);
    }

    /**
     * This is the remover for the GameList (items).
     *
     * @param item is the item that needs to be removed
     */
    @Override
    public void removeGameListItem(IGame item) {
        if (gameListItems.contains(item)) {
            Platform.runLater(() -> {
                gameListItems.remove(item);
            });
        }
    }

    /**
     * This is the updater for the GameList (items).
     *
     * @param item is the item that needs to be updated
     *
     */
    @Override
    public void updateGameListItem(IGame item) {
        for (IGame gameListItem : gameListItems) {
            if (gameListItem.equals(item)) {
                gameListItem.matchTo((Game) item);
                if (gameListItems.indexOf(gameListItem) == gameList.getSelectionModel().getSelectedIndex()) {
                    Platform.runLater(() -> {
                        gameList.getSelectionModel().clearSelection();
                        gameList.getSelectionModel().select(item);
                    });
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Message">
    @Override
    public void addMessage(String message) {
        this.messagesArea.appendText(message + "\n");
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(lobby)">
    public LobbyGUI() {
        super(new Stage());
        GUIs.setLobby(this);
        this.LOBBY = components.Lobby.getInstance();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="start(stage)">
    public void start() {
        //<editor-fold defaultstate="collapsed" desc="Main Pane">
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //<editor-fold defaultstate="collapsed" desc="Find Game TextField">
        TextField findGameTextField = new TextField();
        gridPane.add(findGameTextField, 13, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Chat">
        messagesArea.setEditable(false);
        gridPane.add(messagesArea, 11, 7, 3, 3);
        gridPane.add(messageField, 1, 10, 13, 1);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Buttons">
        //<editor-fold defaultstate="collapsed" desc="Join Game Button">
        Button joinButton = new Button("Join");
        joinButton.setDisable(true);
        joinButton.setOnAction((ActionEvent event) -> {
            IGameWaiting game = (IGameWaiting) gameList.getSelectionModel().getSelectedItem();
            LOBBY.joinGame(game);
            GameLobbyGUI gameLobbyGUI = new GameLobbyGUI(LOBBY, game);
            gameLobbyGUI.start();
        });
        gridPane.add(joinButton, 11, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create Game Button">
        Button createGameButton = new Button("Create Game");
        createGameButton.setOnAction((ActionEvent t) -> {
            GameLobbyGUI gameLobby = new GameLobbyGUI(LOBBY);
            gameLobby.start();
        });

        gridPane.add(createGameButton, 12, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Find Game Button">
        Button findGameButton = new Button("Find");
        findGameButton.setOnAction((ActionEvent event) -> {
        });
        findGameButton.setDisable(true);
        gridPane.add(findGameButton, 14, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Send Message Button">
        Button sendButton = new Button("Send");
        sendButton.setDefaultButton(true);
        sendButton.setOnAction((ActionEvent t) -> {
            String message = messageField.getText();
            if (message.length() > 0) {
                LOBBY.chatSendMessage(message);
            }
            messageField.clear();
        });
        gridPane.add(sendButton, 14, 10);
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Game List">
        gameListItems = FXCollections.observableArrayList();
        gameList.setItems(gameListItems);
        gameList.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends IGame> observable, IGame oldValue, IGame newValue) -> {
            if (newValue != null) {
                if (newValue.getGameState() == GameState.Waiting) {
                    joinButton.setDisable(false);
                } else {
                    joinButton.setDisable(true);
                }
                gameNameLabel.setText(newValue.getName());
                creatorLabel.setText(newValue.getHost().toString());
                gameTypeLabel.setText(newValue.getGameType().toString());
                gameStateLabel.setText(newValue.getGameState().toString());
                player1Label.setText(newValue.getPlayer(0).toString());
                if (newValue.getPlayer(1) != null) {
                    player2Label.setText(newValue.getPlayer(1).toString());
                } else {
                    player2Label.setText("");
                }
                if (newValue.getPlayer(2) != null) {
                    player3Label.setText(newValue.getPlayer(2).toString());
                } else {
                    player3Label.setText("");
                }
            }
        });
        gridPane.add(gameList, 1, 1, 10, 9);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Details">
        //<editor-fold defaultstate="collapsed" desc="Descriptive Labels">
        gridPane.add(new Label("Game Name:"), 11, 2);
        gridPane.add(new Label("Creator:"), 11, 3);
        gridPane.add(new Label("Game Type:"), 11, 4);
        gridPane.add(new Label("Current Players:"), 11, 5);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Value Labels">
        gridPane.add(gameNameLabel, 12, 2);
        gridPane.add(creatorLabel, 12, 3);
        gridPane.add(gameTypeLabel, 12, 4);
        gridPane.add(player1Label, 12, 5);
        gridPane.add(player2Label, 13, 5);
        gridPane.add(player3Label, 14, 5);
        //</editor-fold>
        //</editor-fold>

        borderPane.setCenter(gridPane);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Get Games">
        List<IGame> games = LOBBY.getGames();
        gameListItems.addAll(games);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create Listener">
        LOBBY.startListener(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Scene / Stage">
        Scene scene = new Scene(borderPane, 700, 400);
        STAGE.setTitle("Lobby - MP-Hockey");
        STAGE.setScene(scene);
        STAGE.show();
        STAGE.setOnCloseRequest((WindowEvent event) -> {
            LOBBY.logout();
            System.exit(0);
        });
        messageField.requestFocus();
        //</editor-fold>
    }
    //</editor-fold>
    //</editor-fold>
}

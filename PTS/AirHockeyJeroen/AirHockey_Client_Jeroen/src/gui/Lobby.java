//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import enums.GameState;
import enums.GameType;
import interfaces.ILobbyLoggedIn;
import interfaces.game.IGame;
import interfaces.game.IGameWaiting;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class Lobby extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="FINALS">
    private final ILobbyLoggedIn LOBBY;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GUI Components">
    private Stage STAGE;
    private ObservableList<IGame> gameListItems;
    private ListView<IGame> gameList = new ListView<>();
    private Label gameNameLabel = new Label();
    private Label creatorLabel = new Label();
    private Label gameTypeLabel = new Label();
    private Label gameStateLabel = new Label();
    private Label player1Label = new Label();
    private Label player2Label = new Label();
    private Label player3Label = new Label();
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
    public void addGameListItem(IGame item) {
        this.gameListItems.add(item);
    }

    /**
     * This is the remover for the GameList (items).
     *
     * @param item is the item that needs to be removed
     *
     * @return if the item could be removed.
     */
    public boolean removeGameListItem(IGame item) {
        if (gameListItems.contains(item)) {
            gameListItems.remove(item);
            return true;
        } else {
            return false;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(lobby)">
    public Lobby(ILobbyLoggedIn lobby) {
        this.LOBBY = lobby;
        //Because Lobby is a singleton, I could as well just have called the Lobby.getInstance(). But I think this is better.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="start(stage)">
    @Override
    public void start(Stage stage) {
        //<editor-fold defaultstate="collapsed" desc="Main Pane">
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        //<editor-fold defaultstate="collapsed" desc="Buttons">
        //<editor-fold defaultstate="collapsed" desc="Join Game Button">
        Button joinButton = new Button("Join");
        joinButton.setDisable(true);
        joinButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                IGameWaiting game = (IGameWaiting) gameList.getSelectionModel().getSelectedItem();
                LOBBY.joinGame(game);
                //TODO goto Game Lobby
            }
        });
        gridPane.add(joinButton, 11, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create Game Button">
        Button createGameButton = new Button("Create Game");
        createGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Ask the user for the name and type first.
                IGameWaiting newGame = LOBBY.createGame("TestName", GameType.SingleFun);
                gameListItems.add(newGame);
            }
        });
        gridPane.add(createGameButton, 12, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Find Game TextField">
        TextField findGameTextField = new TextField();
        gridPane.add(findGameTextField, 13, 6);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Find Game Button">
        Button findGameButton = new Button("Find");
        findGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //TODO (get textField Text, Find Game, clear textField Text)
            }
        });
        gridPane.add(findGameButton, 14, 6);
        //</editor-fold>
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Game List">
        gameListItems = FXCollections.observableArrayList();
        gameList.setItems(gameListItems);
        gameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IGame>() {

            @Override
            public void changed(ObservableValue<? extends IGame> observable, IGame oldValue, IGame newValue) {
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
        gridPane.add(gameList, 1, 1, 10, 10);
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

        //<editor-fold defaultstate="collapsed" desc="Scene / Stage">
        Scene scene = new Scene(borderPane, 700, 400);
        stage.setTitle("Lobby - MP-Hockey");
        stage.setScene(scene);
        stage.show();
        this.STAGE = stage;
        //</editor-fold>
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

//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import components.game.Player;
import container.GUIs;
import enums.GameState;
import enums.GameType;
import interfaces.IGame;
import interfaces.ILobbyLoggedIn;
import interfaces.game.IGamePlaying;
import interfaces.game.IGameWaiting;
import interfaces.gui.ILobbyGUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//</editor-fold>

/**
 *
 * @author JMAGPeeters
 */
public class GameLobbyGUI extends GUI implements ILobbyGUI {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private IGameWaiting game;
    private TextField titelTextField;
    private RadioButton rbRanked;
    private RadioButton rbNormal;
    private RadioButton rbAI;
    private RadioButton rbPlayers;
    private Label titelLabel;
    private Label speltypeLabel;
    private Label offOnlineLabel;
    private Label waitingForPlayer;
    private Button btnStartGame;
    private Button btnSetUp;
    private Button btnCancel;
    private Button btnLeave;
    private ToggleGroup groupSpeltype;
    private ToggleGroup groupOffOnline;
    private ObservableList<Player> playerListItems;
    private ListView<Player> playerList = new ListView<>();
    private GameType chosenType;
    private GameState gameState;
    private String gameTitel;
    private int playerCount;
    private ILobbyLoggedIn LOBBY;
    private Player hostPlayer;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="Player List Items">
    public ObservableList<Player> getPlayerListItems() {
        return playerListItems;
    }

    public void setPlatyerListItems(ObservableList<Player> items) {
        this.playerListItems = items;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Game Type">
    public GameType getGameType() {
        if (rbAI.isSelected() && rbNormal.isSelected()) {
            chosenType = GameType.SingleFun;
        }
        if (rbAI.isSelected() && rbRanked.isSelected()) {
            chosenType = GameType.SingleCompetition;
        }
        if (rbPlayers.isSelected() && rbRanked.isSelected()) {
            chosenType = GameType.MultiCompetition;
        }
        if (rbPlayers.isSelected() && rbNormal.isSelected()) {
            chosenType = GameType.MultiFun;
        }
        return chosenType;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Game State">
    public GameState getGameState() {
        if (rbPlayers.isSelected()) {
            if (playerCount < 3) {
                gameState = getGameState().Waiting;
            } else {
                gameState = null;
            }
        } else {
            gameState = null;
        }
        return gameState;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public GameLobbyGUI(ILobbyLoggedIn lobbyInlog) {
        super(new Stage());
        GUIs.setGameLobby(this);
        STAGE.initModality(Modality.APPLICATION_MODAL);
        this.LOBBY = lobbyInlog;
        playerListItems = FXCollections.observableArrayList();
        components.Lobby.getInstance().setGUIGameLobby(this);
    }

    public GameLobbyGUI(ILobbyLoggedIn lobbyInlog, IGameWaiting game) {
        super(new Stage());
        GUIs.setGameLobby(this);
        STAGE.initModality(Modality.APPLICATION_MODAL);
        this.LOBBY = lobbyInlog;
        playerListItems = FXCollections.observableArrayList();
        components.Lobby.getInstance().setGUIGameLobby(this);
        this.game = game;
    }

    public void start() {
        //<editor-fold defaultstate="collapsed" desc="GUI">
        //<editor-fold defaultstate="collapsed" desc="Main Pane">
        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        titelLabel = new Label();
        titelLabel.setText("Game titel:");
        gridPane.add(titelLabel, 1, 1);

        titelTextField = new TextField();
        gridPane.add(titelTextField, 5, 1);

        waitingForPlayer = new Label();
        waitingForPlayer.setText("Waiting for other players...");
        gridPane.add(waitingForPlayer, 5, 5);
        waitingForPlayer.setVisible(false);

        groupSpeltype = new ToggleGroup();
        rbNormal = new RadioButton("Fun game");
        rbNormal.setSelected(true);
        rbRanked = new RadioButton("Ranked");
        rbNormal.setToggleGroup(groupSpeltype);
        rbRanked.setToggleGroup(groupSpeltype);
        gridPane.add(rbNormal, 1, 3);
        gridPane.add(rbRanked, 5, 3);
        rbRanked.setDisable(true);

        groupOffOnline = new ToggleGroup();
        rbPlayers = new RadioButton("Multiplayer (other players)");
        rbAI = new RadioButton("Singleplayer (computer-players)");
        rbPlayers.setSelected(true);
        rbAI.setToggleGroup(groupOffOnline);
        rbPlayers.setToggleGroup(groupOffOnline);
        gridPane.add(rbAI, 5, 4);
        gridPane.add(rbPlayers, 1, 4);

        playerList.setItems(playerListItems);
        gridPane.add(playerList, 1, 5);

        btnSetUp = new Button("Set up game");
        btnCancel = new Button("Cancel");
        btnLeave = new Button("Leave");
        btnStartGame = new Button("Start");
        gridPane.add(btnSetUp, 1, 6);
        gridPane.add(btnCancel, 1, 6);
        gridPane.add(btnLeave, 1, 6);
        gridPane.add(btnStartGame, 5, 6);
        btnSetUp.setDisable(true);
        btnCancel.setVisible(false);
        btnLeave.setVisible(false);
        btnStartGame.setDisable(true);

        rbAI.setOnAction((ActionEvent t) -> {
            if (rbAI.isSelected()) {
                btnStartGame.setDisable(false);
                btnSetUp.setDisable(true);
            }
        });

        titelTextField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            btnSetUp.setDisable(false);
        });

        rbPlayers.setOnAction((ActionEvent t) -> {
            if (!titelTextField.getText().equals("")) {
                btnSetUp.setDisable(false);
                btnStartGame.setDisable(true);
            } else {
            }
            btnStartGame.setDisable(true);
        });

        btnSetUp.setOnAction((ActionEvent t) -> {
            chosenType = getGameType();
            gameTitel = titelTextField.getText();
            game = LOBBY.createGame(gameTitel, chosenType);
            if (game != null) {
                waitingForPlayer.setText("Waiting for other players...");
                waitingForPlayer.setVisible(true);
                titelTextField.setDisable(true);
                rbAI.setDisable(true);
                rbNormal.setDisable(true);
                rbPlayers.setDisable(true);
                rbRanked.setDisable(true);
                playerListItems.add(game.getPlayer(0));
                btnSetUp.setVisible(false);
                btnCancel.setVisible(true);
            } else {
                waitingForPlayer.setVisible(true);
                waitingForPlayer.setText("You can't create this game.\nCheck if you have any other games.");
            }
        });

        btnCancel.setOnAction((ActionEvent t) -> {
            close();
            GUIs.getLobby().select();
            LOBBY.removeGame(game);
        });

        btnLeave.setOnAction((ActionEvent t) -> {
            close();
            GUIs.getLobby().select();
            LOBBY.leave(game);
        });

        btnStartGame.setOnAction((ActionEvent t) -> {
            if (game == null) {
                chosenType = getGameType();
                gameTitel = titelTextField.getText();
                game = LOBBY.createGame(gameTitel, chosenType);
                if (game != null) {
                    IGamePlaying gamePlaying = game.start();
                    GameGUI gameGUI = new GameGUI(gamePlaying);
                    gameGUI.start();
                    GUIs.getLobby().hide();
                    hide();
                    new Thread((Runnable) gamePlaying).start();
                } else {
                    waitingForPlayer.setVisible(true);
                    waitingForPlayer.setText("You can't create this game.\nCheck if you have any other games.");
                }
            } else {
                LOBBY.startGame(game);
            }
        });

        borderPane.setCenter(gridPane);
        if (game != null) {
            waitingForPlayer.setText("Waiting for other players...");
            waitingForPlayer.setVisible(true);
            rbAI.setDisable(true);
            rbNormal.setDisable(true);
            rbPlayers.setDisable(true);
            rbRanked.setDisable(true);
            btnSetUp.setVisible(false);
            btnStartGame.setVisible(false);
            btnLeave.setVisible(true);
            titelTextField.setText(game.getName());
            titelTextField.setEditable(false);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Scene / Stage">
        SCENE = new Scene(borderPane, 500, 300);
        STAGE.setTitle("Game-lobby");
        STAGE.setScene(SCENE);
        STAGE.show();
        //</editor-fold>
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="addGameListItem(item)">
    @Override
    public void addGameListItem(IGame item) {
        //This operation does nothing because the Game Lobby Doesn't care about other games.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="updateGameListItem(item)">
    @Override
    public void updateGameListItem(IGame item) {
        if (item.equals(game)) {
            game = (IGameWaiting) item;
            if (game.getGameState().equals(GameState.Started)) {
                IGamePlaying gamePlaying = game.start();
                Platform.runLater(() -> {
                    GameGUI gameGUI = new GameGUI(gamePlaying);
                    gameGUI.start();
                    GUIs.getLobby().hide();
                    hide();
                });
                new Thread((Runnable) gamePlaying).start();
            } else {
                Platform.runLater(() -> {
                    playerListItems.clear();
                    playerListItems.addAll(game.getPlayers());
                    if (playerListItems.size() == 3) {
                        btnStartGame.setDisable(false);
                        waitingForPlayer.setText("Waiting for Host to start the Game...");
                    } else {
                        btnStartGame.setDisable(true);
                        waitingForPlayer.setText("Waiting for other players...");
                    }
                });
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeGameListItem(item)">
    @Override
    public void removeGameListItem(IGame item) {
        if (item.equals(game)) {
            Platform.runLater(() -> {
                waitingForPlayer.setText("Game Removed...");
                waitingForPlayer.setVisible(true);
            });
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        close();
                    });
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameLobbyGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        }
    }
    //</editor-fold>
    //</editor-fold>

    @Override
    public void addMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

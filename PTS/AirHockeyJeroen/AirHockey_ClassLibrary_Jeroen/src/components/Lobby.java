//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

import components.game.Player;
import components.lobby.User;
import enums.GameType;
import interfaces.IGame;
import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import interfaces.game.IGameWaiting;
import interfaces.gui.ILobbyGUI;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Lobby.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public class Lobby implements ILobbyNotLoggedIn, ILobbyLoggedIn {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static int id = 0;
    private final List<IGame> games;
    private User user;
    private ServerConnecter loginConnecter;
    private ServerConnecter lobbyConnecter;
    private ServerConnecter listenerConnecter;
    private ServerConnecter chatConnecter;
    private ServerConnecter InGameConnecter;
    private ILobbyGUI gUIGameLobby = null;
     //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public static void setID(int id) {
        if (Lobby.id == 0 && id != 0) {
            Lobby.id = id;
        }
    }

    public int getID() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setGUIGameLobby(ILobbyGUI gUIGameLobby) {
        this.gUIGameLobby = gUIGameLobby;
    }

    @Override
    public ServerConnecter getServerConnecter() {
        return lobbyConnecter;
    }
    
    public ServerConnecter getGameConnecter() {
        return  InGameConnecter;
    }
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    private Lobby() {
        this.games = new ArrayList<>();
        loginConnecter = new ServerConnecter();
        lobbyConnecter = new ServerConnecter();
        listenerConnecter = new ServerConnecter();
        chatConnecter = new ServerConnecter();
        InGameConnecter = new ServerConnecter();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getGames()">
    @Override
    public List<IGame> getGames() {
        games.addAll((List<IGame>) lobbyConnecter.readObjectFromServer());
        return games;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="loginConnect()">
    @Override
    public boolean loginConnect() {
        boolean returner = false;
        returner = loginConnecter.connect();
        if (returner) {
            String message = "Setup Login";
            loginConnecter.sendObjectToServer(message);
            Lobby.setID(Integer.parseInt(loginConnecter.readMessageFromServer().split(":")[1]));
        }
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="listenerConnect()">
    public boolean listenerConnect() {
        boolean returner = false;
        returner = listenerConnecter.connect();
        if (returner) {
            String message = "Setup Listener:" + id;
            listenerConnecter.sendObjectToServer(message);
        }
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login(username, password)">
    /**
     * This operation connects to the server and tries to login with the given parameters. The server than returns if it is
     * "Success" or "Access Denied". Depending on the server return, the Lobby will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     *
     * @return the interface to control the Lobby when logged in.
     */
    @Override
    public ILobbyLoggedIn login(String username, String password) {
        ILobbyLoggedIn returner = null;
        String message = null;
        message = "Login";
        loginConnecter.sendObjectToServer(message);
        loginConnecter.sendObjectToServer(username);
        loginConnecter.sendObjectToServer(password);
        message = loginConnecter.readMessageFromServer();
        if (message == null) {
            //TODO throw error.
        } else if (message.equals("Access Granted")) {
            user = (User) loginConnecter.readObjectFromServer();
            returner = this;
            lobbyConnecter = loginConnecter;
            if (chatConnecter.connect()){
                message = "Setup Chat:" + id;
                chatConnecter.sendObjectToServer(message);
            }
        } else if (message.contains("Access Denied")) {
            //TODO let the user retry.
        }
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="register(username, password)">
    /**
     * This operation connects to the server and tries to create a new user with the given parameters. The server than returns if
     * it is "Access Granted" or "Access Denied". Depending on the server return, the Lobby will be opened. (The new user is
     * automatically logged in).
     *
     * @param username the new username (this has to be unique).
     * @param password the password linked to this user.
     *
     * @return the interface to control the Lobby when logged in.
     */
    @Override
    public ILobbyLoggedIn register(String username, String password) {
        ILobbyLoggedIn returner = null;
        String message = null;
        message = "Register";
        loginConnecter.sendObjectToServer(message);
        loginConnecter.sendObjectToServer(username);
        loginConnecter.sendObjectToServer(password);
        message = loginConnecter.readMessageFromServer();
        if (message == null) {
            //TODO throw error.
        } else if (message.equals("Access Granted")) {
            user = (User) loginConnecter.readObjectFromServer();
            returner = this;
            lobbyConnecter = loginConnecter;
            if (chatConnecter.connect()){
                message = "Setup Chat:" + id;
                chatConnecter.sendObjectToServer(message);
            }
        } else if (message.contains("Access Denied")) {
            //TODO let the user retry.
        }
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="startListener(gUILobby)">
    @Override
    public void startListener(ILobbyGUI gUILobby) {
        new Thread(() -> {
            listenerConnect();
            while (true) {
                String message = listenerConnecter.readMessageFromServer();
                switch (message) {
                    case "New Game Added": {
                        IGame game = (IGame) listenerConnecter.readObjectFromServer();
                        games.add(game);
                        Platform.runLater(() -> {
                            gUILobby.addGameListItem(game);
                        });
                        break;
                    }
                    case "Game Updated": {
                        IGame newGame = (IGame) listenerConnecter.readObjectFromServer();
                        gUILobby.updateGameListItem(newGame);
                        if (gUIGameLobby != null) {
                            gUIGameLobby.updateGameListItem(newGame);
                        }
                        break;
                    }
                    case "Game Removed": {
                        IGame newGame = (IGame) listenerConnecter.readObjectFromServer();
                        gUILobby.removeGameListItem(newGame);
                        if (gUIGameLobby != null) {
                            gUIGameLobby.removeGameListItem(newGame);
                        }
                        break;
                    }
                    default: {
                        Platform.runLater(() -> {
                            gUILobby.addMessage(message);
                        });
                        break;
                    }
                }
            }
        }).start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createGame(host, name, gameType)">
    /**
     * This operation creates and returns a new Game.
     *
     * @param name     is the name of the game (does not have to be unique).
     * @param gameType is the type of game that will be created.
     *
     * @return the interface of the game that has been created.
     */
    @Override
    public IGameWaiting createGame(String name, GameType gameType) {
        IGameWaiting gameWaiting = new Game(new Player(user), name, gameType);
        if (gameType == GameType.MultiCompetition || gameType == GameType.MultiFun) {
            String message = "Create Game";
            lobbyConnecter.sendObjectToServer(message);
            lobbyConnecter.sendObjectToServer(gameWaiting);
            message = lobbyConnecter.readMessageFromServer();
            if (message.toLowerCase().contains("not")) {
                return null;
            } else {
                return gameWaiting;
            }
        } else {
            return gameWaiting;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeGame(game)">
    /**
     * This operation removes a previously created game.
     *
     * @param game is the game that should be removed.
     */
    @Override
    public void removeGame(IGame game) {
        String message = "Remove Game";
        lobbyConnecter.sendObjectToServer(message);
        lobbyConnecter.sendObjectToServer(game);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="joinGame(game)">
    /**
     * This operation lets you join a game.
     *
     * @param game is the game the game he wants to join.
     *
     */
    @Override
    public void joinGame(IGameWaiting game) {
        String message = "Join Game";
        lobbyConnecter.sendObjectToServer(message);
        lobbyConnecter.sendObjectToServer(user);
        lobbyConnecter.sendObjectToServer(game);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="leave(game)">
    @Override
    public void leave(IGameWaiting game) {
        String message = "Leave Game";
        lobbyConnecter.sendObjectToServer(message);
        lobbyConnecter.sendObjectToServer(user);
        lobbyConnecter.sendObjectToServer(game);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="startGame(game)">
    @Override
    public void startGame(IGame game) {
        String message = "Start Game";
        lobbyConnecter.sendObjectToServer(message);
        lobbyConnecter.sendObjectToServer(game);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finding">
    //<editor-fold defaultstate="collapsed" desc="findGame(userPlaying)">
    /**
     * This operation will return the game that fit the search.
     *
     * @param userPlaying is one of the players in the game.
     *
     * @return the game where the user is playing in.
     */
    @Override
    public IGame findGame(User userPlaying) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameName)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(String gameName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameType)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(GameType gameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="findGame(gameName, GameType)">
    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    @Override
    public List<IGame> findGame(String gameName, GameType gameType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="logout()">
    /**
     * This operation connects to the server and let the user logout. The Lobby GUI will be closed, and the Login GUI will be
     * shown.
     *
     * @return the interface to control the Lobby when not logged in.
     */
    @Override
    public ILobbyNotLoggedIn logout() {
        String message = "Logout:" + user.getUsername();
        lobbyConnecter.sendObjectToServer(message);
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="chatSendMessage(message)">
    @Override
    public void chatSendMessage(String message) {
        chatConnecter.sendObjectToServer(user.toString() + ": " + message);
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    public static Lobby getInstance() {
        return LobbyHolder.INSTANCE;
    }

    private static class LobbyHolder {

        private static final Lobby INSTANCE = new Lobby();
    }
    //</editor-fold>

}

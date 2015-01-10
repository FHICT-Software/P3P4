//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import components.game.Player;
import components.lobby.User;
import enums.GameState;
import enums.GameType;
import interfaces.game.IGamePlaying;
import interfaces.game.IGameStarted;
import interfaces.game.IGameWaiting;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class you can find all properties and operations for Game. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public class Game implements IGamePlaying, IGameStarted, IGameWaiting {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="final">
    private final String name;
    private final GameType gameType;
    private List<Player> players;
    //</editor-fold>
    private GameState gameState;
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

    //<editor-fold defaultstate="collapsed" desc="Player">
    @Override
    public Player getPlayer(int id) {
        if (players.size() > id) {
            return players.get(id);
        } else {
            return null;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(host, gameName, gameType)">
    /**
     * This is the constructor for Game. This sets the Name, Type, and State of the game.
     *
     * @param host     is the host user for this game.
     * @param gameName is the name of the game.
     * @param gameType is the type of game.
     */
    public Game(User host, String gameName, GameType gameType) {
        players = new ArrayList<>();
        players.add(new Player(host));
        this.name = gameName;
        this.gameType = gameType;
        this.gameState = GameState.Waiting;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="join(user)">
    /**
     * This operation lets a user join the game.
     *
     * @param user is the user that wants to join.
     */
    @Override
    public void join(User user) {
        players.add(new Player(user));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Class">
    @Override
    public String toString() {
        return "Test";
    }
    //</editor-fold>
    //</editor-fold>
}

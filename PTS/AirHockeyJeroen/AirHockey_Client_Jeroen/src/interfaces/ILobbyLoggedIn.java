//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces;

import components.lobby.User;
import enums.GameType;
import interfaces.game.IGame;
import interfaces.game.IGameWaiting;
import java.util.List;
//</editor-fold>

/**
 * In this class you can find all types and operations for ILobbyLoggedIn.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface ILobbyLoggedIn {

    /**
     * This operation creates and returns a new Game.
     *
     * @param name     is the name of the game. The name does not have to be unique. The name cannot contain formatting symbols.
     *                 See http://moridrin.com/Softwate/Formatting/.
     * @param gameType is the type of game that will be created.
     *
     * @return the interface of the game that has been created.
     */
    public IGameWaiting createGame(String name, GameType gameType);

    /**
     * This operation lets you join a game.
     *
     * @param game is the game the game he wants to join.
     */
    public void joinGame(IGameWaiting game);

    //<editor-fold defaultstate="collapsed" desc="Finding">
    /**
     * This operation will return the game that fit the search.
     *
     * @param userPlaying is one of the players in the game.
     *
     * @return the game where the user is playing in.
     */
    public IGame findGame(User userPlaying);

    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     *
     * @return a list of games that fit the search.
     */
    public List<IGame> findGame(String gameName);

    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    public List<IGame> findGame(GameType gameType);

    /**
     * This operation will return a list of games that fit the search.
     *
     * @param gameName is the name of the game. The search goes according to default formatting rules. See
     *                 http://moridrin.com/Softwate/Formatting/.
     * @param gameType is the type of game you want to find.
     *
     * @return a list of games that fit the search.
     */
    public List<IGame> findGame(String gameName, GameType gameType);
    //</editor-fold>

    /**
     * This operation connects to the server and let the user logout. The Lobby GUI will be closed, and the Login GUI will be
     * shown.
     *
     * @return the interface to control the Lobby when not logged in.
     */
    public ILobbyNotLoggedIn logout();
}

package components;

import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import interfaces.IStartedGame;
import interfaces.IWaitingGame;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Deze class is iets te complex om nu nog helemaal goed te schrijven (doe ik later).
 *
 * @author J.B.A.J. Berkvens
 */
public class LobbyTest {

    //<editor-fold defaultstate="collapsed" desc="Declarations()">
    Lobby defaultInstance;

    public Lobby getDefaultInstance() {
        return defaultInstance;
    }
 //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setup">
    public LobbyTest() {
    }

    @Before
    public void setUp() {
        defaultInstance = new Lobby();
    }

    @After
    public void tearDown() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="test">
    /**
     * Test of getIWaitingGames method, of class Lobby.
     */
    @Test
    public void testGetIWaitingGames() {
        System.out.println("getIWaitingGames");
        Lobby instance = defaultInstance;
        IWaitingGame expResult = null;
        IWaitingGame result = instance.getIWaitingGames();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIStartedGame method, of class Lobby.
     */
    @Test
    public void testGetIStartedGame() {
        System.out.println("getIStartedGame");
        Lobby instance = defaultInstance;
        IStartedGame expResult = null;
        IStartedGame result = instance.getIStartedGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of getViewer method, of class Lobby.
     */
    @Test
    public void testGetViewer() {
        System.out.println("getViewer");
        Lobby instance = defaultInstance;
        Viewer expResult = null;
        Viewer result = instance.getViewer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Lobby.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Lobby instance = defaultInstance;
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of createGame method, of class Lobby.
     */
    @Test
    public void testCreateGame() {
        System.out.println("createGame");
        String type = "singleFun";
        User user = new User("Jeroen", 16);
        Lobby instance = defaultInstance;
        IWaitingGame expResult = new Game(defaultInstance, user, type);
        IWaitingGame result = instance.createGame(type, user);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinGame method, of class Lobby.
     */
    @Test
    public void testJoinGame() {
        System.out.println("joinGame");
        IWaitingGame game = null;
        User user = null;
        Lobby instance = defaultInstance;
        IWaitingGame expResult = null;
        IWaitingGame result = instance.joinGame(game, user);
        assertEquals(expResult, result);
    }

    /**
     * Test of logout method, of class Lobby.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Lobby instance = defaultInstance;
        ILobbyNotLoggedIn expResult = null;
        ILobbyNotLoggedIn result = instance.logout();
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class Lobby.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        Lobby instance = defaultInstance;
        ILobbyLoggedIn expResult = null;
        ILobbyLoggedIn result = instance.login(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class Lobby.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String username = "";
        String password = "";
        Lobby instance = defaultInstance;
        ILobbyLoggedIn expResult = null;
        ILobbyLoggedIn result = instance.register(username, password);
        assertEquals(expResult, result);
    }

}

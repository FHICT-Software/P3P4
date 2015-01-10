package components;

import interfaces.ILobbyLoggedIn;
import interfaces.IStartedGame;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class GameTest {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    Lobby defaultLobby;
    User defaultUser;
    Game defaultInstance;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Set Up">
    public GameTest() {
    }

    @Before
    public void setUp() {
        defaultLobby = new Lobby();
        defaultUser = new User("Jeroen", 20);
        defaultInstance = new Game(defaultLobby, defaultUser, "SingleFun");
    }

    @After
    public void tearDown() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="testStart()">
    /**
     * Test of start method, of class Game.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Game instance = defaultInstance;
        IStartedGame expResult = defaultInstance;
        IStartedGame result = instance.start();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="testCancel()">
    /**
     * Test of cancel method, of class Game.
     */
    @Test
    public void testCancel() {
        System.out.println("cancel");
        Game instance = defaultInstance;
        ILobbyLoggedIn expResult = defaultLobby;
        ILobbyLoggedIn result = instance.cancel();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="testGetPlayers()">
    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        Game instance = defaultInstance;
        ArrayList<Player> expResult = null;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        //ERROR
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="testMoveBat()">
    /**
     * Test of moveBat method, of class Game.
     */
    @Test
    public void testMoveBat() {
        System.out.println("moveBat");
        int direction = 0;
        Game instance = defaultInstance;
        instance.moveBat(direction);
        //TODO
    }
    //</editor-fold>
}

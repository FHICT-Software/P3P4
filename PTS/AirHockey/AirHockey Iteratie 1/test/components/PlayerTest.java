
package components;

import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class PlayerTest {
    
    Player defaultInstance;
    User defaultUser;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        defaultUser = new User("Jeroen", 16);
        defaultInstance = new Player(defaultUser);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBat method, of class Player.
     */
    @Test
    public void testGetBat() {
        System.out.println("getBat");
        Player instance = null;
        Bat expResult = new Bat(new Player(new User("Jeroen", 15)));
        Bat result = instance.getBat();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Player.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Player instance = defaultInstance;
        User expResult = defaultUser;
        User result = instance.getUser();
        assertEquals(expResult, result);
    }
    
}

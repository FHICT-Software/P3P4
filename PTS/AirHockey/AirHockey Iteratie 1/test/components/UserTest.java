
package components;

import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class UserTest {
    
    User defaultInstance;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        defaultInstance = new User("Jeroen", 16);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = defaultInstance;
        String expResult = "Jeroen";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRating method, of class User.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        User instance = defaultInstance;
        int expResult = 16;
        int result = instance.getRating();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRatingScores method, of class User.
     */
    @Test
    public void testSetRatingScores() {
        System.out.println("setRatingScores");
        int expected = 0;
        User instance = defaultInstance;
        instance.setRatingScores(expected);
        int result = instance.getRatingScores();
        assertEquals(expected, result);
    }

    /**
     * Test of getRatingScores method, of class User.
     */
    @Test
    public void testGetRatingScores() {
        System.out.println("getRatingScores");
        User instance = defaultInstance;
        int expResult = 16;
        int result = instance.getRatingScores();
        assertEquals(expResult, result);
    }
    
}

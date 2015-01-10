package components;

import java.awt.Point;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class PuckTest {

    Puck defaultInstance;

    public PuckTest() {
    }

    @Before
    public void setUp() {
        defaultInstance = new Puck(15);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSpeed method, of class Puck.
     */
    @Test
    public void testGetSpeed() {
        //TODO
        //Puck needs to get the speed in the constructor.
        System.out.println("getSpeed");
        Puck instance = new Puck(15);
        double expResult = 0.0;
        double result = instance.getSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPosition method, of class Puck.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Point expected = new Point(12, 5);
        Puck instance = new Puck(15);
        instance.setPosition(expected);
        Point result = instance.getPosition();
        assertEquals(expected, result);
    }

    /**
     * Test of getPosition method, of class Puck.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Puck instance = new Puck(15);
        Point expResult = new Point();
        Point result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirection method, of class Puck.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        int expected = 15;
        Puck instance = new Puck(15);
        instance.setDirection(expected);
        int result = instance.getDirection();
        assertEquals(expected, result);
    }

    /**
     * Test of getDirection method, of class Puck.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Puck instance = new Puck(15);
        int expResult = 0;
        int result = instance.getDirection();
        assertEquals(expResult, result);
    }

}

package components;

import java.awt.Point;
import junit.framework.Assert;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class BatTest {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    Bat defaultInstance;
    Point defaultPosition;

    public BatTest() {
        defaultPosition = new Point(15, 20);
        defaultInstance = new Bat(new Player(new User("Jeroen", 15), 1), 1);
        defaultInstance.setPosition(defaultPosition);
        defaultInstance.setDirection(0);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setDirection method, of class Bat.
     */
    @Test
    public void testMoveBat() {
        System.out.println("moveBat");
        int direction = 1;
        Bat instance = defaultInstance;
        instance.setDirection(direction);
        System.out.println("Expecting a different position.");
        Assert.assertFalse(instance.getPosition().equals(defaultPosition));
        System.out.println("Expected: !" + defaultPosition.toString() + ".");
        System.out.println("Got: !    " + instance.getPosition() + ".");
    }

    /**
     * Test of setPosition method, of class Bat.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Point position = new Point(15, 30);
        Bat instance = defaultInstance;
        instance.setPosition(position);
        assertEquals(instance.getPosition(), defaultPosition);
    }

    /**
     * Test of getPosition method, of class Bat.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Bat instance = defaultInstance;
        Point expResult = defaultPosition;
        Point result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirection method, of class Bat.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        int direction = 15;
        Bat instance = defaultInstance;
        instance.setDirection(direction);
        assertEquals(direction, instance.getDirection());
    }

    /**
     * Test of getDirection method, of class Bat.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Bat instance = defaultInstance;
        double expResult = 0;
        double result = instance.getDirection();
        assertEquals(expResult, result);
    }
}

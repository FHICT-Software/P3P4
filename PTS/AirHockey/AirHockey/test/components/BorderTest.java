
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
public class BorderTest {
    
    Border defaultInstance;
    Point defaultStartPoint;
    Point defaultEndPoint;
    
    public BorderTest() {
    }
    
    @Before
    public void setUp() {
        defaultStartPoint = new Point(101, 15);
        defaultEndPoint = new Point(12, 835);
        defaultInstance = new Border(defaultStartPoint, defaultEndPoint, "Red");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStartPoint method, of class Border.
     */
    @Test
    public void testGetStartPoint() {
        System.out.println("getStartPoint");
        Border instance = defaultInstance;
        Point expResult = defaultStartPoint;
        Point result = instance.getStartPoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndPoint method, of class Border.
     */
    @Test
    public void testGetEndPoint() {
        System.out.println("getEndPoint");
        Border instance = defaultInstance;
        Point expResult = defaultEndPoint;
        Point result = instance.getEndPoint();
        assertEquals(expResult, result);
    }
    
}

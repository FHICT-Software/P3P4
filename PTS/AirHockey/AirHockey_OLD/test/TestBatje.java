/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.Batje;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Baya
 */
public class TestBatje {
    Batje batje;
    
    public TestBatje() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        batje = new Batje(Color.RED);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testBatje() {
        batje.setX(400);
        assertEquals(400, batje.getLocation().x);
    }
    
    @Test
    public void testVerplaatsBatje() {
        double x = batje.getLocation().getX();
        x = x - 10;
        batje.verplaatsBatje(180);
        assertEquals(x, batje.getLocation().getX(), 0.001);
        x = x + 10;
        batje.verplaatsBatje(0);
        assertEquals(x, batje.getLocation().getX(), 0.001);
        double y = batje.getLocation().getY();
        batje.verplaatsBatje(90);
        y = y + 10;
        assertEquals(y, batje.getLocation().getY(), 0.001);
        y = y - 10;
        batje.verplaatsBatje(270);
        assertEquals(y, batje.getLocation().getY(), 0.001);
        
        x = Math.round(x + Math.cos(Math.toRadians(60)) * 10);
        y = Math.round(y + Math.sin(Math.toRadians(60)) * 10);
        batje.verplaatsBatje(60);
        assertEquals(x, batje.getLocation().getX(), 0.001);
        assertEquals(y, batje.getLocation().getY(), 0.001);
    }
}
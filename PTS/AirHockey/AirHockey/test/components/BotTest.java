
package components;

import java.awt.Point;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class BotTest {
    
    Bot defaultInstance;
    User defaultUser;
    
    public BotTest() {
    }
    
    @Before
    public void setUp() {
        defaultUser = new User("Moridrin", 16);
        defaultInstance = new Bot();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of preformAction method, of class Bot.
     */
    @Test
    public void testPreformAction() {
        System.out.println("preformAction");
        Bot instance = defaultInstance;
        instance.preformAction();
        Point expected = defaultInstance.getBat().getPosition();
        Point result = instance.getBat().getPosition();
        Assert.assertFalse(result.equals(expected));
    }
    
}


package components;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J.B.A.J. Berkvens
 */
public class ChatTest {
    
    Chat defaultInstance;
    
    public ChatTest() {
    }
    
    @Before
    public void setUp() {
        defaultInstance = new Chat();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sendMessage method, of class Chat.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String message = "TestMessage";
        Chat instance = defaultInstance;
        instance.sendMessage(message);
        //Geen idee hoe ik die moet testen.
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.Persoon;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phinux, Baya
 */
public class TestSetRating {
    
    Persoon x;
    
    public TestSetRating() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Double> ratingScores = new ArrayList<>();
        x = new Persoon("Anne", "", ratingScores);
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
    public void testSetRating()
    {
        x.addNewRatingScores(5);
        x.addNewRatingScores(10);
        x.addNewRatingScores(15);
        x.addNewRatingScores(20);
        x.addNewRatingScores(25);
        
        x.setRating();
        assertEquals(x.getRating(), 18.33, 0.01);
       
    }
}
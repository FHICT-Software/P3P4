/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;

/**
 *
 * @author E.Muit-Laptop
 */
public class Coordinate implements Serializable {
    
    public double x;
    public double y;
    
    public Coordinate(double X, double Y)
    {
        x = X;
        y = Y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
}

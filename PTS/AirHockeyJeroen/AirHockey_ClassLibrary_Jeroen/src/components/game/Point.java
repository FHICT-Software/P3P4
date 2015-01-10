/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package components.game;

import java.io.Serializable;

/**
 *
 * @author JMAGPeeters
 */
public class Point implements Serializable{
    
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;

import collisionphysics.CollisionPhysics;
import collisionphysics.CollisionResponse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
//</editor-fold>

/**
 * In deze class staan alle gegevens van een Batje.
 *
 * @author jeroen
 */
public class Batje {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Virtuele Veld">
    double veldWidth = 1000;
    double veldHeight = Math.sqrt((veldWidth * veldWidth) - ((veldWidth / 2) * (veldWidth / 2)));
    //</editor-fold>
    public float x, y;
    public float speedX, speedY;
    public float speed;
    public float radius;
    public float richting;
    private Color color;
    private static Random random;

    public void setX(float x) {
        this.x = x;
    }

    public Point getLocation() {
        return new Point((int) this.x, (int) this.y);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Dit is de constructor van Batje. Hierin wordt het batje aangemaakt, en
     * worden alle parameters geinitializeerd.
     *
     * @param color
     */
    public Batje(Color color) {
        this.color = color;
        random = new Random(30);
        radius = 40;
        if (color == Color.RED) {
            x = (float) (veldWidth / 2);
            y = (float) (veldHeight - radius);
            speed = 20;
        } else if (color == Color.BLUE) {
            x = (float) (veldWidth / 3 + 20);
            y = (float) (veldHeight / 2);
            y -= 100;
            speed = 10;
        } else {
            x = (float) (veldWidth / 3) * 2 +40;
            y = (float) (veldHeight / 2);
            speed = 10;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Verplaats Batje">
    /**
     * In deze functie wordt het batje verplaatst. De x en y worden dus
     * aangepast naar de nieuwe locatie.
     *
     * @param richting De richting waarin het batje wordt verplaatst.
     */
    public void verplaatsBatje(int richting) {
        boolean move = true;
        double x = Math.cos(Math.toRadians(richting)) * speed;
        double y = Math.sin(Math.toRadians(richting)) * speed;
        if (color.equals(Color.RED)) {
            if (this.x + x > (veldWidth / 10) * 7) {
                move = false;
            } else if (this.x + x < (veldWidth / 10) * 3) {
                move = false;
            }
        } else {
            if (this.y + y > (veldHeight / 10) * 7) {
                move = false;
            } else if (this.y + y < (veldHeight / 10) * 3) {
                move = false;
            }
        }
        if (move) {
            this.x = (float) (this.x + x);
            this.y = (float) (this.y + y);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Draw">
    /**
     * In deze functie tekent hij zichzelf op de megegeven Graphics (scherm).
     *
     * @param g            de Graphics waar hij zichzelf gaat tekenen.
     * @param multyplier   zorgt voor de omzetting van het virtuele veld naar de
     *                     locaties op het scherm.
     * @param xTranslation de afstand van de linker kant tot het begin van het
     *                     veld.
     * @param yTranslation de afstand van de bovenkant tot het begin van het
     *                     veld.
     */
    public void draw(Graphics g, double multyplier, double xTranslation, double yTranslation) {
        g.setColor(color);
        int drawX = (int) (((x * multyplier) + xTranslation) - (radius * multyplier));
        int drawY = (int) (((y * multyplier) + yTranslation) - (radius * multyplier));
        int drawDiameter = (int) (2 * (radius * multyplier));
        g.fillOval(drawX, drawY, drawDiameter, drawDiameter);
        //g.setColor(Color.YELLOW);
        //g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }
    //</editor-fold>
}

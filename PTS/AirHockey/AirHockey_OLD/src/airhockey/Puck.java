//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;

import calculations.Intersect;
import collisionphysics.CollisionPhysics;
import collisionphysics.CollisionResponse;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Formatter;
import java.util.Random;
import javax.swing.text.html.HTML;
//</editor-fold>

/**
 * Deze class bevat alle gegevens en functies van Puck.
 *
 * @author jeroen
 */
public class Puck {

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
    public Object lastIntersectedWith;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In deze constructor worden de default variabelen geset, en objecten
     * geinitializeerd.
     */
    public Puck() {
        random = new Random(30);
        radius = 20;
        speed = 3;
    }
    //</editor-fold>

    //<editor-fold desc="Methodes">
    //<editor-fold defaultstate="collapsed" desc="Restart">
    /**
     * In deze functie wordt de locatie, snelheid, en richting opnieuw bepaald.
     */
    public void restart() {
        this.richting = random.nextInt(360);
        int spawnRichting = random.nextInt(360);
        int spawnAfstand = random.nextInt((int) (veldHeight / 4));
        float spawnX = (float) (Math.cos(Math.toRadians(spawnRichting)) * spawnAfstand);
        float spawnY = (float) (Math.sin(Math.toRadians(spawnRichting)) * spawnAfstand);
        this.x = (float) ((veldWidth / 2) + spawnX);
        this.y = (float) ((veldHeight / 2) + spawnY);
        this.speedX = (float) (Math.cos(Math.toRadians(richting)) * speed);
        this.speedY = (float) (Math.sin(Math.toRadians(richting)) * speed);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Set Last Hit By">
    /**
     * In deze methode wordt de kleur van de puck aangepast aan hand van de
     * laatste speler die de puck heeft aangeraakt.
     *
     * @param lastHitBy geeft het nummer van de speler die de puck als laatst
     *                  heeft aangeraakt.
     */
    public void setLastHitBy(int lastHitBy) {
        switch (lastHitBy) {
            case 0:
                this.color = Color.BLACK;
                break;
            case 1:
                this.color = new Color(200, 0, 0);
                break;
            case 2:
                this.color = new Color(0, 200, 0);
                break;
            case 3:
                this.color = new Color(0, 0, 200);
                break;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update">
    /**
     * Deze functie wordt aangeroepen door de run() in Spel. Hierin moet de puck
     * afhankelijk van zijn locatie, snelheid, richting etc. zijn volgende
     * locatie berekenen.
     */
    public void update() {
        this.speedX = (float) (Math.cos(Math.toRadians(richting)) * speed);
        this.speedY = (float) (Math.sin(Math.toRadians(richting)) * speed);
        x = x + speedX;
        y = y + speedY;
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
        //g.setColor(Color.PINK);
        //g.fillOval((int) (x), (int) (y), (int) (5), (int) (5));
    }
    //</editor-fold>
    //</editor-fold>
}

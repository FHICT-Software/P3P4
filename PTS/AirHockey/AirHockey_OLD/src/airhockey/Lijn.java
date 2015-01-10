//<editor-fold defaultstate="collapsed" desc="Jibberish">
package airhockey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
//</editor-fold>

/**
 * In deze Class staan alle functies en eigenschappen van een lijn. Randen en
 * Goals zijn allebei lijnen.
 *
 * @author jeroen
 */
public class Lijn {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    int x1, y1;
    int x2, y2;
    Color color;

    public Point getLocation1() {
        return new Point(x1, y1);
    }

    public Point getLocation2() {
        return new Point(x2, y2);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Dit is een constructor van Lijn. Hierin worden de megeleverde punten
     * geset.
     *
     * @param x1 de x locatie van het eerste punt.
     * @param y1 de y locatie van het eerste punt.
     * @param x2 de x locatie van het tweede punt.
     * @param y2 de y locatie van het tweede punt.
     * @param color de kleur van de lijn.
     */
    public Lijn(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * Dit is een constructor van Lijn. Hierin wordt een lijn gemaakt zonder
     * specifieke kleur. Daarvoor wordt dus een default kleur gekozen.
     *
     * @param x1 de x locatie van het eerste punt.
     * @param y1 de y locatie van het eerste punt.
     * @param x2 de x locatie van het tweede punt.
     * @param y2 de y locatie van het tweede punt.
     */
    public Lijn(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, Color.BLACK);
    }
    //</editor-fold>

    //<editor-fold desc="Methoden">
    //<editor-fold defaultstate="collapsed" desc="Set nieuwe locaties">
    /**
     * set een nieuwe locatie voor de lijn.
     *
     * @param x1 de x locatie van het eerste punt.
     * @param y1 de y locatie van het eerste punt.
     * @param x2 de x locatie van het tweede punt.
     * @param y2 de y locatie van het tweede punt.
     */
    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Draw">
    /**
     * Hierin wordt de lijn getekent.
     *
     * @param g is het Graphics component waarin de lijn getekent wordt.
     * @param multyplier relatief verschil tussen het virtuele veld, en het
     * scherm.
     * @param xTranslation translatie in x (voor het centreren van het veld).
     * @param yTranslation translatie in y (voor het centreren van het veld).
     */
    public void draw(Graphics g, double multyplier, double xTranslation, double yTranslation) {
        g.setColor(color);
        int drawX1 = (int) ((x1 * multyplier) + xTranslation);
        int drawY1 = (int) ((y1 * multyplier) + yTranslation);
        int drawX2 = (int) ((x2 * multyplier) + xTranslation);
        int drawY2 = (int) ((y2 * multyplier) + yTranslation);
        g.drawLine(drawX1, drawY1, drawX2, drawY2);
        //g.setColor(Color.YELLOW);
        //g.drawLine(x1, y1, x2, y2);
    }
    //</editor-fold>
    //</editor-fold>
}

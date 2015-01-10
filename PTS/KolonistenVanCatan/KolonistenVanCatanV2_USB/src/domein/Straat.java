package domein;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * In this class you can find all properties and operations for Straat
 * 
 * @author Daan
 */
public class Straat {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Point2D begin;
    private Point2D einde;
    private Color kleur;
    public Speler unnamed_Speler_;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Straat(Point2D begin, Point2D einde, Color kleur) {
        this.begin = begin;
        this.einde = einde;
        this.kleur = kleur;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Operations">
    
    public Point2D[] getPlaats() {
        Point2D[] returner = new Point2D[2];
        returner[0] = begin;
        returner[1] = einde;
        return returner;
    }
    
    public void setPlaats(Point2D[] plaats) {
        this.begin = plaats[0];
        this.einde = plaats[1];
    }
    
    public Color getKleur() {
        return this.kleur;
    }
    
    //</editor-fold>
}

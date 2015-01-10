package domein;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * In this class you can find all properties and operations for Vesting
 * 
 * @author Daan
 */
public class Vesting {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Point2D plaats;
    private final Color kleur;
    private boolean isStad;
    public Speler speler;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Vesting(Point2D plaats, Color kleur, boolean isStad) {
        this.plaats = plaats;
        this.kleur = kleur;
        this.isStad = isStad;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="Collapsed" desc="Operations">
    
    public Point2D getPlaats() {
        return this.plaats;
    }
    
    public void setPlaats(Point2D plaats) {
        this.plaats = plaats;
    }
    
    public Color getKleur() {
        return this.kleur;
    }
    
    public void setIsStad(boolean isStad) {
        this.isStad = isStad;
    }
    
    public boolean getIsStad() {
        return this.isStad;
    }
    
    //</editor-fold>
}

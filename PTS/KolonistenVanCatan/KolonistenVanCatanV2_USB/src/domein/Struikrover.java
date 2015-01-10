package domein;

import java.awt.Point;
import javafx.geometry.Point2D;

/**
 * in this class you can find all properties and operations for Struikrover
 * 
 * @author Daan
 */
public class Struikrover {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Point2D plaats;
    public Spel unnamed_Spel_;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Struikrover() {
            
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Operations">
    
    public Point2D getPlaats() {
        return this.plaats;
    }
    
    public void setPlaats(Point2D Plaats) {
        this.plaats = Plaats;
    }
    
    //</editor-fold>
}

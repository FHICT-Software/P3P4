package domein;

import java.awt.Point;
import java.io.Serializable;

/**
 * in this class you can find all properties and operations for Struikrover
 * 
 * @author Daan
 */
public class Struikrover implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Coordinate plaats;
    public Spel unnamed_Spel_;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Struikrover() {
            
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Operations">
    
    public Coordinate getPlaats() {
        return this.plaats;
    }
    
    public void setPlaats(Coordinate Plaats) {
        this.plaats = Plaats;
    }
    
    //</editor-fold>
}

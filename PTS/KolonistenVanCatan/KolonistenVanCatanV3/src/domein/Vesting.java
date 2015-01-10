package domein;

import java.awt.Color;

/**
 * In this class you can find all properties and operations for Vesting
 * 
 * @author Daan
 */
public class Vesting {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Coordinate plaats;
    private final Kleur kleur;
    private boolean isStad;
    public Speler speler;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Vesting(Coordinate plaats, Kleur kleur, boolean isStad) {
        this.plaats = plaats;
        this.kleur = kleur;
        this.isStad = isStad;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="Collapsed" desc="Operations">
    
    public Coordinate getPlaats() {
        return this.plaats;
    }
    
    public void setPlaats(Coordinate plaats) {
        this.plaats = plaats;
    }
    
    public Kleur getKleur() {
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

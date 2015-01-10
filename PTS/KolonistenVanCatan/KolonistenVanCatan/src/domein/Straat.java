package domein;

import java.awt.Color;

/**
 * In this class you can find all properties and operations for Straat
 * 
 * @author Daan
 */
public class Straat {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    private Coordinate begin;
    private Coordinate einde;
    private Kleur kleur;
    public Speler unnamed_Speler_;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public Straat(Coordinate begin, Coordinate einde, Kleur kleur) {
        this.begin = begin;
        this.einde = einde;
        this.kleur = kleur;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Operations">
    
    public Coordinate[] getPlaats() {
        Coordinate[] returner = new Coordinate[2];
        returner[0] = begin;
        returner[1] = einde;
        return returner;
    }
    
    public void setPlaats(Coordinate[] plaats) {
        this.begin = plaats[0];
        this.einde = plaats[1];
    }
    
    public Kleur getKleur() {
        return this.kleur;
    }
    
    //</editor-fold>
}

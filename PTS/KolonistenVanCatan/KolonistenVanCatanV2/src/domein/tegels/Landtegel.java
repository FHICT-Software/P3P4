package domein.tegels;

import domein.*;

/**
 * In this class you can find all properties and operations for Landtegel.
 *  
 * @organization Fontys S31D
 * @author Thomas  
 * @date 24-3-2014
 */
public class Landtegel extends Tegel {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final int landnummer;
    private final Grondstof grondstof;
    private boolean isActief;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(landnummer, isActief)">
    public Landtegel(int landnummer, Grondstof grondstof, Hexagon plaats) {
        super(plaats);
        this.landnummer = landnummer;
        this.isActief = true;
        this.grondstof = grondstof;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getLandnummer() {
        return this.landnummer;
    }
	
    public Grondstof getGrondstof() {
        return this.grondstof;
    }

    public boolean getIsActief() {
        return this.isActief;
    }

    public void setIsActief(boolean isActief) {
        this.isActief = isActief;
    }
    //</editor-fold>
       
    //</editor-fold>
}

package domein.tegels;

import domein.*;

/**
 * In this class you can find all properties and operations for Haventegel.
 * //CHECK
 * 
* @organization: Fontys S31D
 * @author //TODO
 * @date //TODO
 */
public class Haventegel extends Tegel {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private int havezijde;
    private Grondstof grondstof;
    private int handelsratio;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(havezijde, grondstof, handelsratio)">
    public Haventegel(int havezijde, Grondstof grondstof, int handelsratio, Hexagon plaats) {
        super(plaats);
        this.grondstof = grondstof;
        this.handelsratio = handelsratio;
        this.havezijde = havezijde;
    }

    //</editor-fold>
    
    public int getHavezijde() {
        return havezijde;
    }

    public Grondstof getGrondstof() {
        return grondstof;
    }

    public int getHandelsratio() {
        return handelsratio;
    }
	//</editor-fold>

}

package domein.tegels;

import domein.*;
import java.io.Serializable;


/**
 * In this class you can find all properties and operations for Tegel. And all
 * inherratance classes:Woestijn, Landtegel, Havetegel, Zeetegel
 *
*
 * @organization: Fontys S31D
 * @author //TODO
 * @date //TODO
 */
public class Tegel implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final Hexagon plaats;
    //</editor-fold>

	//<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(soort, plaats)">
    public Tegel(Hexagon plaats) {
        this.plaats = plaats;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlaats">
    public Hexagon getPlaats() {
        return this.plaats;
    }
	//</editor-fold>

    //</editor-fold>
}

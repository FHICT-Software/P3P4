//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;
//</editor-fold>

/**
 * In deze class worden alle functies en reaties van een bot geschreven.
 *
 * @author jeroen
 */
class Bot extends Speler {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private int spelerNr;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Dit is de constructor van Bot. Hierin worden alle eigenschappen van bot
     * en van speler (super) geinitializeerd.
     *
     * @param spelerNr is nodig om de locatie en kleur te bepalen.
     */
    public Bot(SpelerKleur spelerKleur) {
	super(spelerKleur);
	if (spelerKleur == SpelerKleur.groen) {
	    this.spelerNr = 2;
	} else if (spelerKleur == SpelerKleur.blauw) {
	    this.spelerNr = 3;
	}
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reaction">
    /**
     * In deze methode wordt er een reactie gedaan aan hand van puck. Hierin
     * wordt dus een nieuwe x en y berekent.
     *
     * @param puck is het object waar de reactie van afhankelijk is.
     */
    public void doReactie(Puck puck) {
	switch (spelerNr) {
	    case 2:
		if (puck.y + puck.radius < super.batje.y - super.batje.radius) {
		    super.batje.speed = (10 / ((super.batje.x - puck.x) / 20));
		    if (super.batje.speed < 2) {
			super.batje.speed = 2;
		    }
		    super.batje.verplaatsBatje(240);
		} else if (puck.y - puck.radius > super.batje.y) {
		    super.batje.verplaatsBatje(60);
		    super.batje.speed = (10 / ((super.batje.x - puck.x) / 20));
		    if (super.batje.speed < 2) {
			super.batje.speed = 2;
		    }
		}
		break;
	    case 3:
		if (puck.y + puck.radius < super.batje.y - super.batje.radius) {
		    super.batje.verplaatsBatje(300);
		    super.batje.speed = (10 / ((super.batje.x - puck.x) / 20));
		    if (super.batje.speed < 2) {
			super.batje.speed = 2;
		    }
		} else if (puck.y - puck.radius > super.batje.y) {
		    super.batje.verplaatsBatje(120);
		    super.batje.speed = (10 / ((super.batje.x - puck.x) / 20));
		    if (super.batje.speed < 2) {
			super.batje.speed = 2;
		    }
		}
		break;
	}
    }
    //</editor-fold>
}

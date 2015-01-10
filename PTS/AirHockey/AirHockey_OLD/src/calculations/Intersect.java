//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculations;

import airhockey.*;
//</editor-fold>

/**
 * Dit is een static class die alleen aangeroepen wordt om te kijken of er een
 * Collision is.
 * Indien er een Collision plaatsvind wordt ook meteen de richting van de puck
 * aangepast.
 *
 * @author jeroen
 */
public class Intersect {

    //<editor-fold defaultstate="collapsed" desc="Puck met Batje">
    /**
     * Deze functie kijkt of er Collision plaatsvind.
     * Indien er collision plaatsvind wordt de richting van de puck aangepast,
     * en als de puck zich in het batje begeeft wordt deze aan de rand van het
     * batje geplaatst.
     *
     * @param puck  Het balletje dat mogelijk weggekaatst wordr door het batje.
     * @param batje Kaatst de puck weg, indien die tegen het batje komt.
     *
     * @return geeft true terug indien de puck tegen het batje is gekomen.
     */
    public static boolean Intersects(Puck puck, Batje batje) {
	//<editor-fold defaultstate="collapsed" desc="Initialization">
	double hoek = 0;
	double oldHoek = 0;
	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Quadrant 1">
	if (batje.x > puck.x && batje.y > puck.y) {
	    if (Math.sqrt(Math.pow(batje.x - puck.x, 2) + Math.pow(batje.y - puck.y, 2)) < batje.radius + puck.radius) {
		hoek = Math.toDegrees(Math.atan2(batje.x - puck.x, batje.y - puck.y));
		puck.x = (float) (batje.x - (Math.sin(Math.toRadians(hoek)) * (batje.radius + puck.radius)));
		puck.y = (float) (batje.y - (Math.cos(Math.toRadians(hoek)) * (batje.radius + puck.radius)));
	    }
	} //</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Quadrant 2">
	else if (batje.x > puck.x && puck.y > batje.y) {
	    if (Math.sqrt(Math.pow(batje.x - puck.x, 2) + Math.pow(puck.y - batje.y, 2)) < batje.radius + puck.radius) {
		hoek = Math.toDegrees(Math.atan2(batje.x - puck.x, puck.y - batje.y));
		puck.x = (float) (batje.x - (Math.sin(Math.toRadians(hoek)) * (batje.radius + puck.radius)));
		puck.y = (float) ((Math.cos(Math.toRadians(hoek)) * (batje.radius + puck.radius)) + batje.y);
	    }
	} //</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Quadrant 3">
	else if (puck.x > batje.x && puck.y > batje.y) {
	    if (Math.sqrt(Math.pow(puck.x - batje.x, 2) + Math.pow(puck.y - batje.y, 2)) < batje.radius + puck.radius) {
		hoek = Math.toDegrees(Math.atan2(puck.x - batje.x, puck.y - batje.y));
		puck.x = (float) ((Math.sin(Math.toRadians(hoek)) * (batje.radius + puck.radius)) + batje.x);
		puck.y = (float) ((Math.cos(Math.toRadians(hoek)) * (batje.radius + puck.radius)) + batje.y);
	    }
	} //</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Quadrant 4">
	else if (puck.x > batje.x && batje.y > puck.y) {
	    if (Math.sqrt(Math.pow(puck.x - batje.x, 2) + Math.pow(batje.y - puck.y, 2)) < batje.radius + puck.radius) {
		hoek = Math.toDegrees(Math.atan2(puck.x - batje.x, batje.y - puck.y));
		puck.x = (float) ((Math.sin(Math.toRadians(hoek)) * (batje.radius + puck.radius)) + batje.x);
		puck.y = (float) (batje.y - (Math.cos(Math.toRadians(hoek)) * (batje.radius + puck.radius)));
	    }
	}
	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Returning">
	if (oldHoek != hoek) {
	    double nieweRichting = hoek - (puck.richting) + hoek;
	    while (nieweRichting > 360) {
		nieweRichting -= 360;
	    }
	    while (nieweRichting < 0) {
		nieweRichting += 360;
	    }
	    if (puck.lastIntersectedWith != batje) {
		puck.richting = (float) nieweRichting;
		puck.lastIntersectedWith = batje;
	    }
	    oldHoek = hoek;
	    return true;
	} else {
	    return false;
	}
	//</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Puck met Lijn">
    /**
     * Deze functie kijkt of er Collision plaatsvind.
     * Indien er collision plaatsvind wordt de richting van de puck aangepast.
     *
     * @param puck Het balletje dat mogelijk afkaatst op de lijn.
     * @param lijn De lijn waar de puck op af kan kaatsen.
     *
     * @return geeft true terug als de puck is afgekaatst.
     */
    public static boolean Intersects(Puck puck, Lijn lijn) {
	boolean isIntersected = false;
	if (puck.x + puck.radius < lijn.getLocation1().getX() && puck.x + puck.radius < lijn.getLocation2().getX()) {
	} else if (puck.x - puck.radius > lijn.getLocation1().getX() && puck.x - puck.radius > lijn.getLocation2().getX()) {
	} else if (puck.y + puck.radius < lijn.getLocation1().getY() && puck.y + puck.radius < lijn.getLocation2().getY()) {
	} else if (puck.y - puck.radius > lijn.getLocation1().getY() && puck.y - puck.radius > lijn.getLocation2().getY()) {
	} else {
	    String locationToLine = locationToLine(puck, lijn);
	    if (locationToLine.equals("OpOp")) {
		puck.restart();
	    } else if (locationToLine.contains("Op")) {
		isIntersected = true;
		float hoek = (float) Math.toDegrees(Math.atan2(lijn.getLocation2().getY() - lijn.getLocation1().getY(), lijn.getLocation2().getX() - lijn.getLocation1().getX()));
		double nieweRichting = hoek - puck.richting + hoek;
		while (nieweRichting > 360) {
		    nieweRichting -= 360;
		}
		while (nieweRichting < 0) {
		    nieweRichting += 360;
		}
		puck.richting = (float) nieweRichting;
		puck.lastIntersectedWith = lijn;
	    }
	}
	return isIntersected;
    }

    /**
     * Deze functie kijkt naar de globale positie van de Puck ten opzichte van
     * de lijn.
     *
     * @param puck De puck waarvan de relative locatie wordt berekend.
     * @param lijn De lijn waar de locatie relatief tot gemeten wordt.
     *
     * @return Een globale relative locatie van de Puck t.o.v. de lijn.
     *         Bijvoorbeeld RechtsOnder.
     */
    private static String locationToLine(Puck puck, Lijn lijn) {
	StringBuilder sb = new StringBuilder();
	//<editor-fold defaultstate="collapsed" desc="Check X">
	double lijnYtoX = (lijn.getLocation2().getX() - lijn.getLocation1().getX()) / (lijn.getLocation2().getY() - lijn.getLocation1().getY());
	if (puck.x + puck.speedX - puck.radius > lijn.getLocation1().getX() + ((puck.y - lijn.getLocation1().getY()) * lijnYtoX)) {
	    sb.append("Rechts");
	} else if (puck.x + puck.speedX + puck.radius < lijn.getLocation1().getX() + ((puck.y - lijn.getLocation1().getY()) * lijnYtoX)) {
	    sb.append("Links");
	} else if (puck.x - puck.radius > lijn.getLocation1().getX() + ((puck.y - lijn.getLocation1().getY()) * lijnYtoX)) {
	    sb.append("Rechts");
	} else if (puck.x + puck.radius < lijn.getLocation1().getX() + ((puck.y - lijn.getLocation1().getY()) * lijnYtoX)) {
	    sb.append("Links");
	} else {
	    sb.append("Op");
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Check Y">
	double lijnXtoY = (lijn.getLocation2().getY() - lijn.getLocation1().getY()) / (lijn.getLocation2().getX() - lijn.getLocation1().getX());
	if (puck.y + puck.speedY - puck.radius > lijn.getLocation1().getY() + ((puck.x - lijn.getLocation1().getX()) * lijnXtoY)) {
	    sb.append("Onder");
	} else if (puck.y + puck.speedY + puck.radius < lijn.getLocation1().getY() + ((puck.x - lijn.getLocation1().getX()) * lijnXtoY)) {
	    sb.append("Boven");
	} else if (puck.y - puck.radius > lijn.getLocation1().getY() + ((puck.x - lijn.getLocation1().getX()) * lijnXtoY)) {
	    sb.append("Onder");
	} else if (puck.y + puck.radius < lijn.getLocation1().getY() + ((puck.x - lijn.getLocation1().getX()) * lijnXtoY)) {
	    sb.append("Boven");
	} else {
	    sb.append("Op");
	}
	//</editor-fold>
	return sb.toString();
    }
    //</editor-fold>
}

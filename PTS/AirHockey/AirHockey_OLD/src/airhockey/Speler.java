//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;
//</editor-fold>

import java.awt.Color;

/**
 * Deze classe bevat alle gegevens van een speler (Persoon of Bot).
 *
 * @author jeroen
 */
public class Speler {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    Persoon persoon;
    private SpelerKleur kleur;
    private int score;
    public Batje batje;

    public int getScore() {
	return this.score;
    }

    public void setScore(int score) {
	this.score += score;
    }

    public void resetScore() {
	this.score = 20;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Dit is de constructor van Speler. Hierin worden alle variabelen
     * geinitializeerd, en wordt de kleur van het batje geset.
     *
     * @param persoon
     * @param spelerKleur
     */
    public Speler(Persoon persoon, SpelerKleur spelerKleur) {
	this.persoon = persoon;
	kleur = spelerKleur;
	score = 20;
	switch (spelerKleur) {
	    case rood:
		batje = new Batje(Color.RED);
		break;
	    case groen:
		batje = new Batje(Color.GREEN);
		break;
	    case blauw:
		batje = new Batje(Color.BLUE);
		break;
	}
    }

    /**
     * Deze constructor wordt gebruikt voor Bots.
     */
    public Speler(SpelerKleur spelerKleur) {
	kleur = spelerKleur;
	score = 20;
	switch (spelerKleur) {
	    case rood:
		batje = new Batje(Color.RED);
		break;
	    case groen:
		batje = new Batje(Color.GREEN);
		break;
	    case blauw:
		batje = new Batje(Color.BLUE);
		break;
	}
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get Rating">
    public double getRating() {
        return this.persoon.getRating();
    }
    //</editor-fold>
}

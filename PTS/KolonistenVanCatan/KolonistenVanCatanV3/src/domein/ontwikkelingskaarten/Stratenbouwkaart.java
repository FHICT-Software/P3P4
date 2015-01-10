/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.ontwikkelingskaarten;

import domein.Bot;
import domein.Spel;
import domein.Speler;
import domein.Straat;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author Gio
 */
public class Stratenbouwkaart implements IOntwikkelingskaart {

    private String naam;
    private String beschrijving;
    private Spel spel;
    private Speler speler;
    private Random r;
    private KVCSpelGUI gui;

    public void Stratenbouwkaart(String naam, String beschrijving) {
        naam = "Stratenbouw";
        beschrijving = "Bij het spelen van deze kaart mag je direct twee straten bouwen";
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.r = new Random();
    }

    @Override
    public void actie() {

        for (int x = 0; x <= 1; x = x + 1) {
            Straat s = null;
            ArrayList<Straat> straten = spel.controleerBeschikbaarheidStraat();

            if (spel.getActiveSpeler() instanceof Bot) {
                s = straten.get(r.nextInt(straten.size()));
            } else {
                s = gui.getStraat(straten, speler.getKleur());
            }
            speler.setStraat(s);
            gui.setStraat(s);
        }
    }

    @Override
    public String getNaam() {
        return this.naam;
    }

    @Override
    public String getBeschrijving() {
        return this.beschrijving;
    }
}

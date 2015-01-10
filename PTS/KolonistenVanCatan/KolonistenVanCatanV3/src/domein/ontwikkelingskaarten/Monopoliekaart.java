/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.ontwikkelingskaarten;

import domein.Bot;
import domein.Grondstof;
import domein.Spel;
import domein.Speler;
import java.util.Iterator;
import java.util.Random;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author Gio
 */
public class Monopoliekaart implements IOntwikkelingskaart {

    private KVCSpelGUI gui;
    private String naam;
    private String beschrijving;
    private Spel spel;
    private Random r;

    public void Monopoliekaart(String naam, String beschrijving) {
        naam = "Monopolie";
        beschrijving = "Bij het spelen van deze kies je één grondstof. Alle Speler geven je hiervan alles in hun bezit";
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.r = new Random();
    }

    @Override
    public void actie() {
        Grondstof g = null;

        if (spel.getActiveSpeler() instanceof Bot) {
            int randomGrondstof = r.nextInt(4);
            if (randomGrondstof == 0) {
                g = g.HOUT;
            } else if (randomGrondstof == 1) {
                g = g.BAKSTEEN;
            } else if (randomGrondstof == 2) {
                g = g.ERTS;
            } else if (randomGrondstof == 3) {
                g = g.GRAAN;
            } else if (randomGrondstof == 4) {
                g = g.WOL;
            }
        } else {
            g = gui.getGekozenGrondstof();
        }

        Iterator i = spel.getSpelers();
        while (i.hasNext()) {
            Speler s = (Speler) i;
            Speler actief;
            int j = s.aantalGrondstoffen(g);

            actief = spel.getActiveSpeler();
            actief.setGrondstof(g, actief.aantalGrondstoffen(g) + j);
            s.setGrondstof(g, 0);
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

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
import java.util.ArrayList;
import java.util.Random;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author Gio
 */
public class Uitvindingkaart implements IOntwikkelingskaart {

    private String naam;
    private String beschrijving;
    private Speler speler;
    private KVCSpelGUI gui;
    private Spel spel;
    private Random r;
    private Grondstof g;

    public void Uitvindingkaart(String naam, String beschrijving) {
        naam = "Uitvinding";
        beschrijving = "Bij het spelen van deze kaart neem je direct twee grondstoffen naar keuze van de bank";
        this.naam = naam;
        this.beschrijving = beschrijving;
        r = new Random();
    }

    public void krijgGrondstof(Grondstof g) {
        speler.setGrondstof(g, speler.aantalGrondstoffen(g) + 1);
    }

    @Override
    public String getNaam() {
        return this.naam;
    }

    @Override
    public String getBeschrijving() {
        return this.beschrijving;
    }

    @Override
    public void actie() {
        ArrayList<Grondstof> grondstoffen = null;
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
            grondstoffen.add(g);
            int randomGrondstoftwee = r.nextInt(4);
            if (randomGrondstoftwee == 0) {
                g = g.HOUT;
            } else if (randomGrondstoftwee == 1) {
                g = g.BAKSTEEN;
            } else if (randomGrondstoftwee == 2) {
                g = g.ERTS;
            } else if (randomGrondstoftwee == 3) {
                g = g.GRAAN;
            } else if (randomGrondstoftwee == 4) {
                g = g.WOL;
            }
            grondstoffen.add(g);

        } else {
            grondstoffen = gui.getTweeSoortenGrondstoffen();
        }
        for (Grondstof g : grondstoffen) {
            krijgGrondstof(g);
        }
    }
}

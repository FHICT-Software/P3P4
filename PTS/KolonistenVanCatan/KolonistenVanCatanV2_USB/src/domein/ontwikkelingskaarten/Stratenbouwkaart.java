/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.ontwikkelingskaarten;

import domein.Spel;
import domein.Speler;
import javafx.scene.paint.Color;

/**
 *
 * @author Gio
 */
public class Stratenbouwkaart implements IOntwikkelingskaart {

    private String naam;
    private String beschrijving;
    private Spel spel;
    private Speler speler;

    public void Stratenbouwkaart(String naam, String beschrijving) {
        naam = "Stratenbouw";
        beschrijving = "Bij het spelen van deze kaart mag je direct twee straten bouwen";
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    @Override
    public void actie() {
        spel.straatBouwenKaart(speler);
        spel.straatBouwenKaart(speler);
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

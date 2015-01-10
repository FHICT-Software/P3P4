/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domein.ontwikkelingskaarten;

import domein.Spel;
import domein.Speler;

/**
 *
 * @author Giovanni
 */
public class Ridderkaart implements IOntwikkelingskaart{

    private String naam;
    private String beschrijving;
    private Spel spel;
    private Speler speler;
    
    public void Ridderkaart(String naam, String beschrijving) 
    {
        naam = "Ridder";
        beschrijving = "Bij het spelen van deze kaart moet je de struikrover verzetten en bij één van de getroffen spelers één grondstofkaart trekken";
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    @Override
    public void actie() 
    {
        spel.struikroverVerzetten();
        speler.setGespeeldeRidderkaarten();
        
    }

   @Override
    public String getNaam() 
    {
        return this.naam;
    }

    @Override
    public String getBeschrijving() 
    {
        return this.beschrijving;
    }
    
}

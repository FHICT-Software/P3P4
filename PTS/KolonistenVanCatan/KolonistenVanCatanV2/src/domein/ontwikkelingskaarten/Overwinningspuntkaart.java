/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domein.ontwikkelingskaarten;

import domein.Speler;

/**
 *
 * @author Gio
 */
public class Overwinningspuntkaart implements IOntwikkelingskaart{

    private String naam;
    private String beschrijving;
    private Speler speler;
    
    public void Overwinningspuntkaart(String naam, String beschrijving) 
    {
        naam = "Overwinningspunt";
        beschrijving = "Deze kaart is 1 overwinningspunt waard. Hij word automatische geheim gehouden voor tegenspelers";
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    @Override
    public void actie() 
    {
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

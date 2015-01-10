/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domein.ontwikkelingskaarten;

import domein.Spel;
import domein.Speler;
import java.util.Iterator;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author Gio
 */
public class Monopoliekaart implements IOntwikkelingskaart{

    private KVCSpelGUI gui;
    private String naam;
    private String beschrijving;
    private Spel spel;
    
  
    public void Monopoliekaart(String naam, String beschrijving) 
    {
        naam = "Monopolie";
        beschrijving = "Bij het spelen van deze kies je één grondstof. Alle Speler geven je hiervan alles in hun bezit";
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    @Override
    public void actie() 
    {
        //Grondstof g = gui.getGekozenGrondstof();
        
        Iterator i = spel.getSpelers();
        while(i.hasNext())
        {
            Speler s;
            Speler actief;
            //int j = s.aantalGrondstoffen(g);
            //actief = spel.getActiveSpeler();
            //actief.setGrondstof(g, actief.aantalGrondstoffen(g) + j);
            //s.setGrondstof(g, 0);
        }
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

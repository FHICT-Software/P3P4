/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.ontwikkelingskaarten;

import domein.Grondstof;
import domein.Speler;
import java.util.ArrayList;
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
    
    public void Uitvindingkaart(String naam, String beschrijving) 
    {
        naam = "Uitvinding";
        beschrijving = "Bij het spelen van deze kaart neem je direct twee grondstoffen naar keuze van de bank";
        this.naam = naam;
        this.beschrijving = beschrijving;    
    }

    public void krijgGrondstof(Grondstof g)
    {
        speler.setGrondstof(g, speler.aantalGrondstoffen(g) + 1);
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

    @Override
    public void actie() 
    {
        
        //ArrayList<Grondstof> grondstoffen = gui.getTweeSoortenGrondstoffen();
        //for(Grondstof g : grondstoffen){
        //    krijgGrondstof(g);
        //}
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.ontwikkelingskaarten.IOntwikkelingskaart;
import domein.ontwikkelingskaarten.Monopoliekaart;
import domein.ontwikkelingskaarten.Overwinningspuntkaart;
import domein.ontwikkelingskaarten.Ridderkaart;
import domein.ontwikkelingskaarten.Stratenbouwkaart;
import domein.ontwikkelingskaarten.Uitvindingkaart;
import domein.tegels.Haventegel;
import domein.tegels.Landtegel;
import domein.tegels.Tegel;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * In this class you can find all properties and operations for Speler. //CHECK
 *
 * @organization
 * @author anne
 * @date 9-apr-2014
 */
public class Speler implements Serializable {
    //<editor-fold defaultstate="collapsed" desc="Declarations">

    private final String gebruikersnaam;
    private ArrayList<Object[]> aantallenVanGrondstoffen;
    private ArrayList<Object[]> aantallenVanOntwikkelingskaarten;
    private int overwinningspunten;
    private final Kleur kleur;
    private ArrayList<Vesting> Vestigingen;
    private ArrayList<Straat> Straten;
    private ArrayList<IOntwikkelingskaart> Ontwikkelingskaarten;
    private Bord b;
    private Hexagon h;
    private int gespeeldeRidderkaarten;
    Object[] monopoliekaarten = null;
    Object[] overwinningspuntenkaarten = null;
    Object[] ridderkaarten = null;
    Object[] stratenbouwkaarten = null;
    Object[] uitvindingkaarten = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Speler(String naam, Kleur kleur) {
        this.gebruikersnaam = naam;
        this.kleur = kleur;
        this.Vestigingen = new ArrayList<>();
        this.Ontwikkelingskaarten = new ArrayList<>();
        this.aantallenVanOntwikkelingskaarten = new ArrayList<Object[]>();

        monopoliekaarten = new Object[2];
        Monopoliekaart monopoliekaart = new Monopoliekaart();
        monopoliekaarten[0] = monopoliekaart;
        monopoliekaarten[1] = 0;
        overwinningspuntenkaarten = new Object[2];
        Overwinningspuntkaart overwinningspuntkaart = new Overwinningspuntkaart();
        overwinningspuntenkaarten[0] = overwinningspuntkaart;
        overwinningspuntenkaarten[1] = 0;
        ridderkaarten = new Object[2];
        Ridderkaart ridderkaart = new Ridderkaart();
        ridderkaarten[0] = ridderkaart;
        ridderkaarten[1] = 0;
        stratenbouwkaarten = new Object[2];
        Stratenbouwkaart stratenbouwkaart = new Stratenbouwkaart();
        stratenbouwkaarten[0] = stratenbouwkaart;
        stratenbouwkaarten[1] = 0;
        uitvindingkaarten = new Object[2];
        Uitvindingkaart uitvindingkaart = new Uitvindingkaart();
        uitvindingkaarten[0] = uitvindingkaart;
        uitvindingkaarten[1] = 0;


    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Oprarations">
    public String getNaam() {
        return gebruikersnaam;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getOverwinningspunten() {
        return this.overwinningspunten;
    }

    public void setOverwinningspunten(int overwinningspunten) {
        this.overwinningspunten = overwinningspunten;
    }

    public int getGespeeldeRidderkaarten() {
        return this.gespeeldeRidderkaarten;
    }

    public void setGespeeldeRidderkaarten() {
        this.gespeeldeRidderkaarten = gespeeldeRidderkaarten + 1;
    }

    public boolean voorraadToereikend(Grondstof grondstof, int aantal) {
        boolean toereikend = false;
        for (Object[] o : aantallenVanGrondstoffen) {
            Grondstof gs = (Grondstof) o[0];
            int i = (int) o[1];
            if (gs == grondstof && i >= aantal) {

                return true;
            }
        }
        return toereikend;
    }

    public int aantalGrondstoffen(Grondstof grondstof) {
        for (Object[] o : aantallenVanGrondstoffen) {
            if (o[0] == grondstof) {
                return (int) o[1];
            }
        }
        return 0;
    }

    public boolean moetGrondstoffenInleveren() {
        int aantal = 0;
        for (Object[] o : aantallenVanGrondstoffen) {
            for (Object o2 : o) {
                aantal += (int) o[1];
            }
        }
        return (aantal > 7);
    }

    public void setGrondstof(Grondstof grondstof, int aantal) {
        for (Object[] o : aantallenVanGrondstoffen) {
            if ((Grondstof) o[0] == grondstof) {
                if (aantal + (int) o[1] >= 0) {
                    o[1] = aantal + (int) o[1];
                }
            }
        }
    }

    public void setDorp(Vesting v) {
        int count = 0;
        for (Vesting vesting : Vestigingen) {
            if (vesting.getIsStad() == false) {
                count = count + 1;
            }
        }
        if (count <= 4) {
            Vestigingen.add(v);
        }
    }

    public void setStad(Vesting v) {
        int count = 0;
        for (Vesting vesting : Vestigingen) {
            if (vesting.getIsStad() == true) {
                count = count + 1;
            }
        }
        if (count <= 3) {
            Vestigingen.add(v);
        }
    }

    public void setStraat(Straat s) {
        Straten.add(s);
    }

    public ArrayList<Straat> getStraten() {
        return Straten;
    }

    public ArrayList<Vesting> getVestigingen() {
        return this.Vestigingen;
    }

    public void grondstofInnen(int i) {
// IK HEB GEEN IDEE HOE IK TEGEL EN LANDTEGEL MOET 'KOPPELEN' WANT ALLEEN LANDTEGELS HEBBEN NUMMERS DIE JE KAN GOOIEN MAAR ER IS GEEN LIJST VAN LANDTEGELS VOLGENS MIJ
        
        Landtegel l = null;
        Grondstof g = null;

        for (Tegel te : b.getTegels("LandTegel")) {
                for (Vesting v : Vestigingen) {
                        for (Coordinate c : h.getVertices()) {
                            if (c.getX() > v.getPlaats().getX() -2 && c.getX() < v.getPlaats().getX() +2 && c.getY() > v.getPlaats().getY() -2 && c.getY() < v.getPlaats().getY() +2) {
                                if (l.getLandnummer() == i) {
                                    g = l.getGrondstof();
                                    setGrondstof(g, 1);
                                }
                            }
                        }
                    
                }
        }
    }

    public ArrayList<IOntwikkelingskaart> getOntwikkelingskaarten() {
        return this.Ontwikkelingskaarten;
    }

    public void addOntwikkelingskaarten(IOntwikkelingskaart ontwikkelingskaart) {
        Ontwikkelingskaarten.add(ontwikkelingskaart);

        switch (ontwikkelingskaart.getNaam()) {
            case "Monopoliekaart":
                monopoliekaarten[0] = monopoliekaarten;
                monopoliekaarten[1] = (int) monopoliekaarten[1] + 1;
                aantallenVanOntwikkelingskaarten.set(0, monopoliekaarten);
                break;
            case "Overwinningspuntkaart":
                overwinningspuntenkaarten[0] = overwinningspuntenkaarten;
                overwinningspuntenkaarten[1] = (int) overwinningspuntenkaarten[1] + 1;
                aantallenVanOntwikkelingskaarten.set(1, overwinningspuntenkaarten);
                break;
            case "Ridderkaart":
                ridderkaarten[0] = ridderkaarten;
                ridderkaarten[1] = (int) ridderkaarten[1] + 1;
                aantallenVanOntwikkelingskaarten.set(2, ridderkaarten);
                break;
            case "Stratenbouwkaart":
                stratenbouwkaarten[0] = stratenbouwkaarten;
                stratenbouwkaarten[1] = (int) stratenbouwkaarten[1] + 1;
                aantallenVanOntwikkelingskaarten.set(3, stratenbouwkaarten);
                break;
            case "Uitvindingkaart":
                uitvindingkaarten[0] = uitvindingkaarten;
                uitvindingkaarten[1] = (int) uitvindingkaarten[1] + 1;
                aantallenVanOntwikkelingskaarten.set(4, uitvindingkaarten);
                break;
        }
    }

    public void verwijderOntwikkelingskaart(IOntwikkelingskaart ontwikkelingskaart) {
        Ontwikkelingskaarten.remove(ontwikkelingskaart);

        switch (ontwikkelingskaart.getNaam()) {
            case "Monopoliekaart":
                monopoliekaarten[0] = monopoliekaarten;
                monopoliekaarten[1] = (int) monopoliekaarten[1] - 1;
                aantallenVanOntwikkelingskaarten.set(0, monopoliekaarten);
                break;
            case "Overwinningspuntkaart":
                overwinningspuntenkaarten[0] = overwinningspuntenkaarten;
                overwinningspuntenkaarten[1] = (int) overwinningspuntenkaarten[1] - 1;
                aantallenVanOntwikkelingskaarten.set(1, overwinningspuntenkaarten);
                break;
            case "Ridderkaart":
                ridderkaarten[0] = ridderkaarten;
                ridderkaarten[1] = (int) ridderkaarten[1] - 1;
                aantallenVanOntwikkelingskaarten.set(2, ridderkaarten);
                break;
            case "Stratenbouwkaart":
                stratenbouwkaarten[0] = stratenbouwkaarten;
                stratenbouwkaarten[1] = (int) stratenbouwkaarten[1] - 1;
                aantallenVanOntwikkelingskaarten.set(3, stratenbouwkaarten);
                break;
            case "Uitvindingkaart":
                uitvindingkaarten[0] = uitvindingkaarten;
                uitvindingkaarten[1] = (int) uitvindingkaarten[1] - 1;
                aantallenVanOntwikkelingskaarten.set(4, uitvindingkaarten);
                break;
        }
    }
    //</editor-fold>
}

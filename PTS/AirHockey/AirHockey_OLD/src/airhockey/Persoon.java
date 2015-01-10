//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;

import java.util.ArrayList;
//</editor-fold>

/**
 * In deze class staan de gegevens over een gebruiker (persoon).
 *
 * @author jeroen
 */
public class Persoon {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private String gebruikersNaam;
    private String wachtwoord;
    private double rating;
    private ArrayList<Double> ratingScores = new ArrayList<Double>();

    public String getGebruikersNaam() {
        return this.gebruikersNaam;
    }

    public String getWachtwoord() {
        return this.wachtwoord;
    }

    public double getRating() {
        return this.rating;
    }

    public ArrayList<Double> getRatingScores() {
        if (this.ratingScores == null) {
            this.ratingScores = new ArrayList<Double>();
            ratingScores.add(15.0);
        }
        return this.ratingScores;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In deze constructor wordt een bestaant persoon aangemaakt.
     *
     * @param gebruikersnaam de gebruikersnaam waarmee de persoon inlogt.
     * @param wachtwoord het wachtwoord waarmee de persoon inlogt.
     * @param ratingScores de scores die bij dit persoon horen.
     */
    public Persoon(String gebruikersnaam, String wachtwoord, ArrayList<Double> ratingScores) {
        this.gebruikersNaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.ratingScores = ratingScores;
        setRating();
    }

    /**
     * In deze constructor wordt een nieuw persoon aangemaakt.
     *
     * @param gebruikersnaam de gebruikersnaam waarmee de persoon inlogt.
     * @param wachtwoord het wachtwoord waarmee de persoon inlogt.
     */
    public Persoon(String gebruikersnaam, String wachtwoord) {
        this.gebruikersNaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.rating = 16;
        this.ratingScores = Serialization.loadScores(this.gebruikersNaam);
    }

    /**
     * In deze constructor wordt een tijdelijk persoon aangemaakt.
     *
     * @param gebruikersnaam de gebruikersnaam waarmee ingelogt wordt.
     */
    public Persoon(String gebruikersnaam) {
        this.gebruikersNaam = gebruikersnaam;
        this.rating = 16;
    }
    //</editor-fold>

    //<editor-fold desc="Methoden">
    
    //<editor-fold defaultstate="collapsed" desc="CheckPassword">
    /**
     * In deze methode wordt gekeken of het wachtwoord gelijk is aan het
     * megegeven wachtwoord gelijk is aan het wachtwoord dat al in de class
     * staat.
     *
     * @param ww het wachtwoord dat vergelijkt wordt met het wachtwoord in de
     * class.
     * @return geeft true als de wachtwoorden overeen komen.
     */
    public boolean CheckWachtWoord(String ww) {
        if (this.wachtwoord.equals(ww)) {
            return true;
        } else {
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Set Rating">
    /**
     * Deze functie set de nieuwe rating aan hand van de aangeleverde formulen.
     */
    public void setRating() {
        double rating = 0;
        if (ratingScores.size() == 5) {
            int i = 1;
            for (Double ratingScore : this.ratingScores) {
                rating = rating + (i * ratingScore);
                i++;
            }
            rating = rating / 15;
            this.rating = rating;
        } else {
            this.rating = 15;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add New Rating Score">
    /**
     * Deze functie voegt een ratingScore toe aan de ratingScores lijst.
     *
     * @param ratingScore is de ratingScore die toegevoegd wordt.
     */
    public void addNewRatingScores(double ratingScore) {
        this.ratingScores.add(ratingScore);
        if (this.ratingScores.size() > 5) {
            this.ratingScores.remove(0);
        }
        this.ratingScores.trimToSize();
        setRating();

        Serialization.saveScores(this);
    }
    //</editor-fold>
    //</editor-fold>
}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
package airhockey;

import com.sun.corba.se.spi.ior.IOR;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//</editor-fold>

/**
 * Dit is een abstracte class die aangeroepen wordt voor het opslaan en uitlezen
 * van documenten.
 *
 * @author Phinux
 */
public abstract class Serialization {

    //<editor-fold defaultstate="collapsed" desc="Nicknames And Password">
    /**
     * In deze methode wordt een nieuwe speler aangemaakt en opgeslagen in een
     * bestand.
     *
     * @param gebruikersnaam de gebruikersnaam van de nieuwe speler.
     * @param wachtwoord     het wachtwoord van de nieuwe speler.
     */
    public static void nieuweSpeler(String gebruikersnaam, String wachtwoord) {
        Save saveBestand = new Save(gebruikersnaam, wachtwoord);
        save(gebruikersnaam, saveBestand);
    }

    /**
     * In deze methode wordt een persoon uitgelezen uit een bestand.
     *
     * @param gebruikersNaam hiermee wordt gekeken welk bestand uitgelezen moet
     *                       worden.
     *
     * @return Persoon geeft de persoonsclasse terug gevuld met de gegevens uit
     *         het bestand.
     */
    public static Persoon GetSpeler(String gebruikersNaam) {
        Persoon persoon = null;
        Save saveBestand = load(gebruikersNaam);
        if (saveBestand != null) {
            persoon = new Persoon(saveBestand.gebruikersnaam, saveBestand.wachtwoord, saveBestand.ratingScores);
        }
        return persoon;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Scores">
    /**
     * set van nieuwe lijst van ratingScore
     *
     * @param persoon
     */
    public static void saveScores(Persoon persoon) {
        Save savebestand = new Save(persoon.getGebruikersNaam(), persoon.getWachtwoord(), persoon.getRatingScores());
        save(persoon.getGebruikersNaam(), savebestand);
    }

    /**
     * get van ratingScores.
     *
     * @param gebruikersnaam is de naam van de gebruiker (en van het bestand).
     *
     * @return geeft een lijst met de laatste 5 scores terug.
     */
    public static ArrayList<Double> loadScores(String gebruikersnaam) {
        return load(gebruikersnaam).ratingScores;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Serilize">
    /**
     * Het opslaan van een save bestand
     *
     * @param bestandsNaam
     * @param saveObject
     */
    private static void save(String bestandsNaam, Save saveObject) {
        StringBuilder sb = new StringBuilder();
        sb.append("saves/");
        sb.append(bestandsNaam);

        try {
            ObjectOutputStream out;
            FileOutputStream stream;
            stream = new FileOutputStream(sb.toString());
            out = new ObjectOutputStream(stream);
            out.writeObject(saveObject);
            //TODO Fixen (hij kan het bestand niet vinden).
        } catch (IOException exception) {
            //TODO Exception afvangen.
        }
    }

    /**
     * Ophalen van een saveBestand
     *
     * @param bestandsNaam
     */
    private static Save load(String bestandsNaam) {
        StringBuilder sb = new StringBuilder();
        sb.append("saves/");
        sb.append(bestandsNaam);

        Save saveBestand = null;

        try {
            ObjectInputStream in;
            FileInputStream stream;
            stream = new FileInputStream(sb.toString());
            in = new ObjectInputStream(stream);
            saveBestand = (Save) in.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }

        return saveBestand;
    }
    //</editor-fold>
}

class Save implements java.io.Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    String gebruikersnaam;
    String wachtwoord;
    Double highRating;
    ArrayList<Double> ratingScores;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Save(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.ratingScores = new ArrayList<Double>();
        this.ratingScores.add(15.0);
    }

    public Save(String gebruikersnaam, String wachtwoord, ArrayList<Double> ratingScores) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.ratingScores = ratingScores;
    }
    //</editor-fold>
}

package domein;

import domein.ontwikkelingskaarten.IOntwikkelingskaart;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * In this class you can find all properties and operations for Bot. //CHECK
 *
 * @organization: Fontys S31D
 * @author //TODO
 * @date //TODO
 */
public class Bot extends Speler {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    Random r;
    private int moeilijkheidsgraad;
    private ArrayList<Grondstof> grondstoffen;
    private int overwinningspunten;
    Spel spel;
    private ArrayList<Vesting> Vestigingen;
    private ArrayList<Straat> Straten;
    private ArrayList<IOntwikkelingskaart> Ontwikkelingskaarten;
    private int gespeeldeRidderkaarten;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(moeilijkheidsgraad)">
    public Bot(int moeilijkheidsgraad, String naam, Color Black) {
        super(naam, new Kleur(Black));
        this.moeilijkheidsgraad = moeilijkheidsgraad;

        this.Ontwikkelingskaarten = new ArrayList<>();
        this.Straten = new ArrayList<>();
        this.Vestigingen = new ArrayList<>();
        r = new Random();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getMoeilijkheidsgraad">
    public int getMoeilijkheidsgraad() {
        return this.moeilijkheidsgraad;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setMoeilijkheidsgraat">
    public void setMoeilijkheidsgraad(int moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="dorpBouwen">
    public void dorpBouwen() {
        spel.dorpBouwen(this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="straatBouwen">
    public void straatBouwen() {
        spel.straatBouwen(this);

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="handelen">
    public void handelen() {
        //spel.Handelen();

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="struikroverVerzetten">
    public void struikroverVerzette() {
        spel.struikroverVerzetten();

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="creeerStad">
    public void stadBouwen() {
        spel.creëerStad(this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ontwikkelingskaartInzetten">
    public void ontwikkelingskaartInzetten() {
        spel.ontwikkelingskaartInzetten();
    }
    //</editor-fold

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ontwikkelingskaartKopen">
    public void ontwikkelingskaartKopen() {
        spel.ontwikkelingskaartKopen(this);
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDorpPlaats">
    public Point getDorpPlaats(ArrayList<Point> legePlekken) {
        int plek = r.nextInt(legePlekken.size());
        return legePlekken.get(plek);
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getStraatPlaats">
    public Point getStraatPlaats(ArrayList<Straat> legeStraten) {
//        int plek = r.nextInt(legeStraten.size());
//        return legeStraten.get(plek).getPlaats().getLocation();
        return null;
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getGewensteGrondstof">
    public Grondstof getGewensteGrondstof(ArrayList<Grondstof> grondstoffen) {
        int grondstof = r.nextInt(grondstoffen.size());
        return grondstoffen.get(grondstof);
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="struikRoverPlek">
    public Point struikRoverPlek(ArrayList<Point> plekken) {
        int plek = r.nextInt(plekken.size());
        return plekken.get(plek).getLocation();
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getNieuweStadPlek">
    public int getNieuweStadPlek(ArrayList<Vesting> dorpen) {
        int dorpPlek = r.nextInt(dorpen.size());
        return dorpPlek;
    }
    //</editor-fold
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getOntwikkelingskaartInzetten">
    public IOntwikkelingskaart getOntwikkelingskaartInzetten(ArrayList<IOntwikkelingskaart> ontwikkelingskaarten) {
        int inzetten = r.nextInt(ontwikkelingskaarten.size());
        return ontwikkelingskaarten.get(inzetten);
    }
    //</editor-fold
    //</editor-fold>
}

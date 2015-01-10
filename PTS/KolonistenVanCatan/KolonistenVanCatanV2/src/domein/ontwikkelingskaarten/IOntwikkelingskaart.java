package domein.ontwikkelingskaarten;

/**
 * In this class you can find all properties and operations for
 * IOntwikkelingskaart.
 * 
*
 * @organization: Fontys S31D
 * @author Anne Toonen
 * @date 23-3-2014
 */
public interface IOntwikkelingskaart {

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="actie">
    /**
     * In deze methode word elke afzondelijke actie van elke afzondelijke
     * ontwikkelings kaart verwerkt. Bijv. Voor de ridderkaart word 1 bij
     * speler.gespeeldeRidderkaart opgetelt en word de functie
     * spel.struikrover(): aageroepen
     *
     */
    public void actie();
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getNaam">
    /**
     *
     * @return de naam van de kaart. Bijv Ridderkaart
     */
    public String getNaam();
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getBeschrijving">
    /**
     *
     * @return de berschrijving van de kaart. bijv. Bij het spelen vandeze
     * kaartmoet je de struikrover verzetten en van één van de getroffenspelers
     * één grondstoffenkaart trekken.
     */
    public String getBeschrijving();
	//</editor-fold>
    //</editor-fold>
}

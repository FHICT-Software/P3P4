//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
/**
 * In this class you can find all properties and operations for Bot. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Bot extends Player {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    public static int nextBotNr = 0;
    public int botNr = 0;
    private int directionUp;
    private int directionDown;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Bot(int Nr) {
        super(null, Nr);
        if (nextBotNr == 0) {
            botNr = nextBotNr;
            nextBotNr++;
            directionUp = 300;
            directionDown = directionUp - 180;
        } else {
            botNr = nextBotNr;
            nextBotNr++;
            directionUp = 240;
            directionDown = directionUp - 180;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="preformAction">
    /**
     * This method contains comes the artificial intelligence coding for the bots. This method calls the super.setDirection()
     * function.
     *
     * @param puck
     */
    public void preformAction(Puck puck) {
        int ppy = puck.getPosition().y;
        double pr = puck.getRadius();
        double bpy = getBat().getPosition().y;
        double br = getBat().getRadius();
        if (ppy + pr < bpy - br) {
            getBat().startMoving();
            getBat().setDirection(directionUp);
        } else if (puck.getPosition().y - puck.getRadius() > this.getBat().getPosition().y + this.getBat().getRadius()) {
            getBat().startMoving();
            getBat().setDirection(directionDown);
        }
    }
    //</editor-fold>
//</editor-fold>

}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

//</editor-fold>
import components.lobby.User;
import java.awt.Color;
import java.io.Serializable;

/**
 * In this class you can find all properties and operations for Player. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public class Player implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Statics">
    private static int nextNr = 1;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finals">
    private final User user;
    private final int nr;
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="Nr">
    public int getNr() {
        return nr;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Color">
    public Color getColor() {
        switch (nr) {
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
        }
        return Color.BLACK;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User">
    public User getUser() {
        return user;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(user)">
    /**
     * This is the constructor for Player.
     *
     * @param user is the user that plays with this player.
     */
    public Player(User user) {
        this.user = user;
        this.nr = nextNr;
        nextNr++;
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Class">
    @Override
    public String toString() {
        return user.toString();
    }
    //</editor-fold>
}

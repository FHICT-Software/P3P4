//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

import components.lobby.User;
import interfaces.IDrawable;
import java.awt.Color;
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Player. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public class Player implements Serializable, IDrawable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Statics">
    private static int nextNr = 0;
    private static boolean serverPlusOne = false;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finals">
    private final Bat bat;
    private final User user;
    private final int nr;
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="Nr">
    public int getNr() {
        return nr;
    }

    public static void serverPlusOne() {
        if (!serverPlusOne) {
            nextNr++;
            serverPlusOne = true;
        }
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
        this.bat = new Bat(this, nr, "black");
        nextNr++;
        if (nextNr == 3){
            nextNr = 0;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getBat">
    public Bat getBat() {
        return this.bat;
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Class">

    @Override
    public String toString() {
        return user.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Player) {
            return this.equals((Player) object);
        } else {
            return false;
        }
    }

    public boolean equals(Player player) {
        return this.nr == player.nr;
    }
    //</editor-fold>

    @Override
    public void draw(GraphicsContext graphicsContext) {
        bat.draw(graphicsContext);
    }
}

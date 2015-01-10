//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import calculations.NameGenerator;
import interfaces.IDrawable;
import javafx.scene.canvas.GraphicsContext;

/**
 * In this class you can find all properties and operations for Player. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Player implements IDrawable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final Bat bat;
    private final User user;
    private int score;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(user)">
    public Player(User user, int Nr) {
        if (user == null) {
            NameGenerator nameGenerator = new NameGenerator();
            user = new User(nameGenerator.getName(), 15);
        }
        this.user = user;
        this.bat = new Bat(this, Nr);
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getBat">
    public Bat getBat() {
        return this.bat;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getUser">
    public User getUser() {
        return this.user;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getScore">
    public int getScore() {
        return this.score;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="scorePlus()">
    public void scorePlus() {
        score++;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="scoreMin()">
    public void scoreMin() {
        score--;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(graphicsContext, size)">
    @Override
    public void draw(GraphicsContext graphicsContext) {
        bat.draw(graphicsContext);
    }
    //</editor-fold>
    //</editor-fold>
}

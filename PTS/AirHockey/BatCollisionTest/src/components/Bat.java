//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import calculations.Drawable;
import interfaces.IDrawable;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;

/**
 * In this class you can find all properties and operations for Bat. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Bat implements IDrawable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final int RADIUS;
    private Point position;
    private final double SPEED;
    private double direction;
    private final Player player;
    private boolean isMoving = false;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Bat(Player player, int Nr) {
        int center = Drawable.getFieldWidth() / 2;
        position = new Point(center, center);
        SPEED = 0;
        this.player = player;
        RADIUS = (int) (calculations.Drawable.getFieldWidth() * 0.04);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="stopMoving()">
    public void stopMoving() {
        isMoving = false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="startMoving()">
    public void startMoving() {
        isMoving = true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setDirection">
    /**
     * This method moves the player's Bat in the direction specified.
     *
     * @param direction The direction in which the bat is moved.
     */
    public void setDirection(double direction) {
        isMoving = true;
        this.direction = direction;
        moveBat();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="moveBat()">
    /**
     * This method moves the player's Bat in the direction specified.
     *
     */
    public void moveBat() {
        if (isMoving) {
            double x = Math.cos(Math.toRadians(direction)) * SPEED;
            double y = Math.sin(Math.toRadians(direction)) * SPEED;
            if (!(player instanceof Bot)) {
                if (position.x + x > (calculations.Drawable.getFieldWidth() / 10) * 7) {
                    isMoving = false;
                } else if (position.x + x < (calculations.Drawable.getFieldWidth() / 10) * 3) {
                    isMoving = false;
                }
            } else {
                if (position.y + y > (calculations.Drawable.getFieldHeight() / 10) * 7) {
                    isMoving = false;
                } else if (position.y + y < (calculations.Drawable.getFieldWidth() / 10) * 3) {
                    isMoving = false;
                }
            }
            if (isMoving) {
                position.x = (int) (position.x + x);
                position.y = (int) (position.y + y);
            }
        }
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlayer">
    public boolean isMoving() {
        return isMoving;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlayer">
    public Player getPlayer() {
        return this.player;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlayer">
    public int getRadius() {
        return this.RADIUS;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setPosition">
    public void setPosition(Point position) {
        this.position = position;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPosition">
    public Point getPosition() {
        return this.position;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getSpeed">
    public double getSpeed() {
        return this.SPEED;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setDirection">
    public void setDirection(int direction) {
        this.direction = direction;
        moveBat();
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDirection">
    public double getDirection() {
        return this.direction;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(graphicsContext)">
    @Override
    public void draw(GraphicsContext graphicsContext) {
        int radius = (int) (Drawable.getScale() * RADIUS);
        int x = Drawable.getX(position.x);
        int y = Drawable.getX(position.y);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
    //</editor-fold>
    //</editor-fold>
}

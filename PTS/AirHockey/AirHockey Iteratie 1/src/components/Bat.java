//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import interfaces.IDrawable;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    public boolean canMove = true;
    private Color color;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Bat(Player player, int Nr) {
        this.RADIUS = (int) (calculations.Drawable.getFieldWidth() * 0.04);
        this.SPEED = (int) (calculations.Drawable.getFieldWidth() * 0.005);
        this.player = player;
        if (Nr == 0) {
            color = Color.RED;
            int x = calculations.Drawable.getFieldWidth() / 2;
            int y = (int) (calculations.Drawable.getFieldHeight() - (RADIUS * 1.5));
            position = new Point(x, y);
        } else if (Nr == 1) {
            color = Color.BLUE;
            int x = calculations.Drawable.getFieldWidth() / 3;
            int y = calculations.Drawable.getFieldHeight() / 2;
            position = new Point(x, y);
        } else {
            color = Color.GREEN;
            int x = calculations.Drawable.getFieldWidth() / 3 * 2;
            int y = calculations.Drawable.getFieldHeight() / 2;
            position = new Point(x, y);
        }
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
        if (isMoving && canMove) {
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
        canMove = true;
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
        graphicsContext.setStroke(color);
        graphicsContext.setFill(color);
        int radius = (int) (RADIUS * calculations.Drawable.getScale());
        int x = calculations.Drawable.getX(position.x) - (radius);
        int y = calculations.Drawable.getY(position.y) - (radius);
        graphicsContext.fillOval(x, y, radius * 2, radius * 2);
        //graphicsContext.fillOval(position.x, position.y, RADIUS, RADIUS);
    }
    //</editor-fold>
    //</editor-fold>
}

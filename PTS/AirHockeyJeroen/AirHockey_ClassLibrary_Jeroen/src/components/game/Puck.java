//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

import interfaces.IDrawable;
import java.awt.Point;
import java.io.Serializable;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Puck. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public final class Puck implements IDrawable, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static int RADIUS;
    private static double SPEED;
    public static double speedX;
    public static double speedY;
    private static Point position;
    private static int direction;
    private static Object lastHitBy;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="getSpeed">
    public double getSpeed() {
        return this.SPEED;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getSpeedX()">
    public int getSpeedX() {
        return (int) speedX;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getSpeedY()">
    public int getSpeedY() {
        return (int) speedY;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getRadius">
    public double getRadius() {
        return Puck.RADIUS;
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

    //<editor-fold defaultstate="collapsed" desc="setDirection">
    public void setDirection(int direction) {
        this.direction = direction;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDirection">
    public int getDirection() {
        return this.direction;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setLastHitBy">
    public void setLastHitBy(Object object) {
        Puck.lastHitBy = object;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getLastHitBy">
    public Object getLastHitBy() {
        return Puck.lastHitBy;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Puck(int speed) {
        RADIUS = (int) (calculations.Drawable.getFieldWidth() * 0.02);
        SPEED = speed * calculations.Drawable.getFieldWidth() * 0.0003;
        reset();
    }
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="move()">
    public void move() {
        this.speedX = (float) (Math.cos(Math.toRadians(direction)) * SPEED);
        this.speedY = (float) (Math.sin(Math.toRadians(direction)) * SPEED);
        position.x += speedX;
        position.y += speedY;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="reset()">
    public void reset() {
        int x = (int) (calculations.Drawable.getFieldWidth() * 0.5);
        int y = (int) (calculations.Drawable.getFieldHeight() * 0.5);
        this.position = new Point(x, y);
        Random random = new Random();

        direction = random.nextInt(360);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(graphicsContext)">
    @Override
    public void draw(GraphicsContext graphicsContext) {
        int radius = (int) (RADIUS * calculations.Drawable.getScale());
        int x = calculations.Drawable.getX(position.x) - (radius);
        int y = calculations.Drawable.getY(position.y) - (radius);
        graphicsContext.setStroke(Paint.valueOf("BLACK"));
        graphicsContext.fillOval(x, y, radius * 2, radius * 2);
        //graphicsContext.setStroke(Color.RED);
        //graphicsContext.fillOval(calculations.Drawable.getX(position.x), calculations.Drawable.getY(position.y), 2, 2);
        graphicsContext.fillOval(position.x - RADIUS, position.y - RADIUS, RADIUS * 2, RADIUS * 2);
    }
    //</editor-fold>
    //</editor-fold>
}

//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import calculations.Drawable;
import interfaces.IDrawable;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;

/**
 * In this class you can find all properties and operations for Puck. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public final class Puck implements IDrawable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static int RADIUS;
    private static double SPEED;
    public static double speedX;
    public static double speedY;
    private static Point position;
    private static int direction = -2;
    private static int testedDirection = 330;
    private static Object lastHitBy;
    private int moveCounter = 0;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Puck(int speed) {
        RADIUS = (int) (calculations.Drawable.getFieldWidth() * 0.02);
        SPEED = speed * calculations.Drawable.getFieldWidth() * 0.0005;
        reset();
    }
	//</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc="move()">
    public void move() {
        if (moveCounter < 100) {
            this.speedX = (float) (Math.cos(Math.toRadians(direction)) * SPEED);
            this.speedY = (float) (Math.sin(Math.toRadians(direction)) * SPEED);
            position.x += speedX;
            position.y += speedY;
            moveCounter++;
        } else {
            reset();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="reset()">
    public void reset() {
        testedDirection++;
        int x = 0;
        int y = 0;
        if (testedDirection >= 9 && testedDirection < 39) {
            testedDirection = 39;
        } else if (testedDirection >= 52 && testedDirection < 82) {
            testedDirection = 82;
        } else if (testedDirection >= 99 && testedDirection < 128) {
            testedDirection = 128;
        } else if (testedDirection >= 142 && testedDirection < 171) {
            testedDirection = 171;
        } else if (testedDirection >= 189 && testedDirection < 219) {
            testedDirection = 219;
        } else if (testedDirection >= 232 && testedDirection < 262) {
            testedDirection = 262;
        } else if (testedDirection >= 279 && testedDirection < 309) {
            testedDirection = 309;
        } else if (testedDirection >= 323 && testedDirection < 351) {
            testedDirection = 351;
        } else if (testedDirection >= 360) {
            testedDirection = 0;
        }
        if (testedDirection >= 0 && testedDirection < 9) {
            x = 0;
            y = 25000;
        } else if (testedDirection >= 39 && testedDirection < 52) {
            x = 1500;
            y = 1500;
        } else if (testedDirection >= 82 && testedDirection < 99) {
            x = 25000;
            y = 0;
        } else if (testedDirection >= 128 && testedDirection < 142) {
            x = 50000 - 1500;
            y = 1500;
        } else if (testedDirection >= 171 && testedDirection < 189) {
            x = 50000;
            y = 25000;
            if (testedDirection == 180){
                int i=0;
            }
        } else if (testedDirection >= 219 && testedDirection < 232) {
            x = 50000 - 1500;
            y = 50000 - 1500;
        } else if (testedDirection >= 262 && testedDirection < 279) {
            x = 25000;
            y = 50000;
        } else if (testedDirection >= 309 && testedDirection < 323) {
            x = 1500;
            y = 50000 - 1500;
        } else if (testedDirection >= 351 && testedDirection < 360) {
            x = 0;
            y = 25000;
        }
        position = new Point(x, y);
        direction = testedDirection;
        lastHitBy = null;
        moveCounter = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(graphicsContext)">
    @Override
    public void draw(GraphicsContext graphicsContext) {
        int radius = (int) (Drawable.getScale() * RADIUS);
        int x = Drawable.getX(position.x);
        int y = Drawable.getX(position.y);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        graphicsContext.fillText("Hoek:" + direction, 10, 10);
        graphicsContext.fillText("Tested:" + testedDirection, 10, 25);
    }
    //</editor-fold>
    //</editor-fold>
}

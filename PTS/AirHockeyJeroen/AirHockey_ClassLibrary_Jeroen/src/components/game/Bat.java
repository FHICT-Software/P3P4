//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

import components.Game;
import interfaces.IDrawable;
import java.awt.Point;
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Bat. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Bat implements IDrawable, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final int RADIUS;
    private Point position;
    private final double SPEED;
    private double direction;
    private final Player player;
    private boolean isMoving = false;
    private String color;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Bat(Player player, int Nr, String color) {
        double edgeLength = Math.sqrt((Math.pow(calculations.Drawable.getFieldHeight(), 2)) + (Math.pow((calculations.Drawable.getFieldWidth() / 2), 2))) * 0.3;
        int yHight = (int) edgeLength /3;
        this.color = color;
        this.RADIUS = (int) (calculations.Drawable.getFieldWidth() * 0.04);
        this.SPEED = (int) (calculations.Drawable.getFieldWidth() * 0.01);
        this.player = player;
        if (Nr == 0) {
            int x = calculations.Drawable.getFieldWidth() / 2;
            int y = (int) (calculations.Drawable.getFieldHeight() - RADIUS) - yHight;
            position = new Point(x, y);
        } else if (Nr == 1) {
               int x = (int) (calculations.Drawable.getFieldWidth() / 4  + RADIUS);
            int y = (int) (calculations.Drawable.getFieldHeight() / 2 - yHight + RADIUS);
            position = new Point(x, y);
        } else {
            int x = (int) (calculations.Drawable.getFieldWidth() / 4 * 3 - RADIUS);
            int y = (int) (calculations.Drawable.getFieldHeight() / 2 - yHight + RADIUS);
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

    public void SetColor(String c){
        this.color = c;
    }
    
    //<editor-fold defaultstate="collapsed" desc="moveBat()">
    /**
     * This method moves the player's Bat in the direction specified.
     *
     */
    public void moveBat() {
         double edgeLength = Math.sqrt((Math.pow(calculations.Drawable.getFieldHeight(), 2)) + (Math.pow((calculations.Drawable.getFieldWidth() / 2), 2))) * 0.3;
        int yHight = (int) edgeLength /3;
        if (isMoving) {
            double x = Math.cos(Math.toRadians(direction)) * SPEED;
            double y = Math.sin(Math.toRadians(direction)) * SPEED;
            if (!(player instanceof Bot)) {
                if (position.x + x > (calculations.Drawable.getFieldWidth() / 3) * 2 - (RADIUS)) {
                    isMoving = false;
                } else if (position.x + x < (calculations.Drawable.getFieldWidth() / 3) * 1 + (RADIUS)) {
                    isMoving = false;
                }
            } else {
                if (position.y + y > (calculations.Drawable.getFieldHeight() / 3) * 2 - yHight) {
                    isMoving = false;
                } else if (position.y + y < (calculations.Drawable.getFieldHeight() / 3) * 1 - yHight) {
                    isMoving = false;
                }
            }
            if (isMoving) {
            
                position.x = (int) Math.round(position.x + x);
                position.y = (int) Math.round(position.y + y);
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
        int radius = (int) (RADIUS * calculations.Drawable.getScale());
        int x = calculations.Drawable.getX(position.x) - (radius);
        int y = calculations.Drawable.getY(position.y) - (radius);
        graphicsContext.setFill(Paint.valueOf(color));
        graphicsContext.fillOval(x, y, radius * 2, radius * 2);
 
        //graphicsContext.fillOval(position.x, position.y, RADIUS, RADIUS);
    }
    //</editor-fold>
    //</editor-fold>
}

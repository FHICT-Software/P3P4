//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

import java.awt.Point;
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
//</editor-fold>

/**
 * Border implements the Line class.
 */
/**
 * In this class you can find all properties and operations for Border.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Border extends calculations.Drawable implements interfaces.IDrawable, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Point startPoint;
    private Point endPoint;
    private String color;
    public final boolean isGoal;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(startPoint, endPoint)">
    /**
     * This method makes a border between the 2 points specified.
     *
     * @param startPoint The starting point of the border
     * @param endPoint   The end point of the border
     * @param color      The color of the border (corresponds with the color of the player)
     */
    public Border(Point startPoint, Point endPoint, String color, boolean isGoal) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
        this.isGoal = isGoal;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getStartPoint">
    public Point getStartPoint() {
        return this.startPoint;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getEndPoint">
    public Point getEndPoint() {
        return this.endPoint;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw(graphicsContext)">
    @Override
    public void draw(GraphicsContext graphicsContext) {
        int x1 = calculations.Drawable.getX(startPoint.x);
        int y1 = calculations.Drawable.getY(startPoint.y);
        int x2 = calculations.Drawable.getX(endPoint.x);
        int y2 = calculations.Drawable.getY(endPoint.y);
        graphicsContext.setStroke(Paint.valueOf(color));
        graphicsContext.strokeLine(x1, y1, x2, y2);
        graphicsContext.strokeLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
    //</editor-fold>
    //</editor-fold>
}

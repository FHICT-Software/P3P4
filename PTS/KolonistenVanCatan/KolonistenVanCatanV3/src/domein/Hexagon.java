package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

/**
 * In this class you can find all properties and operations for Hexagon. 
 * 
 * @organization: Fontys S31D
 * @author Anne Toonen
 * @date 25-03-2014
 */
public class Hexagon implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final Coordinate[] vertices;
    private final double radius;
    private final Coordinate centerPositie;
    private final Coordinate imagePositie;
    private final Coordinate numberPositie;

	//</editor-fold>
	
    //<editor-fold desc="Operations">
    //<editor-fold desc="Constructor">
    public Hexagon(Coordinate centerPosition, double radius) {
        this.radius = radius;
        this.centerPositie = centerPosition;

        vertices = new Coordinate[6];
        setHoekCoördinaten();
        
        double X = (double) (Math.cos(hoek(150)) * radius + (double) centerPositie.getX());
        double Y = (double) ((double) centerPositie.getY() - Math.sin(hoek(90)) * radius);
        imagePositie = new Coordinate(X, Y);

        X = (double) (centerPositie.getX() - 14);
        Y = (double) (centerPositie.getY() - 14);
        numberPositie = new Coordinate(X, Y);
        
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    public Coordinate[] getVertices() {
        return vertices;
    }

    public double getRadius() {
        return radius;
    }

    public Coordinate getCenterPositie() {
        return centerPositie;
    }

    public Coordinate getImagePositie() {
        return imagePositie;
    }

    public Coordinate getNumberPositie() {
        return numberPositie;
    }

    private void setHoekCoördinaten() {
        double Ax = (double) (Math.cos(hoek(30)) * radius + centerPositie.getX());
        double Ay = (double) (Math.sin(hoek(30)) * radius + centerPositie.getY());
        double Bx = (double) (Math.cos(hoek(90)) * radius + centerPositie.getX());
        double By = (double) (Math.sin(hoek(90)) * radius + centerPositie.getY());
        double Cx = (double) (Math.cos(hoek(150)) * radius + centerPositie.getX());
        double Cy = (double) (Math.sin(hoek(150)) * radius + centerPositie.getY());
        double Dx = (double) (Math.cos(hoek(210)) * radius + centerPositie.getX());
        double Dy = (double) (Math.sin(hoek(210)) * radius + centerPositie.getY());
        double Ex = (double) (Math.cos(hoek(270)) * radius + centerPositie.getX());
        double Ey = (double) (Math.sin(hoek(270)) * radius + centerPositie.getY());
        double Fx = (double) (Math.cos(hoek(330)) * radius + centerPositie.getX());
        double Fy = (double) (Math.sin(hoek(330)) * radius + centerPositie.getY());

        vertices[0] = new Coordinate(Ax, Ay);
        vertices[1] = new Coordinate(Bx, By);
        vertices[2] = new Coordinate(Cx, Cy);
        vertices[3] = new Coordinate(Dx, Dy);
        vertices[4] = new Coordinate(Ex, Ey);
        vertices[5] = new Coordinate(Fx, Fy);

    }

    public List<Rectangle> setVestingRectangels() {
        List<Rectangle> rectangles = new ArrayList<>();

        for (Coordinate p : vertices) {
            //driesprongen
            double p1x = (double) (p.getX() - radius);
            double p1y = (double) (p.getY() - radius);
            double p2x = (double) (p.getX() - radius);
            double p2y = (double) (p.getY() - radius);

            double height = p2y - p1y;
            double width = p2x - p1x;

            rectangles.add(new Rectangle(p1x,p1y,width,height));
        }
        return rectangles;
    }
    
    public List<Rectangle> setStrateRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();
        
        for (int i = 0; i < vertices.length; i++) {
            int j = i + 1;
            if (i >= 5) {
                j = 0;
            }

            double p1x = vertices[i].getX();
            double p1y = vertices[i].getY();
            double p2x = vertices[j].getX();
            double p2y = vertices[j].getY();
            
            if (p1x != p2x) {
                //zijden schuin
                double height = p2y - p1y;
                double width = p2x - p1x;
                
                rectangles.add(new Rectangle(p1x,p1y,width,height));
                
            } else {
                //zijden recht
                double p3x = p1x - radius / 2.0;
                double p3y = p1y;
                double p4x = p2x - radius / 2.0;
                double p4y = p2y;
                
                double height = p4y - p3y;
                double width = p4x - p3x;
                
                rectangles.add(new Rectangle(p1x,p1y,width,height));
            }
        }

        return rectangles;
    }
    //</editor-fold>
    
    private double hoek(double graden) {
        return (Math.PI * graden / 180.0);

    }
    
    //</editor-fold>
}

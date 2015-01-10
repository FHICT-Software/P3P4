//<editor-fold defaultstate="collapsed" desc="Jibberish">
package calculations;

//</editor-fold>
/**
 * In this class you can find all properties and operations for Drawable. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/03/30
 */
public abstract class Drawable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private static int width;
    private static int height;
    private static final int WIDTH = 50000;
    private static final int HEIGHT = (int) Math.sqrt((WIDTH * WIDTH) - ((WIDTH / 2) * (WIDTH / 2)));
    private static double scale;
    private static int xTranslation;
    private static int yTranslation;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="getScale()">
    public static double getScale() {
        return scale;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getWidth()">
    public static int getWidth() {
        return width;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getHeight()">
    public static int getHeight() {
        return height;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getFieldWidth()">
    public static int getFieldWidth() {
        return WIDTH;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getFieldHeight()">
    public static int getFieldHeight() {
        return HEIGHT;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getX(x)">
    public static int getX(int x) {
        return (int) ((x * scale) + xTranslation);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getVerticalTranslateion(y)">
    public static int getY(int y) {
        return (int) ((y * scale) + yTranslation);
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="updateCalculations()">
    public static void updateCalculations(int screenWidth, int screenHeight) {
        //<editor-fold defaultstate="collapsed" desc="Drawable Rectangle">
        //Max Width
        if (screenHeight >= (Math.sqrt((screenWidth * screenWidth) - ((screenWidth / 2) * (screenWidth / 2))))) {
            width = screenWidth;
            height = (int) (Math.sqrt((screenWidth * screenWidth) - ((screenWidth / 2) * (screenWidth / 2))));
            xTranslation = 0;
            yTranslation = (screenHeight - height) / 2;
        } //Max Height
        else {
            width = (int) ((Math.tan(Math.toRadians(30)) * screenHeight) * 2);
            height = screenHeight;
            xTranslation = (screenWidth - width) / 2;
            yTranslation = 0;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Controle & Scale">
        if (width / WIDTH != height / HEIGHT) {
            throw new UnknownError("Something went wrong converting the virtual fieldwith to fit the screen.");
        } else {
            scale = ((double) width / (double) WIDTH);
        }
        //</editor-fold>
    }
    //</editor-fold>
    //</editor-fold>
}

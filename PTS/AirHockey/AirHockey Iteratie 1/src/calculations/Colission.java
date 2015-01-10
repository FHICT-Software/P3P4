//<editor-fold defaultstate="collapsed" desc="Jibberish">
package calculations;

import components.Bat;
import components.Border;
import components.Puck;
import java.util.List;
//</editor-fold>

/**
 * In this class you can find all properties and operations for Colission. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 14/04/02
 */
public class Colission {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold desc="Tweeks">
    private static final double PERCENTAGE = 0.5;
    private static final double MARGIN = (Drawable.getFieldWidth() / 100 * PERCENTAGE);
    //</editor-fold>
    private static List<Bat> bats;
    private static List<Border> borders;
    private static Puck puck;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor(puck, bat, border)">
    /**
     * This method detects if game objects are colliding with each other and changes the puck direction accordingly.
     *
     * @param puck    The puck in the game
     * @param bats    The player/AI controlled bats
     * @param borders The different borders in the playing field.
     */
    public Colission(Puck puck, List<Bat> bats, List<Border> borders) {
        Colission.bats = bats;
        Colission.borders = borders;
        Colission.puck = puck;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="intersects()">
    /**
     * This method is called by the Game component, and it checks all components, to see if the puck intersects with something
     * (either a border or a bat). It remembers what the last component is that it hit, to prevent errors.
     *
     * @return
     */
    public static synchronized boolean intersects() {
        intersectsWithBats();
        for (Border border : borders) {
            if (intersectsWithBorder(border)) {
                return true;
            }
        }
        return false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="With Bats">
    private static void intersectsWithBats() {
        for (Bat bat : bats) {
            intersectsWithBat(bat);
        }
    }

    private static void intersectsWithBat(Bat bat) {
        //<editor-fold defaultstate="collapsed" desc="Declarations">
        double hoek = 0;
        double oldHoek = 0;
        int puckX = puck.getPosition().x;
        int puckY = puck.getPosition().y;
        double puckSize = puck.getRadius();
        int batX = bat.getPosition().x;
        int batY = bat.getPosition().y;
        double batSize = bat.getRadius();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Q1">
        if (batX >= puckX && batY >= puckY) {
            if (Math.sqrt(Math.pow(batX - puckX, 2) + Math.pow(batY - puckY, 2)) < batSize + puckSize + MARGIN) {
                hoek = 270 - Math.toDegrees(Math.atan2(batX - puckX, batY - puckY));
                puckX = (int) (batX - (Math.cos(Math.toRadians(hoek - 180)) * (batSize + puckSize)));
                puckY = (int) (batY - (Math.sin(Math.toRadians(hoek - 180)) * (batSize + puckSize)));
                //puck.setPosition(new Point(puckX, puckY));
            }
        }//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Q2">
        else if (batX >= puckX && puckY >= batY) {
            if (Math.sqrt(Math.pow(batX - puckX, 2) + Math.pow(puckY - batY, 2)) < batSize + puckSize + MARGIN) {
                hoek = 90 + Math.toDegrees(Math.atan2(batX - puckX, puckY - batY));
                puckX = (int) (batX - (Math.sin(Math.toRadians(hoek - 90)) * (batSize + puckSize)));
                puckY = (int) ((Math.cos(Math.toRadians(hoek - 90)) * (batSize + puckSize)) + batY);
                //puck.setPosition(new Point(puckX, puckY));
            }
        }//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Q3">
        else if (puckX >= batX && puckY >= batY) {
            if (Math.sqrt(Math.pow(puckX - batX, 2) + Math.pow(puckY - batY, 2)) < batSize + puckSize + MARGIN) {
                hoek = 90 - Math.toDegrees(Math.atan2(puckX - batX, puckY - batY));
                puckX = (int) ((Math.cos(Math.toRadians(hoek)) * (batSize + puckSize)) + batX);
                puckY = (int) ((Math.sin(Math.toRadians(hoek)) * (batSize + puckSize)) + batY);
                //puck.setPosition(new Point(puckX, puckY));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Q4">
        else if (puckX >= batX && batY >= puckY) {
            if (Math.sqrt(Math.pow(puckX - batX, 2) + Math.pow(batY - puckY, 2)) < batSize + puckSize + MARGIN) {
                hoek = 270 + Math.toDegrees(Math.atan2(puckX - batX, batY - puckY));
                puckX = (int) ((Math.sin(Math.toRadians(hoek - 270)) * (batSize + puckSize)) + batX);
                puckY = (int) (batY - (Math.cos(Math.toRadians(hoek - 270)) * (batSize + puckSize)));
                //puck.setPosition(new Point(puckX, puckY));
            }
        }
            //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Returning">
        if (oldHoek != hoek) {
            double newDirection = hoek - (puck.getDirection() + 180) + hoek;
            while (newDirection > 360) {
                newDirection -= 360;
            }
            while (newDirection < 0) {
                newDirection += 360;
            }
            if (puck.getLastHitObject() != bat) {
                puck.setDirection((int) newDirection);
                puck.setLastHitBy(bat);
                bat.canMove = false;
                puck.move();
            }
            oldHoek = hoek;
        }
        //</editor-fold>
    }
        //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Puck met Lijn">
    public static boolean intersectsWithBorder(Border border) {
        boolean isIntersected = false;
        if (puck.getPosition().x + puck.getRadius() < border.getStartPoint().x && puck.getPosition().x + puck.getRadius() < border.getEndPoint().getX()) {
        } else if (puck.getPosition().x - puck.getRadius() > border.getStartPoint().x && puck.getPosition().x - puck.getRadius() > border.getEndPoint().getX()) {
        } else if (puck.getPosition().y + puck.getRadius() < border.getStartPoint().y && puck.getPosition().y + puck.getRadius() < border.getEndPoint().getY()) {
        } else if (puck.getPosition().y - puck.getRadius() > border.getStartPoint().y && puck.getPosition().y - puck.getRadius() > border.getEndPoint().getY()) {
        } else {
            String locationToLine = locationToLine(puck, border);
            if (locationToLine.equals("OpOp")) {
                puck.reset();
            } else if (locationToLine.contains("Op")) {
                isIntersected = true;
                float hoek = (float) Math.toDegrees(Math.atan2(border.getEndPoint().getY() - border.getStartPoint().y, border.getEndPoint().getX() - border.getStartPoint().x));
                double nieweRichting = hoek - puck.getDirection() + hoek;
                while (nieweRichting > 360) {
                    nieweRichting -= 360;
                }
                while (nieweRichting < 0) {
                    nieweRichting += 360;
                }
                puck.setDirection((int) nieweRichting);
                puck.setLastHitBorder(border);
            }
        }
        if (isIntersected) {
            //Substract points
            if (puck.getLastHitBorder() != null) {
                switch (puck.getLastHitBorder().color) {
                    case "Red":
                        bats.get(0).getPlayer().scoreMin();
                        break;
                    case "Green":
                        bats.get(1).getPlayer().scoreMin();
                        break;
                    case "Blue":
                        bats.get(2).getPlayer().scoreMin();
                        break;
                }
            }
            //Add points
            if (border.isGoal) {
                if (puck.getLastHitBy() != null) {
                    puck.getLastHitBy().getPlayer().scorePlus();
                }
                puck.reset();
            }
        }
        return isIntersected && border.isGoal;
    }

    private static String locationToLine(Puck puck, Border border) {
        StringBuilder sb = new StringBuilder();
        //<editor-fold defaultstate="collapsed" desc="Check X">
        double lijnYtoX = (border.getEndPoint().getX() - border.getStartPoint().getX()) / (border.getEndPoint().getY() - border.getStartPoint().getY());
        if (puck.getPosition().x + puck.speedX - puck.getRadius() > border.getStartPoint().getX() + ((puck.getPosition().y - border.getStartPoint().getY()) * lijnYtoX)) {
            sb.append("Rechts");
        } else if (puck.getPosition().x + puck.speedX + puck.getRadius() < border.getStartPoint().getX() + ((puck.getPosition().y - border.getStartPoint().getY()) * lijnYtoX)) {
            sb.append("Links");
        } else if (puck.getPosition().x - puck.getRadius() > border.getStartPoint().getX() + ((puck.getPosition().y - border.getStartPoint().getY()) * lijnYtoX)) {
            sb.append("Rechts");
        } else if (puck.getPosition().x + puck.getRadius() < border.getStartPoint().getX() + ((puck.getPosition().y - border.getStartPoint().getY()) * lijnYtoX)) {
            sb.append("Links");
        } else {
            sb.append("Op");
        }
	//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Check Y">
        double lijnXtoY = (border.getEndPoint().getY() - border.getStartPoint().getY()) / (border.getEndPoint().getX() - border.getStartPoint().getX());
        if (puck.getPosition().y + puck.speedY - puck.getRadius() > border.getStartPoint().getY() + ((puck.getPosition().x - border.getStartPoint().getX()) * lijnXtoY)) {
            sb.append("Onder");
        } else if (puck.getPosition().y + puck.speedY + puck.getRadius() < border.getStartPoint().getY() + ((puck.getPosition().x - border.getStartPoint().getX()) * lijnXtoY)) {
            sb.append("Boven");
        } else if (puck.getPosition().y - puck.getRadius() > border.getStartPoint().getY() + ((puck.getPosition().x - border.getStartPoint().getX()) * lijnXtoY)) {
            sb.append("Onder");
        } else if (puck.getPosition().y + puck.getRadius() < border.getStartPoint().getY() + ((puck.getPosition().x - border.getStartPoint().getX()) * lijnXtoY)) {
            sb.append("Boven");
        } else {
            sb.append("Op");
        }
        //</editor-fold>
        return sb.toString();
    }
    //</editor-fold>
}

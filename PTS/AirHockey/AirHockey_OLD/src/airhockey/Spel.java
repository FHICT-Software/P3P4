//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey;

import calculations.Intersect;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 * In deze class wordt de spelfunctionaliteit aangeroepen. Hierin komt de
 * gameflow, alle componenten (relatief aan de game), etc.
 *
 * @author jeroen
 */
public class Spel implements Runnable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Virtuele Veld">
    double veldWidth = 1000;
    double veldHeight = Math.sqrt((veldWidth * veldWidth) - ((veldWidth / 2) * (veldWidth / 2)));
    //</editor-fold>
    public Puck puck;
    private ArrayList<Speler> spelers = new ArrayList<>();
    private ArrayList<Lijn> randen = new ArrayList<>();
    private ArrayList<Lijn> goals = new ArrayList<>();
    private String spelType;
    private Integer rondes = 1;
    private Speler lastIntersected;
    private Speler priorToLastIntersected;
    private boolean visible = false;

    public Puck getPuck() {
        return this.puck;
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public ArrayList<Lijn> getLijnen() {
        return randen;
    }

    public ArrayList<Lijn> getGoals() {
        return goals;
    }

    public Speler getSpeler(int spelerNr) {
        return spelers.get(spelerNr - 1);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Hierin worden alle componenten die nodig zijn voor het spel aangemaakt.
     *
     * @param spelType     is nodig om na te gaan welke componenten gemaakt
     *                     moeten worden.
     * @param spelEigenaar is de persoon die het spel heeft aangemaakt.
     */
    public Spel(String spelType, Persoon spelEigenaar) {
        maakLijnen();
        if (spelType.contains("Single")) {
            puck = new Puck();
            spelers.add(new Speler(spelEigenaar, SpelerKleur.rood));
            spelers.add(new Bot(SpelerKleur.groen));
            spelers.add(new Bot(SpelerKleur.blauw));
            start();
        }
        this.spelType = spelType;
    }
    //</editor-fold>

    //<editor-fold desc="Methodes">
    //<editor-fold defaultstate="collapsed" desc="Maak Lijnen">
    /**
     * In deze methode worden alle locaties en lijnen berekend die nodig zijn
     * voor het spelbord.
     */
    private void maakLijnen() {
        double edgeLength = Math.sqrt((Math.pow(veldHeight, 2)) + (Math.pow((veldWidth / 2), 2))) * 0.3;
        int Ax = 0;
        int Ay = (int) veldHeight;
        int Bx = (int) (0 + (Math.cos(Math.toRadians(60)) * edgeLength));
        int By = (int) (veldHeight - (Math.sin(Math.toRadians(60)) * edgeLength));
        int Cx = (int) (veldWidth / 2 - (Math.cos(Math.toRadians(60)) * edgeLength));
        int Cy = (int) (0 + (Math.sin(Math.toRadians(60)) * edgeLength));
        int Dx = 500;
        int Dy = 0;
        int Ex = (int) (veldWidth / 2 + (Math.cos(Math.toRadians(60)) * edgeLength));
        int Ey = (int) (0 + (Math.sin(Math.toRadians(60)) * edgeLength));
        int Fx = (int) (veldWidth - (Math.cos(Math.toRadians(60)) * edgeLength));
        int Fy = (int) (veldHeight - (Math.sin(Math.toRadians(60)) * edgeLength));
        int Gx = 1000;
        int Gy = (int) veldHeight;
        int Hx = (int) (veldWidth / 2 + veldWidth / 5);
        int Hy = (int) veldHeight;
        int Ix = (int) (veldWidth / 2 - veldWidth / 5);
        int Iy = (int) veldHeight;

        randen.add(new Lijn(Ax, Ay, Bx, By, Color.black));
        randen.add(new Lijn(Cx, Cy, Dx, Dy, Color.black));
        randen.add(new Lijn(Dx, Dy, Ex, Ey, Color.black));
        randen.add(new Lijn(Fx, Fy, Gx, Gy, Color.black));
        randen.add(new Lijn(Gx, Gy, Hx, Hy, Color.black));
        randen.add(new Lijn(Ix, Iy, Ax, Ay, Color.black));
        goals.add(new Lijn(Hx, Hy, Ix, Iy, Color.RED));
        goals.add(new Lijn(Ex, Ey, Fx, Fy, Color.GREEN));
        goals.add(new Lijn(Bx, By, Cx, Cy, Color.BLUE));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Start">
    /**
     * In deze functie wordt het spel gestart. Alle aangemaakte componenten
     * worden gestart.
     */
    private void start() {
        double puckSpeed = 0;
        for (Speler s : spelers) {
            try {
                Bot b = (Bot) s;
            } catch (Exception e) {
                puckSpeed += s.persoon.getRating();
            }
        }
        puckSpeed /= 5;
        if (puckSpeed != 0) {
            puck.speed = (float) puckSpeed;
        }
        puck.restart();
        setVisible(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Stop">
    private void stop() {
        berekenRatingscore();
        setVisible(false);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Restart">
    public void restart() {
        for (Speler speler : spelers) {
            speler.resetScore();
        }
        rondes = 1;
        puck.restart();
        setVisible(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get Ronde">
    public int getRonde() {
        return rondes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Bereken Ratingscore">
    public void berekenRatingscore() {
        try {
            if (spelType.equals("Single_Fun")) {
                double ratingSpeler1 = getSpeler(1).persoon.getRating();
                double ratingScSpeler1 = getSpeler(1).getScore() - ((30 - 2 * ratingSpeler1) / 8);
                getSpeler(1).persoon.addNewRatingScores(ratingScSpeler1);
            } else {
                double ratingSpeler1 = getSpeler(1).persoon.getRating();
                double ratingSpeler2 = getSpeler(2).persoon.getRating();
                double ratingSpeler3 = getSpeler(3).persoon.getRating();
                double ratingScSpeler1 = getSpeler(1).getScore() - ((ratingSpeler2 + ratingSpeler3 - 2 * ratingSpeler1) / 8);
                double ratingScSpeler2 = getSpeler(2).getScore() - ((ratingSpeler1 + ratingSpeler3 - 2 * ratingSpeler2) / 8);
                double ratingScSpeler3 = getSpeler(3).getScore() - ((ratingSpeler1 + ratingSpeler2 - 2 * ratingSpeler3) / 8);
                getSpeler(1).persoon.addNewRatingScores(ratingScSpeler1);
                getSpeler(1).persoon.addNewRatingScores(ratingScSpeler2);
                getSpeler(1).persoon.addNewRatingScores(ratingScSpeler3);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Run">
    /**
     * In deze run() wordt steeds de locatie van de puck geupdate, en wordt er
     * gekeken naar de reacties van de bots, de collision met lijnen, en of er
     * goals gemaakt zijn.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
                //<editor-fold defaultstate="collapsed" desc="SpelEinde">
                if (rondes > 10) {
                    stop();
                }
                //</editor-fold>
                puck.update();
                //<editor-fold defaultstate="collapsed" desc="Goals">
                for (int i = 0; i < goals.size(); i++) {
                    if (Intersect.Intersects(puck, goals.get(i))) {
                        if (lastIntersected == null) {
                            spelers.get(i).setScore(-1);
                        } else if (lastIntersected != spelers.get(i)) {
                            lastIntersected.setScore(+1);
                            spelers.get(i).setScore(-1);
                        } else if (lastIntersected == spelers.get(i) && priorToLastIntersected != null) {
                            priorToLastIntersected.setScore(+1);
                            spelers.get(i).setScore(-1);
                        } else if (lastIntersected == spelers.get(i) && priorToLastIntersected == null) {
                            spelers.get(i).setScore(-1);
                        }
                        rondes++;
                        puck.restart();
                        lastIntersected = null;
                        priorToLastIntersected = null;
                        puck.setLastHitBy(0);
                    }
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Randen">
                for (Lijn l : randen) {
                    if (Intersect.Intersects(puck, l)) {
                        puck.update();
                    }
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Batjes">
                for (int i = 0; i < spelers.size(); i++) {
                    if (i > 0) {
                        Bot b = (Bot) spelers.get(i);
                        b.doReactie(puck);
                    }
                    if (Intersect.Intersects(puck, spelers.get(i).batje)) {
                        if (spelers.get(i) != lastIntersected) {
                            priorToLastIntersected = lastIntersected;
                        }
                        lastIntersected = spelers.get(i);
                        puck.setLastHitBy(i + 1);
                        puck.update();
                    }
                }
                //</editor-fold>
            } catch (InterruptedException ex) {
                Logger.getLogger(Spel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    //</editor-fold>
}

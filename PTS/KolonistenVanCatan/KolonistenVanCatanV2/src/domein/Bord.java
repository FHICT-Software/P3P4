package domein;

import domein.tegels.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;

/**
 * In this class you can find all properties and operations for Spel. //CHECK
 *
 * @organization: Fontys S31D
 * @author Anne
 * @date 31-03-2014
 */
public final class Bord {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //nodig bij spel constructor
    //GUI
    private ArrayList<Point2D> driesprongen;
    private ArrayList<Point2D[]> paden = new ArrayList<>();
    private ArrayList<Hexagon> hexagons;
    private final ArrayList<Tegel> tegels;

    private ArrayList<Integer> getallen;
    private final boolean optieRand;

    private final double hexRad;
    private final Point2D centerBord;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(naam)">
    public Bord() {
        this.optieRand = true;
        this.centerBord = new Point2D(300, 300);
        this.hexRad = 48;
        this.tegels = new ArrayList<>();

        setHexagonGrid();
        setMouseGrid();
        setTutorialbord();
    }

    //</editor-fold>
    private void setHexagonGrid() {

        double xOff = (double) Math.cos(Math.PI * 30.0 / 180.0) * hexRad; // half of the hex width
        double yOff = (double) Math.sin(Math.PI * 30.0 / 180.0) * hexRad; // third of the hex height

        Hexagon voorgaandeHexagon;
        hexagons = new ArrayList<>();
        boolean exists;

        for (int ring = 1; ring <= 3; ring++) {
            voorgaandeHexagon = new Hexagon(centerBord, hexRad);
            exists = false;
            for (Hexagon h : hexagons) {
                if (h.getCenterPositie() == voorgaandeHexagon.getCenterPositie()) {
                    exists = true;
                }
            }
            if (!exists) {
                //00
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() - xOff;
                double y = voorgaandeHexagon.getCenterPositie().getY() + yOff * 3;
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                exists = false;
                for (Hexagon h : hexagons) {
                    if (h.getCenterPositie() == voorgaandeHexagon.getCenterPositie()) {
                        exists = true;
                    }
                }
                if (!exists) {
                    //01 (<v)
                    hexagons.add(voorgaandeHexagon);
                }
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() + xOff * 2;
                double y = voorgaandeHexagon.getCenterPositie().getY();
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //02 (>>)
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() + xOff;
                double y = voorgaandeHexagon.getCenterPositie().getY() - yOff * 3;
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //03 (^>)
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() - xOff;
                double y = voorgaandeHexagon.getCenterPositie().getY() - yOff * 3;
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //04 (<^)
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() - xOff * 2;
                double y = voorgaandeHexagon.getCenterPositie().getY();
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //05 (<<)
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() - xOff;
                double y = voorgaandeHexagon.getCenterPositie().getY() + yOff * 3;
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //06 (<v)
                hexagons.add(voorgaandeHexagon);
            }
            for (int i = 0; i < ring - 1; i++) {
                double x = voorgaandeHexagon.getCenterPositie().getX() + xOff;
                double y = voorgaandeHexagon.getCenterPositie().getY() + yOff * 3;
                voorgaandeHexagon = new Hexagon(new Point2D(x, y), hexRad);
                //07 (v>)
                hexagons.add(voorgaandeHexagon);
            }
        }
    }

    private void setMouseGrid() {
//            driesprongen = new ArrayList<>();
//            for (Hexagon h : hexagons)
//            {
//                List<Rectangle> hexRec = h.berekenMouseRectangels(); 
//                
//                for (Rectangle r : hexRec)
//                {
//                    if (rectangles.Exists(f => f.Location == r.Location))
//                    {
//                        rectangles.add(r);
//                    }
//                }
//            }
    }

    public void setTegels() {
//
//        int aantalLandtegels = tegels.Where(t =  > t.GetType() == typeof(Landtegel)).Count();
//        int aantalWaterTegels = tegels.Where(t =  > t.GetType() == typeof(HavenTegel)).Count() + tegels.Where(t =  > t.GetType() == typeof(ZeeTegel)).Count();
//        List<int> gekozenHexagons = new List<int>();
//        List<int> gekozenLandNummers = new List<int>();
//        int rndm_Landnummer = r.Next(2, getallen.Count);
//
//        for (int i = 0; i < tegels.Count; i++) {
//            woestijn
//                if (tegels.get(i).GetType() == typeof(WoestijnTegel)) {
//                tegels.get(i).SetPlaats(hexagons[0]);
//            }
//            Land
//                else if (tegels.get(i).GetType() == typeof(Landtegel))
//                {
//                    while (gekozenLandNummers.Contains(rndm_Landnummer))
//                    {
//                        rndm_Landnummer = r.Next(0, getallen.Count);
//                    }
//
//                    int rndm_Hexagon = r.Next(1, aantalLandtegels + 1);
//                    while (gekozenHexagons.Contains(rndm_Hexagon))
//                    {
//                        rndm_Hexagon = r.Next(1, aantalLandtegels + 1);
//                    }
//                    tegels.get(i).SetPlaats(hexagons[rndm_Hexagon]);
//                    Landtegel lt = (Landtegel)tegels.get(i);
//                    lt.SetLandnummer(getallen[rndm_Landnummer]);
//                    gekozenLandNummers.add(rndm_Landnummer);
//                    tegels.get(i) = lt;
//                    gekozenHexagons.add(rndm_Hexagon);
//                }
//
//            Water Optie Tegels
//                if (optieTegels == true) {
//
//                if (tegels.get(i).GetType() == typeof(HavenTegel)) {
//                    tegels.get(i).SetPlaats(hexagons[r.Next(aantalLandtegels + 1, aantalLandtegels + aantalWaterTegels + 1)]);
//                } else if (tegels.get(i).GetType() == typeof(ZeeTegel)) {
//                    int random = r.Next(aantalLandtegels + 1, aantalLandtegels + aantalWaterTegels + 1);
//                    while (gekozenHexagons.Contains(random)) {
//                        random = r.Next(aantalLandtegels + 1, aantalLandtegels + aantalWaterTegels + 1);
//                    }
//                    tegels.get(i).SetPlaats(hexagons[random]);
//                    gekozenHexagons.add(random);
//                }
//            }
//
//            Water Optie Rand
//                else
//                {
//                        Haven
//                    if (tegels.get(i).GetType() == typeof(HavenTegel)) {
//                            HavenTegel h = (HavenTegel) tegels.get(i);
//                            if (h.ratio == 2) {
//                                switch (h.grondstof) {
//                                    case Grondstof.Hout:
//                                        h.SetPlaats(hexagons[0]);
//                                        break;
//                                    case Grondstof.Wol:
//                                        h.SetPlaats(hexagons[0]);
//                                        break;
//                                    case Grondstof.Graan:
//                                        h.SetPlaats(hexagons[0]);
//                                        break;
//                                    case Grondstof.Klei:
//                                        h.SetPlaats(hexagons[0]);
//                                        break;
//                                    case Grondstof.Erts:
//                                        h.SetPlaats(hexagons[0]);
//                                        break;
//                                }
//                            }
//                        }
//                        Zee
//                    else if (tegels.get(i).GetType() == typeof(ZeeTegel))
//                    {
//                        int random = r.Next(aantalLandtegels + 1, aantalLandtegels + aantalWaterTegels + 1 );
//                        while (gekozenHexagons.Contains(random))
//                        {
//                            random = r.Next(aantalLandtegels + 1, aantalLandtegels + aantalWaterTegels + 1);
//                        }
//                        tegels.get(i).SetPlaats(hexagons[random]);
//                        gekozenHexagons.add(random);
//                    }
//                    
//
//                }
//
//        }
    }

    public void setTutorialbord() {
        Hexagon h = new Hexagon(new Point2D(327.5, 334), hexRad);
        //tegels.add(new Woestijntegel(hexagons.get(0)));
        tegels.add(new Woestijntegel(h));
        tegels.add(new Landtegel(3, Grondstof.HOUT, hexagons.get(1)));
        tegels.add(new Landtegel(6, Grondstof.ERTS, hexagons.get(2)));
        tegels.add(new Landtegel(5, Grondstof.GRAAN, hexagons.get(3)));
        tegels.add(new Landtegel(4, Grondstof.ERTS, hexagons.get(4)));
        tegels.add(new Landtegel(9, Grondstof.BAKSTEEN, hexagons.get(5)));
        tegels.add(new Landtegel(11, Grondstof.ERTS, hexagons.get(6)));
        tegels.add(new Landtegel(11, Grondstof.BAKSTEEN, hexagons.get(7)));
        tegels.add(new Landtegel(12, Grondstof.WOL, hexagons.get(8)));
        tegels.add(new Landtegel(9, Grondstof.GRAAN, hexagons.get(9)));
        tegels.add(new Landtegel(10, Grondstof.HOUT, hexagons.get(10)));
        tegels.add(new Landtegel(8, Grondstof.WOL, hexagons.get(11)));
        tegels.add(new Landtegel(3, Grondstof.WOL, hexagons.get(12)));
        tegels.add(new Landtegel(6, Grondstof.HOUT, hexagons.get(13)));
        tegels.add(new Landtegel(2, Grondstof.GRAAN, hexagons.get(14)));
        tegels.add(new Landtegel(5, Grondstof.HOUT, hexagons.get(15)));
        tegels.add(new Landtegel(10, Grondstof.GRAAN, hexagons.get(16)));
        tegels.add(new Landtegel(8, Grondstof.BAKSTEEN, hexagons.get(17)));
        tegels.add(new Landtegel(4, Grondstof.WOL, hexagons.get(18)));
        tegels.add(new Zeetegel(hexagons.get(19)));
        tegels.add(new Haventegel(1, Grondstof.BAKSTEEN, 2, hexagons.get(20)));
        tegels.add(new Zeetegel(hexagons.get(21)));
        tegels.add(new Haventegel(6, null, 3, hexagons.get(22)));
        tegels.add(new Zeetegel(hexagons.get(23)));
        tegels.add(new Haventegel(5, null, 3, hexagons.get(24)));
        tegels.add(new Zeetegel(hexagons.get(25)));
        tegels.add(new Haventegel(5, Grondstof.WOL, 2, hexagons.get(26)));
        tegels.add(new Zeetegel(hexagons.get(27)));
        tegels.add(new Haventegel(4, null, 3, hexagons.get(28)));
        tegels.add(new Zeetegel(hexagons.get(29)));
        tegels.add(new Haventegel(3, Grondstof.ERTS, 2, hexagons.get(30)));
        tegels.add(new Zeetegel(hexagons.get(31)));
        tegels.add(new Haventegel(3, Grondstof.GRAAN, 2, hexagons.get(32)));
        tegels.add(new Zeetegel(hexagons.get(33)));
        tegels.add(new Haventegel(2, null, 3, hexagons.get(34)));
        tegels.add(new Zeetegel(hexagons.get(35)));
        tegels.add(new Haventegel(1, Grondstof.HOUT, 2, hexagons.get(36)));
        tegels.add(new Zeetegel(hexagons.get(37)));
        for (Tegel t : tegels)
        {
            Point2D hoek1 = t.getPlaats().getVertices()[0];
            Point2D hoek2 = t.getPlaats().getVertices()[1];
            Point2D hoek3 = t.getPlaats().getVertices()[2];
            Point2D hoek4 = t.getPlaats().getVertices()[3];
            Point2D hoek5 = t.getPlaats().getVertices()[4];
            Point2D hoek6 = t.getPlaats().getVertices()[5];

            ArrayList<Point2D[]> hexagonPaden = new ArrayList<>();
            Point2D[] pad56 = new Point2D[2];
            //Point2D[] pad65 = new Point2D[2]; OMGEKEERDE MOGELIJKHEDEN!
            pad56[0] = hoek5;
            pad56[1] = hoek6;
            if (!paden.contains(pad56))
                paden.add(pad56);
                        
            Point2D[] pad61 = new Point2D[2];
            pad56[0] = hoek6;
            pad56[1] = hoek1;
            if (!paden.contains(pad61))
                paden.add(pad61);
                        
            Point2D[] pad12 = new Point2D[2];
            pad56[0] = hoek1;
            pad56[1] = hoek2;
            if (!paden.contains(pad12))
                paden.add(pad12);
                        
            Point2D[] pad23 = new Point2D[2];
            pad56[0] = hoek2;
            pad56[1] = hoek3;
            if (!paden.contains(pad23))
                paden.add(pad23);
                        
            Point2D[] pad34 = new Point2D[2];
            pad56[0] = hoek3;
            pad56[1] = hoek4;
            if (!paden.contains(pad34))
                paden.add(pad34);
            
            Point2D[] pad45 = new Point2D[2];
            pad56[0] = hoek4;
            pad56[1] = hoek5;
            if (!paden.contains(pad45))
                paden.add(pad45);
        }
            int index = 0;
            
            for (int i = 0; i<paden.size(); i++)
            {
                try {
                System.out.println("g.drawLine("+(int)paden.get(i)[0].getX()+", "+(int)paden.get(i)[0].getY()+", "+(int)paden.get(i)[1].getX()+", "+(int)paden.get(i)[1].getY()+");\n");
                }
                catch (NullPointerException e)
                {
                    
                }
            }
            
//            for (Point2D[] pad : paden)
//            {
//                index++;
//                try {
//                System.out.println("g.drawLine("+pad[0].getX()+", "+pad[0].getY()+", "+paden.get(index)[0].getX()+", "+paden.get(index)[0].getY()+");\n");
//                }
//                catch (NullPointerException e)
//                {
//                    System.out.println("einde bereikt");
//                }
//            }
    }

    public Collection<Tegel> getAlleTegels() {
        return Collections.unmodifiableCollection(tegels);
    }

    public ArrayList<Tegel> getTegels(String classe) {
        ArrayList<Tegel> returner = new ArrayList<>();
        switch (classe) {
            case "Landtegel":
                for (Tegel t : tegels) {
                    if (t instanceof Landtegel) {
                        returner.add(t);
                    }
                }
                break;
            case "Haventegel":
                for (Tegel t : tegels) {
                    if (t instanceof Landtegel) {
                        returner.add(t);
                    }
                }
                break;
            case "Woestijntegel":
                for (Tegel t : tegels) {
                    if (t instanceof Landtegel) {
                        returner.add(t);
                    }
                }
                break;
            case "Zeetegel":
                for (Tegel t : tegels) {
                    if (t instanceof Landtegel) {
                        returner.add(t);
                    }
                }
                break;
        }

        return returner;
    }

    public boolean isOptieRand() {
        return optieRand;
    }

    public ArrayList<Point2D> getDriesprongen() {
        return driesprongen;
    }

    public ArrayList<Point2D[]> getPaden() {
        return paden;
    }

    //</editor-fold>
    private int count() {
        int returner = 0;

        for (Tegel t : tegels) {
            if (t instanceof Landtegel) {
                returner++;
            }
        }

        return returner;
    }
}

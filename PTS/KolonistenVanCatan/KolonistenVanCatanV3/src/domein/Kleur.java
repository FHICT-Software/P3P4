   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author E.Muit-Laptop
 */
public class Kleur implements Serializable {
    
    public int kleur;
    
    public Kleur(Color color)
    {
        kleur = color.getRGB();
    }
    
    public Color getKleur()
    {
        return new Color(kleur);
    }
}

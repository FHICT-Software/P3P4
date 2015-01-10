//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces.gui;

import interfaces.IDrawable;
import java.io.Serializable;
import java.util.List;
//</editor-fold>

/**
 * In this class you can find all types and operations for IGameGUI.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/06/18
 */
public interface IGameGUI extends Serializable {

    public void requestDraw(List<IDrawable> drawables);
}

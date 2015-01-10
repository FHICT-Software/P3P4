//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

//</editor-fold>

import interfaces.IViewGame;
import java.awt.Graphics;
import java.io.Serializable;

/**
* In this class you can find all properties and operations for Viewer.
* //CHECK
 * 
* @organization: Moridrin
* @author        J.B.A.J. Berkvens
* @date          //TODO
 */
public class Viewer implements IViewGame, Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private IViewGame iViewGame;
    private User user;
	//</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(user)">
    public Viewer(IViewGame iViewGame, User user) {
        this.iViewGame = iViewGame;
        this.user = user;
    }

    //</editor-fold>
    public IViewGame getiViewGame() {
        return iViewGame;
    }

    //<editor-fold defaultstate="collapsed" desc="getUser">
    public User getUser() {
        return this.user;
    }
    //</editor-fold>
    //</editor-fold>

    @Override
    public Graphics view(String userName) {
       return iViewGame.view(userName); 
    }
}

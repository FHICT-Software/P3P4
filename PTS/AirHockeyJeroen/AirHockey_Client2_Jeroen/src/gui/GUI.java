//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
//</editor-fold>

/**
 * In this class you can find all properties and operations for GUI. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/06/19
 */
public class GUI {

    Stage STAGE;
    Scene SCENE;

    public GUI(Stage stage) {
        STAGE = stage;
    }

    public void show() {
        STAGE.show();
    }

    public void hide() {
        STAGE.hide();
    }

    public void close() {
        STAGE.close();
    }

    public void select() {
        STAGE.toFront();
    }
}

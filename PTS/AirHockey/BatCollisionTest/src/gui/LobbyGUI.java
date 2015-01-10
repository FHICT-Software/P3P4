//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//</editor-fold>

/**
 * In this class you can find all properties and operations for LobbyGUI.
 * //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/03/26
 */
public class LobbyGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Stage primaryStage;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //</editor-fold>
    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("LobbyGUI.fxml"));        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void main(String[] args)
    {
        Application.launch(args);
    }
    //</editor-fold>
    //</editor-fold>
}

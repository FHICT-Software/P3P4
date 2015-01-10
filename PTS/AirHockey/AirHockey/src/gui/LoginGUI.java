//<editor-fold defaultstate="collapsed" desc="Jibberish">

package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//</editor-fold>

/**
 * In this class you can find all properties and operations for LoginGUI.
 * //CHECK
 *
 * @organization: Moridrin
 * @author        J.B.A.J. Berkvens
 * @date          2014/03/26
 */
public class LoginGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Stage primaryStage;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    //</editor-fold>


    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Inlog GUI.fxml"));        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args)
    {
        Application.launch(args);           
    }
   

    }



package kolonistenvancatan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * In this class you can find all properties and operations for KVCLobbyGUI. //CHECK
 *
 * @organization
 * @author anne
 * @date 15-apr-2014
 */
public class KVCLobbyGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Events">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Oprarations">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scene Build">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Start">
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("KVCLobbyGUI.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent ev) {
                System.exit(0);
            }
        });

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
     * main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    //</editor-fold>

}

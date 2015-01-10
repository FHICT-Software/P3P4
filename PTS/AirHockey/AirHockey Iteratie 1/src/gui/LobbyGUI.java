//<editor-fold defaultstate="collapsed" desc="Jibberish">
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//</editor-fold>

/**
 * In this class you can find all properties and operations for LobbyGUI. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/03/26
 */
public class LobbyGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Stage primaryStage;
    @FXML
    ListView lvGames;
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
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
//        //<editor-fold defaultstate="collapsed" desc="Fill List with sample Games">
//        ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
//        lvGames.setItems(items);
//        //</editor-fold>
    }

    public void main(String[] args) {
        Application.launch(args);
    }
    //</editor-fold>
    //</editor-fold>
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kolonistenvancatanv3;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import kolonistenvancatan.CvcServer;
import kolonistenvancatan.KVCLobbyGUI;

/**
 *
 * @author jeroen
 */
public class KolonistenVanCatanV3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        CvcServer.main(null);
        KVCLobbyGUI kVCLobbyGUI = new KVCLobbyGUI();
        try {
            kVCLobbyGUI.start(primaryStage);
        } catch (Exception ex) {
            Logger.getLogger(KolonistenVanCatanV3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

package kolonistenvancatan;

import domein.Bord;
import domein.Coordinate;
import domein.Grondstof;
import domein.Kleur;
import domein.Spel;
import domein.Speler;
import domein.Straat;
import domein.Vesting;
import domein.tegels.Haventegel;
import domein.tegels.Landtegel;
import domein.tegels.Tegel;
import domein.tegels.Woestijntegel;
import domein.tegels.Zeetegel;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import java.awt.Color;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * In this main class you can find all properties and operations for KVCSpelGUI.
 *
 * @organization: Fontys S31D
 * @author Anne Toonen
 * @date 24-03-2014
 */
public class KVCSpelGUI extends Application {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Spel
    private Spel spel;
    private Speler GUISpeler;
    private Bord bord;

    //Canvas
    private Canvas catan;
    private GraphicsContext gc;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Events">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Teken op Canvas">
    private void drawBord() {
        
         // Graphics
         gc = catan.getGraphicsContext2D();
         catan.setHeight(catan.getHeight() + 400);
         catan.setWidth(catan.getWidth() + 400);
         Image imageTegel = null;
         Image imageNummer = null;
         //spel.getBord().getAlleTegels();
        
         //voor elke tegel
         for (Tegel t : spel.getBord().getAlleTegels()) {
         double tegelX = t.getPlaats().getImagePositie().getX();
         double tegelY = t.getPlaats().getImagePositie().getY();

         if (t instanceof Woestijntegel) {
         imageTegel = new Image("file:src/Images/Tegels/Woestijn.png");
         gc.drawImage(imageTegel, tegelX, tegelY);

         } else if (t instanceof Landtegel) {
         Landtegel lt = (Landtegel) t;
         imageNummer = new Image("file:src/Image/GetalFishes/Getal_" + lt.getLandnummer());
         double nummerX = t.getPlaats().getNumberPositie().getX();
         double nummerY = t.getPlaats().getNumberPositie().getY();

         switch (lt.getGrondstof()) {
         case HOUT:
         imageTegel = new Image("file:src/Images/Tegels/Bos.png");
         break;
         case BAKSTEEN:
         imageTegel = new Image("file:src/Images/Tegels/Weide.png");
         break;
         case GRAAN:
         imageTegel = new Image("file:src/Images/Tegels/Akker.png");
         break;
         case WOL:
         imageTegel = new Image("file:src/Images/Tegels/Heuvel.png");
         break;
         case ERTS:
         imageTegel = new Image("file:src/Images/Tegels/Berg.png");
         break;
         }
         gc.drawImage(imageTegel, nummerX, nummerY);
         gc.drawImage(imageNummer, tegelY, tegelY);

         } else if (t instanceof Haventegel && !spel.getBord().isOptieRand()) {
         Haventegel ht = (Haventegel) t;
         if (ht.getGrondstof() == null) {
         imageTegel = new Image("file:src/Images/Tegels/Haven_Gewoon.png");
         } else {
         switch (ht.getGrondstof()) {
         case HOUT:
         imageTegel = new Image("file:src/Images/Tegels/Haven_Hout_" + ht.getHavezijde() + ".png");
         break;
         case BAKSTEEN:
         //imageTegel = new Image("file:src/Images/Tegels/Haven_Wol_" + ht.getHavezijde() + ".png");
             imageTegel = new Image("file:src/Images/Tegels/Haven_Backsteen_" + ht.getHavezijde() + ".png");
         break;
         case GRAAN:
         imageTegel = new Image("file:src/Images/Tegels/Haven_Graan_" + ht.getHavezijde() + ".png");
         break;
         case WOL:
         //imageTegel = new Image("file:src/Images/Tegels/Haven_Backsteen_" + ht.getHavezijde() + ".png");
             imageTegel = new Image("file:src/Images/Tegels/Haven_Wol_" + ht.getHavezijde() + ".png");
         break;
         case ERTS:
         imageTegel = new Image("file:src/Images/Tegels/Haven_Erts_" + ht.getHavezijde() + ".png");
         break;
         }
         gc.drawImage(imageTegel, tegelX, tegelY);
         }
         } else if (t instanceof Zeetegel && !spel.getBord().isOptieRand()) {
         imageTegel = new Image("file:src/Images/Tegels/Zee.png");
         gc.drawImage(imageTegel, tegelX, tegelY);

         } else if (spel.getBord().isOptieRand()) {
         imageTegel = new Image("file:src/Images/Tegels/Rand.png");
         gc.drawImage(imageTegel, 297, 98);
         }
         }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Dorpen Bouwen">
    public Coordinate keuzePlaatsDorp(ArrayList<Coordinate> mogelijkheden) {
        Coordinate returner;
        returner = mogelijkheden.get(0);

        return returner;
    }
    //</editor-fold>

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scene Build">
    private Group tegenspeler(Speler speler) {
        Group returner = new Group();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3);
        gridPane.setVgap(3);

        Circle circle = new Circle(60);
        Rectangle rectangle = new Rectangle();
        Text text = new Text(speler.getNaam());

        return returner;
    }

    private VBox tegenspelers(ArrayList<Speler> spelers) {
        VBox returner = new VBox();
        for (int i = 0; i < spelers.size(); i++) {
            returner.getChildren().add(tegenspeler(spelers.get(i)));
        }
        return returner;
    }

    public Group Canvas() {
        Group returner = new Group();
        GridPane gridPane = new GridPane();
        catan = new Canvas(500, 500);
        drawBord();
        gridPane.add(catan, 0, 0, 25, 25);
        returner.getChildren().add(gridPane);
        return returner;
    }

    public Group ActieveGuiSpeler(Speler speler) {
        Group returner = new Group();
        return returner;
    }

    public Group Chat() {
        Group returner = new Group();
        return returner;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Popup Build">
    public void popupGUISpeler() {
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);

        final TextField tfGUISpeler = new TextField();
        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                GUISpeler = new Speler(tfGUISpeler.getText(), new Kleur(Color.BLUE));
                myDialog.close();
            }
        });

        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(tfGUISpeler, okButton)
                .spacing(30)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }

    public void popupErrorGrondstoffen() {
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);
        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }
        });

        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text("Je hebt te weinig grondstoffen"), okButton)
                .spacing(30)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Start">
    public void start(Spel spel, Speler speler) {
        this.spel = spel;
        this.GUISpeler = speler;
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception ex) {
            Logger.getLogger(KVCSpelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    public void setDorp(Vesting v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStad(Vesting v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Straat getStraat(ArrayList<Straat> straten, Kleur kleur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStraat(Straat s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean wilHandellen(ArrayList<Object[]> grondstoffenWillen, ArrayList<Object[]> grondstoffenGeven) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Speler willenHandelen(ArrayList<Speler> spelersRuilen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void letOpErIsGedobbelt(int al1, int al2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tegel setStruikrover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void grondstoffenInleveren(Speler s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        catan = new Canvas(650,650);
        drawBord();
        gridPane.add(catan, 0, 0, 25, 25);
        borderPane.getChildren().add(gridPane);
        primaryStage.setTitle("Kolonisten van catan");
        Parent root = FXMLLoader.load(getClass().getResource("KVCSpelGUI.fxml"));
        StackPane roots = new StackPane();
        roots.getChildren().add(borderPane);
        roots.getChildren().add(root);
        Scene scene = new Scene(roots);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Grondstof getGekozenGrondstof() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Grondstof> getTweeSoortenGrondstoffen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }
    
    public void testConnectie()
    {
        try {
            spel.lobby.plaatsBericht("TEST", "TEST");
        } catch (RemoteException ex) {
            Logger.getLogger(KVCSpelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

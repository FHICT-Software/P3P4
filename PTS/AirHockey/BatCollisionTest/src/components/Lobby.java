//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components;

//</editor-fold>
import calculations.DBconnect;
import gui.AirHockey;
import gui.LobbyGUI;
import gui.NieuwAccountGUI;
import interfaces.ILobbyLoggedIn;
import interfaces.ILobbyNotLoggedIn;
import interfaces.IStartedGame;
import interfaces.IWaitingGame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * In this class you can find all properties and operations for Lobby. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class Lobby implements ILobbyLoggedIn, ILobbyNotLoggedIn {
    
    DBconnect dbcon = new DBconnect();
    
    //Inloggen
    @FXML TextField tfGebruikersnaam;
    @FXML TextField tfWachtwoord;
    @FXML Button btInloggen;
    @FXML Button btNieuwAccount;
    @FXML Button btAfsluiten;
    
    //Nieuw account
    @FXML TextField tfGebruikersnaamNieuw;
    @FXML TextField tfWachtwoordNieuw;
    @FXML TextField tfEmail;
    @FXML TextField tfHerhaalWachtwoordNieuw;
    @FXML Button btMaakAccountAan;
    
    //Lobby
    @FXML Button btStart;
    @FXML Button btVerlaat;
    @FXML Button btStartNieuw;
    @FXML Button btUitloggen;
    

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private IWaitingGame iWaitingGames;
    private IStartedGame iStartedGame;
    private Viewer viewer;
    private User user;
    private List<User> userlist;
    NieuwAccountGUI gui;
    Stage stage;
	//</editor-fold>
    
    
    
	//<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor()">
    public Lobby() {
        userlist = new ArrayList<>();
    }
    
    public Lobby(User user)
    {
        user = new User(tfGebruikersnaam.getText(), 10);
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getIWaitingGames">
    public IWaitingGame getIWaitingGames() {
        return this.iWaitingGames;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getIStartedGame">
    public IStartedGame getIStartedGame() {
        return this.iStartedGame;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getViewer">
    public Viewer getViewer() {
        return this.viewer;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getUser">
    public User getUser() {
        return this.user;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createGame">
    /**
     * This method creates and returns the interface IWaitingGame.
     *
     * @param type This defines the type of game. Types: "SingleFun", "SingleCompetition", "MultiFun", "MultiCompetition".
     * @param user The user who created the game (will be the 1st Player/Host Player).
     */
    public IWaitingGame createGame(String type, User user) 
    {
        Game game = new Game(this, user, type);
        return game;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="joinGame">
    /**
     * A method that lets other players join an existing game. Returns the IGameWaiting interface.
     *
     * @param game The game that the user wants to join.
     * @param user The user who wants to join the game (will be the next Player).
     * @return 
     */
    @Override
    public IWaitingGame joinGame(IWaitingGame game, User user) 
    {
        // @Lennart: Als ik een player moet toevoegen, heb ik dan niet een game object nodig
        // (dus ipv Game ipv IWaitingGame) met een lijst van players? Met de meegegeven interface kan ik niet veel.
        return game;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="logout">
    /**
     * This method will null the user component (log it off) and return the ILobbyNotLoggedIn interface
     * @return 
     */
    @Override
    public ILobbyNotLoggedIn logout() 
    {
        this.user = null;
        return this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="login">
    /**
     * In this function a User instance will be created according to the parameter information, and saved. Also it will return the
     * ILobbyLoggedIn interface.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     *
     * @return
     */
    @Override
    public ILobbyLoggedIn login(String username, String password) 
    {
        
        // User user = new User(username, password);
        // @Lennart: als ik een User instance moet maken met een username en password, 
        // waarom heeft de constructor in User dan geen password? Als dat om security redenen is,
        // heb ik dan niet iets van een database nodig voor deze functie?
        try {
            if(password.equals(dbcon.getPassword(username)))
            {
                System.out.println("Ingelogd");
                return this;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Combinatie van gebruikersnaam/wachtwoord is niet bekend in systeem.", "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="register">
    /**
     * This method will attempt to register a new user. It returns the ILobbyLoggedIn interface.
     *
     * @param username The username of the new user. This needs to be a unique name.
     * @param password The password of the new user. There are no restrictions to this.
     *
     * @return
     */
    @Override
    public ILobbyLoggedIn register(String username, String email, String password) 
    {
        try {
            if(dbcon.CheckUsername(username)==false)
            {
                dbcon.CreateUser(username, email, password);
                JOptionPane.showMessageDialog(null, "Gebruiker is aangemaakt", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            //else
            {
                JOptionPane.showMessageDialog(null, "Deze gebruikersnaam bestaat al", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    //</editor-fold>
    //</editor-fold>
    
    public void Inloggen(Event evt)
    {
       if(login(tfGebruikersnaam.getText(),tfWachtwoord.getText()) != null)
       {
            LobbyGUI lobbygui = new LobbyGUI();
            stage = new Stage();
            try
            {
                lobbygui.start(stage);
            } 
            catch (IOException ex)
            {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage)btInloggen.getScene().getWindow();
            stage.close();
       }
    }
    
    public void NieuwAccountGUI(Event evt)
    {
        gui = new NieuwAccountGUI();    
        stage = new Stage();
        try
        {
            gui.start(stage);
        } catch (Exception ex)
        {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MaakAccountAan(Event evt)
    {
        if(!tfGebruikersnaamNieuw.getText().equals("") && !tfWachtwoordNieuw.getText().equals("") && !tfEmail.getText().equals("") && !tfHerhaalWachtwoordNieuw.getText().equals(""))
        {
            if(tfWachtwoordNieuw.getText().equals(tfHerhaalWachtwoordNieuw.getText()))
            {
        try
        {
            
          register(tfGebruikersnaamNieuw.getText(), tfEmail.getText(), tfWachtwoordNieuw.getText());
          stage = (Stage)btMaakAccountAan.getScene().getWindow();
          stage.close();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Er is iets verkeerd gegaan bij het aanmaken van het account.\n Probeer het opnieuw", "Warning", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            else
            {
                JOptionPane.showMessageDialog(null, "Wachtwoorden komen niet overeen", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Zorg dat alle velden correct ingevuld zijn", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void Start(Event evt)
    {
        
    }
    
    public void Verlaat(Event evt)
    {
        
    }
    
    public void StartNieuw(Event evt)
    {
        AirHockey air = new AirHockey();
        stage = new Stage();
        air.start(stage);
    }
    
    public void Afsluiten(Event evt)
    {
        stage = (Stage)btAfsluiten.getScene().getWindow();
        stage.close();
    }
    
    public void AfsluitenLobby(Event evt)
    {
        stage = (Stage)btUitloggen.getScene().getWindow();
        stage.close();
    }
   
}

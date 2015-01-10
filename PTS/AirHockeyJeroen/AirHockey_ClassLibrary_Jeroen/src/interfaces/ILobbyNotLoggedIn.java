//<editor-fold defaultstate="collapsed" desc="Jibberish">
package interfaces;

//</editor-fold>
/**
 * In this class you can find all types and operations for ILobbyNotLoggedIn.
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public interface ILobbyNotLoggedIn {

    /**
     * This operation connects to the server.
     *
     * @return true if the connection is successful.
     */
    public boolean loginConnect();

    /**
     * This operation connects to the server and tries to login with the given parameters. The server returns if it is "Access
     * Granted" or "Access Denied". Depending on the server return, the Lobby GUI will be opened.
     *
     * @param username the username with which the actor tries to login.
     * @param password the password with which the actor tries to login.
     *
     * @return the interface to control the Lobby when logged in.
     */
    public ILobbyLoggedIn login(String username, String password);

    /**
     * This operation connects to the server and tries to create a new user with the given parameters. The server than returns if
     * it is "Access Granted" or "Access Denied". Depending on the server return, the Lobby will be opened. (The new user is
     * automatically logged in).
     *
     * @param username the new username (this has to be unique).
     * @param password the password linked to this user.
     *
     * @return the interface to control the Lobby when logged in.
     */
    public ILobbyLoggedIn register(String username, String password);
}

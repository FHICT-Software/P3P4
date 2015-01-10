//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.lobby;

//</editor-fold>
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class you can find all properties and operations for User. //CHECK
 *
 * @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date 2014/05/25
 */
public class User implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //<editor-fold defaultstate="collapsed" desc="Finals">
    private final String username;
    private final List<Double> ratings;
    private final UserPreferences userPreferences;
    //</editor-fold>
    private String firstName = "";
    private String lastName = "";
    private String birthday = "";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //</editor-fold>
    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(name)">
    /**
     * This is the constructor for User.
     *
     * @param username is an unique identifier for the user.
     */
    public User(String username) {
        this.username = username;
        ratings = new ArrayList<>();
        userPreferences = new UserPreferences();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Class">
    @Override
    public String toString() {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !userPreferences.preferUsername()) {
            return lastName + ", " + firstName;
        } else {
            return username;
        }
    }
    //</editor-fold>
    //</editor-fold>
}

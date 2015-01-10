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
    private final String email;
    private final List<Double> ratings;
    private final UserPreferences userPreferences;
    //</editor-fold>
    private String firstName = "";
    private String lastName = "";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    //<editor-fold defaultstate="collapsed" desc="Username">
    public String getUsername() {
        return username;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Name">
    public String getName() {
        return lastName + ", " + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(username, email)">
    /**
     * This is the constructor for User.
     *
     * @param username is an unique identifier for the user.
     * @param email    is the email connected to this user.
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        ratings = new ArrayList<>();
        userPreferences = new UserPreferences();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor(username, email, firstName, lastName)">
    /**
     * This is the constructor for User.
     *
     * @param username  is an unique identifier for the user.
     * @param email     is the email connected to this user.
     * @param firstName is the first name of the user.
     * @param lastName  is the last name of the user.
     */
    public User(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ratings = new ArrayList<>();
        this.userPreferences = new UserPreferences();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Class">
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return this.equals((User) o);
        } else {
            return false;
        }
    }

    public boolean equals(User o) {
        return this.username.equals(o.username);
    }
    
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

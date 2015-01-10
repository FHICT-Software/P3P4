//<editor-fold defaultstate="collapsed" desc="Jibberish">
package components.game;

//</editor-fold>

import java.io.Serializable;

/**
 * In this class you can find all properties and operations for User. //CHECK
 * 
* @organization: Moridrin
 * @author J.B.A.J. Berkvens
 * @date //TODO
 */
public class User implements Serializable{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private String name;
    private int rating = 15;
    private int ratingScores;
	//</editor-fold>

	//<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(name, ratingScores)">
    public User(String name, int ratingScores) {
        this.name = name;
        this.ratingScores = ratingScores;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getName">
    public String getName() {
        return this.name;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getRating">
    public int getRating() {
        return this.rating;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setRatingScores">
    public void setRatingScores(int ratingScores) {
        this.ratingScores = ratingScores;
    }
	//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getRatingScores">
    public int getRatingScores() {
        return this.ratingScores;
    }
	//</editor-fold>
    //</editor-fold>
}

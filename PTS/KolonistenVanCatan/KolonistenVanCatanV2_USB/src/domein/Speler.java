/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domein;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * In this class you can find all properties and operations for Speler. 
 * //CHECK
 *
 * @organization    
 * @author          anne
 * @date            9-apr-2014 
 */
public class Speler {
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final String gebruikersnaam;
    private ArrayList<Object[]> aantallenVanGrondstoffen;
    private int overwinningspunten;
    private final Color kleur;
    private ArrayList<Vesting> Vestigingen;
    private ArrayList<Straat> Straten;
    //private ArrayList<IOntwikkelingskaart> Ontwikkelingskaarten;
    private int gespeeldeRidderkaarten;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Speler(String naam, Color kleur){
        this.gebruikersnaam = naam;
        this.kleur = kleur;
        this.Vestigingen = new ArrayList<>();
    }

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Oprarations">
    public String getNaam() {
        return gebruikersnaam;
    }
    
    public Color getKleur(){
        return kleur;
    }
    
    public int getOverwinningspunten() {
        return this.overwinningspunten;
    }
    
    public void setOverwinningspunten(int overwinningspunten) {
        this.overwinningspunten = overwinningspunten;
    }
    
    public int getGespeeldeRidderkaarten() {
        return this.gespeeldeRidderkaarten;
    }
    
    public void setGespeeldeRidderkaarten() {
        this.gespeeldeRidderkaarten = gespeeldeRidderkaarten + 1;
    }
    
    public boolean voorraadToereikend(Grondstof grondstof, int aantal) {
        boolean toereikend = false;
        for (Object[] o : aantallenVanGrondstoffen) {
            Grondstof gs = (Grondstof) o[0];
            int i = (int) o[1];
            if (gs == grondstof && i >= aantal) {
                
                return true;
            }
        }
        return toereikend;
    }
    
    public int aantalGrondstoffen(Grondstof grondstof) {
        for (Object[] o : aantallenVanGrondstoffen)
        {
            if(o[0] == grondstof){
                return  (int) o[1];
            }
        }
        return 0;
    }
    
    public boolean moetGrondstoffenInleveren() {
        int aantal = 0;
        for (Object[] o : aantallenVanGrondstoffen){
            for (Object o2 : o){
                aantal += (int)o[1];
            }
        }
        return (aantal > 7);     
    }
        
    public void setGrondstof(Grondstof grondstof, int aantal) {
        for (Object[] o : aantallenVanGrondstoffen) {
            if ((Grondstof) o[0] == grondstof) {
                if (aantal + (int) o[1] >= 0) {
                    o[1] = aantal + (int) o[1];
                }
            }
        }
    }
    
    public void setDorp(Vesting v){
        int count = 0;
        for(Vesting vesting : Vestigingen){
            if(vesting.getIsStad() == false){
                count = count +1;
            }
        }
        if(count <=4){
           Vestigingen.add(v); 
        }
    }
    
    public void setStad(Vesting v){
        int count = 0;
        for(Vesting vesting : Vestigingen){
            if(vesting.getIsStad() == true){
                count = count +1;
            }
        }
        if(count <=3){
           Vestigingen.add(v); 
        }
    }
    
    public void setStraat(Straat s){
        Straten.add(s);
    }
    
    public ArrayList<Straat> getStraten(){
        return Straten;
    }
    
    public ArrayList<Vesting> getVestigingen(){
        return this.Vestigingen;
    }

    void grondstofInnen(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
}

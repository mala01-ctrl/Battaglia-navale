/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

/**
 *
 * @author lorenzo
 */
public class Posizione {
    /**
     * Variabile che rappresenta la posizione orizzontale 
     */
    private int x;
    /**
     * Variabile che rappresenta la posizione verticale
     */
    private int y;
    
    /**
     * Costruttore della classe Posizione con i parametri
     * @param x posizione orizzontale 
     * @param y posizione verticale 
     * @throws Exception lancia l'eccezione se uno dei due valori è minore di 0
     */
    public Posizione(int x, int y) throws Exception
    {
        if (x < 0 || y < 0)
            throw new Exception("Posizione impossibile da assegnare");
        this.x = x;
        this.y = y;
    }
    /**
     * Costruttore vuoto della classe Posizione
     */
    public Posizione(){}
    
    /**
     * Getter della posizione orizzontale
     * @return posizione orizzontale
     */
    public int getX() {
        return x;
    }
    
    /**
     * Getter della posizione verticale
     * @return posizione verticale
     */
    public int getY() {
        return y;
    }
    
}

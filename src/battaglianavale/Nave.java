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
public class Nave {
    /**
     * Nome della nave
     */
    private String nome;
    /**
     * lunghezza della nave 
     */
    private int lunghezza;
    /**
     * valore della nave 
     */
    private int valore;
    /**
     * variabile per vedere se la nave è affondata
     */
    private boolean affondata;
    
    /**
     * Costruttore della classe Nave con i parametri
     * @param nome nome della nave 
     * @param lunghezza lunghezza della nave 
     * @throws Exception lancia l'eccezione se il nome passato è nullo o se 
     * la lunghezza è minore o uguale a 0
     */
    public Nave (String nome, int lunghezza) throws Exception
    {
        if (nome == null)
            throw new Exception("Nome passato nullo");
        if (lunghezza <= 0)
            throw new Exception("La lunghezza della nave deve essere maggiore o uguale a 0");
        this.nome = nome;
        this.lunghezza = lunghezza;
        this.affondata = false;
        this.valore = lunghezza;
    }
    
    /**
     * Getter del nome della nave
     * @return nome della nave
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Getter della lunghezza della nave
     * @return lunghezza della nave
     */
    public int getLunghezza() {
        return lunghezza;
    }
    
    /**
     * Getter del valore della nave
     * @return valore della nave
     */
    public int getValore() {
        return valore;
    }
    
    /**
     * Getter della variabile affondata
     * @return lo stato della nave
     */
    public boolean isAffondata() {
        return affondata;
    }
    
    /**
     * Metodo che decrementa il valore della nave quando viene colpita e controlla
     * se il suo valore è uguale a 0. Se il valore è 0 setta la variabile affondata 
     * a true.
     */
    public void naveColpita()
    {
        valore--;
        if (valore == 0)
            affondata = true;
    }
    
}

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
public class Cella {
    private boolean libera;
    private Nave nave;
    private int valore;
    
    public Cella()
    {
        libera = true;
        valore = 0;
    }
    
    public int getValore() {
        return valore;
    }
    public void assegnaNave(Nave nave)
    {
        this.nave = nave;
        libera = false;
        valore = 1;
    }

    public boolean isLibera() {
        return libera;
    }

    public Nave getNave() {
        return nave;
    }
    
    public void colpita()
    {
        valore--;
        this.nave.naveColpita();
    }
    
   
}

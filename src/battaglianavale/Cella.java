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
    
    public Cella()
    {
        libera = true;
    }
    
    public void assegnaNave(Nave nave)
    {
        this.nave = nave;
        libera = false;
    }

    public boolean isLibera() {
        return libera;
    }

    public Nave getNave() {
        return nave;
    }
    
   
}

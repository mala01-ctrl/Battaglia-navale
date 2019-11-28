/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

/**
 *
 * @author informatica
 */
public class Game { 
    private Player currentPlayer;
    
    public synchronized void move(Player player)
    {
        if (player != currentPlayer)
            throw new IllegalStateException("Non è il tuo turno");
        if (player.getOpponent() == null)
            throw new IllegalStateException("Non hai ancora un'avversario");
    }
}

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
public class Game {
    private Player currentPlayer;
    private Player opponent;

    public Game() {
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }
    
    public synchronized boolean CheckConnection()
    {
        if (opponent == null)
            return false;
        return true;
    }
    
}

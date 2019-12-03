/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author lorenzo
 */
public class Game {
    private Player currentPlayer;

    public Game() {
    }

    /*public void setCurrentPlayer(Player currentPlayer) {
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
    }*/
    
    public class Player implements Runnable
    {
        private Player opponent;
        private Socket socket;
        private Scanner input;
        private PrintWriter output;
        private String nomeGiocatore;
        private int id;
        
        public Player(Socket socket, int id) throws IOException
        {
            this.socket = socket;
            this.id = id;
            this.input = new Scanner(this.socket.getInputStream());
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
        }
        
        @Override
        public void run() {
            setup();
        }
        
        private void setup()
        {
            output.println("Benvenuto");
            if (id == 1)
            {
                currentPlayer = this;
                output.println("Attendere la connessione di un avversario");
            }
            else
            {
                opponent = currentPlayer;
                opponent.opponent = this;
                opponent.output.println("è il tuo turno");
            }
        }
    }
    
}

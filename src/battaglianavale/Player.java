/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author informatica
 */
public class Player implements Runnable{
    /**
     * Variabile di tipo Socket utilizzata per instaurare la 
     * connessione con il client
     */
    private Socket socket;
    /**
     * Rapprsenta il nome del giocatore 
     */
    private String nomeGiocatore;
    /**
     * Variabile utilizzata per ricevere qualcosa in input
     */
    private Scanner input;
    /**
     * Variabile utilizzata per la comunicazione con il client
     */
    private PrintWriter output;
    /**
     * Matrice di interi che rappresenta la matrice della battaglia navale
     */
    private int[][] matrice;
    
    /**
     * Costruttore della classe player 
     * @param socket socket per instaurare la connessione con il client
     * @param nomeGiocatore nome del giocatore
     */
    public Player(Socket socket, String nomeGiocatore)
    {
        this.socket = socket;
        this.nomeGiocatore = nomeGiocatore;
        this.matrice = new int[21][21];
    }
    
    public void stampaMatrice() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println("");
        }
    }

    @Override
    public void run() {
        System.out.println("Connesso: " + socket);
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println(new Date().toString());
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

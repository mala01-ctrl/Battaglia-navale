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
     * Numero che contraddistingue i due giocatori
     */
    private int numero;
    
    /**
     * Variabile statica della classe Game utilizzata per ogni istanza 
     * della classe Player
     */
    private static Game game = new Game();
    
    /**
     * Costruttore della classe player 
     * @param socket socket per instaurare la connessione con il client
     * @param numero numero che contraddistingue il giocatore
     */
    public Player(Socket socket, int numero) throws IOException
    {
        this.socket = socket;
        this.nomeGiocatore = "";
        this.matrice = new int[10][10];
        this.numero = numero;
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.input = new Scanner(socket.getInputStream());
    }
    
    /**
     * Metodo che stampa la griglia delle navi 
     */
    public void stampaMatrice() {
        output.println("Visualizzazione griglia Battaglia navale");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                output.print(matrice[i][j] + " ");
            }
            output.println("");
        }
    }
    
    /**
     * Metodo setter che assegna uno username al giocatore 
     * @param nomeGiocatore username da assegnare al giocatore
     */
    private void setNomeGiocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }
    
    /**
     * Metodo che richiede al client un nome giocatore e che successivamente
     * tramite il metodo setNomeGiocatore(String nomegiocatore) assegnerà alla 
     * variabile. Utilizza inoltre i metodi della classe Game per impostare 
     * il giocatore corrente e l'avversario.
     */
    public void setUsernamePlayer() {
        output.println("Inserire nome giocatore: ");
        setNomeGiocatore(input.nextLine());
        output.println(nomeGiocatore);
        if (this.numero == 1) {
            game.setCurrentPlayer(this);
            output.println("attendere connessione altro giocatore");
        } else {
            game.setOpponent(this);
            game.getOpponent().output.println("Pronto");
            game.getCurrentPlayer().output.println("Pronto");
        }
    }
    
    /**
     * I Thread eseguono questo metodo 
     */
    @Override
    public void run() {
        setUsernamePlayer();
        stampaMatrice();
        CheckPlayers();
        GetCoordinate();
    }
    
    /**
     * Metodo che controlla che entrambi i giocatori siano connessi
     */
    public void CheckPlayers()
    {
        while (true)
        {
            if (game.CheckConnection()) {
                Player p = game.getCurrentPlayer();
                game.setCurrentPlayer(game.getOpponent());
                game.setOpponent(p);
                return;
            }
        }
    }
    /**
     * Metodo per l'inserimento delle coordinate
     */
    public void GetCoordinate() {
        int i;
        int j;
        output.println("Inserire la colonna della prima nave");
        String response = input.nextLine();
        i = Integer.parseInt(response);
        output.println("Inserire la posizione verticale della nave");
    }
}

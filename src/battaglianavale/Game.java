/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author lorenzo
 */
public class Game {
    /**
     * Giocatore corrente
     */
    private Player currentPlayer;
    
    private ArrayList <Nave> navi;
    /**
     * Costruttore vuoto della classe Game
     */
    public Game() throws Exception {
        this.navi = new ArrayList<>();
        setCacciatorpediniere();
        setSottomarini();
        this.navi.add(new Nave("Corazzata", 4));
        this.navi.add(new Nave("Portaerei", 5));
    }
    
    private void setCacciatorpediniere() throws Exception
    {
        for (int i = 0; i < 3; i++)
        {
            this.navi.add(new Nave("Cacciatorpediniere", 2));
        }
    }
    
    private void setSottomarini() throws Exception
    {
        for (int i = 0; i < 2; i++)
        {
            this.navi.add(new Nave("Sottomarino", 3));
        }
    }
    
    
    public class Player implements Runnable
    {
        /**
         * Giocatore avversario
         */
        private Player opponent;
        /**
         * Socket con cui si instaura la connessione con il client
         */
        private Socket socket;
        /**
         * Variabile necessaria per ricevere informazioni dal client
         */
        private Scanner input;
        /**
         * Variabile necessaria per inviare informazioni al client
         */
        private PrintWriter output;
        /**
         * Nome del giocatore
         */
        private String nomeGiocatore;
        /**
         * Id del giocatore
         */
        private int id;
        
        private Cella[][] griglia;
        
        /**
         * Costruttore della inner class Player con i parametri
         * @param socket socket per la connessione 
         * @param id id del giocatore
         * @throws IOException 
         */
        public Player(Socket socket, int id) throws IOException
        {
            this.socket = socket;
            this.id = id;
            this.input = new Scanner(this.socket.getInputStream());
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            griglia = new Cella[21][21];
        }
        
        @Override
        public void run() {
            setNomeGiocatore();
            setup();
        }
        
        /**
         * Setter del nome del giocatore 
         */
        public void setNomeGiocatore() {
            output.println("Inserire nome giocatore: ");
            this.nomeGiocatore = input.nextLine();
        }
        
        /**
         * Metodo che stabilisce chi tra i due giocatori sia quello
         * corrente e l'avversario
         */
        private void setup()
        {
            output.println("Benvenuto " + nomeGiocatore);
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
        
        public void posizionaNave()
        {
            this.output.println("Posiziona le navi");
            this.output.println("Posizionare navi in verticale?");
            if (this.input.nextLine().equals("SI"))
            {
                this.output.println("Digitare posizione verticale");
                String response = this.input.nextLine();
                int x = Integer.parseInt(response);
            }
        }
    }
    
    
}

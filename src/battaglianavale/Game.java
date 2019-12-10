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
    
    private String[] nomi = {"cacciatorpediniere", "sottomarino", 
        "corazzata", "portaerei"};
    
    private int[] valori = {2, 3, 4, 5}; 
    /**
     * Costruttore vuoto della classe Game
     */
    public Game() throws Exception {
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

        private ArrayList<Nave> navi;

        private ArrayList<Nave> naviDaPosizionare;
        
        /**
         * Costruttore della inner class Player con i parametri
         * @param socket socket per la connessione 
         * @param id id del giocatore
         * @throws IOException 
         */
        public Player(Socket socket, int id) throws IOException, Exception
        {
            this.socket = socket;
            this.id = id;
            this.input = new Scanner(this.socket.getInputStream());
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            griglia = new Cella[21][21];
            this.navi = new ArrayList<>();
            this.naviDaPosizionare = new ArrayList<>();
            for (int i = 0; i < 4; i++)
            {
                this.navi.add(new Nave(Game.this.nomi[i], Game.this.valori[i]));
                this.naviDaPosizionare.add(this.navi.get(i));
            }
        }
        
        @Override
        public void run() {
            setNomeGiocatore();
            posizionaNave();
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
            this.output.println("Posizionare navi in verticale?S/N");
            String risposta = this.input.nextLine();
            if (risposta.equals("S"))
            {
                this.output.println("Digitare numero righe");
                String response = this.input.nextLine();
                int x = Integer.parseInt(response);
            }
        }
    }
    
    
}

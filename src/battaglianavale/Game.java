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
    
    private int[] lunghezze = { 2, 3, 4, 5 };
    
    private String[] nomi = { "cacciatorpediniere", "sottomarino", 
        "corazzate", "portaerei" };
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
            for (int i = 0; i < 3; i++) {
                this.naviDaPosizionare.add(new Nave("Cacciatorpediniere", 2));
            }
            for (int i = 0; i < 2; i++) {
                this.naviDaPosizionare.add(new Nave("Sottomarino", 3));
            }
            naviDaPosizionare.add(new Nave("Corazzata", 4));
            naviDaPosizionare.add(new Nave("Portaerei", 5));
            for (int i = 0; i < 21; i++)
            {
                for (int j = 0; j < 21; j++)
                {
                    griglia[i][j] = new Cella();
                }
            }
        }
        
        @Override
        public void run() {
            setNomeGiocatore();
            stampaMatrice();
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
        
        private void posizionaNave() {
            this.output.println("Posiziona le navi");
            int x;
            int y;
            while (!naviDaPosizionare.isEmpty()) {
                output.println("Nave da Posizionare: " + naviDaPosizionare.get(0).getNome());
                this.output.println("Orientamento");
                String risposta = this.input.nextLine();
                if (risposta.equals("S")) {
                    this.output.println("Colonna");
                    risposta = this.input.nextLine();
                    y = convertiLetteraNumero(risposta.charAt(0));
                    System.out.println("Y: " + y);
                    this.output.println("Righe");
                    risposta = input.nextLine();
                    x = Integer.parseInt(risposta) - 1;
                    System.out.println("X: " + x);
                    if (PosizionaNaveValoreVerticale(x, y, naviDaPosizionare.get(0))) {
                        output.println("Posizionato");
                        naviDaPosizionare.remove(0);
                    }
                    else
                        output.println("Non posizionato");
                    stampaMatrice();
                }
                else {
                    this.output.println("Digitare lettera delle colonne");
                    risposta = this.input.nextLine();
                    y = convertiLetteraNumero(risposta.charAt(0));
                    System.out.println("Y: " + y);
                    this.output.println("Digitare numero delle righe");
                    risposta = input.nextLine();
                    x = Integer.parseInt(risposta) - 1;
                    System.out.println("X: " + x);
                    if (posizionaNaveOrizzontale(x, y, naviDaPosizionare.get(0)))
                    {
                        output.println("Posizionato");
                        naviDaPosizionare.remove(0);
                        stampaMatrice();
                    }
                }
            }
        } 
        
        private int convertiLetteraNumero(char lettera) {
            return lettera - 65;
        }
        
        private boolean PosizionaNaveValoreVerticale(int x, int y, Nave nave) {
            //Controllo verticale
            if (!controlloVerticalePosVerticale(x, y, nave.getLunghezza()))
                return false;
            //Controllo orizzontale 
            if (!controlloVerticalePosOrizzontale(x, y, nave.getLunghezza()))
                return false;
            for (int i = 0; i < nave.getLunghezza(); i++) {
                griglia[x + i][y].assegnaNave(nave);
            }
            return true;
        }
        
        private boolean controlloVerticalePosOrizzontale(int x, int y, int valore) {
            for (int i = 0; i < valore; i++) {
                if (!griglia[x + i][y + 1].isLibera())
                    return false;
                if (y > 0)
                    if (!griglia[x + i][y - 1].isLibera()) 
                        return false;
            }
            return true;
        }
        
        private boolean controlloVerticalePosVerticale(int x, int y, int valore) {
            for (int i = 0; i < valore; i++) {
                if (!griglia[x + i][y].isLibera()) 
                    return false;
            }
            if (x > 0) {
                if (!griglia[x - 1][y].isLibera()) 
                    return false;
            }
            return true;
        }
        
        private void stampaMatrice()
        {
            for (int i = 0; i < 21; i++)
            {
                for (int j = 0; j < 21; j++)
                {
                    output.print(griglia[i][j].getValore());
                }
                output.println();
            }
        }
        
        private boolean controlloOrizzontalePosVerticale(int x, int y, int valore)
        {
            for (int i = 0; i < valore; i++)
            {
                if (!griglia[x + 1][y + i].isLibera())
			return false;
                if (x > 0)
                    if (!griglia[x - 1][y + i].isLibera())
			return false;
            }
            return true;
        }
        
        private boolean controlloOrizzontalePosOrizzontale(int x, int y, int valore) {
            if (y > 0) 
                if (!griglia[x][y - 1].isLibera()) 
                    return false;
            for (int i = 0; i < valore; i++) {
                if (!griglia[x][y + i].isLibera())
                    return false;
            }
            if ((21 - y) < valore)
                return false;
            return true;
        }
        
        private boolean posizionaNaveOrizzontale(int x, int y, Nave nave)
        {
            if (!controlloOrizzontalePosOrizzontale(x, y, nave.getValore()))
		return false;
            if (!controlloOrizzontalePosVerticale(x, y, nave.getValore()))
		return false;
            for (int i = 0; i < nave.getValore(); i++)
            {
		griglia[x][y + i].assegnaNave(nave);
            }
            return true;
        }
    }
}

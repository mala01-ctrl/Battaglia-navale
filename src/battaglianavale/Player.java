/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author informatica
 */
public class Player {
    private Socket socket;
    private String nomeGiocatore;
    private Scanner input;
    private PrintWriter output;
    private int[][] matrice;
    
    public Player(/*Socket socket,*/ String nomeGiocatore)
    {
        /*this.socket = socket;*/
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
}

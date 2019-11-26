/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author informatica
 */
public class BattagliaNavale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Player p = new Player(/*new Socket("127.0.0.1", 55555),*/ "User");
        p.stampaMatrice();
    }
    
}

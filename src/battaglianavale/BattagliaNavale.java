/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        try (ServerSocket listener = new ServerSocket(55555))
        {
            System.out.println("Server battaglia navale pronto...");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true)
            {
                pool.execute(new Player(listener.accept()));
                pool.execute(new Player(listener.accept()));
            }
        }
    }
    
}

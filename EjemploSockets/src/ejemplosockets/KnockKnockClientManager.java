/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnockKnockClientManager implements Runnable {

    private Socket clientSocket;
    private KnockKnockProtocol kkp;
    private KnockKnockProxy proxy;
    private boolean salir=true;

    public KnockKnockClientManager(Socket c) {
        this.clientSocket = c;
        this.kkp = new KnockKnockProtocol();
        
    }

    public void run() {
        this.proxy=new KnockKnockProxy(kkp, this);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            proxy.enviarMensaje(null);
            while ((inputLine = in.readLine()) != null) {
                // Utiliza el proxy para manejar la comunicación
                proxy.enviarMensaje(inputLine);

                if (salir==false) {     
                    break;
                }
            }

            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void enviarMensaje(String mensaje) {
        if(mensaje.equals("Bye.")){
            salir=false;
        }
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego, marcos, carmen,oscar 
 */
public class ClienteSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket kkSocket = null;
        Stub stub = null;

        try {
            kkSocket = new Socket("localhost", 4444);
            SocketCliente cliente = new SocketCliente(kkSocket);
           
            stub = new Stub(cliente);
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;
            
            Cliente c = new Cliente(stub,scanner);
            cliente.agregarObserver(c);
            cliente.start();
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

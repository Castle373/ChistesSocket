/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Cliente implements Observer {

    private Stub stub = null;
    private BufferedReader scanner=null;
    public Cliente(Stub stub,BufferedReader scanner) {
        this.scanner = scanner;
        this.stub = stub;
    }

    public void enviarRespuesta() {
        try {
            
            String fromUser;
            fromUser = scanner.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                stub.enviarMensaje(fromUser);
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
            enviarRespuesta();
    }
}

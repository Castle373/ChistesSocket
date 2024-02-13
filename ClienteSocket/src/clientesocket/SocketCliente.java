/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego, marcos, carmen,oscar 
 */
public class SocketCliente extends Thread implements Observable{
    public List<Observer> listaObservable;
    private Socket socketCliente;
    private PrintWriter out;
    private BufferedReader in;
    private ClienteSocket cliente;


    public SocketCliente(Socket kkSocket) {
       listaObservable = new ArrayList<>();
        try {
            socketCliente = kkSocket;
            out = new PrintWriter(socketCliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void enviarAlServidor(String mensaje) {
        out.println(mensaje);
    }

    @Override
    public void run() {
        try {
            String fromServer; 
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
               
                if (fromServer.equals("Bye.")) {
                    break;
                }else{
                     notificarObservers();
                }
            }  
            out.close();
            in.close();
            socketCliente.close();  
            
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void agregarObserver(Observer o) {
       listaObservable.add(o);
    }

    @Override
    public void eliminarObserver(Observer o) {
      listaObservable.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer observer : new ArrayList<>(listaObservable)) {
            observer.update();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import proxy.ConexionProxy;

/**
 *
 * @author diego, marcos, carmen,oscar 
 */
public class Stub implements ConexionProxy{

    public SocketCliente socket;
    public Stub(SocketCliente socket) {
        this.socket=socket;
    }
    @Override
    public void enviarMensaje(String string) {
        socket.enviarAlServidor(string);
        
    }
    
}

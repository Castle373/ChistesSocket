/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

import proxy.ConexionProxy;

/**
 *
 * @author oscar
 */
public class KnockKnockProxy implements ConexionProxy {

    private KnockKnockProtocol kkproto;
    private KnockKnockClientManager clientManager;

    public KnockKnockProxy(KnockKnockProtocol kkproto, KnockKnockClientManager clientManager) {
        this.kkproto = kkproto;
        this.clientManager = clientManager;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        // Utiliza el resultado de processInput para enviar al cliente
        String respuesta = kkproto.processInput(mensaje);
        clientManager.enviarMensaje(respuesta);
    } 

}

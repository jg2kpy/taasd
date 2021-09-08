package py.una.pol.taaprimerparcial.UDP.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import static py.una.pol.taaprimerparcial.UDP.Client.Menu.MenuCLI;
import static py.una.pol.taaprimerparcial.UDP.Client.Menu.menuError;
import static py.una.pol.taaprimerparcial.UDP.Client.Menu.menuRespuesta;
import py.una.pol.taaprimerparcial.UDP.classes.Mensaje;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class UDPClient {
    
    public static boolean salir = false;
    
    public static void main(String a[]) throws Exception {

        // Datos necesario
        String direccionServidor = "127.0.0.1";
        int puertoServidor = 6969;

        if (a.length > 0) {
            direccionServidor = a[0];
        }
        
        InetAddress IPAddress = InetAddress.getByName(direccionServidor);
        System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");
        while(!salir){
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                //ESPERANDO AL CLIENTE
                Mensaje enviarCliente = MenuCLI();
                if(enviarCliente.cuerpo == null){
                    UDPClient.salir = true;
                    System.exit(0);
                }

                String datoPaquete = enviarCliente.toJSON();
                sendData = datoPaquete.getBytes();
                System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket =  new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperamos si viene la respuesta.");

                //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                clientSocket.setSoTimeout(10000);

                try {
                    // ESPERAMOS LA RESPUESTA, BLOQUENTE
                    clientSocket.receive(receivePacket);
                    String respuesta = new String(receivePacket.getData());

                    InetAddress returnIPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);

                    Mensaje respuestaObj = new Mensaje(respuesta);

                    menuRespuesta(respuestaObj);

                } catch (SocketTimeoutException ste) {

                    menuError("TimeOut: El paquete udp se asume perdido.");
                }
                clientSocket.close();
            } catch (UnknownHostException ex) {
                menuError(ex.toString());
            } catch (IOException ex) {
                menuError(ex.toString());
            }
        }
    }
}
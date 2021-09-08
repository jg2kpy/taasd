package py.una.pol.taaprimerparcial.TCP.Server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import py.una.pol.taaprimerparcial.TCP.classes.Mensaje;

public class TCPServerHilo extends Thread {

    private Socket socket = null;

    TCPMultiServer servidor;
    
    public TCPServerHilo(Socket socket, TCPMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine, outputLine;
            Mensaje enviarCliente,recibidoCliente;

            while ((inputLine = in.readLine()) != null) {
                recibidoCliente = new Mensaje(inputLine);    
                enviarCliente = procesarMensaje(recibidoCliente);
                if(enviarCliente == null){
                    break;
                }    
                out.println(enviarCliente.toJSON());
            }
            
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(TCPServerHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Mensaje procesarMensaje(Mensaje datoRecibido) throws ParseException{
        int tipo_operacion = datoRecibido.tipo_operacion;
        String cuerpo = datoRecibido.cuerpo;
        switch (tipo_operacion) {
            case 1:
                //TO DO OPERACION 1
                return new Mensaje(1,"");
            case 2:
                //TO DO OPERACION 2
                return new Mensaje(2,"");
            case 3:
                //TO DO OPERACION 3
                return new Mensaje(3,"");
            default:
                break;
        }
        return null;
    }   
}

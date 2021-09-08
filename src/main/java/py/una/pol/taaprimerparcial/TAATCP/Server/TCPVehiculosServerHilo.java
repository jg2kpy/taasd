package py.una.pol.taaprimerparcial.TAATCP.Server;

import java.net.*;
import java.io.*;
import org.json.simple.parser.ParseException;
import py.una.pol.taaprimerparcial.TAATCP.classes.Mensaje;
import py.una.pol.taaprimerparcial.TAATCP.classes.Persona;

public class TCPVehiculosServerHilo extends Thread {

    private Socket socket = null;

    TCPVehiculosMultiServer servidor;
    
    public TCPVehiculosServerHilo(Socket socket, TCPVehiculosMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            Mensaje recibidoCliente;
            Mensaje enviarCliente = new Mensaje(1,"Comunicacion TCP establecida");            
            out.println(enviarCliente.toJSON());

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
            System.err.println(ex.toString());            
        }
    }
    
    public static Mensaje procesarMensaje(Mensaje datoRecibido) throws ParseException{
        int tipo_operacion = datoRecibido.tipo_operacion;
        String cuerpo = datoRecibido.cuerpo;
        switch (tipo_operacion) {
            case 1:
                Persona persona = new Persona(cuerpo);
                TCPVehiculosMultiServer.db.datos.add(persona);
                return new Mensaje(1,"Operacion completada con exito");
            case 2:
                String cedula = cuerpo;
                String lista = "";
                for(int i=0;i<TCPVehiculosMultiServer.db.datos.size();i++){
                    if(TCPVehiculosMultiServer.db.datos.get(i).cedula.equals(cedula)){
                        lista = lista + TCPVehiculosMultiServer.db.datos.get(i).chapa + ",";
                    }
                }
                return new Mensaje(2,lista);
            default:
                break;
        }
        return null;
    }   
}
package py.una.pol.taaprimerparcial.TAATCP.Server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TCPVehiculosMultiServer {
    
        public static DB db = new DB();

	//variables compartidas
	boolean listening = true;
	List<TCPVehiculosServerHilo> hilosClientes; //almacenar los hilos (no se utiliza en el ejemplo, se deja para que el alumno lo utilice)
	List<String> usuarios; //almacenar una lista de usuarios (no se utiliza, se deja para que el alumno lo utilice)
        
        static int puertoServidor = 6969;

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(puertoServidor);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: "+puertoServidor);
            System.exit(1);
        }
        System.out.println("Puerto abierto: "+6969);

        while (listening) {
        	
            TCPVehiculosServerHilo hilo = new TCPVehiculosServerHilo(serverSocket.accept(), this);
            hilosClientes.add(hilo);
            hilo.start();
        }

        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        
        puertoServidor = 6969;
        if(args.length == 1){
            puertoServidor = Integer.parseInt(args[0]);
        }else if(args.length > 1){
            System.err.println("ERROR en args");
            System.exit(1);
        }
        
        db.datosPrecargados();
    	
    	TCPVehiculosMultiServer tms = new TCPVehiculosMultiServer();
    	
    	tms.hilosClientes = new ArrayList<TCPVehiculosServerHilo>();
    	tms.usuarios = new ArrayList<String>();
    	
    	tms.ejecutar();
    	
    }
}

package py.una.pol.taaprimerparcial.TAAUDP.Server;

import py.una.pol.taaprimerparcial.TAAUDP.classes.Mensaje;
import py.una.pol.taaprimerparcial.TAAUDP.classes.Sensor;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class UDPMeteorologiaServer {
    
    public static DB db = new DB();
   
    public static void main(String[] args){
        int puertoServidor = 6969;
        if(args.length == 1){
            puertoServidor = Integer.parseInt(args[0]);
        }else if(args.length > 1){
            System.err.println("ERROR en args");
            System.exit(1);
        }
        
        db.datosPrecargados();
        
        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
            System.out.println("Servidor Sistemas Distribuidos - UDP");
            
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            //3) Servidor siempre esperando
            while (true) {
                
                receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperando a algun cliente... ");
                
                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);	
		System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");
                
                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido);
                
                
                Mensaje recibidoCliente = new Mensaje(datoRecibido);
                Mensaje enviarCliente = procesarMensaje(recibidoCliente);
                
                
                sendData = enviarCliente.toJSON().getBytes();
                
                // Enviando response
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                serverSocket.send(sendPacket);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public static Mensaje procesarMensaje(Mensaje datoRecibido) throws ParseException{
        int tipo_operacion = datoRecibido.tipo_operacion;
        String cuerpo = datoRecibido.cuerpo;
        switch (tipo_operacion) {
            case 1:
                Sensor nuevoSensor = new Sensor(cuerpo);
                db.sensores.add(nuevoSensor);
                return new Mensaje(1,"Se agrego el sensor correctamente");
            case 2:
                String ciudad = cuerpo;
                for(int i=0;i<db.sensores.size();i++){
                    if(db.sensores.get(i).ciudad.equals(ciudad)){
                        String temperatura = Float.toString(db.sensores.get(i).temperatura);
                        return new Mensaje(2,temperatura);
                    }
                }
                return new Mensaje(2,"No hay temperatura");
            case 3:
                float temperaturaTotal = 0;
                int n = 0;
                for(int i=0;i<db.sensores.size();i++){
                    if(db.sensores.get(i).fecha.equals(cuerpo)){
                        temperaturaTotal = temperaturaTotal + db.sensores.get(i).temperatura;
                        n++;
                    }
                }
                float temperaturaPromedio = temperaturaTotal/n;
                return new Mensaje(3,Float.toString(temperaturaPromedio));
            default:
                break;
        }
        return null;
    }
}
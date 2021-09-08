package py.una.pol.taaprimerparcial.TAAUDP.Client;

import java.util.Scanner;
import py.una.pol.taaprimerparcial.TAAUDP.classes.Mensaje;
import py.una.pol.taaprimerparcial.TAAUDP.classes.Sensor;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class Menu {
    
    public static void menuError(String error){
        System.err.println(error);
    }
    
    public static Mensaje MenuCLI(){
        System.out.println("Menu Principal");
        System.out.println("1 Enviar datos de sensor meteorologico");
        System.out.println("2 Consultar temperatura de una ciudad");
        System.out.println("3 Consultar temperatura promedio de un dia");
        System.out.println("Cualquier otro caracter para salir");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String cuerpo = null;
        
        switch (input) {
            case 1:
                Sensor datos = new Sensor();
                System.out.println("id_estacion: ");
                datos.id_estacion = sc.nextInt();
                System.out.println("Ciudad: ");
                datos.ciudad = sc.next();
                System.out.println("Porcentaje de humedad: ");
                datos.porcentaje_humedad = sc.nextFloat();
                System.out.println("Temperatura: ");
                datos.temperatura = sc.nextFloat();
                System.out.println("Velocidad de viento: ");
                datos.velocidad_viento = sc.nextFloat();
                System.out.println("Fecha en formato AAAA/MM/DD: ");
                datos.fecha = sc.next();
                System.out.println("Hora: ");
                datos.hora = sc.next();
                cuerpo = datos.toJSON();
                break;
            case 2:
                System.out.println("Introduzca la ciudad: ");
                cuerpo = sc.next();
                break;
            case 3:
                System.out.println("Introduzca la fecha: ");
                cuerpo = sc.next();
                break;
            default:
                UDPMeteorologiaClient.salir = true;
                cuerpo = null;
        }
        return new Mensaje(input,cuerpo);
    }
    
    public static void menuRespuesta(Mensaje respuesta){
        System.out.println();
        System.out.println(respuesta.cuerpo);
        System.out.println();
    }

}

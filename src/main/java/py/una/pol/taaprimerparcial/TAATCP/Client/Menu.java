package py.una.pol.taaprimerparcial.TAATCP.Client;

import java.util.Scanner;
import py.una.pol.taaprimerparcial.TAATCP.classes.Mensaje;
import py.una.pol.taaprimerparcial.TAATCP.classes.Persona;

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
        System.out.println("1 - Agregar una persona y un vehiculo");
        System.out.println("2 - Listar vehiculos por cedula");
        System.out.println("Cualquier otro caracter para salir");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String cuerpo = null;
        
        switch (input) {
            case 1:
                Persona datos = new Persona();
                
                System.out.println("Nombre: ");
                datos.nombre = sc.next();
                System.out.println("Apellido: ");
                datos.apellido = sc.next();
                System.out.println("Cedula: ");
                datos.cedula = sc.next();
                System.out.println("Marca: ");
                datos.marca = sc.next();
                System.out.println("Chapa: ");
                datos.chapa = sc.next();
                
                cuerpo = datos.toJSON();
                break;
            case 2:
                System.out.println("Introduzca la cedula: ");
                cuerpo = sc.next();
                break;
            default:
                System.exit(0);
        }
        return new Mensaje(input,cuerpo);
    }
    
    public static void menuRespuesta(Mensaje respuesta){
        System.out.println();
        System.out.println(respuesta.cuerpo);
        System.out.println();
    }

}

package py.una.pol.taaprimerparcial.TCP.Client;

import java.util.Scanner;
import py.una.pol.taaprimerparcial.TCP.classes.Mensaje;

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
        System.out.println("1 - Tipo Operacion 1");
        System.out.println("2 - Tipo Operacion 2");
        System.out.println("3 - Tipo Operacion 3");
        System.out.println("Cualquier otro caracter para salir");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String cuerpo = null;
        
        switch (input) {
            case 1:
                System.out.println("Tipo Operacion 1");
                cuerpo = sc.next();
                break;
            case 2:
                System.out.println("Tipo Operacion 2");
                cuerpo = sc.next();
                break;
            case 3:
                System.out.println("Tipo Operacion 3");
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

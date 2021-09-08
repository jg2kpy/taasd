package py.una.pol.taaprimerparcial.TAATCP.Server;

import java.util.ArrayList;
import py.una.pol.taaprimerparcial.TAATCP.classes.Persona;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class DB {
    public ArrayList<Persona> datos = new ArrayList<>();
    
    public void datosPrecargados(){
        this.datos.add(new Persona("5308115","Junior","Gutierrez","AAAA 000","Tesla"));
        this.datos.add(new Persona("1","Jose","Aguero","AAAA 001","Tesla"));
        this.datos.add(new Persona("2","Luis","Qwerty","AAAA 002","Toyota"));
    }
}
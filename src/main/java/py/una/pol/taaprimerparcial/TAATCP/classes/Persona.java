package py.una.pol.taaprimerparcial.TAATCP.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class Persona {
    public String cedula;
    public String nombre;
    public String apellido;
    public String chapa;
    public String marca;

    public Persona() {
    }
    
    public Persona(String cedula, String nombre, String apellido, String chapa, String marca) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.chapa = chapa;
        this.marca = marca;
    }
    
    public Persona(String JSON) throws ParseException{
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(JSON.trim());
        JSONObject jsonObject = (JSONObject)obj;
        
        this.cedula = (String) jsonObject.get("cedula");
        this.nombre = (String) jsonObject.get("nombre");
        this.apellido = (String) jsonObject.get("apellido");
        this.chapa = (String) jsonObject.get("chapa");
        this.marca = (String) jsonObject.get("marca");
    }
    
    public String toJSON(){
        JSONObject obj = new JSONObject();
        
        obj.put("cedula", this.cedula);
        obj.put("nombre", this.nombre);
        obj.put("apellido", this.apellido);
        obj.put("chapa", this.chapa);
        obj.put("marca", this.marca);

        return obj.toJSONString();
    }
}

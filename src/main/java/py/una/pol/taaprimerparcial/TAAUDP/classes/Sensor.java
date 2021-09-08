package py.una.pol.taaprimerparcial.TAAUDP.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class Sensor {
    public int id_estacion;
    public String ciudad;
    public float porcentaje_humedad;
    public float temperatura;
    public float velocidad_viento;
    public String fecha;
    public String hora;

    public Sensor() {
    }

    public Sensor(int id_estacion, String ciudad, float porcentaje_humedad, float temperatura, float velocidad_viento, String fecha, String hora) {
        this.id_estacion = id_estacion;
        this.ciudad = ciudad;
        this.porcentaje_humedad = porcentaje_humedad;
        this.temperatura = temperatura;
        this.velocidad_viento = velocidad_viento;
        this.fecha = fecha;
        this.hora = fecha;
    }
    
    public Sensor(String JSON) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(JSON.trim());
        JSONObject jsonObject = (JSONObject)obj;
        
        this.id_estacion = (int) (long) jsonObject.get("id_estacion");
        this.ciudad = (String) jsonObject.get("ciudad");
        this.porcentaje_humedad = (float) (double) jsonObject.get("porcentaje_humedad");
        this.temperatura = (float) (double) jsonObject.get("temperatura");
        this.velocidad_viento = (float) (double) jsonObject.get("velocidad_viento");
        this.fecha = (String) jsonObject.get("fecha");
        this.hora = (String) jsonObject.get("hora");
    }
    
    public String toJSON(){
        JSONObject obj = new JSONObject();
        
        obj.put("id_estacion", this.id_estacion);
        obj.put("ciudad", this.ciudad);
        obj.put("porcentaje_humedad", this.porcentaje_humedad);
        obj.put("temperatura", this.temperatura);
        obj.put("velocidad_viento", this.velocidad_viento);
        obj.put("fecha", this.fecha);
        obj.put("hora", this.hora);
        
        return obj.toJSONString();
    }
}
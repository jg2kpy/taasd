package py.una.pol.taaprimerparcial.TAAUDP.Server;

import py.una.pol.taaprimerparcial.TAAUDP.classes.Sensor;
import java.util.ArrayList;

/**
 *
 * @author jg2kpy https://github.com/jg2kpy https://juniorgutierrez.com/
 */
public class DB {
    public ArrayList<Sensor> sensores = new ArrayList<>();
    
    public void datosPrecargados(){
        this.sensores.add(new Sensor(1,"Asuncion",50,20,15,"2021-9-8","10:00"));
        this.sensores.add(new Sensor(2,"Lambare",35,20,16,"2021-9-8","10:00"));
        this.sensores.add(new Sensor(1,"Asuncion",51,20,15,"2021-9-8","10:05"));
        this.sensores.add(new Sensor(3,"San Lorenzo",62,20,14,"2021-9-8","11:00"));
        this.sensores.add(new Sensor(1,"Asuncion",52,20,15,"2021-9-8","10:00"));
    }
}
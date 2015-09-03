package co.edu.uniandes.csw.mpcellphone.persistence;

import co.edu.uniandes.csw.mpcellphone.entities.OrderEntity;
import javax.ejb.Stateless;

/**
 * Clase encargada de contener la comunicacion con la persistencia
 * @author Cindy Hern�ndez - cv.hernandez10
 */
@Stateless
public class OrderPersistence extends CrudPersistence<OrderEntity> {

    /**
     * Constructor de la clase
     */
    public OrderPersistence() {
        this.entityClass = OrderEntity.class;
    }
}

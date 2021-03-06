/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author g.gonzalez10
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.mpcellphone.providers.CreatedFilter.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.providers.EJBExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.CartItemService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.CellPhoneService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.CityService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ClientService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ImeiService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.OrderService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.PaymentMethodService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.PhotoService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ProductService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ProviderService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.QuestionService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.RateService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.SaleService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ShipService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.ShippingTypeService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.StateService.class);
        resources.add(co.edu.uniandes.csw.mpcellphone.services.UserService.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.persistence;

import co.edu.uniandes.csw.mpcellphone.entities.PhotoEntity;
import javax.ejb.Stateless;

/**
 *
 * @author m.amaya11
 */
@Stateless
public class PhotoPersistence extends CrudPersistence<PhotoEntity>{
    public PhotoPersistence(){
        this.entityClass = PhotoEntity.class;
    }
}
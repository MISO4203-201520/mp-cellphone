/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.persistence;

import co.edu.uniandes.csw.mpcellphone.entities.CommentEntity;

/**
 *
 * @author jh.rubiano10
 */
public class CommentPersistence extends CrudPersistence<CommentEntity>{
    public CommentPersistence(){
        this.entityClass = CommentEntity.class;
    }
}
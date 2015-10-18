/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpcellphone.tests;

import co.edu.uniandes.csw.mpcellphone.converters.CityConverter;
import co.edu.uniandes.csw.mpcellphone.dtos.CityDTO;
import co.edu.uniandes.csw.mpcellphone.entities.CityEntity;
import co.edu.uniandes.csw.mpcellphone.persistence.CityPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext; 
import javax.transaction.UserTransaction; 
import org.junit.Assert; 
import org.jboss.arquillian.container.test.api.Deployment; 
import org.jboss.arquillian.junit.Arquillian; 
import org.jboss.shrinkwrap.api.ShrinkWrap; 
import org.jboss.shrinkwrap.api.spec.WebArchive; 
import org.junit.Before; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import uk.co.jemos.podam.api.PodamFactory; 
import uk.co.jemos.podam.api.PodamFactoryImpl; 

/**
 *
 * @author Mauro
 */
@RunWith(Arquillian.class) 
public class CityPersistenceTest {
    /**
     * @generated
     */
    public static final String DEPLOY = "Prueba";
    
    /**
     * @generated
     */
    @Inject 
    UserTransaction utx; 
    
    
    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @generated
     */
    @Inject
    private CityPersistence cityPersistence;
    
    private List<CityEntity> data = new ArrayList(); 
    
    /**
     * @return 
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(CityEntity.class.getPackage())
                .addPackage(CityPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
     /**
     * @generated
     */
    private void clearData() { 
        em.createQuery("delete from CityEntity").executeUpdate(); 
    } 
    
     /**
     * @generated
     */
    private void insertData() { 
        for (int i = 0; i < 3; i++) { 
            PodamFactory factory = new PodamFactoryImpl(); 
            CityEntity entity = CityConverter.basicDTO2Entity(factory.manufacturePojo(CityDTO.class)); 
            em.persist(entity); 
            data.add(entity); 
        } 
    }
    
    /**
     * @generated
     */
    @Test
    public void createCityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CityEntity newEntity = CityConverter.basicDTO2Entity(factory.manufacturePojo(CityDTO.class));

        CityEntity result = cityPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CityEntity entity = em.find(CityEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getLatitude(), entity.getLatitude());
        Assert.assertEquals(newEntity.getLongitude(), entity.getLongitude());
    }
    
      /**
     * @generated
     */
    @Test
    public void getCityTest() {
        CityEntity entity = data.get(0);
        CityEntity newEntity = cityPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getLatitude(), newEntity.getLatitude());
        Assert.assertEquals(entity.getLongitude(), newEntity.getLongitude());
    }
    
    /**
     * @generated
     */
    @Test
    public void deleteCityTest() {
        CityEntity entity = data.get(0);
        cityPersistence.delete(entity.getId());
        CityEntity deleted = em.find(CityEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCityTest() {
        CityEntity entity = data.get(0);

        PodamFactory factory = new PodamFactoryImpl();
        CityEntity newEntity = CityConverter.basicDTO2Entity(factory.manufacturePojo(CityDTO.class));
        newEntity.setId(entity.getId());

        cityPersistence.update(newEntity);

        CityEntity resp = em.find(CityEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    /**
     * @generated
     */
    @Test
    public void getCitiesTest() {
        List<CityEntity> list = cityPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CityEntity ent : list) {
            boolean found = false;
            for (CityEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
}

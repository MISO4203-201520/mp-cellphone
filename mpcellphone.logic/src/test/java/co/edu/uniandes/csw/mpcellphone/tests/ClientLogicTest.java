package co.edu.uniandes.csw.mpcellphone.tests;

import co.edu.uniandes.csw.mpcellphone.ejbs.ClientLogic;
import co.edu.uniandes.csw.mpcellphone.api.IClientLogic;
import co.edu.uniandes.csw.mpcellphone.api.IUserLogic;
import co.edu.uniandes.csw.mpcellphone.converters.ClientConverter;
import co.edu.uniandes.csw.mpcellphone.dtos.ClientDTO;
import co.edu.uniandes.csw.mpcellphone.entities.ClientEntity;
import co.edu.uniandes.csw.mpcellphone.entities.UserEntity;
import co.edu.uniandes.csw.mpcellphone.persistence.ClientPersistence;
import static co.edu.uniandes.csw.mpcellphone.tests._TestUtil.*;
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

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ClientLogicTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(UserEntity.class.getPackage())
                .addPackage(ClientDTO.class.getPackage())
                .addPackage(ClientConverter.class.getPackage())
                .addPackage(ClientLogic.class.getPackage())
                .addPackage(IClientLogic.class.getPackage())
                .addPackage(IUserLogic.class.getPackage())
                .addPackage(ClientPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IClientLogic clientLogic;

    @Inject
    private IUserLogic userLogic;
    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

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
        em.createQuery("delete from ClientEntity").executeUpdate();
        em.createQuery("delete from UserEntity where role='client'").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ClientEntity> data = new ArrayList<ClientEntity>();

    private List<UserEntity> dataU = new ArrayList<UserEntity>();
    /**
     * @generated
     */
    private void insertData() {
        String name = generateRandom(String.class);
        String userId = generateRandom(String.class);
        String email = generateRandom(String.class);
        for (int i = 0; i < 3; i++) {
            ClientEntity entity = new ClientEntity();
        	entity.setName(name);
        	entity.setUserId(userId);
        	entity.setEmail(email);
            em.persist(entity);
            data.add(entity);
            UserEntity entityU = new UserEntity();
        	entityU.setName(name);
        	entityU.setStormpath(userId);
        	entityU.setEmail(email);
            em.persist(entityU);
            dataU.add(entityU);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createClientTest() {
        ClientDTO dto = new ClientDTO();
        dto.setName(generateRandom(String.class));
        dto.setUserId(generateRandom(String.class));
        dto.setEmail(generateRandom(String.class));

        ClientDTO result = clientLogic.createClient(dto);

        Assert.assertNotNull(result);

        ClientEntity entity = em.find(ClientEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
        Assert.assertEquals(dto.getUserId(), entity.getUserId());
        Assert.assertEquals(dto.getEmail(), entity.getEmail());
    }


    @Test
    public void countClientsTest(){
        int size = clientLogic.countClients();
        Assert.assertEquals(data.size(), size);
    }
    /**
     * @generated
     */
    @Test
    public void getClientsTest() {
        List<ClientDTO> list = clientLogic.getClients(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (ClientDTO dto : list) {
            boolean found = false;
            for (ClientEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getClientTest() {
        ClientEntity entity = data.get(0);
        ClientDTO dto = clientLogic.getClient(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getUserId(), dto.getUserId());
        Assert.assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    public void getClientByUserIdTest() {
        ClientEntity entity = data.get(0);
        ClientDTO dto = clientLogic.getClientByUserId(entity.getUserId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getUserId(), dto.getUserId());
        Assert.assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    public void getClientByEmailTest() {
        ClientEntity entity = data.get(0);
        ClientDTO dto = clientLogic.getClientByEmail(entity.getEmail());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getUserId(), dto.getUserId());
        Assert.assertEquals(entity.getEmail(), dto.getEmail());
    }
    /**
     * @generated
     */
    @Test
    public void deleteClientTest() {
        ClientEntity entity = data.get(0);
        clientLogic.deleteClient(entity.getId());
        ClientEntity deleted = em.find(ClientEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateClientTest() {
        String name = generateRandom(String.class);
        String email = generateRandom(String.class);
        
        ClientEntity entity = data.get(0);
        
        ClientDTO dto = clientLogic.getClient(entity.getId());
        dto.setName(name);
        dto.setEmail(email);
        
        ClientDTO updDto = clientLogic.updateClient(dto);
        
        Assert.assertNotNull(updDto);

        Assert.assertEquals(updDto.getName(), name);
        Assert.assertEquals(updDto.getEmail(), email);
    }
    

    /**
     * @generated
     */
    @Test
    public void getClientPaginationTest() {
        //Page 1
        List<ClientDTO> dto1 = clientLogic.getClients(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<ClientDTO> dto2 = clientLogic.getClients(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (ClientDTO dto : dto1) {
            boolean found = false;
            for (ClientEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (ClientDTO dto : dto2) {
            boolean found = false;
            for (ClientEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void findByName() {
        String name = data.get(0).getName();
        List<ClientEntity> cache = new ArrayList<ClientEntity>();
        List<ClientDTO> list = clientLogic.findByName(name);

        for (ClientEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(cache.size(), list.size());
        for (ClientDTO dto : list) {
            boolean found = false;
            for (ClientEntity cacheEntity : cache) {
                if (cacheEntity.getName().equals(dto.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail();
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.test.logic;

import co.edu.uniandes.csw.bookstore.ejb.CompraLogic;
import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.CompraPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Test de lógica
 * @author puro-lovets
 */
@RunWith(Arquillian.class)
public class CompraLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CompraLogic compraLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CompraEntity> data = new ArrayList<>();

    private List<UsuarioEntity> usuarioData = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraLogic.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CompraEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            usuarioData.add(usuario);
        }
        for (int i = 0; i < 3; i++) {
            CompraEntity entity = factory.manufacturePojo(CompraEntity.class);
            entity.setUsuario(usuarioData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCompraTest() throws BusinessLogicException {
        CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);
        newEntity.setUsuario(usuarioData.get(0));
        CompraEntity result = compraLogic.createCompra(newEntity);
        Assert.assertNotNull(result);
        CompraEntity entity = em.find(CompraEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getEstado(), entity.getEstado());;
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCompraTestConNullUsuario() throws BusinessLogicException {
        CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);
        newEntity.setUsuario(null);
        compraLogic.createCompra(newEntity);
    }
    
    @Test
    public void getComprasTest() {
        List<CompraEntity> list = compraLogic.getCompras();
        Assert.assertEquals(data.size(), list.size());
        for (CompraEntity entity : list) {
            boolean found = false;
            for (CompraEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCompraTest() {
        CompraEntity entity = data.get(0);
        CompraEntity resultEntity = compraLogic.getCompra(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
    }
    
    @Test
    public void updateBookTest() throws BusinessLogicException {
        CompraEntity entity = data.get(0);
        CompraEntity pojoEntity = factory.manufacturePojo(CompraEntity.class);
        pojoEntity.setId(entity.getId());
        compraLogic.updateCompra(pojoEntity.getId(), pojoEntity);
        CompraEntity resp = em.find(CompraEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado());
    }
}

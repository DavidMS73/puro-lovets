/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.ItemCarritoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Estudiante
 */
@Stateless
public class ItemCarritoPersistence {
    private static final Logger LOGGER = Logger.getLogger(ReviewPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;
    
    public ItemCarritoEntity create(ItemCarritoEntity itemCarritoEntity) {
        em.persist(itemCarritoEntity);
        return itemCarritoEntity;
    }
    
    public List<ItemCarritoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las compras");
        Query q = em.createQuery("select u from CompraEntity u");
        return q.getResultList();
    }
    
    public ItemCarritoEntity find(Long booksId) {
        LOGGER.log(Level.INFO, "Consultando la compra con id={0}", booksId);
        return em.find(ItemCarritoEntity.class, booksId);
    }
}

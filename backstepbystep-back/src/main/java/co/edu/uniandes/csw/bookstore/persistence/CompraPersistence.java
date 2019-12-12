/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Clase que maneja la persistencia para compra
 * @author puro-lovets
 */
@Stateless
public class CompraPersistence {
    private static final Logger LOGGER = Logger.getLogger(ReviewPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;
    
    /**
     * Crear una compra
     *
     * Crea una nueva compra con la información recibida en la entidad.
     *
     * @param compraEntity La entidad que representa la nueva compra
     * @return La entidad creada
     */
    public CompraEntity create(CompraEntity compraEntity) {
        LOGGER.log(Level.INFO, "Creando una compra nueva");
        em.persist(compraEntity);
        LOGGER.log(Level.INFO, "Compra creado");
        return compraEntity;
    }
    
    /**
     * Actualizar una compra
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param compraEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la actualización
     */
    public CompraEntity update(CompraEntity compraEntity) {
        LOGGER.log(Level.INFO, "Actualizando compra con id = {0}", compraEntity.getId());
        return em.merge(compraEntity);
    }
    
    public List<CompraEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las compras");
        Query q = em.createQuery("select u from CompraEntity u");
        return q.getResultList();
    }
    
    public CompraEntity find(Long booksId) {
        LOGGER.log(Level.INFO, "Consultando la compra con id={0}", booksId);
        return em.find(CompraEntity.class, booksId);
    }
}

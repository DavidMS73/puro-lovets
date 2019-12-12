/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.CompraPersistence;
import co.edu.uniandes.csw.bookstore.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexión con la persistencia para lal entidad de Compra
 * @author puro-lovets
 */
@Stateless
public class CompraLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BookLogic.class.getName());

    @Inject
    private CompraPersistence persistence;
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    public CompraEntity createCompra(CompraEntity compraEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la compra");
        if (compraEntity.getUsuario() == null || usuarioPersistence.find(compraEntity.getUsuario().getId()) == null) {
            throw new BusinessLogicException("La editorial es inválida");
        }
        if (compraEntity.getEstado()== null) {
            throw new BusinessLogicException("La compra es inválida");
        }
        persistence.create(compraEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la compra");
        return compraEntity;
    }
    
    public List<CompraEntity> getCompras() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las compras");
        List<CompraEntity> compras = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las compras");
        return compras;
    }
    
    public CompraEntity getCompra(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la compra con id = {0}", booksId);
        CompraEntity compraEntity = persistence.find(booksId);
        if (compraEntity == null) {
            LOGGER.log(Level.SEVERE, "La compra con el id = {0} no existe", booksId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la compra con id = {0}", booksId);
        return compraEntity;
    }
    
    public CompraEntity updateCompra(Long comprasId, CompraEntity compraEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la compra con id = {0}", comprasId);
        CompraEntity newEntity = persistence.update(compraEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la compra con id = {0}", compraEntity.getId());
        return newEntity;
    }
}

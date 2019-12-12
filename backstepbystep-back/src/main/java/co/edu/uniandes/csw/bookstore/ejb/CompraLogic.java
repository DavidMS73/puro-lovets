/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.CompraPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexión con la persistencia para lal entidad de Compra
 * @author puro-lovets
 */
public class CompraLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BookLogic.class.getName());

    @Inject
    private CompraPersistence persistence;
    
    public CompraEntity createBook(CompraEntity compraEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la compra");
        if (compraEntity.getEstado()== null) {
            throw new BusinessLogicException("La compra es inválida");
        }
        persistence.create(compraEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la compra");
        return compraEntity;
    }
}

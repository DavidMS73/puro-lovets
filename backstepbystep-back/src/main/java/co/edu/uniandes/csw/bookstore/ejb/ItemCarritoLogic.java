/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.bookstore.persistence.ItemCarritoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Estudiante
 */
@Stateless
public class ItemCarritoLogic {

    @Inject
    private ItemCarritoPersistence persistence;

    @Inject
    private BookPersistence bookPersistence;

    public ItemCarritoEntity createItem(ItemCarritoEntity compraEntity) throws BusinessLogicException {
        if (compraEntity.getBook() == null || bookPersistence.find(compraEntity.getBook().getId()) == null) {
            throw new BusinessLogicException("El libro es inválido");
        }
        if (compraEntity.getCantidad() == null) {
            throw new BusinessLogicException("La cantidad es inválida");
        }
        persistence.create(compraEntity);
        return compraEntity;
    }

    public List<ItemCarritoEntity> getItems() {
        List<ItemCarritoEntity> compras = persistence.findAll();
        return compras;
    }

    public ItemCarritoEntity getItem(Long booksId) {
        ItemCarritoEntity compraEntity = persistence.find(booksId);
        return compraEntity;
    }
}

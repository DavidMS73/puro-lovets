/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.ItemCarritoEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class ItemCarritoDTO implements Serializable {

    private BookDTO book;
    private Integer cantidad;
    private Long id;

    public ItemCarritoDTO() {
    }

    public ItemCarritoDTO(ItemCarritoEntity compraEntity) {
        if (compraEntity != null) {
            this.id = compraEntity.getId();
            this.cantidad = compraEntity.getCantidad();
            if (compraEntity.getBook() != null) {
                this.book = new BookDTO(compraEntity.getBook());
            } else {
                this.book = null;
            }
        }
    }

    public ItemCarritoEntity toEntity() {
        ItemCarritoEntity itemCarritoEntity = new ItemCarritoEntity();
        itemCarritoEntity.setId(this.getId());
        itemCarritoEntity.setCantidad(this.getCantidad());
        if (this.getBook() != null) {
            itemCarritoEntity.setBook(this.getBook().toEntity());
        }
        return itemCarritoEntity;
    }

    /**
     * @return the book
     */
    public BookDTO getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(BookDTO book) {
        this.book = book;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Estudiante
 */
@Entity
public class ItemCarritoEntity extends BaseEntity implements Serializable {

    private Integer cantidad;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BookEntity book;

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
     * @return the book
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }
}

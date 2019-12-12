/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Estudiante
 */
@Entity
public class ItemCarritoEntity extends BaseEntity implements Serializable {

    private Integer cantidad;

    @PodamExclude
    @OneToOne
    private OrganizationEntity book;

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
    public OrganizationEntity getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(OrganizationEntity book) {
        this.book = book;
    }
}

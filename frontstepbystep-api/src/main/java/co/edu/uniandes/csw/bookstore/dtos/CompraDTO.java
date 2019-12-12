/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import java.io.Serializable;

/**
 *
 * @author puro-lovets
 */
public class CompraDTO implements Serializable {

    private UsuarioDTO usuario;
    private Integer estado;
    private Long id;

    /**
     * Constructor por defecto
     */
    public CompraDTO() {
    }

    public CompraDTO(CompraEntity compraEntity) {
        if (compraEntity != null) {
            this.id = compraEntity.getId();
            this.estado = compraEntity.getEstado();
            if (compraEntity.getUsuario() != null) {
                this.usuario = new UsuarioDTO(compraEntity.getUsuario());
            } else {
                this.usuario = null;
            }
        }
    }

    public CompraEntity toEntity() {
        CompraEntity bookEntity = new CompraEntity();
        bookEntity.setId(this.getId());
        bookEntity.setEstado(this.getEstado());
        if (this.getUsuario() != null) {
            bookEntity.setUsuario(this.getUsuario().toEntity());
        }
        return bookEntity;
    }

    /**
     * @return the usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Deatalle de UsuarioDTO
 *
 * @author puro-lovets
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {

    private List<CompraDTO> compras;

    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO() {
    }

    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
        if (usuarioEntity != null) {
            if (usuarioEntity.getCompras() != null) {
                compras = new ArrayList<>();
                for (CompraEntity entityCompra : usuarioEntity.getCompras()) {
                    compras.add(new CompraDTO(entityCompra));
                }
            }
        }
    }

    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if (getCompras() != null) {
            List<CompraEntity> booksEntity = new ArrayList<>();
            for (CompraDTO dtoCompra : getCompras()) {
                booksEntity.add(dtoCompra.toEntity());
            }
            usuarioEntity.setCompras(booksEntity);
        }
        return usuarioEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the compras
     */
    public List<CompraDTO> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<CompraDTO> compras) {
        this.compras = compras;
    }
}

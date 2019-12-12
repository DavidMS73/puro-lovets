/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Deatalle de UsuarioDTO
 * @author puro-lovets
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO() {
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
    }
    
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        return usuarioEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

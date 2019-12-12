/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.CompraDTO;
import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
class CompraDetailDTO extends CompraDTO implements Serializable{

    public CompraDetailDTO() {
        super();
    }
    
    public CompraDetailDTO(CompraEntity compraEntity) {
        super(compraEntity);
    }
}

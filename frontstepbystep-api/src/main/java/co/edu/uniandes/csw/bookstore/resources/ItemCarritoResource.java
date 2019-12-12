/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.ItemCarritoDTO;
import co.edu.uniandes.csw.bookstore.ejb.ItemCarritoLogic;
import co.edu.uniandes.csw.bookstore.entities.ItemCarritoEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Estudiante
 */
public class ItemCarritoResource {
    
    @Inject
    private ItemCarritoLogic itemCarritoLogic;
    
    @POST
    public ItemCarritoDTO createItemCarrito(ItemCarritoDTO compra) throws BusinessLogicException {
        ItemCarritoDTO nuevoBookDTO = new ItemCarritoDTO(itemCarritoLogic.createItem(compra.toEntity()));
        return nuevoBookDTO;
    }

    @GET
    public List<ItemCarritoDTO> getItemsCarrito() {
        List<ItemCarritoDTO> listaCompras = listEntity2DetailDTO(itemCarritoLogic.getItems());
        return listaCompras;
    }

    @GET
    @Path("{itemsId: \\d+}")
    public ItemCarritoDTO getItemCarrito(@PathParam("itemsId") Long comprasId) {
        ItemCarritoEntity compraEntity = itemCarritoLogic.getItem(comprasId);
        if (compraEntity == null) {
            throw new WebApplicationException("El recurso /items/" + comprasId + " no existe.", 404);
        }
        ItemCarritoDTO bookDetailDTO = new ItemCarritoDTO(compraEntity);
        return bookDetailDTO;
    }
    
    private List<ItemCarritoDTO> listEntity2DetailDTO(List<ItemCarritoEntity> entityList) {
        List<ItemCarritoDTO> list = new ArrayList<>();
        for (ItemCarritoEntity entity : entityList) {
            list.add(new ItemCarritoDTO(entity));
        }
        return list;
    }
}

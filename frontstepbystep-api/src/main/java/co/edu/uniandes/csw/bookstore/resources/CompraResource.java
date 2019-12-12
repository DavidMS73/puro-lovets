/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.CompraDTO;
import co.edu.uniandes.csw.bookstore.ejb.CompraLogic;
import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Estudiante
 */
@Path("compras")
@Produces("application/json")
@Consumes("application/json")
public class CompraResource {

    private static final Logger LOGGER = Logger.getLogger(BookResource.class.getName());

    @Inject
    private CompraLogic compraLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public CompraDTO createCompra(CompraDTO compra) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompraResource createCompra: input: {0}", compra);
        CompraDTO nuevoBookDTO = new CompraDTO(compraLogic.createCompra(compra.toEntity()));
        LOGGER.log(Level.INFO, "CompraResource createCompra: output: {0}", nuevoBookDTO);
        return nuevoBookDTO;
    }

    @GET
    public List<CompraDetailDTO> getCompras() {
        LOGGER.info("CompraResource getCompras: input: void");
        List<CompraDetailDTO> listaCompras = listEntity2DetailDTO(compraLogic.getCompras());
        LOGGER.log(Level.INFO, "CompraResource getCompras: output: {0}", listaCompras);
        return listaCompras;
    }

    @GET
    @Path("{comprasId: \\d+}")
    public CompraDetailDTO getCompra(@PathParam("comprasId") Long comprasId) {
        LOGGER.log(Level.INFO, "CompraResource getCompra: input: {0}", comprasId);
        CompraEntity compraEntity = compraLogic.getCompra(comprasId);
        if (compraEntity == null) {
            throw new WebApplicationException("El recurso /compras/" + comprasId + " no existe.", 404);
        }
        CompraDetailDTO bookDetailDTO = new CompraDetailDTO(compraEntity);
        LOGGER.log(Level.INFO, "CompraResource getCompra: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }

    @PUT
    @Path("{comprasId: \\d+}")
    public CompraDetailDTO updateCompra(@PathParam("booksId") Long booksId, CompraDetailDTO book) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompraResource updateCompra: input: id: {0} , compra: {1}", new Object[]{booksId, book});
        book.setId(booksId);
        if (compraLogic.getCompra(booksId) == null) {
            throw new WebApplicationException("El recurso /compras/" + booksId + " no existe.", 404);
        }
        CompraDetailDTO detailDTO = new CompraDetailDTO(compraLogic.updateCompra(booksId, book.toEntity()));
        LOGGER.log(Level.INFO, "CompraResource updateCompra: output: {0}", detailDTO);
        return detailDTO;
    }

    private List<CompraDetailDTO> listEntity2DetailDTO(List<CompraEntity> entityList) {
        List<CompraDetailDTO> list = new ArrayList<>();
        for (CompraEntity entity : entityList) {
            list.add(new CompraDetailDTO(entity));
        }
        return list;
    }
}

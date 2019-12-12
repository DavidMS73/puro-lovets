/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.CompraDTO;
import co.edu.uniandes.csw.bookstore.ejb.CompraLogic;
import co.edu.uniandes.csw.bookstore.ejb.UsuarioComprasLogic;
import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "editorial/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioComprasResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioComprasResource.class.getName());

    @Inject
    private UsuarioComprasLogic usuarioComprasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CompraLogic compraLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    @Path("{booksId: \\d+}")
    public CompraDTO addCompra(@PathParam("editorialsId") Long editorialsId, @PathParam("booksId") Long booksId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: editorialsID: {0} , booksId: {1}", new Object[]{editorialsId, booksId});
        if (compraLogic.getCompra(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + " no existe.", 404);
        }
        CompraDTO compraDTO = new CompraDTO(usuarioComprasLogic.addCompra(booksId, editorialsId));
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: output: {0}", compraDTO);
        return compraDTO;
    }

    @GET
    public List<CompraDetailDTO> getCompras(@PathParam("editorialsId") Long editorialsId) {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: input: {0}", editorialsId);
        List<CompraDetailDTO> listaDetailDTOs = comprasListEntity2DTO(usuarioComprasLogic.getCompras(editorialsId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    @GET
    @Path("{booksId: \\d+}")
    public CompraDetailDTO getCompra(@PathParam("editorialsId") Long editorialsId, @PathParam("booksId") Long booksId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{editorialsId, booksId});
        if (compraLogic.getCompra(booksId) == null) {
            throw new WebApplicationException("El recurso /editorials/" + editorialsId + "/books/" + booksId + " no existe.", 404);
        }
        CompraDetailDTO bookDetailDTO = new CompraDetailDTO(usuarioComprasLogic.getCompra(editorialsId, booksId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: output: {0}", bookDetailDTO);
        return bookDetailDTO;
    }

    @PUT
    public List<CompraDetailDTO> replaceBooks(@PathParam("editorialsId") Long editorialsId, List<CompraDetailDTO> books) {
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: input: editorialsId: {0} , books: {1}", new Object[]{editorialsId, books});
        for (CompraDetailDTO book : books) {
            if (compraLogic.getCompra(book.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + book.getId() + " no existe.", 404);
            }
        }
        List<CompraDetailDTO> listaDetailDTOs = comprasListEntity2DTO(usuarioComprasLogic.replaceBooks(editorialsId, comprasListDTO2Entity(books)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    private List<CompraDetailDTO> comprasListEntity2DTO(List<CompraEntity> entityList) {
        List<CompraDetailDTO> list = new ArrayList();
        for (CompraEntity entity : entityList) {
            list.add(new CompraDetailDTO(entity));
        }
        return list;
    }

    private List<CompraEntity> comprasListDTO2Entity(List<CompraDetailDTO> dtos) {
        List<CompraEntity> list = new ArrayList<>();
        for (CompraDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

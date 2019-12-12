/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.resources;

import co.edu.uniandes.csw.bookstore.dtos.UsuarioDTO;
import co.edu.uniandes.csw.bookstore.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.bookstore.ejb.UsuarioLogic;
import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Recuso de la clase Usuario
 *
 * @author puro-lovets
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    @Inject
    private UsuarioLogic uLogic;

    /**
     * Parte del mensaje
     */
    private String msg1 = "El recurso /usuarios/";

    /**
     * Parte del mensaje
     */
    private String msg2 = " no existe.";

    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource crearUsuario: input: {0}", usuario);
        UsuarioEntity usuarioEntity = usuario.toEntity();
        usuarioEntity = uLogic.createUsuario(usuarioEntity);
        UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource crearUsuario: output: {0}", nuevoUsuarioDTO);
        return nuevoUsuarioDTO;
    }

    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        LOGGER.info("UsuarioResource getUsuarios: input: void");
        List<UsuarioDetailDTO> listaUsuarios = listEntity2DTO(uLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: output: {0}", listaUsuarios);
        return listaUsuarios;
    }

    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input: {0}", usuariosId);
        UsuarioEntity usuarioEntity = uLogic.getUsuario(usuariosId);
        if (usuarioEntity == null) {
            throw new WebApplicationException(msg1 + usuariosId + msg2, 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @GET
    @Path("username/{username: [a-zA-Z][a-zA-Z]*}")
    public UsuarioDetailDTO getUsuarioUsername(@PathParam("username") String username) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarioUsername: input: {0}", username);
        UsuarioEntity usuarioEntity = uLogic.getUsuarioUsername(username);
        if (usuarioEntity == null) {
            throw new WebApplicationException(msg1 + username + msg2, 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarioUsername: output: {0}", detailDTO);
        return detailDTO;
    }

    @Path("{usuariosId: \\d+}/compras")
    public Class<UsuarioComprasResource> getUsuarioComprasResource(@PathParam("usuariosId") Long usuariosId) {
        if (uLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        return UsuarioComprasResource.class;
    }

    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}

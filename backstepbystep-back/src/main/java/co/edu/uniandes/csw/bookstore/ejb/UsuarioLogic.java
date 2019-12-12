/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexión con la persistencia para la entidad de Usuario
 * @author puro-lovets
 */
public class UsuarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un usuario");
        if (usuario.getNombre() == null) {
            throw new BusinessLogicException("El nombre del usuario esta vacío");
        }
        if (usuario.getContrasena() == null) {
            throw new BusinessLogicException("La contraseña del usuario esta vacía");
        }
        if (usuario.getUsername() == null) {
            throw new BusinessLogicException("El username del usuario esta vacía");
        }
        if(persistence.findByUsername(usuario.getUsername()) != null){
            throw new BusinessLogicException("El nombre de usuario ya existe");
        }
        usuario = persistence.create(usuario);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return usuario;
    }

    public UsuarioEntity getUsuario(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = persistence.find(usuariosId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.INFO, "El usuario con el id = {0} no existe", usuariosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", usuariosId);
        return usuarioEntity;
    }
    
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> usuarios = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los usuarios");
        return usuarios;
    }
    
    public UsuarioEntity getUsuarioUsername(String username){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con username = {0}", username);
        UsuarioEntity usuarioEntity = persistence.findByUsername(username);
        if(usuarioEntity == null){
            LOGGER.log(Level.INFO, "El usuario con el username = {0} no existe", username);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con el username = {0}", username);
        return usuarioEntity;
    }
}
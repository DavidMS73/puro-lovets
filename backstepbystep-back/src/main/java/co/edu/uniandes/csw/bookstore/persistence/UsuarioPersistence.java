/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Usuario. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author puro-lovets
 */
@Stateless
public class UsuarioPersistence {
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;
    
    /**
     * Crear un usuario
     *
     * Crea un nuevo usuario con la información recibida en la entidad.
     *
     * @param usuarioEntity La entidad que representa el nuevo usuario
     * @return La entidad creada
     */
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Creando un usuario nuevo");
        em.persist(usuarioEntity);
        LOGGER.log(Level.INFO, "Usuaio creado");
        return usuarioEntity;
    }
    
    /**
     * Busca si hay algún usuario con el id que se envía de argumento
     *
     * @param usuariosID: id correspondiente al usuario buscado.
     * @return un usuario.
     */
    public UsuarioEntity find(Long usuariosID) {
        LOGGER.log(Level.INFO, "Consultando usuario con id={0}", usuariosID);
        return em.find(UsuarioEntity.class, usuariosID);
    }
    
    public List<UsuarioEntity> findAll() {
        Query query = em.createQuery("select u from UsuarioEntity u");
        return query.getResultList();
    }
    
    public UsuarioEntity findByUsername(String name) {
        TypedQuery query = em.createQuery("select u from UsuarioEntity u where u.username = :name", UsuarioEntity.class);
        query = query.setParameter("name", name);
        List<UsuarioEntity> usuarios = query.getResultList();
        UsuarioEntity usuario;
        if (usuarios == null) {
            usuario = null;
        } else if (usuarios.isEmpty()) {
            usuario = null;
        } else {
            usuario = usuarios.get(0);
        }
        return usuario;
    }
}
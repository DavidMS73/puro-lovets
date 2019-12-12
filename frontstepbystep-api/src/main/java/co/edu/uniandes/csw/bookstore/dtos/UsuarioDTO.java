/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import java.io.Serializable;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuarios
 * @author puro-lovets
 */
public class UsuarioDTO implements Serializable {
    /**
     * Atributo que modela el nombre del usuario
     */
    private String nombre;
    
    /**
     * Atributo que modela la contraseña de un usuario
     */
    private String contrasena;
    
    /**
     * Atributo que modela el username de un usuario
     */
    private String username;
    
    /**
     * Atributo que modela el id del usuario
     */
    private Long id;
    
    /**
     * Constructor con parámetros
     * @param usuario Usuario
     */
    public UsuarioDTO(UsuarioEntity usuario) {
        if (usuario != null) {
            setId(usuario.getId());
            setNombre(usuario.getNombre());
            setContrasena(usuario.getContrasena());
            setUsername(usuario.getUsername());
        }
    }
    
    /**
     * Constructor vacio
     */
    public UsuarioDTO() {
        //Constructor
    }
    
    public UsuarioEntity toEntity() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(this.getId());
        usuario.setContrasena(this.getContrasena());
        usuario.setNombre(this.getNombre());
        usuario.setUsername(this.getUsername());
        return usuario;
    }
    
    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Entidad usuario
 * @author puro-lovets
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    /**
     * Atributo que modela el nombre del usuario
     */
    private String nombre;
    
    /**
     * Atributo que modela la contraseña del usuario
     */
    private String contrasena;
    
    /**
     * Atributo que modela el nick del usuario
     */
    private String username;

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
}

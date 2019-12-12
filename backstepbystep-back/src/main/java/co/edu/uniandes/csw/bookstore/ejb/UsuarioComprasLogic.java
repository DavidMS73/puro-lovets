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
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.CompraEntity;
import co.edu.uniandes.csw.bookstore.entities.UsuarioEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.CompraPersistence;
import co.edu.uniandes.csw.bookstore.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Editorial y Book.
 *
 * @author ISIS2603
 */
@Stateless
public class UsuarioComprasLogic {

    @Inject
    private CompraPersistence compraPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un book a la editorial
     *
     * @param booksId El id libro a guardar
     * @param editorialsId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public CompraEntity addCompra(Long booksId, Long editorialsId) {
        UsuarioEntity usuarioEntity = usuarioPersistence.find(editorialsId);
        CompraEntity compraEntity = compraPersistence.find(booksId);
        compraEntity.setUsuario(usuarioEntity);
        return compraEntity;
    }

    /**
     * Retorna todos los books asociados a una editorial
     *
     * @param editorialsId El ID de la editorial buscada
     * @return La lista de libros de la editorial
     */
    public List<CompraEntity> getCompras(Long editorialsId) {
        return usuarioPersistence.find(editorialsId).getCompras();
    }

    /**
     * Retorna un book asociado a una editorial
     *
     * @param editorialsId El id de la editorial a buscar.
     * @param booksId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * editorial
     */
    public CompraEntity getCompra(Long editorialsId, Long booksId) throws BusinessLogicException {
        List<CompraEntity> books = usuarioPersistence.find(editorialsId).getCompras();
        CompraEntity bookEntity = compraPersistence.find(booksId);
        int index = books.indexOf(bookEntity);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("La compra no está asociada al usuario");
    }

    /**
     * Remplazar books de una editorial
     *
     * @param books Lista de libros que serán los de la editorial.
     * @param editorialsId El id de la editorial que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<CompraEntity> replaceBooks(Long editorialsId, List<CompraEntity> books) {
        UsuarioEntity editorialEntity = usuarioPersistence.find(editorialsId);
        List<CompraEntity> bookList = compraPersistence.findAll();
        for (CompraEntity book : bookList) {
            if (books.contains(book)) {
                book.setUsuario(editorialEntity);
            } else if (book.getUsuario() != null && book.getUsuario().equals(editorialEntity)) {
                book.setUsuario(null);
            }
        }
        return books;
    }
}

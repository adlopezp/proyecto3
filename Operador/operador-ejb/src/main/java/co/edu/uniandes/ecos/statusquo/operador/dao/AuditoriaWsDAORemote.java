/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import java.util.List;
import javax.ejb.Remote;
import javax.persistence.EntityManager;

/**
 *
 * @author Dev
 */
@Remote
public interface AuditoriaWsDAORemote<T> {

    void actualizar(T entity);

    void borrar(T entity);

    T buscar(final Long id);

    T buscarNamedQuery(final String queryName);

    T buscarNamedQuery(final String queryName, final List<Object> params);

    List<T> consultar();

    List<T> consultarNamedQuery(final String queryName);

    List<T> consultarNamedQuery(final String queryName, final List<Object> params);

    List<T> consultarNamedQuery(final String queryName, final Integer primerRegistro, final Integer maxResultados);

    List<T> consultarNamedQuery(final String queryName, final Integer primerRegistro, final Integer maxResultados, final List<?> params);

    void desconectar(T entity);

    EntityManager getEntityManager();

    void insertar(T entity);
    
    T insertarReturn(T entity);

    void refrescar(T entity);
    
}

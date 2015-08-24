package co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;



/**
 * Clase AbstractFacade.java
 *
 * @author Dev
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    /**
     * mapa de parametros a reemplazar en la consulta
     */
    protected HashMap<String, Object> parameters;
    /**
     * variable que contiene el texto de la consulta
     */
    protected String consulta;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        parameters = new HashMap<String, Object>();
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }

    /**
     * Método que remueve una entidad de la base de datos. Se conserva por
     * compatibilidad del Módulo de Seguridad de Expert Group
     *
     * @param entity
     */
    public void remove(T entity, Object primaryKey) throws Exception {
        Object entityRemove = getEntityManager().find(entity.getClass(), primaryKey);
        if (entityRemove != null) {
            getEntityManager().remove(entityRemove);
        } else {
            throw new Exception("Entidad a ser eliminada no ha sido encontrada");
        }
        getEntityManager().flush();
    }

    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Método que utiliza la consulta y mapa de parametros establecidos para
     * realizar una consulta, conservado por compatibilidad del módulo de
     * seguridad de Expert Group
     *
     * @return
     */
    public List<T> find() {
        Query query = getEntityManager().createQuery(consulta);
        for (String parameterName : parameters.keySet()) {
            query.setParameter(parameterName, parameters.get(parameterName));
        }

        parameters = new HashMap<String, Object>();

        return query.getResultList();
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados basados en NamedQueries y con
     * parametros
     *
     * @param queryName NamedQuery
     * @param params Hashtable de parámetros del Query
     * @return Lista de resultados
     */
    public List<T> findByNamedQuery(String queryName, Hashtable<String, Object> params) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);

        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }

        return q.getResultList();

    }

    public List<T> findSingleByNamedQuery(String queryName, Hashtable<String, Object> params) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);

        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }
        q.setMaxResults(1);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return new ArrayList<T>();
        }
    }

    public T findSingleObjectByNamedQuery(String queryName, Hashtable<String, Object> params) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);

        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }
        return (T)q.getSingleResult();
    }

    /**
     * Método que permite buscar resultados basados en NamedQueries sin
     * parametros
     *
     * @param queryName NamedQuery
     * @return Lista de resultados
     */
    public List<T> findByNamedQuery(String queryName) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);

        return q.getResultList();

    }

    /**
     * Método que permite buscar resultados basados en NamedQueries no identado
     * con parametros
     *
     * @param queryName NamedQuery
     * @param params Hashtable de parámetros
     * @return una lista de resultados <p>List<Object></p>
     */
    public List findByNamedQueryNonIdenty(String queryName, Hashtable<String, Object> params, int maxRegistros) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);
        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }
        if (maxRegistros > 0) {
            q.setMaxResults(maxRegistros);
        }
        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados basados en JPQLQueries tipados con
     * parámetros
     *
     * @param jpqlQuery Query en JPQL
     * @param clase Class del tipo de retorno esperado en el Query o null
     * @param params Hashtable de parámetros
     * @return una lista tipada de resultados, si el tipo provisto en el método
     * es null retorna <p>List<Object></p>
     */
    public List<T> findByJPQLQuery(String jpqlQuery, Class<T> clase, Hashtable<String, Object> params) {
        TypedQuery<T> q = getEntityManager().createQuery(jpqlQuery, entityClass);

        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }

        return q.getResultList();
    }

    /**
     * * Método que permite buscar resultados basados en JPQLQueries con
     * parámetros
     *
     * @param jpqlQuery Query en JPQL
     * @param params Hashtable de parámetros
     * @return una lista de resultados <p>List<Object></p>
     */
    public List findByJPQLQuery(String jpqlQuery, Hashtable<String, Object> params) {
        javax.persistence.Query q = getEntityManager().createQuery(jpqlQuery);

        Enumeration<String> enu = params.keys();

        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            q.setParameter(key, params.get(key));
        }

        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados basados en JPQLQueries tipados
     *
     * @param jpqlQuery Query en JPQL
     * @param clase Class del tipo de retorno esperado en el Query o null
     * @return una lista tipada de resultados, si el tipo provisto en el método
     * es null retorna <p>List<Object></p>
     */
    public List<T> findByJPQLQuery(String jpqlQuery, Class<T> clase) {
        TypedQuery<T> q = getEntityManager().createQuery(jpqlQuery, entityClass);

        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados basados en JPQLQueries
     *
     * @param jpqlQuery Query en JPQL
     * @return una lista de resultados <p>List<Object></p>
     */
    public List findByJPQLQuery(String jpqlQuery) {
        javax.persistence.Query q = getEntityManager().createQuery(jpqlQuery);

        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados basados en Queries nativos (SQL)
     *
     * @param nquery Query nativo (Sentencia SQL)
     * @return Una lista de objetos con el resultado
     */
    public List findListByNativeQuery(String nquery) {
        javax.persistence.Query q = getEntityManager().createNativeQuery(nquery);
        return q.getResultList();
    }

    /**
     * Método que permite buscar resultados de un solo registro basados en
     * Queries nativos (SQL)
     *
     * @param nquery Query nativo (Sentencia SQL)
     * @return Un objeto con el resultado
     * @throws NoResultException En caso de no tener resultados
     */
    public Object findSingleResultByNativeQuery(String nquery) throws NoResultException {
        javax.persistence.Query q = getEntityManager().createNativeQuery(nquery);
        return q.getSingleResult();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Método para iniciar transacciones. OJO: Estas solo funcionan para
     * contextos NO controlados por un contenedor
     *
     * @param utx UserTransaction
     */
    public void initTransaction(UserTransaction utx) {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
    }

    /**
     * Método para hacer rollback de transacciones. OJO: Estas solo funcionan
     * para contextos NO controlados por un contenedor
     *
     * @param utx UserTransaction
     */
    public void rollbackTransaction(UserTransaction utx) {
        try {
            utx.rollback();
        } catch (Exception ex) {
        }
    }

    /**
     * Método para hacer commit de transacciones. OJO: Estas solo funcionan para
     * contextos NO controlados por un contenedor
     *
     * @param utx UserTransaction
     */
    public void commitTransaction(UserTransaction utx) {
        try {
            getEntityManager().flush();
            utx.commit();
        } catch (Exception ex) {
        }
    }
}

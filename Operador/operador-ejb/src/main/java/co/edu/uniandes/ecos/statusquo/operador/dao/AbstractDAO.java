package co.edu.uniandes.ecos.statusquo.operador.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<T> implements AuditoriaWsDAORemote<T> {

    @PersistenceContext(unitName = "STATUSQUO-PU")
    protected EntityManager em;

    private final Class<T> clase;

    public AbstractDAO(final Class<T> clase) {
        this.clase = clase;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void insertar(T entity) {
        em.persist(entity);
        em.flush();
    }
    
    @Override
    public T insertarReturn(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public void actualizar(T entity) {
        em.merge(entity);
        em.flush();
    }

    @Override
    public void borrar(T entity) {
        em.remove(em.merge(entity));
        em.flush();
    }

    @Override
    public void desconectar(T entity) {
        em.detach(entity);
    }

    @Override
    public void refrescar(T entity) {
        em.refresh(entity);
    }

    @Override
    public List<T> consultar() {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(clase);
        cq.select(cq.from(clase));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<T> consultarNamedQuery(final String queryName) {
        return consultarNamedQuery(queryName, null, null, null);
    }

    @Override
    public List<T> consultarNamedQuery(final String queryName, final List<Object> params) {
        return consultarNamedQuery(queryName, null, null, params);
    }

    @Override
    public List<T> consultarNamedQuery(final String queryName, final Integer primerRegistro, final Integer maxResultados) {
        return consultarNamedQuery(queryName, primerRegistro, maxResultados, null);
    }

    @Override
    public List<T> consultarNamedQuery(final String queryName, final Integer primerRegistro, final Integer maxResultados, final List<?> params) {
        TypedQuery<T> q = em.createNamedQuery(queryName, clase);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i + 1, params.get(i));
            }
        }
        if (primerRegistro != null) {
            q.setFirstResult(primerRegistro);
        }

        if (maxResultados != null) {
            q.setMaxResults(maxResultados);
        }
        return q.getResultList();
    }

    @Override
    public T buscar(final Long id) {
        return em.find(clase, id);
    }

    @Override
    public T buscarNamedQuery(final String queryName) {
        return buscarNamedQuery(queryName, null);
    }

    @Override
    public T buscarNamedQuery(final String queryName, final List<Object> params) {
        try {
            TypedQuery<T> q = em.createNamedQuery(queryName, clase);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    q.setParameter(i + 1, params.get(i));
                }
            }
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}

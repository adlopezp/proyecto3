package co.edu.uniandes.ecos.statusquo.operador.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<T> {

    @PersistenceContext(unitName = "STATUSQUO-PU")
    private EntityManager em;

    private final Class<T> clase;

    public AbstractDAO(final Class<T> clase) {
        this.clase = clase;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void insertar(T entity) {
        em.persist(entity);
    }

    public void actualizar(T entity) {
        em.merge(entity);
    }

    public void borrar(T entity) {
        em.remove(em.merge(entity));
    }

    public void refrescar(T entity) {
        em.refresh(entity);
    }

    public T buscar(final Long id) {
        return em.find(clase, id);
    }

    public List<T> consultarTodas() {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(clase);
        cq.select(cq.from(clase));
        return em.createQuery(cq).getResultList();
    }
}

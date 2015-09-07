package co.edu.uniandes.ecos.statusquo.operador.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDAO<T> {

    private final Class<T> clase;
    private final EntityManager em;

    public AbstractDAO(final Class<T> clase, final EntityManager em) {
        this.clase = clase;
        this.em = em;
    }

    public AbstractDAO(final Class<T> clase, final EntityManagerFactory emf) {
        this.clase = clase;
        this.em = emf.createEntityManager();
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

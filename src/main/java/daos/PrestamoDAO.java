package daos;

import dto.Prestamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PrestamoDAO implements GenericoDAO<Prestamo> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    public void insertar(Prestamo entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Prestamo leerPorId(Object id) {
        EntityManager em = emf.createEntityManager();
        Prestamo entidad = em.find(Prestamo.class, id);
        em.close();
        return entidad;
    }

    @Override
    public List<Prestamo> leerTodos() {
        EntityManager em = emf.createEntityManager();
        List<Prestamo> entidades = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
        em.close();
        return entidades;
    }

    @Override
    public void actualizar(Prestamo entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void borrar(Object id) {
        EntityManager em = emf.createEntityManager();
        Prestamo entidad = em.find(Prestamo.class, id);
        if (entidad != null) {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        }
        em.close();
    }
}

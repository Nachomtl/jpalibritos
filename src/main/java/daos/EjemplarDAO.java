package daos;

import dto.Ejemplar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EjemplarDAO implements GenericoDAO<Ejemplar> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    public void insertar(Ejemplar entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Ejemplar leerPorId(Object id) {
        EntityManager em = emf.createEntityManager();
        Ejemplar entidad = em.find(Ejemplar.class, id);
        em.close();
        return entidad;
    }

    @Override
    public List<Ejemplar> leerTodos() {
        EntityManager em = emf.createEntityManager();
        List<Ejemplar> entidades = em.createQuery("SELECT e FROM Ejemplar e", Ejemplar.class).getResultList();
        em.close();
        return entidades;
    }

    @Override
    public void actualizar(Ejemplar entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void borrar(Object id) {
        EntityManager em = emf.createEntityManager();
        Ejemplar entidad = em.find(Ejemplar.class, id);
        if (entidad != null) {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        }
        em.close();
    }
}

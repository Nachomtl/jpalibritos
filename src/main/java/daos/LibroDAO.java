package daos;

import dto.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class LibroDAO implements GenericoDAO<Libro> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    public void insertar(Libro entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Libro leerPorId(Object id) {
        EntityManager em = emf.createEntityManager();
        Libro entidad = em.find(Libro.class, id);
        em.close();
        return entidad;
    }

    @Override
    public List<Libro> leerTodos() {
        EntityManager em = emf.createEntityManager();
        List<Libro> entidades = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        em.close();
        return entidades;
    }

    @Override
    public void actualizar(Libro entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void borrar(Object id) {
        EntityManager em = emf.createEntityManager();
        Libro entidad = em.find(Libro.class, id);
        if (entidad != null) {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        }
        em.close();
    }
}

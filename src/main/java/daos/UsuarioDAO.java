package daos;

import dto.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UsuarioDAO implements GenericoDAO<Usuario> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    @Override
    public void insertar(Usuario entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Usuario leerPorId(Object id) {
        EntityManager em = emf.createEntityManager();
        Usuario entidad = em.find(Usuario.class, id);
        em.close();
        return entidad;
    }

    @Override
    public List<Usuario> leerTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> entidades = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        em.close();
        return entidades;
    }

    @Override
    public void actualizar(Usuario entidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidad);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void borrar(Object id) {
        EntityManager em = emf.createEntityManager();
        Usuario entidad = em.find(Usuario.class, id);
        if (entidad != null) {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        }
        em.close();
    }
}

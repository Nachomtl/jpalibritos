package daos;

import dto.Usuario;
import jakarta.persistence.EntityManager;
import daos.DBConnector;

import java.util.List;

    public class UsuarioDAO implements GenericoDAO<Usuario> {

        @Override
        public void insertar(Usuario entidad) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
            em.close();
        }

        @Override
        public Usuario leerPorId(Object id) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            Usuario entidad = em.find(Usuario.class, id);
            em.close();
            return entidad;
        }

        @Override
        public List<Usuario> leerTodos() {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            List<Usuario> entidades = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            em.close();
            return entidades;
        }

        @Override
        public void actualizar(Usuario entidad) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
            em.close();
        }

        @Override
        public void borrar(Object id) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            Usuario entidad = em.find(Usuario.class, id);
            if (entidad != null) {
                em.getTransaction().begin();
                em.remove(entidad);
                em.getTransaction().commit();
            }
            em.close();
        }
    }


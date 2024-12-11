package daos;
import dto.Prestamo;
import jakarta.persistence.EntityManager;

import java.util.List;

    public class PrestamoDAO implements GenericoDAO<Prestamo> {

        @Override
        public void insertar(Prestamo entidad) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
            em.close();
        }

        @Override
        public Prestamo leerPorId(Object id) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            Prestamo entidad = em.find(Prestamo.class, id);
            em.close();
            return entidad;
        }

        @Override
        public List<Prestamo> leerTodos() {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            List<Prestamo> entidades = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
            em.close();
            return entidades;
        }

        @Override
        public void actualizar(Prestamo entidad) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
            em.close();
        }

        @Override
        public void borrar(Object id) {
            EntityManager em = DBConnector.getEntityManagerFactory().createEntityManager();
            Prestamo entidad = em.find(Prestamo.class, id);
            if (entidad != null) {
                em.getTransaction().begin();
                em.remove(entidad);
                em.getTransaction().commit();
            }
            em.close();
        }
    }



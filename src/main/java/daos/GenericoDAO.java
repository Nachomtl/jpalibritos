package daos;

import java.util.List;
    public interface GenericoDAO<T> {
        void insertar(T entidad);
        T leerPorId(Object id);
        List<T> leerTodos();
        void actualizar(T entidad);
        void borrar(Object id);
    }



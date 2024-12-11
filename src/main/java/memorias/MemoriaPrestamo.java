package memorias;

import dto.Prestamo;
import daos.PrestamoDAO;
import java.util.ArrayList;
import java.util.List;

    public class MemoriaPrestamo {
        private List<Prestamo> prestamosEnMemoria = new ArrayList<>();
        private PrestamoDAO prestamoDAO = new PrestamoDAO();
        public List<Prestamo> getPrestamosEnMemoria() {
            return prestamosEnMemoria;
        }

        public void sincronizarConBaseDeDatos() {
            prestamosEnMemoria = prestamoDAO.leerTodos(); // carga todos los préstamos desde la base
        }

        public void agregarPrestamo(Prestamo prestamo) {
            prestamoDAO.insertar(prestamo);
            sincronizarConBaseDeDatos();
        }

        public void actualizarPrestamo(Prestamo prestamo) {
            prestamoDAO.actualizar(prestamo);
            sincronizarConBaseDeDatos();
        }

        public void borrarPrestamo(int id) {
            prestamoDAO.borrar(id);
            sincronizarConBaseDeDatos();
        }
    }



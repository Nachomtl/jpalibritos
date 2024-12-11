package memorias;
import dto.Libro;
import daos.LibroDAO;
import java.util.ArrayList;
import java.util.List;

    public class MemoriaLibro {
        private List<Libro> librosEnMemoria = new ArrayList<>();
        private LibroDAO libroDAO = new LibroDAO();

        public List<Libro> getLibrosEnMemoria() {
            return librosEnMemoria;
        }
        public void sincronizarConBaseDeDatos() {
            librosEnMemoria = libroDAO.leerTodos(); // carga todos los libros desde la base
        }
        public void agregarLibro(Libro libro) {
            libroDAO.insertar(libro);
            sincronizarConBaseDeDatos();
        }

        public void actualizarLibro(Libro libro) {
            libroDAO.actualizar(libro);
            sincronizarConBaseDeDatos();
        }
        public void borrarLibro(int id) {
            libroDAO.borrar(id);
            sincronizarConBaseDeDatos();
        }
    }



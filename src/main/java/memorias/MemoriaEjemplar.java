package memorias;
import dto.Ejemplar;
import daos.EjemplarDAO;
import java.util.ArrayList;
import java.util.List;

    public class MemoriaEjemplar {
        private List<Ejemplar> ejemplaresEnMemoria = new ArrayList<>();
        private EjemplarDAO ejemplarDAO = new EjemplarDAO();

        public List<Ejemplar> getEjemplaresEnMemoria() {
            return ejemplaresEnMemoria;
        }

        public void sincronizarConBaseDeDatos() {
            ejemplaresEnMemoria = ejemplarDAO.leerTodos(); // carga todos los ejemplares desde la base
        }


        public void agregarEjemplar(Ejemplar ejemplar) {
            ejemplarDAO.insertar(ejemplar);
            sincronizarConBaseDeDatos();
        }


        public void actualizarEjemplar(Ejemplar ejemplar) {
            ejemplarDAO.actualizar(ejemplar);
            sincronizarConBaseDeDatos();
        }

        public void borrarEjemplar(int id) {
            ejemplarDAO.borrar(id);
            sincronizarConBaseDeDatos();
        }
    }



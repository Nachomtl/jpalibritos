package memorias;
import daos.UsuarioDAO;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;


    public class MemoriaUsuario {
        private List<Usuario> usuariosEnMemoria = new ArrayList<>();
        private UsuarioDAO usuarioDAO = new UsuarioDAO();

        public List<Usuario> getUsuariosEnMemoria() {
            return usuariosEnMemoria;
        }
        public void sincronizarConBaseDeDatos() {
            usuariosEnMemoria = usuarioDAO.leerTodos();
        }

        public void agregarUsuario(Usuario usuario) {
            usuarioDAO.insertar(usuario);
            sincronizarConBaseDeDatos();
        }

        public void actualizarUsuario(Usuario usuario) {
            usuarioDAO.actualizar(usuario);
            sincronizarConBaseDeDatos();
        }

        public void borrarUsuario(int id) {
            usuarioDAO.borrar(id);
            sincronizarConBaseDeDatos();
        }
    }



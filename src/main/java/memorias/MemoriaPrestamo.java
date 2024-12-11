package memorias;

import dto.Prestamo;
import dto.Usuario;
import dto.Ejemplar;
import daos.UsuarioDAO;
import daos.EjemplarDAO;
import daos.PrestamoDAO;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrestamo {
    private List<Prestamo> prestamosEnMemoria = new ArrayList<>();
    private List<Usuario> usuariosEnMemoria = new ArrayList<>();
    private List<Ejemplar> ejemplaresEnMemoria = new ArrayList<>();

    private PrestamoDAO prestamoDAO = new PrestamoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private EjemplarDAO ejemplarDAO = new EjemplarDAO();

    public List<Prestamo> getPrestamosEnMemoria() {
        return prestamosEnMemoria;
    }

    // Sincroniza los prestamos
    public void sincronizarConBaseDeDatos() {
        prestamosEnMemoria = prestamoDAO.leerTodos();
        usuariosEnMemoria = usuarioDAO.leerTodos();
        ejemplaresEnMemoria = ejemplarDAO.leerTodos();
    }

    // Agregar
    public void agregarPrestamo(Prestamo prestamo) {
        prestamoDAO.insertar(prestamo);
        sincronizarConBaseDeDatos();
    }

    // Actualizar
    public void actualizarPrestamo(Prestamo prestamo) {
        prestamoDAO.actualizar(prestamo);
        sincronizarConBaseDeDatos();
    }

    // Borrar   sincronizar la memoria
    public void borrarPrestamo(int id) {
        prestamoDAO.borrar(id);
        sincronizarConBaseDeDatos();
    }

    // Buscar un usuario por su ID
    public Usuario buscarUsuarioPorId(int id) {
        return usuariosEnMemoria.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Buscar un ejemplar por su ID
    public Ejemplar buscarEjemplarPorId(int id) {
        return ejemplaresEnMemoria.stream()
                .filter(ejemplar -> ejemplar.getId() == id)
                .findFirst()
                .orElse(null);  // Devuelve null si no encuentra el ejemplar
    }
}

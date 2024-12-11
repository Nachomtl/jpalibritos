package menus;

import dto.Prestamo;
import dto.Usuario;
import dto.Ejemplar;
import memorias.MemoriaPrestamo;

import java.util.Scanner;

public class MenuPrestamo {
    private static Scanner scanner = new Scanner(System.in);
    private MemoriaPrestamo memoriaPrestamo = new MemoriaPrestamo();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("1. Leer prestamos");
            System.out.println("2. Insertar prestamo");
            System.out.println("3. Actualizar prestamo");
            System.out.println("4. Borrar prestamo");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> leerPrestamos();
                case 2 -> insertarPrestamo();
                case 3 -> actualizarPrestamo();
                case 4 -> borrarPrestamo();
            }
        } while (opcion != 5);
    }

    private void leerPrestamos() {
        memoriaPrestamo.sincronizarConBaseDeDatos();
        memoriaPrestamo.getPrestamosEnMemoria().forEach(System.out::println);
    }

    private void insertarPrestamo() {
        System.out.println("Introduce el ID del libro:");
        int libroId = scanner.nextInt();
        System.out.println("Introduce el ID del usuario:");
        int usuarioId = scanner.nextInt();

        // Buscar los objetos Usuario y Ejemplar por su ID
        Usuario usuario = memoriaPrestamo.buscarUsuarioPorId(usuarioId);
        Ejemplar ejemplar = memoriaPrestamo.buscarEjemplarPorId(libroId);

        if (usuario == null || ejemplar == null) {
            System.out.println("Usuario o ejemplar no encontrado.");
            return;
        }

        // Crear el préstamo y asignar los objetos
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaInicio(java.time.LocalDate.now());

        memoriaPrestamo.agregarPrestamo(prestamo);
        System.out.println("Prestamo insertado correctamente");
    }

    private void actualizarPrestamo() {
        System.out.println("Introduce el ID del prestamo:");
        int id = scanner.nextInt();
        Prestamo prestamo = memoriaPrestamo.getPrestamosEnMemoria().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            System.out.println("Prestamo no encontrado");
            return;
        }

        System.out.println("Introduce el nuevo estado del prestamo (Activo, Devuelto):");
        String estado = scanner.next();
        //prestamo.setEstado(estado);

        memoriaPrestamo.actualizarPrestamo(prestamo);
        System.out.println("Prestamo actualizado");
    }

    private void borrarPrestamo() {
        System.out.println("Introduce el ID del prestamo:");
        int id = scanner.nextInt();
        memoriaPrestamo.borrarPrestamo(id);
        System.out.println("Prestamo borrado");
    }
}

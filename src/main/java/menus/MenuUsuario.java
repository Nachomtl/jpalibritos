package menus;
import dto.Usuario;
import memorias.MemoriaUsuario;
import java.util.Scanner;
    public class MenuUsuario {
        private static Scanner scanner = new Scanner(System.in);
        private MemoriaUsuario memoriaUsuario = new MemoriaUsuario();

        public void mostrarMenu() {
            int opcion;
            do {
                System.out.println("1. Leer usuarios");
                System.out.println("2. Insertar usuario");
                System.out.println("3. Actualizar usuario");
                System.out.println("4. Borrar usuario");
                System.out.println("5. Salir");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> leerUsuarios();
                    case 2 -> insertarUsuario();
                    case 3 -> actualizarUsuario();
                    case 4 -> borrarUsuario();
                }
            } while (opcion != 5);
        }

        private void leerUsuarios() {
            memoriaUsuario.sincronizarConBaseDeDatos();
            memoriaUsuario.getUsuariosEnMemoria().forEach(System.out::println);
        }

        private void insertarUsuario() {
            System.out.println("Introduce el nombre del usuario:");
            String nombre = scanner.next();
            System.out.println("Introduce el email del usuario:");
            String email = scanner.next();
            System.out.println("Introduce el tipo de usuario (normal, administrador):");
            String tipo = scanner.next();

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setTipo(tipo);

            memoriaUsuario.agregarUsuario(usuario);
            System.out.println("Usuario insertado ");
        }

        private void actualizarUsuario() {
            System.out.println("Introduce el ID del usuario:");
            int id = scanner.nextInt();
            Usuario usuario = memoriaUsuario.getUsuariosEnMemoria().stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (usuario == null) {
                System.out.println("Usuario no encontrado");
                return;
            }

            System.out.println("Introduce el nuevo nombre del usuario:");
            String nombre = scanner.next();
            usuario.setNombre(nombre);

            memoriaUsuario.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado ");
        }

        private void borrarUsuario() {
            System.out.println("Introduce el ID del usuario:");
            int id = scanner.nextInt();
            memoriaUsuario.borrarUsuario(id);
            System.out.println("Usuario borrado ");
        }
    }



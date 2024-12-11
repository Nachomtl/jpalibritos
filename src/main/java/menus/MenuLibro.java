package menus;
import dto.Libro;
import memorias.MemoriaLibro;
import Validaciones.LibroValidacion;

import java.util.Scanner;
    public class MenuLibro {
        private static Scanner scanner = new Scanner(System.in);
        private MemoriaLibro memoriaLibro = new MemoriaLibro();

        public void mostrarMenu() {
            int opcion;
            do {
                System.out.println("1. Leer libros");
                System.out.println("2. Insertar libro");
                System.out.println("3. Actualizar libro");
                System.out.println("4. Borrar libro");
                System.out.println("5. Salir");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> leerLibros();
                    case 2 -> insertarLibro();
                    case 3 -> actualizarLibro();
                    case 4 -> borrarLibro();
                }
            } while (opcion != 5);
        }

        private void leerLibros() {
            memoriaLibro.sincronizarConBaseDeDatos();
            memoriaLibro.getLibrosEnMemoria().forEach(System.out::println);
        }

        private void insertarLibro() {
            System.out.println("Introduce el título del libro:");
            String titulo = scanner.next();
            System.out.println("Introduce el autor del libro:");
            String autor = scanner.next();
            System.out.println("Introduce el ISBN del libro:");
            String isbn = scanner.next();

            if (!LibroValidacion.validarIsbn(isbn)) {
                System.out.println("El ISBN no es valido");
                return;
            }

            Libro libro = new Libro();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setIsbn(isbn);

            memoriaLibro.agregarLibro(libro);
            System.out.println("Libro insertado correctamente");
        }

        private void actualizarLibro() {
            System.out.println("Introduce el ID del libro:");
            int id = scanner.nextInt();
            Libro libro = memoriaLibro.getLibrosEnMemoria().stream()
                    .filter(l -> l.getIsbn() == l.getIsbn())
                    .findFirst()
                    .orElse(null);

            if (libro == null) {
                System.out.println("Libro no encontrado");
                return;
            }

            System.out.println("Introduce el nuevo titulo del libro:");
            String titulo = scanner.next();
            libro.setTitulo(titulo);

            memoriaLibro.actualizarLibro(libro);
            System.out.println("Libro actualizado correctamente");
        }

        private void borrarLibro() {
            System.out.println("Introduce el ID del libro:");
            int id = scanner.nextInt();
            memoriaLibro.borrarLibro(id);
            System.out.println("Libro borrado correctamente");
        }
    }



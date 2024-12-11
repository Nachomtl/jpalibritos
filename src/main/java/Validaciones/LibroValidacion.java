package Validaciones;

    public class LibroValidacion {

        // Validacin para ISBN
        public static boolean validarIsbn(String isbn) {
            return isbn != null && isbn.length() == 13;
        }

        // Validacion titulo
        public static boolean validarTitulo(String titulo) {
            return titulo != null && !titulo.trim().isEmpty();
        }

        // Validacion para el autor
        public static boolean validarAutor(String autor) {
            return autor != null && !autor.trim().isEmpty();
        }
    }

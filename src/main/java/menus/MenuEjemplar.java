package menus;
import dto.Ejemplar;
import memorias.MemoriaEjemplar;
import java.util.Scanner;
    public class MenuEjemplar {
        private static Scanner scanner = new Scanner(System.in);
        private MemoriaEjemplar memoriaEjemplar = new MemoriaEjemplar();

        public void mostrarMenu() {
            int opcion;
            do {
                System.out.println("1. Leer ejemplares");
                System.out.println("2. Insertar ejemplar");
                System.out.println("3. Actualizar ejemplar");
                System.out.println("4. Borrar ejemplar");
                System.out.println("5. Salir");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> leerEjemplares();
                    case 2 -> insertarEjemplar();
                    case 3 -> actualizarEjemplar();
                    case 4 -> borrarEjemplar();
                }
            } while (opcion != 5);
        }

        private void leerEjemplares() {
            memoriaEjemplar.sincronizarConBaseDeDatos();
            memoriaEjemplar.getEjemplaresEnMemoria().forEach(System.out::println);
        }

        private void insertarEjemplar() {
            System.out.println("Introduce el ISBN del libro:");
            String isbn = scanner.next();
            System.out.println("Introduce el estado del ejemplar (Disponible, Prestado, Dañado):");
            String estado = scanner.next();

            Ejemplar ejemplar = new Ejemplar();
            ejemplar.setIsbn(isbn);
            ejemplar.setEstado(estado);

            memoriaEjemplar.agregarEjemplar(ejemplar);
            System.out.println("Ejemplar insertado correctamente");
        }

        private void actualizarEjemplar() {
            System.out.println("Introduce el ID del ejemplar:");
            int id = scanner.nextInt();
            Ejemplar ejemplar = memoriaEjemplar.getEjemplaresEnMemoria().stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (ejemplar == null) {
                System.out.println("Ejemplar no encontrado");
                return;
            }

            System.out.println("Introduce el nuevo estado del ejemplar (Disponible, Prestado, Dañado):");
            String estado = scanner.next();
            ejemplar.setEstado(estado);

            memoriaEjemplar.actualizarEjemplar(ejemplar);
            System.out.println("Ejemplar actualizado correctamente");
        }

        private void borrarEjemplar() {
            System.out.println("Introduce el ID del ejemplar:");
            int id = scanner.nextInt();
            memoriaEjemplar.borrarEjemplar(id);
            System.out.println("Ejemplar borrado correctamente");
        }
    }



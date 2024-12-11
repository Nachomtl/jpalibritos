package main;
import menus.MenuLibro;
import menus.MenuUsuario;
import menus.MenuEjemplar;
import menus.MenuPrestamo;

import java.util.Scanner;
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println(" Menú Principal");
                System.out.println("1. Gestión de Libros");
                System.out.println("2. Gestión de Ejemplares");
                System.out.println("3. Gestión de Usuarios");
                System.out.println("4. Gestión de Préstamos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> {
                        MenuLibro menuLibro = new MenuLibro();
                        menuLibro.mostrarMenu();
                    }
                    case 2 -> {
                        MenuEjemplar menuEjemplar = new MenuEjemplar();
                        menuEjemplar.mostrarMenu();
                    }
                    case 3 -> {
                        MenuUsuario menuUsuario = new MenuUsuario();
                        menuUsuario.mostrarMenu();
                    }
                    case 4 -> {
                        MenuPrestamo menuPrestamo = new MenuPrestamo();
                        menuPrestamo.mostrarMenu();
                    }
                    case 5 -> System.out.println("saliendo");
                    default -> System.out.println("error prueba potra vesz");
                }
            } while (opcion != 5);

            scanner.close();
        }
    }


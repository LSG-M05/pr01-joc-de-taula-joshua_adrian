import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {
        mostrarMenu();

    }
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menú:");
        System.out.println("1. Jugar");
        System.out.println("2. Ver Cartas/Mazo");
        System.out.println("3. Reglas");
        System.out.println("4. Salir");
        System.out.print("Ingrese su opción: ");

        // Validar la entrada del usuario
        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.println("Ha seleccionado Jugar");
                    jugarPartida();
                    break;
                case 2:
                    System.out.println("Ha seleccionado Ver Cartas/Mazo");
                    System.out.println("Seleccione el mazo que desea ver:");
                    System.out.println("1. Mazo Uno");
                    System.out.println("2. Mazo Dos");
                    System.out.print("Ingrese su opción: ");

                    if (scanner.hasNextInt()) {
                        int opcionMazo = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer del scanner

                        switch (opcionMazo) {
                            case 1:
                                System.out.println("Mazo Uno:");
                                mostrarMazo(mazoUno());
                                break;
                            case 2:
                                System.out.println("Mazo Dos:");
                                mostrarMazo(mazoDos());
                                break;
                            default:
                                System.out.println("Error: Opción de mazo no válida.");
                        }
                    } else {
                        System.out.println("Error: Por favor, ingrese un número válido.");
                        scanner.nextLine(); // Limpiar el buffer del scanner
                    }
                    break;
                case 3:
                    System.out.println("Ha seleccionado Reglas");
                    // Aquí puedes agregar la lógica correspondiente a la opción 3
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Opción no válida. Por favor, ingrese una opción del 1 al 4.");
            }
        } else {
            System.out.println("Error: Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }

        // Llamada recursiva para mostrar el menú nuevamente
        mostrarMenu();
    }
    public static List<String> mazoUno(){
        List<String> mazoUno = new ArrayList<>();
        mazoUno.addAll(List.of("Criatura 1", "2", "2"));
        mazoUno.addAll(List.of("Criatura 2", "2", "2"));
        mazoUno.addAll(List.of("Criatura 3", "2", "2"));
        mazoUno.addAll(List.of("Criatura 4", "2", "2"));
        mazoUno.addAll(List.of("Criatura 5", "2", "2"));
        mazoUno.addAll(List.of("Criatura 6", "2", "2"));
        mazoUno.addAll(List.of("Criatura 7", "2", "2"));
        mazoUno.addAll(List.of("Criatura 8", "2", "2"));
        mazoUno.addAll(List.of("Criatura 9", "2", "2"));
        mazoUno.addAll(List.of("Criatura 10", "2", "2"));
        mazoUno.addAll(List.of("Criatura 11", "2", "2"));
        mazoUno.addAll(List.of("Criatura 12", "2", "2"));

        return mazoUno;
    }

    public static List<String> mazoDos(){
        List<String> mazoDos = new ArrayList<>();
        mazoDos.addAll(List.of("Criatura 1", "2", "2"));
        mazoDos.addAll(List.of("Criatura 2", "2", "2"));
        mazoDos.addAll(List.of("Criatura 3", "2", "2"));
        mazoDos.addAll(List.of("Criatura 4", "2", "2"));
        mazoDos.addAll(List.of("Criatura 5", "2", "2"));
        mazoDos.addAll(List.of("Criatura 6", "2", "2"));
        mazoDos.addAll(List.of("Criatura 7", "2", "2"));
        mazoDos.addAll(List.of("Criatura 8", "2", "2"));
        mazoDos.addAll(List.of("Criatura 9", "2", "2"));
        mazoDos.addAll(List.of("Criatura 10", "2", "2"));
        mazoDos.addAll(List.of("Criatura 11", "2", "2"));
        mazoDos.addAll(List.of("Criatura 12", "2", "2"));

        return mazoDos;
    }

    public static int elegirCarta(List<String> mazoJugador) {
        Scanner scanner = new Scanner(System.in);
        int seleccion;

        do {
            System.out.println("Seleccione una carta:");
            for (int i = 0; i < mazoJugador.size(); i += 3) {
                String nombreCarta = mazoJugador.get(i);
                String fuerzaCarta = mazoJugador.get(i + 1);
                String resistenciaCarta = mazoJugador.get(i + 2);
                System.out.println("(" + (i / 3 + 1) + ") " + nombreCarta + " - Fuerza: " + fuerzaCarta + ", Resistencia: " + resistenciaCarta);
            }
            System.out.print("Ingrese el número de la carta: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
            seleccion = scanner.nextInt();

            if (seleccion < 1 || seleccion > mazoJugador.size() / 3) {
                System.out.println("Número de carta no válido. Por favor, ingrese un número válido.");
            } else {
                return seleccion - 1;
            }
        } while (true);
    }


    public static String getFuerza(int numero, List mazo){
        String fuerza = (String) mazo.get(numero*3-1);
        return fuerza;
    }
    public static String getResistencia(int numero, List mazo){
        String resistencia = (String) mazo.get(numero*3);
        return resistencia;
    }
}


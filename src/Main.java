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
        //Tier 1 Max 20 puntos
        mazoUno.addAll(List.of("Gabriel", "10", "10"));
        mazoUno.addAll(List.of("Michael", "15", "5"));
        mazoUno.addAll(List.of("Rafael", "7", "13"));
        mazoUno.addAll(List.of("Uriel", "14", "6"));
        mazoUno.addAll(List.of("Ariel", "8", "12"));
        //Tier 2 Max 12 puntos
        mazoUno.addAll(List.of("Seraphiel", "8", "4"));
        mazoUno.addAll(List.of("Azrael", "6", "6"));
        mazoUno.addAll(List.of("Metatron", "9", "3"));
        mazoUno.addAll(List.of("Raziel", "4", "8"));
        mazoUno.addAll(List.of("Haniel", "2", "10"));
        //Tier 3 Max 6 puntos
        mazoUno.addAll(List.of("Jophiel", "6", "0"));
        mazoUno.addAll(List.of("Zadkiel", "3", "3"));
        mazoUno.addAll(List.of("Cassiel", "1", "5"));
        mazoUno.addAll(List.of("Chamuel", "4", "2"));
        mazoUno.addAll(List.of("Raguel", "2", "4"));

        return mazoUno;
    }

    public static List<String> mazoDos(){
        List<String> mazoDos = new ArrayList<>();
        //Tier 1 Max 20 puntos
        mazoDos.addAll(List.of("Lucifer", "19", "1"));
        mazoDos.addAll(List.of("Belial", "12", "8"));
        mazoDos.addAll(List.of("Asmodeus", "15", "5"));
        mazoDos.addAll(List.of("Mammon", "0", "20"));
        mazoDos.addAll(List.of("Beelzebub", "17", "3"));
        //Tier 2 Max 12 puntos
        mazoDos.addAll(List.of("Abaddon", "6", "6"));
        mazoDos.addAll(List.of("Leviatán", "6", "6"));
        mazoDos.addAll(List.of("Baphomet", "8", "4"));
        mazoDos.addAll(List.of("Astaroth", "3", "9"));
        mazoDos.addAll(List.of("Lilith", "2", "11"));
        //Tier 3 Max 6 puntos
        mazoDos.addAll(List.of("Mephistopheles", "3", "3"));
        mazoDos.addAll(List.of("Baal", "2", "4"));
        mazoDos.addAll(List.of("Azazel", "3", "3"));
        mazoDos.addAll(List.of("Moloch", "4", "2"));
        mazoDos.addAll(List.of("Nyarlathotep", "4", "2"));
        return mazoDos;
    }
    public static List<String> obtenerCartaAleatoria(List<String> mazo) {
        Random random = new Random();
        List<String> cartaAleatoria = new ArrayList<>();

        int numeroRandom = random.nextInt(mazo.size() / 3);
        cartaAleatoria.addAll(List.of(mazo.get(numeroRandom * 3), mazo.get(numeroRandom * 3 + 1), mazo.get(numeroRandom * 3 + 2)));

        return cartaAleatoria;
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


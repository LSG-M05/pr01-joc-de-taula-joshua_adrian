import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {

        mostrarMenu();
    }

    public static void jugarPartida() {
        int vidaJugadorUno = 15;
        int vidaJugadorDos = 15;
        int ronda = 1;
        System.out.println("¡Comienza la partida!");

        // Inicialización de los mazos de los jugadores solo en la primera ronda
        if (ronda == 1) {
            mazoPlayerUno = reparteCartas(mazoUno());
            mazoPlayerDos = reparteCartas(mazoDos());
        }

        while (vidaJugadorUno > 0 && vidaJugadorDos > 0) {
            System.out.println("Ronda " + ronda);

            // Cada jugador elige una carta para jugar
            int cartaJugadorUno = elegirCarta(mazoPlayerUno);
            System.out.println("\n\n\n\n\n\n\n");
            int cartaJugadorDos = elegirCarta(mazoPlayerDos);
            System.out.println("\n\n\n\n\n\n\n");

            // Calcular el daño
            int fuerzaCartaUno = Integer.parseInt(mazoPlayerUno.get(cartaJugadorUno * 3 + 1));
            int vidaCartaDos = Integer.parseInt(mazoPlayerDos.get(cartaJugadorDos * 3 + 2));
            int dañoCartaUno = Math.max(0, fuerzaCartaUno - vidaCartaDos);

            int fuerzaCartaDos = Integer.parseInt(mazoPlayerDos.get(cartaJugadorDos * 3 + 1));
            int vidaCartaUno = Integer.parseInt(mazoPlayerUno.get(cartaJugadorUno * 3 + 2));
            int dañoCartaDos = Math.max(0, fuerzaCartaDos - vidaCartaUno);

            // Actualizar la vida de cada jugador

            vidaJugadorUno -= dañoCartaUno;
            vidaJugadorDos -= dañoCartaDos;

            System.out.println("Jugador 1 hizo " + dañoCartaUno + " de daño. Vida restante: " + vidaJugadorUno);
            System.out.println("-------------------------------------------");
            System.out.println("Jugador 2 hizo " + dañoCartaDos + " de daño. Vida restante: " + vidaJugadorDos);
            System.out.println("\n\n\n\n\n\n\n");

            // Eliminar las cartas jugadas del mazo de cada jugador
            mazoPlayerUno.remove(cartaJugadorUno * 3);
            mazoPlayerUno.remove(cartaJugadorUno * 3);
            mazoPlayerUno.remove(cartaJugadorUno * 3);

            mazoPlayerDos.remove(cartaJugadorDos * 3);
            mazoPlayerDos.remove(cartaJugadorDos * 3);
            mazoPlayerDos.remove(cartaJugadorDos * 3);

            // Verificar si algún jugador tiene 0 o menos de vida
            if (vidaJugadorUno <= 0 || vidaJugadorDos <= 0) {
                System.out.println("¡El juego ha terminado!");
                if (vidaJugadorUno <= 0 && vidaJugadorDos <= 0) {
                    System.out.println("¡Empate!");
                } else if (vidaJugadorUno <= 0) {
                    System.out.println("¡Jugador 2 gana!");
                } else {
                    System.out.println("¡Jugador 1 gana!");
                }
                break;
            }

            // Agregar una carta aleatoria al finalizar el turno de cada jugador
            mazoPlayerUno.addAll(obtenerCartaAleatoria(mazoUno()));
            mazoPlayerDos.addAll(obtenerCartaAleatoria(mazoDos()));

            ronda++;
        }
    }

    public static List<String> reparteCartas(List<String> mazo) {
        Random random = new Random();
        List<String> mazoJugable = new ArrayList<>();

        for (int a = 0; a < 4; a++) {
            // Genera números aleatorios dependiendo de la longitud de la lista.
            int numeroRandom = random.nextInt(mazo.size() / 3 - 1 + 1) + 1;

            mazoJugable.addAll(List.of(getNombre(numeroRandom, mazo), getFuerza(numeroRandom, mazo), getResistencia(numeroRandom, mazo)));
            for (int i = 0; i < 3; i++) {
                mazo.remove(numeroRandom * 3 - 3);
            }
        }
        return mazoJugable;
    }

    public static void mostrarMazo(List<String> mazo) {
        for (int i = 0; i < mazo.size(); i += 3) {
            String nombreCarta = mazo.get(i);
            String fuerzaCarta = mazo.get(i + 1);
            String resistenciaCarta = mazo.get(i + 2);
            System.out.println(nombreCarta + " - Fuerza: " + fuerzaCarta + ", Resistencia: " + resistenciaCarta);
        }
    }

    public class JuegoDeCartas {
        // Variables globales para los mazos de los jugadores
        private static List<String> mazoPlayerUno;
        private static List<String> mazoPlayerDos;

        public static void main(String[] args) {
            mostrarMenu();
        }

        public static String getNombre(int numero, List mazo){
            String nombre = (String) mazo.get(numero*3-2);
            return nombre;
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
    }
}

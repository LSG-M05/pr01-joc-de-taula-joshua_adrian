import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {

        mostrarMenu();
    }
    public static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Jugar");
        System.out.println("2. Ver Cartas/Mazo");
        System.out.println("3. Reglas");
        System.out.println("4. Salir");
        System.out.print("Ingrese su opción: ");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ha seleccionado Jugar");

                System.out.println("¡Comienza la partida!");
                //Creacion de Mazos
                mazoUno();
                mazoDos();
                //Reparte Cartas al jugador 1
                //reparteCartas(mazoUno());
                //Reparte Cartas al jugador 2
                //reparteCartas(mazoDos());

                System.out.println(reparteCartas(mazoDos()));

                break;

                case 2:
                    System.out.println("Ha seleccionado Ver Cartas/Mazo");
                    // Aquí puedes agregar la lógica correspondiente a la opción 2
                    break;

                case 3:
                    System.out.println("Ha seleccionado Reglas");
                    // Aquí puedes agregar la lógica correspondiente a la opción 2
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                }

                // Llamada recursiva para mostrar el menú nuevamente
                mostrarMenu();
            }

        public static String getNombre(int numero, List mazo){
        String nombre = (String) mazo.get(numero*3-2);
        return nombre;
        }

    public static void jugarPartida() {
        List<String> mazoPlayerUno;
        List<String> mazoPlayerDos;
        int vidaJugadorUno = 15;
        int vidaJugadorDos = 15;
        int ronda = 1;
        System.out.println("¡Comienza la partida!");

        while (vidaJugadorUno > 0 && vidaJugadorDos > 0) {
            System.out.println("Ronda " + ronda);

            // Repartición de Cartas para la ronda
            mazoPlayerUno = reparteCartas(mazoUno());
            mazoPlayerDos = reparteCartas(mazoDos());

            // Cada jugador elige una carta para jugar
            int cartaJugadorUno = elegirCarta(mazoPlayerUno);
            int cartaJugadorDos = elegirCarta(mazoPlayerDos);

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
            System.out.println("Jugador 2 hizo " + dañoCartaDos + " de daño. Vida restante: " + vidaJugadorDos);

            ronda++;

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
        }
    }
    public static List<String> reparteCartas(List mazo){
        Random random = new Random();
        List<String> mazoJugable = new ArrayList<>();

        for(int a = 0; a<4; a++){
            // Genera números aleatorios dependiendo de la longitud de la lista.
            int numeroRandom = random.nextInt(mazo.size()/3 - 1 + 1) + 1;

            mazoJugable.addAll(List.of(getNombre(numeroRandom,mazo), getFuerza(numeroRandom,mazo), getResistencia(numeroRandom,mazo)));
            for(int i = 0; i<3; i++) {
                mazo.remove(numeroRandom*3-3);
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
}


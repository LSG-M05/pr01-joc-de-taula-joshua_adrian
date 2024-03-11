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

    public static String getFuerza(int numero, List mazo){
        String fuerza = (String) mazo.get(numero*3-1);
        return fuerza;
    }
    public static String getResistencia(int numero, List mazo){
        String resistencia = (String) mazo.get(numero*3);
        return resistencia;
    }
}


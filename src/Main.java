import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
    public static List<String> reparteCartas(List mazo) {
        Random random = new Random();
        List<String> mazoJugable = new ArrayList<>();

        for (int a = 0; a < 4; a++) {
            // Genera números aleatorios dependiendo de la longitud de la lista.
            int numeroRandom = random.nextInt(mazo.size() / 3 + 1);
            System.out.println("Número aleatorio: " + numeroRandom);
        }
        mazoJugable.addAll(List.of(getNombre(numeroRandom,mazo), getFuerza(numeroRandom,mazo), getResistencia(numeroRandom,mazo)));
        for(int i = 0; i<3; i++) {
            mazo.remove(numeroRandom*3-2);
            }
        }

            return mazoJugable;
        }
    }
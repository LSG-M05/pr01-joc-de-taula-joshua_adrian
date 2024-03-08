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
    }
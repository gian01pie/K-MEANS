import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

public class MainTest {

    /**
     * @param args
     */
    public static void main(String[] args) {


        Data data = new Data();
        System.out.println(data);
        while (true) {
            System.out.println("Inserire numero di cluster da scoprire: ");
            int k = Keyboard.readInt();
            // Se si è inserito un valore che non è un intero readInt restituisce MIN_VALUE
            if (k == Integer.MIN_VALUE) {
                System.out.println("Valore inserito non valido\n");
                continue;
            }

            KMeansMiner kmeans = new KMeansMiner(k);

            // Blocco try catch per catturare l'eccezione sollevata da sampling nel caso il numero di cluster k
            // sia maggiore del numero di cluster generabili
            try {
                int numIter = kmeans.kmeans(data);
                System.out.println("Numero di Iterazione:" + numIter);

            } catch (OutOfRangeSampleSize err) {
                System.out.println(err.getMessage());
                continue;
            }

            System.out.println(kmeans.getC().toString(data));

            System.out.println("Vuoi ripetere l'esecuzione Y/n ?\n");
            char choice = Keyboard.readChar();
            if (Character.toLowerCase(choice) == 'n') {
                System.out.println("Termine esecuzione...\n");
                break;
            }
        }
    }
}

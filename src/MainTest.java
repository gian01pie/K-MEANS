import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KMeansMiner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Data data = new Data("MapDB");
        System.out.println(data);
        KMeansMiner kmeans = null;

        while (true) {
            kmeans = loadClustersFromFile(kmeans);
            if (kmeans == null) {
                int k = getClusterCountFromUser();
                kmeans = new KMeansMiner(k);
                try {
                    int numIter = kmeans.kmeans(data);
                    System.out.println("Numero di Iterazione:" + numIter);
                } catch (OutOfRangeSampleSize err) {
                    System.out.println(err.getMessage());
                    continue;
                }
            }

            System.out.println(kmeans.getC().toString(data));

            // Se l'utente vuole salva i clusters su file
            saveClustersToFile(kmeans);

            if (!repeatExecutionPrompt()) {
                System.out.println("Termine esecuzione...\n");
                return;
            }
        }
    }

    /**
     * Funzione che permette di scegliere se caricare i cluster da file
     * @param kmeans riferimento al KMeans
     * @return restituisce KMeansMiner caricato con il ClusterSet del file
     */
    private static KMeansMiner loadClustersFromFile(KMeansMiner kmeans) {
        while (true) {
            System.out.println("Vuoi caricare i cluster da file Y/n ?\n");
            char choice = Keyboard.readChar();
            if (Character.toLowerCase(choice) == 'n') {
                break;
            } else if (Character.toLowerCase(choice) == 'y') {
                System.out.println("Inserire il nome del file o il percorso se si trova in una directory diversa:\n");
                String fileName = Keyboard.readString();
                try {
                    kmeans = new KMeansMiner(fileName);
                    break; // Esci dal ciclo dopo il caricamento riuscito
                } catch (FileNotFoundException err) {
                    System.out.println(err.getMessage());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                } catch (ClassNotFoundException err) {
                    System.out.println(err.getMessage());
                }
            } else {
                System.out.println("Scelta non valida. Riprova.");
            }
        }
        return kmeans;
    }


    /**
     * Prende il numero di cluster che si vogliono trovare dall'utente
     * @return numero di cluster da calcolare
     */
    private static int getClusterCountFromUser() {
        System.out.println("Inserire numero di cluster da scoprire: ");
        int k = Keyboard.readInt();
        if (k == Integer.MIN_VALUE) {
            System.out.println("Valore inserito non valido\n");
            return getClusterCountFromUser(); // Ricorsione in caso d'input non valido
        }
        return k;
    }

    /**
     * Salva i cluster calcolati su file se l'utente lo desidera
     * @param kmeans riferimento all'algoritmo che ha individuato i cluster
     */
    private static void saveClustersToFile(KMeansMiner kmeans){
        boolean shouldSave = true;
        while (shouldSave) {
            System.out.println("Vuoi salvare i cluster su file Y/n ?\n");
            char choice = Keyboard.readChar();
            if (Character.toLowerCase(choice) == 'n') {
                shouldSave = false;
            } else if (Character.toLowerCase(choice) == 'y') {
                System.out.println("Inserire 'NomeFile.bin' o il percorso se si desidera salvare in una cartella specifca:\n");
                String fileName = Keyboard.readString();
                try {
                    kmeans.salva(fileName);
                    shouldSave = false; //siccome ho salvato setto should save a false
                } catch (FileNotFoundException err) {
                    System.out.println(err.getMessage());
                    System.out.println("Non puoi accedere al file inserito in scrittura");
                    System.out.println("Salvataggio in un file di default 'default_out.bin'");
                    try {
                        kmeans.salva("default_out.bin");
                        shouldSave = false;
                    }
                    // poiché FileNotFoundException è sottoclasse d'IOException possiamo anche non inserirla nel catch
                    catch (IOException exception) {
                        System.out.println(err.getMessage());
                    }
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            } else {
                System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    /**
     * Chiede all'utente se vuole ripetere l'esecuzione
     * @return True se si vuole ripetere l'esecuzione, false altrimenti
     */
    private static boolean repeatExecutionPrompt() {
        System.out.println("Vuoi ripetere l'esecuzione Y/n ?\n");
        char choice = Keyboard.readChar();
        if (Character.toLowerCase(choice) == 'y'){
            return true;
        } else if (Character.toLowerCase(choice) == 'n') {
            return false;
        } else {
            System.out.println("Scelta non valida. Riprova.");
            return repeatExecutionPrompt(); //chiamata ricorsiva in caso di scelta non valida
        }
    }
}

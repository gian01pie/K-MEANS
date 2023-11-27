import java.io.*;
import java.net.Socket;

import data.Data;
import data.OutOfRangeSampleSize;
import mining.KMeansMiner;

/**
 * Classe ServerOneCliente che modella l'interazione con il client sfruttando il multithreading
 */
class ServerOneClient extends Thread {
    /**
     * Riferimento alla socket utilizzata
     */
    private final Socket socket;
    /**
     * Stream di input in cui si ricevono i messaggi del client
     */
    private final ObjectInputStream in;
    /**
     * Stream di output in cui si inviano i messaggi al client
     */
    private final ObjectOutputStream out;
    /**
     * Riferimento all'oggetto della classe che modella il K-means
     */
    private KMeansMiner kmeans;

    /**
     * Costruttore di classe, inizializza la socket con quella in input e apre gli stream d'input e output,
     * avvia l'esecuzione del thread
     *
     * @param s socket da assegnare
     * @throws IOException se avviene un errore di I/O
     */
    ServerOneClient(Socket s) throws IOException {
        socket = s;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        // Se una delle precedenti solleva eccezione è il processo chiamante che si occupa
        // della chiusura della socket, altrimenti senza eccezioni se ne occuperò il thread
        start();// chiama run()
    }

    /**
     * Esegue le operazioni richiesta dal client, su un thread secondario.
     * Le operazioni possibili sono:
     * <ul>
     * <li> Caricare la tabella di transazioni dal DB</li>
     * <li> Trovare il numero di cluster indicato dal client</li>
     * <li> Salvare i cluster trovati su file </li>
     * <li> Caricare i cluster da file </li>
     * </ul>
     */
    @Override
    public void run() {
        Data data = null;
        String tabName = "";
        int numIter = 0;
        try {
            while (true) {

                int request = (int) in.readObject();
                switch (request) {
                    case 0:    //carica i dati dal DB
                        try {
                            tabName = (String) in.readObject();
                            data = new Data(tabName);
                            out.writeObject("OK");
                        } catch (Exception e) {
                            out.writeObject("[!] Errore: " + e.getMessage());
                        }
                        break;
                    case 1: // calcola i cluster sulla tabella presa dal DB

                        int k = (int) in.readObject();  //numero cluster

                        try {
                            kmeans = new KMeansMiner(k);
                            numIter = kmeans.kmeans(data); //numero iterazioni
                            StringBuilder str = new StringBuilder("\nTabella: " + tabName + "\n" + data);
                            out.writeObject("OK");
                            str.append("\nNumero di Iterazioni: ").append(numIter);
                            out.writeObject(str.toString());
                            out.writeObject(kmeans.getC().toString());
                        } catch (OutOfRangeSampleSize e) {
                            out.writeObject("[!] Errore: " + e.getMessage());
                        } catch (NegativeArraySizeException e) {
                            // A livello logico non andrebbe inserito perché difficilmente qualcuno inserirebbe un numero negativo.
                            // Tuttavia far crashare il client per un "eventuale errore di battitura" non mi sembra altrettanto corretto
                            out.writeObject("[!] Errore: Non è possibile generare un numero di centroidi negativo");
                        }

                        break;
                    case 2: //Salva cluster
                        try {
                            String fileName = tabName + "_" + numIter + ".bin";
                            kmeans.salva(fileName);
                            out.writeObject("OK");
                        } catch (IOException e) {
                            out.writeObject("[!] Errore: " + e.getMessage());
                        }
                        break;
                    case 3: // Carica Cluster
                        tabName = (String) in.readObject();
                        numIter = (int) in.readObject();
                        String fileName = tabName + "_" + numIter + ".bin";
                        try {
                            kmeans = new KMeansMiner(fileName);
                            out.writeObject("OK");
                            out.writeObject(kmeans.getC().toString());
                        } catch (IOException e) {
                            out.writeObject("[!] Errore: " + e.getMessage());
                        }
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("[!] Socket non chiusa: " + e.getMessage());
            }
        }
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;


import exception.ServerException;
import keyboardinput.Keyboard;


public class MainTest {

    /**
     * Stream con risposte del server
     */
    private static ObjectOutputStream out;
    /**
     * stream con richieste del client
     */
    private static ObjectInputStream in;

    /**
     * Stabilisce la connessione con il server
     *
     * @param ip   indirizzo ip del server
     * @param port porta in cui il serve è in ascolot
     * @throws IOException in caso di un'eccezione di I/O
     */
    public MainTest(String ip, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, port); //Port
        System.out.println(socket);

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        ; // stream con richieste del client
    }

    /**
     * Manu di scelta delle operazioni da effettuare
     *
     * @return intero che indivua la scelta presa
     */
    private int menu() {
        int answer;
        System.out.println("Scegli una opzione");
        do {
            System.out.println("(1) Carica Cluster da File");
            System.out.println("(2) Carica Dati");
            System.out.print("Risposta:");
            answer = Keyboard.readInt();
        }
        while (answer <= 0 || answer > 2);
        return answer;

    }

    /**
     * Richiede al server di caricare i cluster da file
     *
     * @return i cluster letti da file
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    private String learningFromFile() throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(3);

        System.out.print("Nome tabella:");
        String tabName = Keyboard.readString();
        out.writeObject(tabName);
        System.out.print("Numero iterate:");
        int k = Keyboard.readInt();
        out.writeObject(k);
        String result = (String) in.readObject();
        if (result.equals("OK"))
            return (String) in.readObject();
        else throw new ServerException(result);

    }

    /**
     * Richiede al server di memorizzare la tabella dal DB
     *
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    private void storeTableFromDb() throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(0);
        System.out.print("Nome tabella:");
        String tabName = Keyboard.readString();
        out.writeObject(tabName);
        String result = (String) in.readObject();
        if (!result.equals("OK"))
            throw new ServerException(result);

    }


    /**
     * Richiede al server di effettuare la ricerca dei cluster sulla tabella
     *
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    private String learningFromDbTable() throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(1);
        System.out.print("Numero di cluster:");
        int k = Keyboard.readInt();
        out.writeObject(k);
        String result = (String) in.readObject();
        if (result.equals("OK")) {
            System.out.println("Clustering output:" + in.readObject());
            return (String) in.readObject();
        } else throw new ServerException(result);


    }

    /**
     * Richiede al server di memorizzare i cluster su file
     *
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    private void storeClusterInFile() throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(2);


        String result = (String) in.readObject();
        if (!result.equals("OK"))
            throw new ServerException(result);

    }

    /**
     * Classe main che avvia il client che si collega all'ip e alla porta passati in input da riga di comando
     *
     * @param args ip, porta
     */
    public static void main(String[] args) {
        String ip = args[0];
        int port = new Integer(args[1]).intValue();
        MainTest main = null;
        try {
            main = new MainTest(ip, port);
        } catch (IOException e) {
            System.out.println(e);
            return;
        }


        do {
            int menuAnswer = main.menu();
            switch (menuAnswer) {
                case 1:
                    try {
                        String kmeans = main.learningFromFile();
                        System.out.println(kmeans);
                    } catch (SocketException e) {
                        System.out.println(e);
                        return;
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                        return;
                    } catch (IOException e) {
                        System.out.println(e);
                        return;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);
                        return;
                    } catch (ServerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2: // learning from db

                    while (true) {
                        try {
                            main.storeTableFromDb();
                            break; //esce fuori dal while
                        } catch (SocketException e) {
                            System.out.println(e);
                            return;
                        } catch (FileNotFoundException e) {
                            System.out.println(e);
                            return;

                        } catch (IOException e) {
                            System.out.println(e);
                            return;
                        } catch (ClassNotFoundException e) {
                            System.out.println(e);
                            return;
                        } catch (ServerException e) {
                            System.out.println(e.getMessage());
                        }
                    } //end while [viene fuori dal while con un db (in alternativa il programma termina)

                    char answer = 'y';//itera per learning al variare di k
                    do {
                        try {
                            String clusterSet = main.learningFromDbTable();
                            System.out.println(clusterSet);

                            main.storeClusterInFile();

                        } catch (SocketException e) {
                            System.out.println(e);
                            return;
                        } catch (FileNotFoundException e) {
                            System.out.println(e);
                            return;
                        } catch (ClassNotFoundException e) {
                            System.out.println(e);
                            return;
                        } catch (IOException e) {
                            System.out.println(e);
                            return;
                        } catch (ServerException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Vuoi ripetere l'esecuzione?(y/n)");
                        answer = Keyboard.readChar();
                    }
                    while (answer == 'y');
                    break; //fine case 2
                default:
                    System.out.println("Opzione non valida!");
            }

            System.out.print("Vuoi scegliere una nuova operazione da menu?(y/n)");
            if (Keyboard.readChar() != 'y')
                break;
        }
        while (true);
    }
}




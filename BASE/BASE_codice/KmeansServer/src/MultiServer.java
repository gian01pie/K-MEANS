import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe MultiServer che modella il funzionamento del Server che resta in attesi di una richiesta di connesione
 * da parte di un client. Stabilita la connessione, l'interazione viene gestita su un'altro thread dalla classe
 * ServerOneCliente mentre MultiServer rimane in ascolto per altre connessioni
 */
public class MultiServer {
    /**
     * Numero di porta su cui il server è in ascolto
     */
    private int PORT = 8080;

    /**
     * Costruttore di classe inizializza il valore della porta
     * @param port porta di ascolto del server
     */
    public MultiServer(int port){
        PORT = port;
        run();
    }

    /**
     * Avvia il funzionamento di un MultiServer
     * @param args
     */
    public static void main(String[] args){
        MultiServer ms = new MultiServer(8080);
    }

    /**
     * Gestisce il funzionamento del MultiServer. Il server resta in attesa di connessioni sulla porta stabilita.
     * Al presentarsi di una connessione fa gestire l'interazione alla classe ServerOneCliente su thread secondario.
     * Il server principale reste invece in ascolto. Il server termina solo al presentarsi di errori
     */
    void run() {
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("Server avviato...");
            try {
                while (true) {
                    Socket socket = s.accept(); //Socket in ascolto
                    System.out.println("Connessione con Client: " + socket.getInetAddress().getHostName());
                    try {
                        new ServerOneClient(socket);
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            } catch (IOException e) {
                System.out.println("[!] Errore: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("[!] Errore: " + e.getMessage());
        }
    }

}

package com.map.kmeansclientui.model;

import com.map.kmeansclientui.model.exception.ServerException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Classe ClientSingleton è una classe singoletto (è possibile una sola istanza di questa classe).
 * Modella l'interazione di un Client con un Server
 */
public class ClientSingleton {
    /**
     * Unica istanza possibile della classe
     */
    private static final ClientSingleton instance = new ClientSingleton();
    /**
     * Stream con risposte del server
     */
    private static ObjectOutputStream out;
    /**
     * stream con richieste del client
     */
    private static ObjectInputStream in;

    /**
     * Costruttore, reso privato per impedire l'istanziazione
     */
    private ClientSingleton() {
        // Costruttore privato per impedire l'istanziazione diretta
    }

    /**
     * Restituisce l'istanza dell'unico oggetto di ClientSingleton
     * @return l'istanza dell'unico oggetto di ClientSingleton
     */
    public static ClientSingleton getInstance(){
        return instance;
    }

    /**
     * Stabilisce la connessione con il server
     *
     * @param ip   indirizzo ip del server
     * @param port porta in cui il serve è in ascolot
     * @throws IOException in caso di un'eccezione di I/O
     */
    public void connectToServer(String ip, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, port); //Port
        System.out.println(socket);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Richiede al server di caricare i cluster da file
     *
     * @param tabName nome della tabella
     * @param k numero d'iterazioni
     * @return la stringa contenente i cluster letti da file
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    public String learningFromFile(String tabName, int k) throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(3);

        out.writeObject(tabName);
        out.writeObject(k);

        String result = (String) in.readObject();
        if (result.equals("OK"))
            return (String) in.readObject();
        else throw new ServerException(result);
    }

    /**
     * Richiede al server di memorizzare la tabella dal DB
     *
     * @param tabName nome della tabella nel DB
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    public void storeTableFromDb(String tabName) throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(0);
        out.writeObject(tabName);
        String result = (String) in.readObject();
        if (!result.equals("OK"))
            throw new ServerException(result);
    }

    /**
     * Richiede al server di effettuare la ricerca dei cluster sulla tabella
     *
     * @param k numero di cluster da calcolare
     * @return la stringa contenente la risposta del server ovvero i cluster calcolati
     * @throws SocketException        se errore nel creare la socket
     * @throws ServerException        se viene sollevata un'eccezione dal server
     * @throws IOException            se avviene un'eccezione di tipo I/O
     * @throws ClassNotFoundException se non si trova la classe di riferimento
     */
    public String learningFromDbTable(int k) throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(1);
        out.writeObject(k);
        String result = (String) in.readObject();
        if (result.equals("OK")) {
            StringBuilder str = new StringBuilder("Clustering output:" + in.readObject() + "\n");
            str.append((String) in.readObject());
            return str.toString();
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
    public void storeClusterInFile() throws SocketException, ServerException, IOException, ClassNotFoundException {
        out.writeObject(2);
        String result = (String) in.readObject();
        if (!result.equals("OK"))
            throw new ServerException(result);
    }
}

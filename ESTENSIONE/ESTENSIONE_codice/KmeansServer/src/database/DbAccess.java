package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe DbAccess che modella la connessione a un DB tramite JDBC
 */
public class DbAccess {
    /**
     * ClassName della classe Driver della libreria JDBC utilizzata,
     * Driver è la classe che stabilisce la vera e propria connessione tra programma e DB
     */
    private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * DBMS al quale ci si intende collegare
     */
    private final String DBMS = "jdbc:mysql";
    /**
     * Identificativo del server su cui risiede la base di dati
     */
    private final String SERVER = "localhost";
    /**
     * Nome della base di dati
     */
    private final String DATABASE = "MapDB";
    /**
     * Porta su cui il DBMS MySQL accetta le connessioni
     */
    private final String PORT = "3306";
    /**
     * Nome dell’utente per l’accesso alla base di dati
     */
    private final String USER_ID = "MapUser";
    /**
     * Password di autenticazione per l’utente identificato da USER_ID
     */
    private final String PASSWORD = "map";
    /**
     * Gestisce una connessione
     */
    private Connection conn;


    // Metodi

    /**
     * Carica il Driver mysql e inizializza la connessione riferita da conn
     *
     * @throws DatabaseConnectionException in caso di fallita connessione al database
     */
    public void initConnection() throws DatabaseConnectionException {
        try {
            // Chiedo al ClassLoader di caricare in memoria l'ObjectClass di Driver
            Class.forName(DRIVER_CLASS_NAME).newInstance();
            // Poiché voglio stabilire la connessione col DB correttamente, qualunque eccezione venga sollevata
            // espello all'esterno una DatabaseConnectionException che mi faccia uscire immediatamente dal costruttore
            // quindi gestisco l'eccezione esternamente
        } catch (ClassNotFoundException err) {
            System.err.println("[!] Driver non trovato: " + err.getMessage());
            throw new DatabaseConnectionException();
        } catch (InstantiationException err) {
            System.err.println("[!] Errore durante l'istanziazione: " + err.getMessage());
            throw new DatabaseConnectionException();
        } catch (IllegalAccessException err) {
            System.err.println("[!] Impossibile accedere al Driver: " + err.getMessage());
            throw new DatabaseConnectionException();
        }

        // costruisco la stringa di connessione
        String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE
                + "?user=" + USER_ID + "&password=" + PASSWORD + "&serverTimezone=UTC";

        try {
            //Configuro la connessione col DB
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException err) {
            System.out.println("[!] SQLException: " + err.getMessage());
            System.out.println("[!] SQLState: " + err.getSQLState());
            System.out.println("[!] VendorError: " + err.getErrorCode());
            throw new DatabaseConnectionException();
        }
    }

    /**
     * Restituisce il riferimento alla connessione stabilita
     *
     * @return riferimento alla connessione stabilita
     */
    Connection getConnection() {
        return conn;
    }

    /**
     * Chiude la connessione
     *
     * @throws SQLException se c'è un errore nell'accesso al DB
     */
    public void closeConnection() throws SQLException {
        conn.close();
    }
}

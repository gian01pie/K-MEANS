package database;

/**
 * Classe DatabaseConnectionException che modella un'Eccezione non controllata sollevata in caso di errore durante
 * la connessione a un DB
 */
public class DatabaseConnectionException extends Exception {
    /**
     * Genera un eccezzione chiamando il costruttore della superclasse
     */
    public DatabaseConnectionException() {
        super("[!] Connessione al Database fallita.");
    }

    /**
     * Genera un eccezzione chiamando il costruttore della superclasse e passandole il messaggio in input
     *
     * @param e messaggio che verrà mostrato dall'eccezione
     */
    public DatabaseConnectionException(String e) {
        super("[!] Connessione al Database fallita: " + e);
    }

    /**
     * Genera un eccezzione chiamando il costruttore della superclasse e passandole un'eccezione
     *
     * @param e eccezione passata
     */
    public DatabaseConnectionException(Exception e) {
        super(e);
    }
}

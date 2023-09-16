package database;

/**
 * Classe DatabaseConnectionException che modella un'Eccezione non controllata sollevata in caso di errore durante
 * la connessione a un DB
 */
public class DatabaseConnectionException extends Exception {
    DatabaseConnectionException(){
        super("[!] Connessione al Database fallita.");
    }
}

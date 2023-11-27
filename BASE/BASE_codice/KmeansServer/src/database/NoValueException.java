package database;

/**
 * Classe DatabaseConnectionException che modella l'assenza di un valore all'interno di un resultSet (risultato di una query)
 */
public class NoValueException extends Exception {
    /**
     * Genera un eccezzione chiamando il costruttore della superclasse
     */
    public NoValueException() {
        super("[!] Valore assente nel resultSet.");
    }
}

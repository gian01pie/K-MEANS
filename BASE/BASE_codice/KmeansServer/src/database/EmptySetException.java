package database;

/**
 * Classe EmptySetException che modella l'eccezione sollevata dalla restituzione di un resultSet (risultato di una query) vuoto
 */
public class EmptySetException extends Exception {
    /**
     * Genera un eccezzione chiamando il costruttore della superclasse
     */
    public EmptySetException() {
        super("[!] Il resultSet restituito è vuoto.");
    }
}

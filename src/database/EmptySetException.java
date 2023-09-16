package database;

/**
 * Classe EmptySetException che modella la restituzione di un resultSet (risultato di una query) vuoto
 */
public class EmptySetException extends Exception{
    EmptySetException() {
        super("[!] Il resultSet restituito è vuoto.");
    }
}

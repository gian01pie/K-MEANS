package database;

/**
 * Classe DatabaseConnectionException che modella l'assenza di un valore all'interno di un resultSet (risultato di una query)
 */
class NoValueException extends Exception{
    NoValueException() {
        super("[!] Valore assente nel resultSet.");
    }
}

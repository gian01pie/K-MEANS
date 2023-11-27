package database;

import database.TableSchema.Column;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Modella l'insieme delle transazioni collezionate in una tabella.
 * Una transazione singola è modellata dalla classe Example
 */
public class TableData {
    /**
     * DB a cui si è connessi
     */
    private final DbAccess db;


    /**
     * Costruttore di classe che inizializza il riferimento al DB a cui si è connessi
     *
     * @param db DB a cui si è connessi
     */
    public TableData(DbAccess db) {
        this.db = db;
    }

    /**
     * Esegue una query sul DB per recuperare solo le tuple diverse nella tabella
     *
     * @param table nome della tabella nel DB
     * @return Lista di transazioni distinte memorizzate nella tabella
     * @throws SQLException      In caso di errori nell'esecuzione della query
     * @throws EmptySetException Se il ResultSet restituito è vuoto
     */
    public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException {
        List<Example> examples = new ArrayList<>();

        // Ricavo lo schema della tabella
        TableSchema tbSchema = new TableSchema(db, table);

        // Creo uno Statement per poter eseguire una query
        try (Statement s = db.getConnection().createStatement();
             // Effettuo la query
             ResultSet r = s.executeQuery("SELECT DISTINCT * FROM " + table)) { //try-with-resources
            // Quando il ResultSet viene restituito ha un cursore che punta immediatamente prima del suo inizio,
            // per questo motivo tramite r.next() metto il cursore nella posizione iniziale del ResultSet
            // r.next() restiuisce treu se c'è un elemento false se non c'è
            if (r.next()) {// se viene restituito un ResultSet con almeno un valore
                // Per quanto detto sopra ho spostato già il cursore alla prima posizione
                // Quindi uso il do while per far spostare il cursore alla fine delle operazioni
                do {
                    Example ex = new Example();
                    // Ciclo sul numero di colonne della tabella
                    for (int i = 0; i < tbSchema.getNumberOfAttributes(); i++) {
                        Object value;
                        // se la colonna osservata ha valori numerici
                        if (tbSchema.getColumn(i).isNumber()) {
                            value = r.getDouble(tbSchema.getColumn(i).getColumnName());
                        } else { // se non è numerico è una stringa (per come è definito TableSchema)
                            value = r.getString(tbSchema.getColumn(i).getColumnName());
                        }
                        // aggiungo il valore all'esempio
                        ex.add(value);
                    }
                    // aggiungo l'esempio alla lista di esempi
                    examples.add(ex);
                } while (r.next()); //finché non ci sono più elementi
            } else { // Se il ResultSet è vuoto
                throw new EmptySetException();
            }
        }
        return examples;
    }

    /**
     * Formula ed esegue una query SQL per estrarre i valori distinti
     * della colonna di nome 'column' ordinati in modo ascendente, i quali vengono restituiti sottoforma di insieme
     *
     * @param table  nome della tabella
     * @param column nome della colonna nella tabella
     * @return insieme dei valori delle colonne distinti ordinati in modalità ascendente
     * @throws SQLException In caso di errori nell'esecuzione della query
     */
    public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
        // Poiché voglio i valori ordinati in modo ascendente utilizzo un TreeSet
        Set<Object> columnValues = new TreeSet<>();
        // Ricavo lo schema della tabella
        TableSchema tbSchema = new TableSchema(db, table);

        // Creo uno Statement per poter eseguire una query
        try (Statement s = db.getConnection().createStatement();
             // Effettuo la query
             ResultSet r = s.executeQuery("SELECT DISTINCT " + column.getColumnName() + " FROM " + table)) { //try-with-resources

            if (column.isNumber()) { // se la colonna osservata ha valori numerici
                while (r.next()) { //finché non ci sono più elementi
                    columnValues.add(r.getDouble(column.getColumnName()));
                }
            } else { // se non è numerico è una stringa (per come è definito TableSchema)
                while (r.next()) {
                    columnValues.add(r.getString(column.getColumnName()));
                }
            }
        }
        return columnValues;
    }

    /**
     * Formula ed esegue una query SQL per estrarre il valore aggregato (valore minimo o massimo)
     * cercato nella colonna di nome clumn della tabella di nome table
     *
     * @param table     nome della tabella
     * @param column    nome della colonna della tabella
     * @param aggregate operatore SQL di aggregazione (min, max)
     * @return Aggregato cercato
     * @throws SQLException     In caso di errori nell'esecuzione della query
     * @throws NoValueException Se il ResultSet restituito è vuoto o il valore calcolato è null
     */
    public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate) throws SQLException, NoValueException {
        Object value = null;
        // Creo uno Statement per poter eseguire una query
        String query = "SELECT " + aggregate.toString() + "(" + column.getColumnName() + ") AS " + column.getColumnName() + " FROM " + table + ";";
        try (Statement s = db.getConnection().createStatement();
             // Effettuo la query
             ResultSet r = s.executeQuery(query)) { //try-with-resources
            if (r.next()) { //Se c'è un elemento
                // se la colonna osservata ha valori numerici
                if (column.isNumber()) {
                    value = r.getDouble(column.getColumnName());
                } else { // se non è numerico è una stringa (per come è definito TableSchema)
                    value = r.getString(column.getColumnName());
                }
                if (value == null) { //se value è nullo
                    throw new NoValueException();
                }
            } else { //Se il treeSet è vuoto
                throw new NoValueException();
            }
        }
        return value;
    }


}

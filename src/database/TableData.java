package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



import database.TableSchema.Column;


/**
 * Modella l'insieme delle transazioni collezionate in una tabella.
 * Una transazione singola è modellata dalla classe Example
 */
public class TableData {
	/**
	 * DB a cui si è connessi
	 */
	DbAccess db;

	
	
	public TableData(DbAccess db) {
		this.db=db;
	}

	/**
	 * Esegue una query sul DB per recuperare solo le tuple diverse nella tabella
	 * @param table nome della tabella nel DB
	 * @return Lista di transazioni distinte memorizzate nella tabella
	 * @throws SQLException In caso di errori nell'esecuzione della query
	 * @throws EmptySetException Se il ResultSet restituito è vuoto
	 */
	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException{
		List<Example> examples = new ArrayList<>();
		try {
			// Ricavo lo schema della tabella
			TableSchema tbSchema = new TableSchema(db, table);

				// Creo uno Statement per poter eseguire una query
			try (Statement s = db.getConnection().createStatement();
				 // Effettuo la query
				 ResultSet r = s.executeQuery("SELECT DISTINCT * FROM " + table))  { //try-with-resources
				// se viene restituito un ResultSet con almeno un valore
				if (r.next()) {
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
			} catch (SQLException err){
				err.getMessage();
			}
		} catch (SQLException err){
			err.getMessage();
		}
		return examples;
	}

	/**
	 * Formula ed esegue una query SQL per estrarre i valori distinti
	 * della colonna di nome 'column' ordinati in modo ascendente, i quali vengono restituiti sottoforma di insieme
	 * @param table nome della tabella
	 * @param column nome della colonna nella tabella
	 * @return insieme dei valori delle colonne distinti ordinati in modalità ascendente
	 * @throws SQLException In caso di errori nell'esecuzione della query
	 */
	public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
		// Poiché voglio i valori ordinati in modo ascendente utilizzo un TreeSet
		Set<Object> columnValues = new TreeSet<>();
		try {
			// Ricavo lo schema della tabella
			TableSchema tbSchema = new TableSchema(db, table);

			// Creo uno Statement per poter eseguire una query
			try (Statement s = db.getConnection().createStatement();
				 // Effettuo la query
				 ResultSet r = s.executeQuery("SELECT DISTINCT " + column.getColumnName() + " FROM " + table)) { //try-with-resources

				while (r.next()) { //finché non ci sono più elementi
					Object value;
					// se la colonna osservata ha valori numerici
					if (column.isNumber()) {
						value = r.getDouble(column.getColumnName());
					} else { // se non è numerico è una stringa (per come è definito TableSchema)
						value = r.getString(column.getColumnName());
					}
					columnValues.add(value);
				}
			} catch (SQLException err) {
				err.getMessage();
			}
		} catch (SQLException err) {
			err.getMessage();
		}
		return columnValues;
	}

	/**
	 * Formula ed esegue una query SQL per estrarre il valore aggregato (valore minimo o massimo)
	 * cercato nella colonna di nome clumn della tabella di nome table
	 * @param table nome della tabella
	 * @param column nome della colonna della tabella
	 * @param aggregate operatore SQL di aggregazione (min, max)
	 * @return Aggregato cercato
	 * @throws SQLException In caso di errori nell'esecuzione della query
	 * @throws NoValueException Se il ResultSet restituito è vuoto o il valore calcolato è null
	 */
	public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate) throws SQLException, NoValueException {
		Object value = null;
		String valueAggregator = "";
		switch (aggregate) {
			case MIN -> valueAggregator = "MIN";
			case MAX -> valueAggregator = "MAX";
		}
		// Creo uno Statement per poter eseguire una query
		try (Statement s = db.getConnection().createStatement();
			 // Effettuo la query
			 ResultSet r = s.executeQuery("SELECT " + valueAggregator + "(" + column.getColumnName() + ")" + " FROM " + table)) { //try-with-resources

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
		} catch (SQLException err) {
			err.getMessage();
		}
		return value;
	}

	

	

}
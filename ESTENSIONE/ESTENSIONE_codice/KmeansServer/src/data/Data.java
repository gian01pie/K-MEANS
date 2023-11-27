package data;

import database.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Classe data.Data modella l'insieme di transazioni (o tuple, o esempi)
 */
public class Data {
    /**
     * Lista di esempi (transazioni, tuple) che costituiscono il dataset
     */
    private List<Example> data;
    /**
     * Cardinalità dell'insieme di transazioni (i.e. numero di esempi in data)
     */
    private int numberOfExamples;
    /**
     * Lista degli attributi in ciascuna tupla (schema della tabella di dati)
     */
    private List<Attribute> attributeSet;

    /**
     * Costruttore di classe:
     * <ul>
     * <li> Inizializza data con le transazioni in tabella
     * <li> Inizializza attributeSet con il numero di attributi nella tabella
     * <li> Inizializza numberOfExamples
     * </ul>
     *
     * @param table nome della tabella nel DB
     */
    public Data(String table) {
        // Avvio la connesione
        DbAccess db = new DbAccess();
        try {
            db.initConnection();
        } catch (DatabaseConnectionException e) {
            // Se si verifica un qualche errore è inutile proseguire
            // perchè si potrebbe avere dati compromessi
            // lancio quindi una RuntimeException perchè non è controllata
            // in questo modo posso non cambiare la firma del costruttore aggiungendo le eccezioni non gestite
            throw new RuntimeException(e.getMessage());
        }

        // Recupero la tabella dal database
        TableData tbData = new TableData(db);
        TableSchema tbSchema = null;
        try {
            data = tbData.getDistinctTransazioni(table);
            tbSchema = new TableSchema(db, table);
        } catch (EmptySetException | SQLException e) {
            // Se si verifica un qualche errore è inutile proseguire
            // perchè si potrebbe avere dati compromessi
            // lancio quindi una RuntimeException perchè non è controllata
            // in questo modo posso non cambiare la firma del costruttore aggiungendo le eccezioni non gestite
            throw new RuntimeException(e.getMessage());
        }

        // numberOfExamples
        numberOfExamples = data.size();

        //attributeSet
        attributeSet = new LinkedList<Attribute>();

        for (int i = 0; i < tbSchema.getNumberOfAttributes(); i++) {
            if (tbSchema.getColumn(i).isNumber()) {
                try {
                    double min = (double) tbData.getAggregateColumnValue(table, tbSchema.getColumn(i), QUERY_TYPE.MIN);
                    double max = (double) tbData.getAggregateColumnValue(table, tbSchema.getColumn(i), QUERY_TYPE.MAX);
                    attributeSet.add(new ContinuousAttribute(tbSchema.getColumn(i).getColumnName(), i, min, max));
                } catch (SQLException | NoValueException e) {
                    // Se si verifica un qualche errore è inutile proseguire
                    // perchè si potrebbe avere dati compromessi
                    // lancio quindi una RuntimeException perchè non è controllata
                    // in questo modo posso non cambiare la firma del costruttore aggiungendo le eccezioni non gestite
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                try {
                    Set<Object> columnValues = tbData.getDistinctColumnValues(table, tbSchema.getColumn(i));
                    attributeSet.add(new DiscreteAttribute(tbSchema.getColumn(i).getColumnName(), i, columnValues.toArray(new String[columnValues.size()])));
                } catch (SQLException e) {
                    // Se si verifica un qualche errore è inutile proseguire
                    // perchè si potrebbe avere dati compromessi
                    // lancio quindi una RuntimeException perchè non è controllata
                    // in questo modo posso non cambiare la firma del costruttore aggiungendo le eccezioni non gestite
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Restituisce la cardinalità dell'insieme di transazioni
     *
     * @return cardinalità dell'insieme di transazioni
     */
    public int getNumberOfExamples() {
        return numberOfExamples;
    }

    /**
     * Restituisce la cardinalità dell'insieme degli attributi
     *
     * @return cardinalità dell'insieme degli attributi
     */
    public int getNumberOfAttributes() {
        return attributeSet.size();
    }

    /**
     * Restituisce lo schema dei dati
     *
     * @return restituisce lo schema dei dati
     */
    List<Attribute> getAttributeSchema() {
        return attributeSet;
    }

    /**
     * Restituisce il valore assunto, in data, dall'attributo in posizione attributeIndex, nella riga in posizione exampleIndex.
     *
     * @param exampleIndex   indice di riga della matrice data
     * @param attributeIndex indice di colonna della matrice data
     * @return valore assunto, in data, dall'attributo in posizione attributeIndex,
     * nella riga in posizione exampleIndex.
     */
    public Object getAttributeValue(int exampleIndex, int attributeIndex) {
        // restituisce il valore dell'attributo
        return data.get(exampleIndex).get(attributeIndex);
    }

    /**
     * Restituisce l'attributo in posizione index in attributeSet
     *
     * @param index indice di posizione in attributeSet
     * @return restituisce l'attributo in posizione index in attributeSet
     */
    Attribute getAttribute(int index) {
        return attributeSet.get(index);
    }

    /**
     * Crea una stringa in cui memorizza lo schema della tabella (attributeSet)
     * e le transazioni memorizzate in data, opportunamente enumerate.
     *
     * @return restuisce la stringa che modella lo stato dell'oggetto
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Outlook Temperature Humidity Wind PlayTennis\n");
        for (int i = 0; i < numberOfExamples; i++) {
            s.append(i + 1).append(": ");
            for (int j = 0; j < attributeSet.size(); j++) {
                s.append(data.get(i).get(j)).append(" ");
            }
            s.append("\n");

        }
        return s.toString();
    }

    /**
     * Restituisce Un oggetto di tipo data.Tuple che modella la riga index in data
     * come una sequenza di coppie Attributo-ValoreAssunto
     *
     * @param index indice di riga
     * @return Un oggetto di tipo data.Tuple che modella la riga index in data
     * come una sequenza di coppie Attributo-ValoreAssunto
     * <ul><li>e.g.: <pre>Outlook="sunny", Temperature="hot", Humidity="high", Wind="weak", PlayTennis="no"</pre> </li></ul>
     */
    public Tuple getItemSet(int index) {
        Tuple tuple = new Tuple(attributeSet.size());
        for (int i = 0; i < attributeSet.size(); i++)
            if (attributeSet.get(i) instanceof ContinuousAttribute) {
                tuple.add(new ContinuousItem(attributeSet.get(i), getAttributeValue(index, i)), i);
            } else if (attributeSet.get(i) instanceof DiscreteAttribute) {
                tuple.add(new DiscreteItem(attributeSet.get(i), getAttributeValue(index, i)), i);
            }
        return tuple;
    }

    /**
     * Sceglie randomicamente i semi (centroidi) da utilizzare per la definizione dei cluster
     *
     * @param k numero di cluster da generare
     * @return array di k interi rappresentanti gli indici di riga in data per le
     * tuple scelte come centroidi (passo 1 del k-means)
     * @throws OutOfRangeSampleSize se k è maggiore rispetto al numero di centroidi generabili,
     * oppure è <span>&#8804;</span> 0
     */
    public int[] sampling(int k) throws OutOfRangeSampleSize {
        if (k <= 0 || k > data.size()) {
            throw new OutOfRangeSampleSize();
        }
        int centroidIndexes[] = new int[k];
        //choose k random different centroids in data.
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < k; i++) {
            boolean found = false;
            int c;
            do {
                found = false;
                c = rand.nextInt(getNumberOfExamples());
                // verify that centroid[c] is not equal to a centroide already stored in CentroidIndexes
                for (int j = 0; j < i; j++)
                    if (compare(centroidIndexes[j], c)) {
                        found = true;
                        break;
                    }
            }
            while (found);
            centroidIndexes[i] = c;
        }
        return centroidIndexes;
    }

    /**
     * Confronta due righe di data.Data, ovvero due transazioni
     *
     * @param i indice di riga nell'insieme in data.Data
     * @param j indice di riga nell'insieme in data.Data
     * @return Vero se due righe contengono gli stessi valori, falso altrimenti
     */
    private boolean compare(int i, int j) {
        Tuple t1 = getItemSet(i);
        Tuple t2 = getItemSet(j);
        for (int k = 0; k < t1.getLength(); k++)
            if (!t1.get(k).equals(t2.get(k)))
                return false;
        return true;
    }

    /**
     * Calcola il centroide (prototipo) rispetto all'attributo in input sulle righe
     * il cui indice è in idList
     *
     * @param idList    insieme di indici di riga da considerare
     * @param attribute attributo su cui calcolare il centroide
     * @return valore del centroide rispetto al parametro attribute calcolato per le rgihe in idList
     */
    Object computePrototype(Set<Integer> idList, Attribute attribute) {
        if (attribute instanceof ContinuousAttribute) {
            return computePrototype(idList, (ContinuousAttribute) attribute);
        } else {
            return computePrototype(idList, (DiscreteAttribute) attribute);
        }
    }

    /**
     * Determina il valore che occorre più frequentemente per un data.DiscreteAttribute nel sottoinsieme di dati individuato da idList
     *
     * @param idList    insieme degli indici degli elementi di data appartenenti ad un cluster
     * @param attribute data.DiscreteAttribute su cui calcolare il centroide
     * @return valore del centroide rispetto al parametro discreto attribute
     */
    private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {    //messo privato perchè viene chiamato da Object computePrototype
        // HashMap che avrà come chiavi i valori dell'attributo discreto,
        // e come valore le occorrenze di quell'attributo nelle tuple facenti parte del cluster indicato da idList
        Map<String, Integer> mapFreq = new HashMap<>();
        for (String value : attribute) {
            mapFreq.put(value, attribute.frequency(this, idList, value));
        }
		/*
		 Per trovare la chiave con valore massimo utilizzo la funzione max di collections che permette di trovare
		 il valore massimo in una collection usando un comparator come criterio di comparazione
		 poiché hashMap non è una collection ci serviamo della funzione entrySet che restituisce un insieme delle coppie
		 presenti in hashMap. Max restituisce un oggetto dello stesso tipo della collection su cui lavora,
		 in questo caso Map.Entry<String, Integer>, ovvero una coppia <String, Integer> facente parte di un entrySet
		*/
        Map.Entry<String, Integer> maxEntry = Collections.max(mapFreq.entrySet(), Map.Entry.comparingByValue());
        return maxEntry.getKey();
    }

    /**
     * Determina il valore prototipo come media dei valori
     * osservati per attribute nelle transazioni di data aventi indice di riga in idList
     *
     * @param idList    insieme degli indici degli elementi di data appartenenti ad un cluster
     * @param attribute data.ContinuousAttribute su cui calcolare il centroide
     * @return valore del centroide rispetto al parametro continuo attribute
     */
    private Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
        Double avg = 0.0;
        for (Integer i : idList) {
            avg += (double) getAttributeValue(i, attribute.getIndex());
        }
        return (avg / idList.size());
    }
}

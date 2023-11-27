package data;

import java.io.Serializable;
import java.util.Set;

/**
 * Classe data.Tuple che modella una tupla come sequenza di coppie attributo-valore
 * <ul><li>e.g.: <pre>Outlook="sunny", Temperature="hot", ...</pre> </li></ul>
 */
public class Tuple implements Serializable {
    /**
     * Tupla di coppie attributo valore
     */
    private final Item[] tuple;

    /**
     * Costruttore di classe che costruisce l'oggetto riferito da tuple
     *
     * @param size dimensione dell'array tuple
     */
    public Tuple(int size) {
        tuple = new Item[size];
    }

    /**
     * Restituisce la dimensione della tupla
     *
     * @return restituisce la dimensione di tuple
     */
    public int getLength() {
        return tuple.length;
    }

    /**
     * Restituisce l'item in posizione i
     *
     * @param i indice di posizione dell'item in tuple
     * @return l'item (coppia Atrributo=valore) in posizione i
     */
    public Item get(int i) {
        return tuple[i];
    }

    /**
     * Aggiunge l'item (coppia Atrributo=valore) c alla posizione i di tuple
     *
     * @param c item da aggiungere a tuple
     * @param i indice della posizione in cui effettuare l'aggiunta
     */
    public void add(Item c, int i) {
        tuple[i] = c;
    }

    /**
     * Calcola la distanza tra la tupla di riferimento e la tupla obj.
     * La distanza è calcolata come la somma delle distanze tra gli item in posizioni uguali nelle due tuple
     *
     * @param obj tupla da cui calcolare la distanza
     * @return distanza tra la tupla corrente e la tupla obj
     */
    public double getDistance(Tuple obj) {
        double sum = 0;
        for (int i = 0; i < tuple.length; i++) {
            sum += tuple[i].distance(obj.get(i).getValue());
        }
        return sum;
    }

    /**
     * Calcola la media delle distanze tra la tupla corrente
     * e quelle ottenibili dalle righe della matrice in data aventi indice in clusteredData
     *
     * @param data          dataset che contiene tutte le tuple
     * @param clusteredData insieme di tuple (indici di riga di data) delle quali calcolare la distanza
     * @return media delle distanze da data a ciascuna tupla indicizzata da clusteredData
     */
    public double avgDistance(Data data, Set<Integer> clusteredData) {
        double p = 0.0;
        double sumD = 0.0;
        for (Integer i : clusteredData) {
            double d = getDistance(data.getItemSet(i));
            sumD += d;
        }
        p = sumD / clusteredData.size();
        return p;
    }

    /**
     * Rappresenta una tupla sottoforma di stringa
     *
     * @return una stringa rappresentante la tupla
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T: ");
        for (int i = 0; i < tuple.length; i++) {
            sb.append(tuple[i].toString());
            if (i < tuple.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}

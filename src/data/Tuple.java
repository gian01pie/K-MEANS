package data;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe data.Tuple che modella una tupla come sequenza di coppie attributo-valore
 * <li>e.g.: <pre>Outlook="sunny", Temperature="hot", ...</pre> </li>
 */
public class Tuple {
    /**
     * tupla di coppie attributo valore
     */
    private Item []tuple;

    /**
     * Costruttore di classe che costruisce l'oggetto riferito da tuple
     * @param size dimensione dell'array tuple
     */
    Tuple(int size){
        tuple = new Item[size];
    }

    /**
     * @return restituisce la dimensione di tuple
     */
    public int getLength(){
        return tuple.length;
    }

    /**
     * @param i indice di posizione dell'item in tuple
     * @return l'item (coppia Atrributo=valore) in posizione i
     */
    public Item get(int i){
        return tuple[i];
    }

    /**
     * Aggiunge l'item (coppia Atrributo=valore) c alla posizione i di tuple
     * @param c item da aggiungere a tuple
     * @param i indice della posizione in cui effettuare l'aggiunta
     */
    void add(Item c, int i){
        tuple[i] = c;
    }

    /**
     * Calcola la distanza tra la tupla di riferimento e la tupla obj.
     * La distanza è calcolata come la somma delle distanze tra gli item in posizioni uguali nelle due tuple
     * @param obj tupla da cui calcolare la distanza
     * @return distanza tra la tupla corrente e la tupla obj
     */
    public double getDistance(Tuple obj){
        double sum = 0;
        for (int i = 0; i < tuple.length; i++){
            sum += tuple[i].distance(obj.get(i).getValue());
        }
        return sum;
    }

    /**
     * Calcola la media delle distanze tra la tupla corrente
     * e quelle ottenibili dalle righe della matrice in data aventi indice in clusteredData
     * @param data dataset che contiene tutte le tuple
     * @param clusteredData insieme di tuple (indici di riga di data) delle quali calcolare la distanza
     * @return media delle distanze da data a ciascuna tupla indicizzata da clusteredData
     */
    public double avgDistance(Data data, Set<Integer> clusteredData){
        double p = 0.0;
        double sumD = 0.0;
        for (Integer i : clusteredData) {
            double d = getDistance(data.getItemSet(i));
            sumD += d;
        }
        p = sumD/clusteredData.size();
        return p;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T: ");
        for (int i = 0; i < tuple.length; i++){
            sb.append(tuple[i].toString());
            if (i < tuple.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // @TODO rimuovere funzione di TEST
    public static void main(String args[]){
        // Creazione degli attributi
        DiscreteAttribute da = new DiscreteAttribute("Disney", 0,
                new String[]{"topolino","paperino","pippo"});
        DiscreteAttribute da1 = new DiscreteAttribute("Streamer,", 0,
                new String[]{"dario","nanni","dada"});
        DiscreteAttribute da2 = new DiscreteAttribute("Penne,", 0,
                new String[]{"biro","stilo","bic"});
        // creazione delle tuple
        Tuple t = new Tuple(3);
        Tuple t1 = new Tuple(3);
        // agiunta valori
        t.add(new DiscreteItem(da, "topolino"), 0);
        t.add(new DiscreteItem(da1, "dario"), 1);
        t.add(new DiscreteItem(da2, "bic"), 2);

        t1.add(new DiscreteItem(da, "paperino"), 0);
        t1.add(new DiscreteItem(da1, "nanni"), 1);
        t1.add(new DiscreteItem(da2, "biro"), 2);
        //Test
        System.out.println("Test funzioni:");
        System.out.println("getLength:" + t.getLength());
        System.out.println("get(2):" + t.get(2));
        System.out.println("t.toString: " + t);
        System.out.println("t1.toString: " + t1);
        System.out.println("getDistance: " + t.getDistance(t1));
        //avg Distance
        Data d = new Data();
        Set<Integer> cs = new HashSet<>();
        cs.add(0);
        cs.add(1);
        cs.add(3);
        cs.add(4);
        Tuple t2 = d.getItemSet(5);
        System.out.println("Data:");
        System.out.println(d);
        System.out.println("clusteredData:");
        for (Integer i : cs){
            System.out.println(cs.toArray()[i]);
        }
        System.out.println("t2:" + t2);
        System.out.println("t2.avgDistance: " + t2.avgDistance(d,cs));
    }
}

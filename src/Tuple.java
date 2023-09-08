/**
 * Classe Tuple che modella una tupla come sequenza di coppie attributo-valore
 */
class Tuple {
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
    int getLength(){
        return tuple.length;
    }

    /**
     * @param i indice di posizione dell'item in tuple
     * @return l'item in posizione i
     */
    Item get(int i){
        return tuple[i];
    }

    /**
     * Aggiunge l'item c alla posizione i di tuple
     * @param c item da aggiungere a tuple
     * @param i indice della posizione in cui effettuare l'aggiunta
     */
    void add(Item c, int i){
        tuple[i] = c;
    }

    /**
     * Calcola la distanza tra la tupla di riferimento e la tupla obj.
     * La distanza è calcolata come la somma tra gli item in posizioni uguali nelle due tuple
     * @param obj tupla da cui calcolare la distanza
     * @return distanza tra la tupla corrente e la tupla obj
     */
    double getDistance(Tuple obj){
        double sum = 0;
        for (int i = 0; i < this.tuple.length; i++){
            sum += this.tuple[i].distance(obj.get(i).getValue());
        }
        return sum;
    }

    /**
     * Calcola la media delle distanze tra la tupla
     * corrente e quelle ottenibili dalle righe della matrice in data aventi indice in
     * clusteredData
     * @param data sataset che contiene tutte le tuple
     * @param clusteredData insieme di tuple dalle quali calcolare la distanza
     * @return media delle distanze
     */
    double avgDistance(Data data, int clusteredData[]){
        double p = 0.0;
        double sumD = 0.0;
        for (int i=0; i<clusteredData.length; i++ ) {
            double d = getDistance(data.getItemSet(clusteredData[i]));
            sumD += d;
        }
        p = sumD/clusteredData.length;
        return p;
    }
}

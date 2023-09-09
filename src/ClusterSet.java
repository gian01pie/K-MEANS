/**
 * Classe ClusterSet che rappresenta un insieme di cluster (determinati tramite il K-means)
 */
class ClusterSet {
    private Cluster C[];
    /**
     * Posizione valida per la memorizzazione di un nuovo cluster in C
     */
    private int i = 0;

    /**
     * Costruttore di classe che crea l'oggetto riferito da C
     * @param k numero di cluster da generare
     */
    ClusterSet(int k){
        C = new Cluster[k];
    }

    /**
     * Inserisce il cluster c nella posizione i
     * @param c cluster da inserire
     */
    void add(Cluster c){
        C[i] = c;
        i++;
    }

    /**
     * @param i posizione all'interno dell'insieme di cluster C
     * @return Cluster in posizione i
     */
    Cluster get(int i){
        return C[i];
    }

    /**
     * Sceglie i centroidi per la prima iterazione, crea un cluster per ogni centroide e lo memorizza
     * nell'insieme dei cluster C
     * @param data insieme delle tuple
     */
    void initializeCentroids(Data data){
        // array che contiene gli indici di riga delle tuple scelte come centroidi
        int centroidIndexes[]=data.sampling(C.length);
        for(int i=0;i<centroidIndexes.length;i++)
        {
            // memorizza la tupla (che è un centroide in questo caso) di indice i
            Tuple centroidI = data.getItemSet(centroidIndexes[i]);
            // aggiunge il cluster cenerato dal centroide scelto nell'insieme dei cluster
            add(new Cluster(centroidI));
        }
    }

    /**
     * Calcola la distanza tra la tupla in input ed il centroide di ciascun cluster nell'insieme C.
     * Restituisce il cluster più vicino
     * @param tuple tupla di cui calcolare il cluster più vicino
     * @return cluster più vicino alla tupla passata in input
     */
    Cluster nearestCluster(Tuple tuple){
        // suppongo che il primo cluster nell'insieme sia il minimo
        Cluster nearestC = C[0];
        for (int i = 1; i < C.length; i++){
            // se il cluster successivo ha una distanza minore dalla tupla rispetto al cluster precedente
            // aggiorno il valore di nearestC al cluster più vicino
            if (tuple.getDistance(this.C[i].getCentroid()) < tuple.getDistance(nearestC.getCentroid())){
                nearestC = C[i];
            }
        }
        return nearestC;
    }

    /**
     * Restituisce il cluster che contenente la transazione di indice i
     * @param id indice della transazione
     * @return cluster contenente la transazione, null se la transazione non appartiena ad alcun cluster
     */
    Cluster currentCluster(int id){
        for (int i = 0; i < C.length; i++){
            if (C[i].contain(id)){
                return C[i];
            }
        }
        return null;
    }

    /**
     * Calcola il nuovo centroide per ciascun cluster in C
     * @param data
     */
    void updateCentroids(Data data){
        for (int i = 0; i < C.length; i++) {
            C[i].computeCentroid(data);
        }
    }

    /**
     * @return Restituisce una stringa fatta da ciascun centroide nell'insieme dei cluster
     */
    public String toString(){
        String str = "";
        for (int i = 0; i < C.length; i++) {
            str += i + ": " + C[i].getCentroid().toString() + "\n";
        }
        return str;
    }

    /**
     * @param data
     * @return Restituisce lo stato di ciascun cluster in C
     */
    public String toString(Data data ){
        String str="";
        for(int i = 0; i < C.length; i++){
            if (C[i] != null){
                str += i + ": " + C[i].toString(data) + "\n";
            }
        }
        return str;
    }

}

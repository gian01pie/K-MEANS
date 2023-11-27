package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

import java.io.Serializable;

/**
 * Classe mining.ClusterSet che rappresenta un insieme di cluster (determinati tramite il K-means)
 */
public class ClusterSet implements Serializable {
    /**
     * Insieme dei cluster
     */
    private final Cluster[] C;
    /**
     * Posizione valida per la memorizzazione di un nuovo cluster in C
     */
    private int i = 0;

    /**
     * Costruttore di classe che crea l'oggetto riferito da C
     *
     * @param k numero di cluster da generare
     */
    public ClusterSet(int k) {
        C = new Cluster[k];
    }

    /**
     * Inserisce il cluster c nella posizione i
     *
     * @param c cluster da inserire
     */
    public void add(Cluster c) {
        C[i] = c;
        i++;
    }

    /**
     * Restituisce il cluster alla posizione in input
     *
     * @param i posizione all'interno dell'insieme di cluster C
     * @return mining.Cluster in posizione i
     */
    public Cluster get(int i) {
        return C[i];
    }

    /**
     * Sceglie i centroidi per la prima iterazione, crea un cluster per ogni centroide e lo memorizza
     * nell'insieme dei cluster C
     *
     * @param data insieme delle tuple
     * @throws OutOfRangeSampleSize se k è maggiore rispetto al numero di centroidi generabili, oppure è <span>&#8804;</span> 0
     */
    public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
        // array che contiene gli indici di riga delle tuple scelte come centroidi
        int[] centroidIndexes = data.sampling(C.length);
        for (int centroidIndex : centroidIndexes) {
            // memorizza la tupla (che è un centroide in questo caso) d'indice i
            Tuple centroidI = data.getItemSet(centroidIndex);
            // aggiunge il cluster, individuato dal centroide scelto, nell'insieme dei cluster
            add(new Cluster(centroidI));
        }
    }

    /**
     * Calcola la distanza tra la tupla in input ed il centroide di ciascun cluster nell'insieme C.
     * Restituisce il cluster più vicino alla tupla
     *
     * @param tuple tupla di cui calcolare il cluster più vicino
     * @return cluster il cui centroide è più vicino alla tupla passata in input
     */
    public Cluster nearestCluster(Tuple tuple) {
        // suppongo che il primo cluster nell'insieme sia il più vicino
        Cluster nearestC = C[0];
        // il ciclo parte da 1 perchè l'indice 0 è quello che ho assunto come minimo
        for (int i = 1; i < C.length; i++) {
            // se il cluster successivo ha una distanza minore dalla tupla rispetto al cluster precedente
            // aggiorno il valore di nearestC al cluster più vicino
            if (tuple.getDistance(this.C[i].getCentroid()) < tuple.getDistance(nearestC.getCentroid())) {
                nearestC = C[i];
            }
        }
        return nearestC;
    }

    /**
     * Restituisce il cluster contenente la transazione di indice i
     *
     * @param id indice della transazione
     * @return cluster contenente la transazione, null se la transazione non appartiena ad alcun cluster
     */
    public Cluster currentCluster(int id) {
        for (Cluster cluster : C) {
            if (cluster.contain(id)) {
                return cluster;
            }
        }
        return null;
    }

    /**
     * Calcola il nuovo centroide per ciascun cluster in C
     *
     * @param data insieme di transazioni
     */
    public void updateCentroids(Data data) {
        for (Cluster cluster : C) {
            cluster.computeCentroid(data);
        }
    }

    /**
     * @return Restituisce una stringa fatta da ciascun centroide nell'insieme dei cluster
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < C.length; i++) {
            str += i + ": " + C[i].getCentroid().toString() + "\n";
        }
        return str;
    }

    /**
     * Restituisce la rappresentazione sottoforma di stringa dei cluster
     *
     * @param data riferimento alla tabella di transazioni
     * @return Restituisce lo stato di ciascun cluster in C
     */
    public String toString(Data data) {
        String str = "";
        for (int i = 0; i < C.length; i++) {
            if (C[i] != null) {
                str += i + ": " + C[i].toString(data) + "\n";
            }
        }
        return str;
    }

}

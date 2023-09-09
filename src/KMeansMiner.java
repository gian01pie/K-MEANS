/**
 * Classe KMeansMiner che implementa l'algoritmo K-Means
 */
class KMeansMiner {
    /**
     * Insieme dei cluster
     */
    private ClusterSet C;

    /**
     * Crea l'insieme di cluster riferito da C
     * @param k numero di cluster da generare
     */
    KMeansMiner(int k){
        C = new ClusterSet(k);
    }

    /**
     * @return restituisce l'insieme dei cluster C
     */
    ClusterSet getC(){
        return C;
    }

    /**
     * Esegue l'algoritmo k-means seguendo i passi dello pseudocodice:
     * <p>
     * <ol type="1">
     *   <li>Scelta casuale di centroidi per k clusters;</li>
     *   <li>Assegnazione di ciascuna riga della matrice in data al cluster avente
     * centroide più vicino all'esempio;</li>
     *   <li>Calcolo dei nuovi centroidi per ciascun cluster;</li>
     *   <li>Ripete i passi 2 e 3. finché due iterazioni consecuitive non restituiscono
     * centroidi uguali.</li>
     * </ol>
     * </p>
     *
     * @param data insieme delle transazione
     * @return numero di iterazioni eseguite
     */
    int kmeans(Data data){
        int numberOfIterations=0;
        //STEP 1
        C.initializeCentroids(data);
        boolean changedCluster=false;
        do{
            numberOfIterations++;
            //STEP 2
            changedCluster=false;
            for(int i=0;i<data.getNumberOfExamples();i++){
                Cluster nearestCluster = C.nearestCluster(data.getItemSet(i));
                Cluster oldCluster = C.currentCluster(i);
                boolean currentChange = nearestCluster.addData(i);
                if(currentChange)
                    changedCluster = true;
                //rimuovo la tupla dal vecchio cluster
                if(currentChange && oldCluster != null)
                    //il nodo va rimosso dal suo vecchio cluster
                    oldCluster.removeTuple(i);
            }
            //STEP 3
            C.updateCentroids(data);
        }
        while(changedCluster);
        return numberOfIterations;
    }

}

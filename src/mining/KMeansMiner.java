package mining;

import data.Data;
import data.OutOfRangeSampleSize;

import java.io.*;

/**
 * Classe mining.KMeansMiner che implementa l'algoritmo K-Means
 */
public class KMeansMiner {
    /**
     * Insieme dei cluster individuati dal K-Means
     */
    private ClusterSet C;

    /**
     * Crea l'insieme di cluster riferito da C
     * @param k numero di cluster da generare
     */
    public KMeansMiner(int k){
        C = new ClusterSet(k);
    }

    /**
     * Costruttore che carica il ClusterSet da file
     *
     * @param fileName path file da cui caricare il ClusterSet
     * @throws FileNotFoundException il file non esiste o non può essere aperto per qualche ragione
     * @throws IOException errore di I/O nella lettura dello stream
     * @throws ClassNotFoundException impossibile trovare la classe dell'oggetto serializzato
     */
    public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        // il try-with-resources si assicura che ogni risorsa aperta nella sua dichiarazione venga chiusa,
        // sia quando si è terminato il lavoro sulla risorsa, sia quando viene sollevata un'eccezione
        // è equivalente a scrivere try{...} finally{...}
        try (FileInputStream inFile = new FileInputStream(fileName);
             ObjectInputStream inStream = new ObjectInputStream(inFile)){
            C = (ClusterSet) inStream.readObject();
        }
    }

    /**
     * @return restituisce l'insieme dei cluster C
     */
    public ClusterSet getC(){
        return C;
    }

    /**
     * Salva il ClusterSet sul file dato come parametro
     * @param filename path file su cui salvare il ClusterSet
     * @throws FileNotFoundException il file non può essere aperto perchè non si hanno i permessi
     * @throws IOException errore di I/O nella scrittura dello stream
     */
    public void salva(String filename) throws FileNotFoundException, IOException {
        // il try-with-resources si assicura che ogni risorsa aperta nella sua dichiarazione venga chiusa,
        // sia quando si è terminato il lavoro sulla risorsa, sia quando viene sollevata un'eccezione
        // è equivalente a scrivere try{...} finally{...}
        try (FileOutputStream outFile = new FileOutputStream(filename); //try-with-resources
             ObjectOutputStream outStream = new ObjectOutputStream(outFile)){
            outStream.writeObject(C);
        }
    }

    /**
     * Esegue l'algoritmo k-means seguendo i passi dello pseudocodice:
     * <p>
     * <ol type="1">
     *   <li>Scelta casuale di centroidi per k clusters;</li>
     *   <li>Assegnazione di ciascuna riga della matrice in data al cluster avente
     * centroide più vicino all'esempio;</li>
     *   <li>Calcolo dei nuovi centroidi per ciascun cluster;</li>
     *   <li>Ripete i passi 2 e 3 finché due iterazioni consecuitive non restituiscono
     * centroidi uguali.</li>
     * </ol>
     * </p>
     *
     * @param data insieme delle transazioni
     * @return numero di iterazioni eseguite
     */
    public int kmeans(Data data) throws OutOfRangeSampleSize {
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

    public static void main(String[] args){
        try {
            Data data = new Data();
            System.out.println(data);
            KMeansMiner kmeans = new KMeansMiner("temp.bin");
            System.out.println(kmeans.getC().toString());

        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (IOException err) {
            System.out.println(err.getMessage());
        } catch (ClassNotFoundException err) {
            System.out.println(err.getMessage());
        } /*catch (OutOfRangeSampleSize err) {
            System.out.println(err.getMessage());
        }*/
    }

}

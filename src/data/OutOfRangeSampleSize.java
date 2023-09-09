package data;

/**
 * Classe OutOfRangeSampleSize che modella un'eccezione controllata (estende direttamente Exception)
 * da considerare qualora il numero k di cluster inserito dall'utente è maggiore rispetto al numero di centroidi
 * generabili dall'insieme di transazione
 */
public class OutOfRangeSampleSize extends Exception{
    OutOfRangeSampleSize(){}
    OutOfRangeSampleSize(String errorMsg){
        super(errorMsg);
    }
    OutOfRangeSampleSize(String errorMsg, Exception e){
        super(errorMsg, e);
    }
}
package data;

/**
 * Classe OutOfRangeSampleSize che modella un'eccezione controllata (estende direttamente Exception)
 * da considerare qualora il numero k di cluster inserito dall'utente è maggiore rispetto al numero di centroidi
 * generabili dall'insieme di transazione
 */
public class OutOfRangeSampleSize extends Exception {
    /**
     * Genera un eccezzione chiamando il costruttore della superclasse
     */
    public OutOfRangeSampleSize() {
        super("Numero di cluster k maggiore rispetto al numero di centroidi generabili");
    }

    /**
     * Genera un eccezzione chiamando il costruttore della superclasse e passandole errorMsg
     *
     * @param errorMsg messaggio che verrà mostrato dall'eccezione
     */
    public OutOfRangeSampleSize(String errorMsg) {
        super(errorMsg);
    }
}

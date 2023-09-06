/**
 * classe astratta Attribute:
 * modella le entità attributo membri e metodi comuni a tutti gli attributi, siano essi continui o discreti.
 * Inoltre, il costruttore viene definito in modo che sia richiesto il nome e l'identificativo numerico dell'attributo,
 * e il metodo toString() viene sovrascritto per restituire il nome dell'attributo come rappresentazione dell'oggetto.
 */
abstract class Attribute {
    // Membri attributi
    /**
     * nome simbolico dell'attributo
     */
    protected String name;
    /**
     * identificativo numerico dell'attributo
     */
    protected int index;

    /**
     * Costruttore di classe che inizializza i valori dei membri name, index
     *
     * @param name nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     * @return
     */
    Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * @return Restituisce il nome simbolico dell'attributo
     */
    String getName() {
        return name;
    }

    /**
     * @return Restituisce l'identificativo numerico dell'attributo
     */
    int getIndex() {
        return index;
    }

    /**
     * @return restuisce la stringa rappresentante lo stato dell'oggetto
     */
    public String toString() {
        return name;
    }
}

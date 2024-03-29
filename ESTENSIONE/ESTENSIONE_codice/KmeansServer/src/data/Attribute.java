package data;

import java.io.Serializable;

/**
 * Classe astratta data.Attribute: modella le entità attributo.
 * e il metodo toString() viene sovrascritto per restituire il nome dell'attributo come rappresentazione dell'oggetto.
 */
abstract class Attribute implements Serializable {
    // Membri attributi
    /**
     * Nome simbolico dell'attributo (i.e. "sunny", "x1"...)
     */
    private final String name;
    /**
     * Identificativo numerico dell'attributo
     */
    private final int index;

    /**
     * Costruttore di classe che inizializza i valori di name e index
     *
     * @param name  nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     */
    Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * Restituisce il nome simbolico dell'attributo
     *
     * @return Restituisce il nome simbolico dell'attributo
     */
    String getName() {
        return name;
    }

    /**
     * Restituisce l'identificativo numerico dell'attributo
     *
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

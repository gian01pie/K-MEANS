package data;

import java.io.Serializable;
import java.util.Set;

/**
 * Classe astratta data.Item che modella un generico item (coppia attributo-valore) (e.g. Outlook=”Sunny”)
 */
public abstract class Item implements Serializable {
    /**
     * Attributo coinvolto nell'item
     */
    private final Attribute attribute;
    /**
     * Valore assunto dall'attributo
     */
    private Object value;

    /**
     * Costruttore di classe che inizializza i valori dei membri attributi
     *
     * @param attribute attributo da coinvolgere nell'item
     * @param value     valore da assegnare all'attributo
     */
    Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce l'attributo coinvolto nell'item
     *
     * @return attributo coinvolto nell'item
     */
    Attribute getAttribute() {
        return attribute;
    }

    /**
     * Restituisce il valore assegnato all'attributo
     *
     * @return valore assegnato all'attributo
     */
    Object getValue() {
        return value;
    }

    /**
     * Rappresentazione sotto forma di stringa dell'oggetto
     *
     * @return valore dell'item
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Calcola la distanza tra l'item contestuale e quello dato in input.
     * <p>La distanza sarà diversa a seconda che si parli di item discreto o continuo</p>
     *
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return distanza tra i due item
     */
    abstract double distance(Object a);

    /**
     * Aggiorna il membro value dell'item, calcolandolo come il prototipo (centroide) dell'attributo rispetto al clusteredData
     *
     * @param data          il dataset di riferimento
     * @param clusteredData l'insieme degli indici delle righe in data che formano il cluster,
     *                      ovvero gli indici delle transazioni appartenenti al cluster in considerazione
     */
    public void update(Data data, Set<Integer> clusteredData) {
        value = data.computePrototype(clusteredData, attribute);
    }

    /**
     * Confronta l'Item di riferimento con l'Object in input.
     * Restituisce vero se e solo se l'argomento non è nullo ed è un oggetto Item con attributo e valore uguale.
     *
     * @param obj elemento con cui effettuare il confronto (deve essere un'istanza di Item)
     * @return true se l'oggeto in input è equivalente all'Item di riferimento, false altrimento
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Item) {
            //se sono lo stesso oggetto
            if (super.equals(obj)) {
                return true;
            }
            /*
            @NOTA: non è necessario definire esplicitamente equals per Attribute, questo perché
            siccome il confronto avviene sempre tra valori all'interno di data,
            i valori (value) confrontati faranno capo sempre allo stesso Attribute (proprio al medesimo oggetto)
            quindi è sufficiente fare il confronto tra gli indirizzi di memoria per Attribute
            */
            return attribute.equals(((Item) obj).getAttribute()) &&
                    value.equals(((Item) obj).getValue());
        }
        return false;
    }
}

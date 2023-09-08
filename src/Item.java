/**
 * Classe astratta Item che modella un generico item (coppia attributo-valore)
 */
abstract class Item {
    /**
     * attributo coinvolto nell'item
     */
    private Attribute attribute;
    /**
     * valore assegnato all'attributo
     */
    private Object value;

    /**
     * Costruttore di classe che inizializza i valori dei membri attributi
     * @param attribute attributo da coinvolgere nell'item
     * @param value valore da assegnare all'attributo
     */
    Item(Attribute attribute, Object value){
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * @return attributo coinvolto nell'item
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * @return valore assegnato all'attributo
     */
    public Object getValue() {
        return value;
    }

    /**
     * rappresentazione sottoforma di stringa dell'oggetto
     * @return valore dell'item
     */
    public String toString(){
        return value.toString();
    }

    /**
     * Calcola la distanza tra l'item contestuale e quello dato in input
     * <p>La distanza sarà diversa a seconda che si parli di item discreto o continuo</p>
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return distanza tra i due item
     */
    abstract double distance(Object a);

    /**
     * Aggiorna il membro value dell'item, calcolandolo come il prototipo (centroide) dell'attributo rispetto al clusteredData
     * @param data il dataset di riferimento
     * @param clusteredData l'insieme degli indici delle righe nella matrice in data che formano il cluster,
     *                     ovvero gli indici delle transazioni appartenenti al cluster in considerazione
     */
    void update(Data data, ArraySet clusteredData){
        value = data.computePrototype(clusteredData, attribute);
    }
}

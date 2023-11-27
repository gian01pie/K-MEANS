package data;

/**
 * Classe ContinuousItem che estende la classe Item e modella una coppia:
 * <ul><li>Attributo continuo - valore numerico (e.g. Temerature = 30.5</li></ul>
 */
class ContinuousItem extends Item {
    /**
     * Costruttore di classe che inizializza i valori dei membri attributi
     * chiamando il costruttore della superclasse
     *
     * @param attribute attributo da coinvolgere nell'item
     * @param value     valore da assegnare all'attributo
     */
    ContinuousItem(Attribute attribute, Object value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza in valore assoluto tra il valore scalato dell'item corrente
     * e il valore scalato dell'item in input
     *
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return valore assoluto della distanza tra i valori scalati: |v1 - v2|
     */
    @Override
    double distance(Object a) {
        // poichè la distanza viene calcolata sempre tra valori facenti parte dello stesso attributo
        // posso utilizzare lo stesso riferminto al ContinuousAttribute per entrambi gli item
        ContinuousAttribute attribute = ((ContinuousAttribute) this.getAttribute());
        double x = attribute.getScaledValue((double) this.getValue());
        // Qui posso fare direttamente il cast a ContinuousItem poichè la dove viene chiamata distance
        // le viene passato sempre un Item
        double y = attribute.getScaledValue((double) a);
        return Math.abs(x - y);

    }

}

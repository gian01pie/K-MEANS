package data;

import java.io.Serializable;

/**
 * Classe ContinuousItem che estende la classe Item e modella una coppia:
 * <li>Attributo continuo - valore numerico (e.g. Temerature = 30.5</li>
 */
class ContinuousItem extends Item {
    /**
     * Costruttore di classe che inizializza i valori dei membri attributi
     * chiamando il costruttore della superclasse
     *
     * @param attribute attributo da coinvolgere nell'item
     * @param value     valore da assegnare all'attributo
     */
    public ContinuousItem(Attribute attribute, Object value) {
        super(attribute, value);
    }

    /**
     * Calcola la distanza in valore assoluto tra il valore scalato dell'item corrente
     * e il valore scalato dell'item in input
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return valore assoluto della distanza tra i valori scalati: |v1 - v2|
     */
    @Override
    public double distance(Object a) {
        // poichè la distanza viene calcolata sempre tra valori facenti parte dello stesso attributo
        // posso utilizzare lo stesso riferminto al ContinuousAttribute per entrambi gli item
        ContinuousAttribute attribute = ((ContinuousAttribute) this.getAttribute());
        double x = attribute.getScaledValue((double) this.getValue());
        // Qui posso fare direttamente il cast a ContinuousItem poichè la dove viene chiamata distance
        // le viene passato sempre un Item
        double y = attribute.getScaledValue((double) a);
        return Math.abs(x - y);

    }


    //@TODO rimuovere funzione di TEST
    public static void main(String args[]){
        ContinuousAttribute ca = new ContinuousAttribute("Temperatura",0,0,50);
        ContinuousItem ci = new ContinuousItem(ca,30.0);
        System.out.println("Test funzioni:");
        System.out.println("getAttribute:" + ci.getAttribute());
        System.out.println("getValue:" + ci.getValue());
        System.out.println("toString:" + ci);
        System.out.println("distance con 40:" + ci.distance(new ContinuousItem(ca, 40.0)));
        System.out.println("distance con 30:" + ci.distance(new ContinuousItem(ca, 30.0)));

        System.out.println("equals con 40:" + ci.equals(new ContinuousItem(ca, 40.0)));
        System.out.println("equals con 30:" + ci.equals(new ContinuousItem(ca, 30.0)));

        ContinuousAttribute ca2 = new ContinuousAttribute("Temperatura",0,0,50);
        System.out.println("equals con 30 ma con un altro continuous Attribute anche se uguale nei valori:");
        System.out.println("\t" + ci.equals(new ContinuousItem(ca2, 30)) + " - Vedi commento in Item.equals");
    }
}

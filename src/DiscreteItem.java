/**
 * Classe concreta DiscreteItem (estende Item) che rappresenta una coppia:
 * <p><ul>
 * <li> Attributo discreto - valore discreto (i.e. Outlook = "Sunny")
 * </ul>
 * </p>
 */
class DiscreteItem extends Item {

    /**
     * Costruttore di classe che inizializza i valori dei membri attributi
     * tramite la chiamata al costruttore della classe madre
     *
     * @param attribute attributo da coinvolgere nell'item
     * @param value     valore da assegnare all'attributo
     */
    DiscreteItem(Attribute attribute, Object value) {
        super(attribute, value);
    }

    /**
     * Restituisce 0 se value = a, 1 altrimenti
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return 0 o 1
     */
    @Override
    double distance(Object a) {
        if (getValue().equals(a)){
            return 0;
        }
        return 1;
    }
}

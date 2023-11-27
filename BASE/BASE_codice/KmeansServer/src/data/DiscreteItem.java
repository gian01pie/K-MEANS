package data;

/**
 * Classe concreta data.DiscreteItem (estende data.Item) che rappresenta una coppia:
 * <ul><li> Attributo discreto - valore discreto (e.g. Outlook = "Sunny")</li></ul>
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
     * Restituisce 0 se il valore dell'item corrente è uguale ad a, 1 altrimenti
     *
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return 0 o 1
     */
    @Override
    double distance(Object a) {
        /*
         @NOTA: in input abbiamo un Object a, tuttavia equals lo chiamiamo su getValue()
         che restituisce formalmente un Object ma in realtà,
         per il principio di sostituibilità, restituisce il tipo di value.
         Dunque per far si che il confronto sia consistente teoricamente andrebbe passato come parametro attuale
         alla chiamata di distance un oggetto che sia confrontabile con value
        */
        if (getValue().equals(a)) {
            return 0;
        }
        return 1;
    }

}

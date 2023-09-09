package data;

/**
 * Classe concreta data.DiscreteItem (estende data.Item) che rappresenta una coppia:
 * <li> Attributo discreto - valore discreto (e.g. Outlook = "Sunny")
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
     * @param a l'oggetto rispetto al quale calcolare la distanza
     * @return 0 o 1
     */
    @Override
    public double distance(Object a) {
        /*
         @NOTA: in input abbiamo un Object a, tuttavia equals lo chiamiamo su getValue()
         che restituisce formalmente un Object ma in realtà,
         per il principio di sostituibilità, restituisce il tipo di value.
         Dunque per far si che il confronto sia consistente teoricamente andrebbe passato come parametro attuale
         alla chiamata di distance un oggetto che sia confrontabile con value
        */
        if (getValue().equals(a)){
            return 0;
        }
        return 1;
    }



    //@TODO rimuovere funzione di TEST
    public static void main(String args[]){
        DiscreteAttribute da = new DiscreteAttribute("Disney", 0,
                new String[]{"topolino","paperino","pippo"});
        DiscreteItem di = new DiscreteItem(da, "topolino");
        System.out.println("Test funzioni:");
        System.out.println("getAttribute:" + di.getAttribute());
        System.out.println("getValue:" + di.getValue());
        System.out.println("toString:" + di);
        System.out.println("distance con paperino:" + di.distance(new DiscreteItem(da, "paperino").getValue()));
        System.out.println("distance con topolino:" + di.distance(new DiscreteItem(da, "topolino").getValue()));

        System.out.println("equals con paperino:" + di.equals(new DiscreteItem(da, "paperino")));
        System.out.println("equals con topolino:" + di.equals(new DiscreteItem(da, "topolino")));

        DiscreteAttribute da2 = new DiscreteAttribute("Disney", 0,
                new String[]{"topolino","paperino","pippo"});
        System.out.println("equals con topolino ma con un altro discrete attribute anche se uguale nei valori:");
        System.out.println("\t" + di.equals(new DiscreteItem(da2, "topolino")) + " - Vedi commento in Item.equals");
    }
}

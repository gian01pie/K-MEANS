package data;

import java.io.Serializable;
import java.util.*;

/**
 * Classe concreta data.DiscreteAttribute (estende data.Attribute) rappresenta un attributo discreto (categorico)
 * <p>Gli attributi categorici assumono un numero finito (e anche non molto elevato) di valori distinti
 * <li> i.e.: l'attributo categorico di nome "play tennis" assume due valori "si" o "no"</li>
 *  </p>
 */
class DiscreteAttribute extends Attribute implements Iterable<String> {
    /**
     * Insieme di oggetti String, uno per ciascun valore del dominio
     * discreto. I valori del dominio sono memorizzati in values seguendo un ordine
     * lessicografico.
     */
    private TreeSet<String> values;

    /**
     * Costruttore di classe che invoca il costruttore della superclasse per inizializzare i valori di name e index
     * e poi inizializza values con il parametro in input
     *
     * @param name nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     * @param values valori del dominio discreto
     */
    DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);      
        this.values = new TreeSet<>();
        this.values.addAll(List.of(values));
    }

    /**
     * @return restituisce il numero di valori discreti nel dominio dell'attributo
     */
    int getNumberOfDistinctValues(){
        return values.size();
    }

    /**
     * Conta le occorrenze di v, nell'ambito dell'attributo corrente
     * (ovvero nella colonna di data che ha per indice quello dell'attributo corrente),
     * per tutti gli esempi (o transazioni, o tuple) memorizzati in data e indicizzati (per riga) da idList
     *
     * @param data dataset a cui appartengono le tuple su cui si contano le occorrenze
     * @param idList l'insieme degli indici di riga di alcune tuple memorizzate in data sul quale contare le occorrenze
     * @param v valore discreto di cui contare le occorrenze
     * @return Numero di occorenze di v, rispetto all'attributo corrente, nell'insieme di esempi di data indicizzati (per riga) da idList
     */
    int frequency(Data data, Set<Integer> idList, String v) {
        int count = 0;
        for (Integer i : idList){
            //Se il valore assunto dall'attributo nella transazione di indice i è = a v conta l'occorrenza
            if (data.getAttributeValue(i, this.getIndex()).equals(v)){
                count++;
            }
        }
        return count;
    }

    /**
     * @return l'iterator sugli elementi di tipo String di values
     */
    @Override
    public Iterator<String> iterator() {
        return values.iterator();
    }


    // @TODO rimuovere funzione di TEST
    public static void main(String args[]){
        String[] temp = {"topolino","paperino","pippo"};
        DiscreteAttribute discAtt = new DiscreteAttribute("i 3 della Disney", 0, temp);
        System.out.println("Test funzioni:");
        System.out.println("getName:" + discAtt.getName());
        System.out.println("getIndex:" + discAtt.getIndex());
        System.out.println("toString:" + discAtt);
        System.out.println("getNumberOfDistinctValues:" + discAtt.getNumberOfDistinctValues());
        System.out.println("getValue:" + discAtt.values.first());


        //Test di frequency:
        // Creiamo un nuovo Data (il costruttore di Data lo avvalora di tutti i dati quindi non serve aggiungere niente)
        Data d = new Data();
        // Ci prendiamo il riferimento all'attributo in posizione 0 dell'attributeSet di data
        // pocihè restituisce un Attributo facciamo il cast a DiscreteAttribute, questo lo possiamo fare perchè sono certo
        // che li dentro ci sia effettivamente un DiscreteAttribute, in generale dovrei usare is istance
        DiscreteAttribute att = (DiscreteAttribute) d.getAttribute(0);
        // Creiamo un nuovo array set ed aggiungiamo gli indici di riga che vogliamo considerare con frequency
        Set<Integer> idList = new HashSet<>();
        idList.add(0);
        idList.add(1);
        idList.add(3);
        idList.add(4);
        System.out.println(d);
        // calcolo la frequenza del valore "sunny" per le tuple di indice individuato da idList
        System.out.println(att.frequency(d,idList,"overcast"));
    }
}

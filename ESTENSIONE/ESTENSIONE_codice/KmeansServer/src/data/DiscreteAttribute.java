package data;

import java.util.*;

/**
 * Classe concreta data.DiscreteAttribute (estende data.Attribute) rappresenta un attributo discreto (categorico)
 * <p>Gli attributi categorici assumono un numero finito (e anche non molto elevato) di valori distinti </p>
 * <ul>
 * <li> i.e.: l'attributo categorico di nome "play tennis" assume due valori "si" o "no"</li>
 * </ul>
 */
class DiscreteAttribute extends Attribute implements Iterable<String> {
    /**
     * Insieme di oggetti String, uno per ciascun valore del dominio
     * discreto. I valori del dominio sono memorizzati in values seguendo un ordine
     * lessicografico.
     */
    private final TreeSet<String> values;

    /**
     * Costruttore di classe che invoca il costruttore della superclasse per inizializzare i valori di name e index
     * e poi inizializza values con il parametro in input
     *
     * @param name   nome simbolico dell'attributo
     * @param index  identificativo numerico dell'attributo
     * @param values valori del dominio discreto
     */
    DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        this.values = new TreeSet<>();
        this.values.addAll(List.of(values));
    }

    /**
     * Restituisce il numero di valori discreti nel dominio dell'attributo
     * @return restituisce il numero di valori discreti nel dominio dell'attributo
     */
    int getNumberOfDistinctValues() {
        return values.size();
    }

    /**
     * Conta le occorrenze di v, nell'ambito dell'attributo corrente
     * (ovvero nella colonna di data che ha per indice quello dell'attributo corrente),
     * per tutti gli esempi (o transazioni, o tuple) memorizzati in data e indicizzati (per riga) da idList
     *
     * @param data   dataset a cui appartengono le tuple su cui si contano le occorrenze
     * @param idList l'insieme degli indici di riga di alcune tuple memorizzate in data sul quale contare le occorrenze
     * @param v      valore discreto di cui contare le occorrenze
     * @return Numero di occorrenze di v, rispetto all'attributo corrente, nell'insieme di esempi di data indicizzati (per riga) da idList
     */
    int frequency(Data data, Set<Integer> idList, String v) {
        int count = 0;
        for (Integer i : idList) {
            //Se il valore assunto dall'attributo nella transazione di indice i è = a v conta l'occorrenza
            if (data.getAttributeValue(i, this.getIndex()).equals(v)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Restituisce l'iterator sugli elementi di tipo String di values
     * @return l'iterator sugli elementi di tipo String di values
     */
    @Override
    public Iterator<String> iterator() {
        return values.iterator();
    }


}

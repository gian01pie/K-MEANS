/**
 * Classe concreta DiscreteAttribute (estende Attribute) rappresenta un attributo discreto (categorico)
 */
class DiscreteAttribute extends Attribute {
    /**
     * array di oggetti String, uno per ciascun valore del dominio
     * discreto. I valori del dominio sono memorizzati in values seguendo un ordine
     * lessicografico.
     */
    private String[] values;

    /**
     * Costruttore di classe che invoca il costruttore della superclasse per inizializzare i valori di name e index
     * e poi inizializza values con il parametro in input
     *
     * @param name nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     * @param values valori del dominio discreto
     */
    public DiscreteAttribute(String name, int index, String[] values) {    
        super(name, index);      
        this.values = values;
    }

    /**
     * @return restituisce il numero di valori discreti nel dominio dell'attributo
     */
    public int getNumberOfDistinctValues(){
        return values.length;
    }

    /**
     * @param i posizione di un valore in values
     * @return restituisce il valore in posizione i di values (values[i])
     */
    public String getValue(int i){
        return values[i];
    }

    /**
     * Determina il numero di volte che il valore v compare in corrispondenza dell'attributo corrente (indice di colonna) negli
     * esempi memorizzati in data e indicizzati (per riga) da idList
     * @param data dataset su cui contare le occorrenze
     * @param idList l'insieme degli indici di riga di alcune tuple memorizzate in data sul quale contare le occorrenze
     * @param v valore discreto di cui contare le occorrenze
     * @return numero di occorenze di v nell'insieme di esempi idList rispetto all'attributo corrente
     */
    int frequency(Data data, ArraySet idList, String v) {
        int count = 0;
        for (int i = 0; i < idList.toArray().length; i++){
            if (data.getAttributeValue(i, this.getIndex()).equals(v)){
                count++;
            }
        }
        return count;
    }
    
}

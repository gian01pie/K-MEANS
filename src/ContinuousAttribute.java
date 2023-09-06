/**
 * Classe concreta ContinuousAttribute (estende Attribute)
 * che modella un attributo continuo (numerico).
 * Include i metodi per la “normalizzazione”
 * del dominio dell'attributo nell'intervallo [0,1] al fine di rendere
 * confrontabili attributi aventi domini diversi.
 */
class ContinuousAttribute extends Attribute {
    /**
     * valore di dominio minimo che l'attributo può assumere
     */
    private double min;
    /**
     * valore di dominio massimo che l'attributo può assumere
     */
    private double max;

    /**
     * Costrutture di classe che inizializza i valori di name e index chiamando il cosrtuttore della superclasse
     * e inizializza i valori minimo e massimo del dominio dell'attributo
     *
     * @param name nome simbolico dell'attributo
     * @param index identificativo numerico dell'attributo
     * @param min valore di dominio minimo
     * @param max valore di dominio massimo
     */
    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     * Calcola e restituisce il valore normalizzato del parametro
     * passato in input.
     * <p> La normalizzazione ha come codominio l'intervallo [0,1] e viene calcolata come segue:</p>
     * <p> v'=(v-min)/(max-min) </p>
     *
     * @param v valore da normalizzare
     * @return valore normalizzato
     */
    public double getScaledValue(double v) {
        return (v - min) / (max - min);
    }

}

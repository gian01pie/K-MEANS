package data;

/**
 * Classe concreta data.ContinuousAttribute (estende data.Attribute)
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
    ContinuousAttribute(String name, int index, double min, double max) {
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
    double getScaledValue(double v) {
        return ((v - min) / (max - min));
    }



    // @TODO rimuovere funzione di TEST
    public static void main(String args[]){
        ContinuousAttribute conAtt = new ContinuousAttribute("Da 0 a 10", 0, 1, 5);
        System.out.println("Test funzioni:");
        System.out.println("getName:" + conAtt.getName());
        System.out.println("getIndex:" + conAtt.getIndex());
        System.out.println("toString:" + conAtt);
        //Ovviamente devo metter un valore di v: min<=v<=max
        System.out.println("getScaledValue:" + conAtt.getScaledValue(4));
    }

}

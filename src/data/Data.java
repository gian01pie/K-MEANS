package data;

import java.util.*;

/**
 * Classe data.Data modella l'insieme di transazioni (o tuple, o esempi)
 */
public class Data {
	/**
	 * Lista di esempi (transazioni, tuple) che costituiscono il dataset
	 */
	private List<Example> data;
	/**
	 * cardinalità dell'insieme di transazioni (i.e. numero di esempi in data)
	 */
	private int numberOfExamples;
	/**
	 * Lista degli attributi in ciascuna tupla (schema della tabella di dati)
	 */
	private List<Attribute> attributeSet;

	/**
	 * Costruttore di classe:
	 * <p><ul>
	 * <li> Inizializza la matrice data [ ][ ] con transazioni di esempio (14 esempi e 5 attributi al momento)
	 * <li> Inizializza attributeSet creando cinque oggetti di tipo data.DiscreteAttribute
	 * <li> Inizializza numberOfExamples
	 * </ul><p>
	 */
	public Data(){
		//data

		//memorizzo gli esempi momentaneamente in un TreeSet in modo da non avere esempi duplicati
		TreeSet<Example> tempData = new TreeSet<>();

		Example ex0 = new Example();
		Example ex1 = new Example();
		Example ex2 = new Example();
		Example ex3 = new Example();
		Example ex4 = new Example();
		Example ex5 = new Example();
		Example ex6 = new Example();
		Example ex7 = new Example();
		Example ex8 = new Example();
		Example ex9 = new Example();
		Example ex10 = new Example();
		Example ex11 = new Example();
		Example ex12 = new Example();
		Example ex13 = new Example();

		ex0.add(new String("sunny"));
		ex1.add(new String("sunny"));
		ex2.add(new String("overcast"));
		ex3.add(new String("rain"));
		ex4.add(new String("rain"));
		ex5.add(new String("rain"));
		ex6.add(new String("overcast"));
		ex7.add(new String("sunny"));
		ex8.add(new String("sunny"));
		ex9.add(new String("rain"));
		ex10.add(new String("sunny"));
		ex11.add(new String("overcast"));
		ex12.add(new String("overcast"));
		ex13.add(new String("rain"));

		ex0.add(new String("hot"));
		ex1.add(new String("hot"));
		ex2.add(new String("hot"));
		ex3.add(new String("mild"));
		ex4.add(new String("cool"));
		ex5.add(new String("cool"));
		ex6.add(new String("cool"));
		ex7.add(new String("mild"));
		ex8.add(new String("cool"));
		ex9.add(new String("mild"));
		ex10.add(new String("mild"));
		ex11.add(new String("mild"));
		ex12.add(new String("hot"));
		ex13.add(new String("mild"));

		ex0.add(new String("high"));
		ex1.add(new String("high"));
		ex2.add(new String("high"));
		ex3.add(new String("high"));
		ex4.add(new String("normal"));
		ex5.add(new String("normal"));
		ex6.add(new String("normal"));
		ex7.add(new String("high"));
		ex8.add(new String("normal"));
		ex9.add(new String("normal"));
		ex10.add(new String("normal"));
		ex11.add(new String("high"));
		ex12.add(new String("normal"));
		ex13.add(new String("high"));

		ex0.add(new String("weak"));
		ex1.add(new String("strong"));
		ex2.add(new String("weak"));
		ex3.add(new String("weak"));
		ex4.add(new String("weak"));
		ex5.add(new String("strong"));
		ex6.add(new String("strong"));
		ex7.add(new String("weak"));
		ex8.add(new String("weak"));
		ex9.add(new String("weak"));
		ex10.add(new String("strong"));
		ex11.add(new String("strong"));
		ex12.add(new String("weak"));
		ex13.add(new String("strong"));

		ex0.add(new String("no"));
		ex1.add(new String("no"));
		ex2.add(new String("yes"));
		ex3.add(new String("yes"));
		ex4.add(new String("yes"));
		ex5.add(new String("no"));
		ex6.add(new String("yes"));
		ex7.add(new String("no"));
		ex8.add(new String("yes"));
		ex9.add(new String("yes"));
		ex10.add(new String("yes"));
		ex11.add(new String("yes"));
		ex12.add(new String("yes"));
		ex13.add(new String("no"));

		/*ex0.add(new String(""));
		ex1.add(new String(""));
		ex2.add(new String(""));
		ex3.add(new String(""));
		ex4.add(new String(""));
		ex5.add(new String(""));
		ex6.add(new String(""));
		ex7.add(new String(""));
		ex8.add(new String(""));
		ex9.add(new String(""));
		ex10.add(new String(""));
		ex11.add(new String(""));
		ex12.add(new String(""));
		ex13.add(new String(""));*/


		tempData.add(ex0);
		tempData.add(ex1);
		tempData.add(ex2);
		tempData.add(ex3);
		tempData.add(ex4);
		tempData.add(ex5);
		tempData.add(ex6);
		tempData.add(ex7);
		tempData.add(ex8);
		tempData.add(ex9);
		tempData.add(ex10);
		tempData.add(ex11);
		tempData.add(ex12);
		tempData.add(ex13);

		data = new ArrayList<Example>(tempData);

		/*data[0][0]=new String ("sunny");
		data[1][0]=new String ("sunny");
		data[2][0]=new String ("sunny");
		data[3][0]=new String ("rain");
		data[4][0]=new String ("rain");
		data[5][0]=new String ("rain");
		data[6][0]=new String ("rain");
		data[7][0]=new String ("rain");
		data[8][0]=new String ("rain");
		data[9][0]=new String ("rain");
		data[10][0]=new String ("overcast");
		data[11][0]=new String ("overcast");
		data[12][0]=new String ("overcast");
		data[13][0]=new String ("overcast");

		data[0][1]=new String ("hot");
		data[1][1]=new String ("hot");
		data[2][1]=new String ("hot");
		data[3][1]=new String ("mild");
		data[4][1]=new String ("mild");
		data[5][1]=new String ("mild");
		data[6][1]=new String ("mild");
		data[7][1]=new String ("mild");
		data[8][1]=new String ("mild");
		data[9][1]=new String ("mild");
		data[10][1]=new String ("cold");
		data[11][1]=new String ("cold");
		data[12][1]=new String ("cold");
		data[13][1]=new String ("cold");

		data[0][2]=new String ("high");
		data[1][2]=new String ("high");
		data[2][2]=new String ("high");
		data[3][2]=new String ("normal");
		data[4][2]=new String ("normal");
		data[5][2]=new String ("normal");
		data[6][2]=new String ("normal");
		data[7][2]=new String ("normal");
		data[8][2]=new String ("normal");
		data[9][2]=new String ("normal");
		data[10][2]=new String ("high");
		data[11][2]=new String ("high");
		data[12][2]=new String ("high");
		data[13][2]=new String ("high");


		data[0][3]=new String ("weak");
		data[1][3]=new String ("weak");
		data[2][3]=new String ("weak");
		data[3][3]=new String ("strong");
		data[4][3]=new String ("strong");
		data[5][3]=new String ("strong");
		data[6][3]=new String ("strong");
		data[7][3]=new String ("strong");
		data[8][3]=new String ("strong");
		data[9][3]=new String ("strong");
		data[10][3]=new String ("strong");
		data[11][3]=new String ("strong");
		data[12][3]=new String ("strong");
		data[13][3]=new String ("strong");


		data[0][4]=new String ("no");
		data[1][4]=new String ("no");
		data[2][4]=new String ("no");
		data[3][4]=new String ("yes");
		data[4][4]=new String ("yes");
		data[5][4]=new String ("yes");
		data[6][4]=new String ("yes");
		data[7][4]=new String ("yes");
		data[8][4]=new String ("yes");
		data[9][4]=new String ("yes");
		data[10][4]=new String ("yes");
		data[11][4]=new String ("yes");
		data[12][4]=new String ("yes");
		data[13][4]=new String ("yes");*/
		
		
		// numberOfExamples
		
		 numberOfExamples = data.size();
		 
		
		//attributeSet
		
		attributeSet = new LinkedList<Attribute>();
		
		String outLookValues[]=new String[3];
		outLookValues[0]="overcast";
		outLookValues[1]="rain";
		outLookValues[2]="sunny";
		attributeSet.add(new DiscreteAttribute("Outlook",0, outLookValues));

		String temperatureValues[]=new String[3];
		temperatureValues[0]="cool";
		temperatureValues[1]="hot";
		temperatureValues[2]="mild";
		attributeSet.add(new DiscreteAttribute("Temperature",1, temperatureValues));

		String humidityValues[]=new String[2];
		humidityValues[0]="high";
		humidityValues[1]="normal";
		attributeSet.add(new DiscreteAttribute("Humidity",2, humidityValues));

		String windValues[]=new String[2];
		windValues[0]="strong";
		windValues[1]="weak";
		attributeSet.add(new DiscreteAttribute("Wind",3, windValues));

		String playTennisValues[]=new String[2];
		playTennisValues[0]="no";
		playTennisValues[1]="yes";
		attributeSet.add(new DiscreteAttribute("PlayTennis",4, playTennisValues));

	}

	/**
	 * @return cardinalità dell'insieme di transazioni
	 */
	public int getNumberOfExamples(){
		return numberOfExamples;
	}

	/**
	 * @return cardinalità dell'insieme degli attributi
	 */
	public int getNumberOfAttributes(){
		return attributeSet.size();
	}

	/**
	 * @return restituisce lo schema dei dati
	 */
	List<Attribute> getAttributeSchema(){
		return attributeSet;
	}

	/**
	 * @param exampleIndex indice di riga della matrice data
	 * @param attributeIndex indice di colonna della matrice data
	 * @return valore assunto, in data, dall'attributo in posizione attributeIndex,
	 * nella riga in posizione exampleIndex.
	 *
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		// restituisce il valore dell'attributo
		return data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * @param index indice di posizione in attributeSet
	 * @return restituisce l'attributo in posizione index in attributeSet
	 */
	Attribute getAttribute(int index){
		return attributeSet.get(index);
	}

	/**
	 * Crea una stringa in cui memorizza lo schema della tabella (attributeSet)
	 * e le transazioni memorizzate in data, opportunamente enumerate.
	 * @return restuisce la stringa che modella lo stato dell'oggetto
	 */
	@Override
	public String toString() {
		String s = "";
		s += "Outlook Temperature Humidity Wind PlayTennis\n";
		for (int i = 0; i < numberOfExamples; i++) {
			s = s + (i + 1) + ": ";
			for (int j = 0; j < attributeSet.size(); j++) {
				s = s + data.get(i).get(j) + " ";
			}
			s = s + "\n";

		}
		return s;
	}

	/**
	 * @param index indice di riga
	 * @return Un oggetto di tipo data.Tuple che modella la riga index in data
	 * 	 come una sequenza di coppie Attributo-ValoreAssunto
	 * 	 <li>e.g.: <pre>Outlook="sunny", Temperature="hot", Humidity="high", Wind="weak", PlayTennis="no"</pre> </li>
	 */
	public Tuple getItemSet(int index){
		Tuple tuple = new Tuple(attributeSet.size());
		for(int i = 0; i < attributeSet.size(); i++)
			tuple.add(new DiscreteItem(attributeSet.get(i), (String)data.get(index).get(i)),i);
		return tuple;
	}

	/**
	 * Sceglie randomicamente i semi (centroidi) da utilizzare per la definizione dei cluster
	 *
	 * @param k numero di cluster da generare
	 * @return array di k interi rappresentanti gli indici di riga in data per le
	 * tuple scelte come centroidi (passo 1 del k-means)
	 * @throws OutOfRangeSampleSize se k è maggiore rispetto al numero di centroidi generabili, oppure è <= 0
	 */
	public int[] sampling(int k) throws OutOfRangeSampleSize{
		if (k <= 0 || k > data.size()){
			throw new OutOfRangeSampleSize("Numero di cluster k maggiore " +
					"rispetto al numero di centroidi generabili");
		}
		int centroidIndexes[]=new int[k];
		//choose k random different centroids in data.
		Random rand=new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i=0;i<k;i++){
			boolean found=false;
			int c;
			do
			{
				found=false;
				c=rand.nextInt(getNumberOfExamples());
				// verify that centroid[c] is not equal to a centroide already stored in CentroidIndexes
				for(int j=0;j<i;j++)
					if(compare(centroidIndexes[j],c)){
						found=true;
						break;
					}
			}
			while(found);
			centroidIndexes[i]=c;
		}
		return centroidIndexes;
	}

	/**
	 * Confronta due righe di data.Data, ovvero due transazioni
	 * @param i indice di riga nell'insieme in data.Data
	 * @param j indice di riga nell'insieme in data.Data
	 * @return Vero se due righe contengono gli stessi valori, falso altrimenti
	 */
	private boolean compare(int i, int j){
		Tuple t1 = getItemSet(i);
		Tuple t2 = getItemSet(j);
		for (int k = 0; k < t1.getLength(); k++)
			if (!t1.get(k).equals(t2.get(k)))
				return false;
		return true;
	}

	/**
	 *
	 * Calcola il centroide (prototipo) rispetto all'attributo in input sulle righe
	 * il cui indice è in idList
	 *
	 * @param idList insieme di indici di riga da considerare
	 * @param attribute attributo su cui calcolare il centroide
	 * @return valore del centroide rispetto al parametro attribute calcolato per le rgihe in idList
	 */
	Object computePrototype(Set<Integer> idList, Attribute attribute){
		if (attribute instanceof ContinuousAttribute) {
			// non è necessario fare il cast esplicito ContinuousAttribute attribute
			// poichè sappiamo già il tipo se instanceof restituisce true
			return computePrototype(idList, attribute);
		}
		else {
			return computePrototype(idList, (DiscreteAttribute) attribute);
		}
	}

	/**
	 * Determina il valore che occorre più frequentemente per un data.DiscreteAttribute nel sottoinsieme di dati individuato da idList
	 *
	 * @param idList insieme degli indici degli elementi di data appartenenti ad un cluster
	 * @param attribute data.DiscreteAttribute su cui calcolare il centroide
	 * @return valore del centroide rispetto al parametro discreto attribute
	 */
	private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {    //messo privato perchè viene chiamato da Object computePrototype
		// HashMap che avrà come chiavi i valori dell'attributo discreto,
		// e come valore le occorrenze di quell'attributo nelle tuple facenti parte del cluster indicato da idList
		Map<String, Integer> mapFreq = new HashMap<>();
		for (String value : attribute) {
			mapFreq.put(value, attribute.frequency(this, idList, value));
		}
		/*
		 Per trovare la chiave con valore massimo utilizzo la funzione max di collections che permette di trovare
		 il valore massimo in una collection usando un comparator come criterio di comparazione
		 poiché hashMap non è una collection ci serviamo della funzione entrySet che restituisce un insieme delle coppie
		 presenti in hashMap. Max restituisce un oggetto dello stesso tipo della collection su cui lavora,
		 in questo caso Map.Entry<String, Integer>, ovvero una coppia <String, Integer> facente parte di un entrySet
		*/
		Map.Entry<String, Integer> maxEntry = Collections.max(mapFreq.entrySet(), Map.Entry.comparingByValue());
		return maxEntry.getKey();
	}




	/**
	 * Inner class Example, modella ciascuna transazione
	 */
	class Example implements Comparable<Example> {
		/**
		 * array di Object che rappresentano la singola transazione (esempio), ovvero la riga della tabella
		 */
		private List<Object> example = new ArrayList<>();

		/**
		 * Aggiunge l'elemento in input in coda all'esempio
		 * @param o elemento da aggiungere
		 */
		void add (Object o){
			example.add(o);
		}

		/**
		 * Restituisce l'i-esimo riferimento collezionato in example
		 * @param i indice dell'elemento da restituire
		 * @return riferimento all'elemento nella posizione i
		 */
		Object get(int i){
			return example.get(i);
		}

		/**
		 * Confronta due esempi, restituendo 0 se sono uguali,
		 * altrimenti il risultato del compareTo sulla prima coppia di valori in disaccordo
		 * @param ex the object to be compared.
		 * @return 0 se sono uguali, 1 se alla prima coppia di valori in disaccordo il valore dell'oggetto corrente è maggiore
		 * di quello del parametro, -1 altrimenti
		 */
		@Override
		public int compareTo(Example ex) {
			int i = 0;
			for (Object obj : example) {
				if (!obj.equals(ex.get(i)))
					// faccio un cast a Comparable per poter utilizzare il compareTo
					// dell'elemento contenuto in example, posso farlo perché so che l'elemento
					// che troverò li sarà un oggetto di una classe che implementa Comparable
					return ((Comparable) obj).compareTo(ex.get(i));
				i++;
			}
			return 0;
		}

		@Override
		public String toString() {
			StringBuilder str = new StringBuilder("Example{");
			int count = 0;
			for (Object obj : example){
				str.append(obj.toString());
				count++;
				if (count < example.size()){
					str.append(", ");
				}
			}
			str.append("}") ;
			return  str.toString();
		}
	}


	
	public static void main(String args[]){
		Data trainingSet=new Data();
		System.out.println(trainingSet);
		System.out.println(trainingSet.compare(0,1));
	}
}

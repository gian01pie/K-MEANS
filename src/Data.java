import javax.swing.text.AttributeSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Classe Data modella l'insieme di transazioni (o tuple)
 */
class Data {
	/**
	 * matrice nXm in cui ogni riga modella una transazione
	 */
	private Object data [][];
	/**
	 * cardinalità dell'insieme di transazioni (i.e. numero di righe in data)
	 */
	private int numberOfExamples;
	/**
	 * vettore degli attributi in ciascuna tupla (schema della tabella di dati)
	 */
	private Attribute attributeSet[];

	/**
	 * Costruttore di classe:
	 * <p><ul>
		 * <li> Inizializza la matrice data [ ][ ] con transazioni di esempio (14 esempi e 5 attributi al momento)
		 * <li> Inizializza attributeSet creando cinque oggetti di tipo DiscreteAttribute
		 * <li> Inizializza numberOfExamples
	 * </ul><p>
	 *
	 */
	Data(){
		
		//data
		
		data = new Object [14][5];

		data[0][0]=new String ("sunny");
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
		data[13][4]=new String ("yes");

		/*data[0][0]=new String ("sunny");
		data[1][0]=new String ("sunny");
		data[2][0]=new String ("overcast");
		data[3][0]=new String ("rain");
		data[4][0]=new String ("rain");
		data[5][0]=new String ("rain");
		data[6][0]=new String ("overcast");
		data[7][0]=new String ("sunny");
		data[8][0]=new String ("sunny");
		data[9][0]=new String ("rain");
		data[10][0]=new String ("sunny");
		data[11][0]=new String ("overcast");
		data[12][0]=new String ("overcast");
		data[13][0]=new String ("rain");

		data[0][1]=new String ("hot");
		data[1][1]=new String ("hot");
		data[2][1]=new String ("hot");
		data[3][1]=new String ("mild");
		data[4][1]=new String ("cool");
		data[5][1]=new String ("cool");
		data[6][1]=new String ("cool");
		data[7][1]=new String ("mild");
		data[8][1]=new String ("cool");
		data[9][1]=new String ("mild");
		data[10][1]=new String ("mild");
		data[11][1]=new String ("mild");
		data[12][1]=new String ("hot");
		data[13][1]=new String ("mild");

		data[0][2]=new String ("high");
		data[1][2]=new String ("high");
		data[2][2]=new String ("high");
		data[3][2]=new String ("high");
		data[4][2]=new String ("normal");
		data[5][2]=new String ("normal");
		data[6][2]=new String ("normal");
		data[7][2]=new String ("high");
		data[8][2]=new String ("normal");
		data[9][2]=new String ("normal");
		data[10][2]=new String ("normal");
		data[11][2]=new String ("high");
		data[12][2]=new String ("normal");
		data[13][2]=new String ("high");


		data[0][3]=new String ("weak");
		data[1][3]=new String ("strong");
		data[2][3]=new String ("weak");
		data[3][3]=new String ("weak");
		data[4][3]=new String ("weak");
		data[5][3]=new String ("strong");
		data[6][3]=new String ("strong");
		data[7][3]=new String ("weak");
		data[8][3]=new String ("weak");
		data[9][3]=new String ("weak");
		data[10][3]=new String ("strong");
		data[11][3]=new String ("strong");
		data[12][3]=new String ("weak");
		data[13][3]=new String ("strong");


		data[0][4]=new String ("no");
		data[1][4]=new String ("no");
		data[2][4]=new String ("yes");
		data[3][4]=new String ("yes");
		data[4][4]=new String ("yes");
		data[5][4]=new String ("no");
		data[6][4]=new String ("yes");
		data[7][4]=new String ("no");
		data[8][4]=new String ("yes");
		data[9][4]=new String ("yes");
		data[10][4]=new String ("yes");
		data[11][4]=new String ("yes");
		data[12][4]=new String ("yes");
		data[13][4]=new String ("no");*/
		
		
		// numberOfExamples
		
		 numberOfExamples=14;		 
		 
		
		//explanatory Set
		
		attributeSet = new Attribute[5];
		
		String outLookValues[]=new String[3];
		outLookValues[0]="overcast";
		outLookValues[1]="rain";
		outLookValues[2]="sunny";
		attributeSet[0] = new DiscreteAttribute("Outlook",0, outLookValues);

		String temperatureValues[]=new String[3];
		temperatureValues[0]="cool";
		temperatureValues[1]="hot";
		temperatureValues[2]="mild";
		attributeSet[1] = new DiscreteAttribute("Temperature",1, temperatureValues);

		String humidityValues[]=new String[2];
		humidityValues[0]="high";
		humidityValues[1]="normal";
		attributeSet[2] = new DiscreteAttribute("Humidity",2, humidityValues);

		String windValues[]=new String[2];
		windValues[0]="strong";
		windValues[1]="weak";
		attributeSet[3] = new DiscreteAttribute("Wind",3, windValues);

		String playTennisValues[]=new String[2];
		playTennisValues[0]="no";
		playTennisValues[1]="yes";
		attributeSet[4] = new DiscreteAttribute("PlayTennis",4, playTennisValues);

	}

	/**
	 * @return cardinalità dell'insieme di transazioni
	 */
	int getNumberOfExamples(){
		return numberOfExamples;
	}

	/**
	 * @return cardinalità dell'insieme degli attributi
	 */
	int getNumberOfAttributes(){
		return attributeSet.length;
	}

	/**
	 * @return restituisce lo schema dei dati
	 */
	Attribute[] getAttributeSchema(){
		return attributeSet;
	}

	/**
	 *
	 * @param exampleIndex indice di riga della matrice data
	 * @param attributeIndex indice di colonna della matrice data
	 * @return valore assunto in data dall'attributo in posizione attributeIndex, nella riga in
	 * posizione exampleIndex
	 */
	Object getAttributeValue(int exampleIndex, int attributeIndex){
		return data[exampleIndex][attributeIndex];
	}

	/**
	 *
	 * @param index indice di posizione in attributeSet
	 * @return restituisce i valori degli attributi nella tupla di posizione index
	 */
	Attribute getAttribute(int index){
		return attributeSet[index];
	}

	/**
	 * Crea una stringa in cui memorizza lo schema della tabella (vedi
	 * attributeSet) e le transazioni memorizzate in data, opportunamente enumerate.
	 * @return restuisce la stringa che modella lo stato dell'oggetto
	 */
	public String toString(){
		String s=new String();
		s=s+"Outlook Temperature Humidity Wind PlayTennis\n";
		for(int i=0;i<numberOfExamples;i++){
			s = s + (i+1) + ": ";
			for(int j=0;j<attributeSet.length;j++){
				s=s+data[i][j]+" ";
			}
			s=s+"\n";

		}
		return s;
	}

	/**
	 * Crea un oggetto di tipo Tuple che modella come sequenza di coppie Attributo-Valore la i-esima riga in data
	 * @param index indice di riga
	 * @return retituisce l'oggetto di Tuple creato
	 */
	Tuple getItemSet(int index){
		Tuple tuple = new Tuple(attributeSet.length);
		for(int i = 0; i < attributeSet.length; i++)
			tuple.add(new DiscreteItem(attributeSet[i], (String)data[index][i]),i);
		return tuple;
	}

	/**
	 * Sceglie in maniera casuale i primi semi (centroidi) da utilizzare per la definizione dei cluster
	 * @param k numero di cluster da generare
	 * @return array, di k interi rappresentanti gli indici di riga in data per le
	 * tuple inizialmente scelte come centroidi (passo 1 del k-means)
	 */
	int[] sampling(int k){
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
	 * Confronta due righe di Data, ovvero due transazioni
	 * @param i indice di riga nell'insieme in Data
	 * @param j indice di riga nell'insieme in Data
	 * @return Vero se due righe contengono gli stessi valori, falso altrimenti
	 */
	private boolean compare(int i, int j){
		Tuple t1 = getItemSet(i), t2 = getItemSet(j);
		for (int k = 0; k < t1.getLength(); k++)
			if (!t1.get(k).equals(t2.get(k)))
				return false;
		return true;
	}

	/**
	 * Calcola il centroide rispetto all'attributo in input, dato un insieme di indici di riga
	 *
	 * @param idList insieme di indici di riga da considerare
	 * @param attribute attributo su cui calcolare il centroide
	 * @return valore del centroide rispetto al parametro attribute
	 */
	Object computePrototype(ArraySet idList, Attribute attribute){
		if (attribute instanceof ContinuousAttribute)
			return computePrototype(idList, (ContinuousAttribute) attribute);
		else
			return computePrototype(idList, (DiscreteAttribute) attribute);
	}

	/**
	 * Determina il valore che occorre più frequentemente per un DiscreteAttribute nel sottoinsieme di dati individuato da idList
	 *
	 * @param idList insieme degli indici delle righe di data appartenenti ad un cluster
	 * @param attribute DiscreteAttribute su cui calcolare il centroide
	 * @return valore del centroide rispetto al parametro discreto attribute
	 */
	String computePrototype(ArraySet idList, DiscreteAttribute attribute){
		// HashMap che avrà come chiavi i valori dell'attributo discreto,
		// e come valore le occorrenze di quell'attributo nelle tuple facenti parte del cluster indicate da idList
		Map<String, Integer> mapFreq = new HashMap<>();
		for (int i = 0; i < attribute.getNumberOfDistinctValues(); i++){
			mapFreq.put(attribute.getValue(i),attribute.frequency(this, idList, attribute.getValue(i)));
		}
		// Per trovare la chiave con valore massimo utilizzo la funzione max di collections che permette di trovare
		// il valore massimo in una collection usando un comparator come criterio di comparazione
		// poiché hashMap non è una collection ci serviamo della funzione entrySet che restituisce un insieme delle coppie
		// presenti in hashMap.
		// Max restituisce un oggetto dello stesso tipo della collection su cui lavora,
		// in questo caso Map.Entry<String, Integer>, ovvero una coppia <String, Integer> facente parte di un entrySet
		Map.Entry<String, Integer> maxEntry = Collections.max(mapFreq.entrySet(), Map.Entry.comparingByValue());
		return maxEntry.getKey();
	}

	
	public static void main(String args[]){
		Data trainingSet=new Data();
		System.out.println(trainingSet);
		
		
	}

}

import java.util.Arrays;

/**
 * Classe ArraySet che modella il dato astratto insieme di interi con una rappresentazione basata su vettore booleano.
 * <p>Attributi:<ul>
 * <li> set: array di booleani
 * <li> size: intero che rappresenta la dimensione dell'insieme
 * </ul><p>
 * Tramite il metodo toArray() è possibile convertire l'oggetto in un array di interi ed utilizzaro per indicizzare il dataset
 */
class ArraySet {
	private boolean set[];
	/**
	 * @TODO (non sono sicuro) Variabile che tiene traccia dei valori true nel vettore set, rappresenta la dimesnione dell'insieme
	 */
	private int size=0;

	/**
	 * Costruttore di classe, inizializza il vettore booleano set a tutti falso
	 */
	ArraySet (){
		set=new boolean[50];
		for(int i=0;i<set.length;i++)
			set[i]=false;
	}

	/**
	 * Setta a True il valore di posizione i nel vettore booleano set
	 * @param i indice di posizione (in set) del valore da settare true
	 * @return True se si è cambiato l'arraySet (ovvero si è settato un valore da false a true)
	 */
	//return true if add is changing the arraySet
	boolean add(int i){
		if(i>=set.length)
		{
			//enlarge the set
			boolean temp[]=new boolean[set.length*2];
			Arrays.fill(temp,false);
			System.arraycopy(set, 0, temp, 0, set.length);
			set=temp;
		}	
		boolean added=set[i];
		set[i]=true;
		if(i>=size)
			size=i+1;
		return !added;
		
		
	}
	
	boolean delete(int i){
		if(i<size){
			boolean deleted=set[i];
			set[i]=false;
			if(i==size-1){
				//update size
				int j;
				for(j=size-1;j>=0 && !set[j];j--);
				
				size=j+1;
			}
			
			return deleted;
		}
		return false;
	}

	/**
	 * @param i indice di posizione in set
	 * @return valore boolean dell'elemento in posizione i di set
	 */
	boolean get(int i){
		return set[i];
	}

	/**
	 * converte l'oggetto in un array di interi da utilizzare per indicizzare il dataset
	 * @return array di interi
	 */
	int[] toArray(){
		int a[]=new int[0];
		for(int i=0;i<size;i++){
			if(get(i)){
				int temp[]=new int[a.length+1];
				System.arraycopy(a, 0, temp, 0, a.length);
				a=temp;
				a[a.length-1]=i;
			}
		}
		return a;
	}
}

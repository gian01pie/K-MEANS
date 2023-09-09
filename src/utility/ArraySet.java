package utility;

import java.util.Arrays;

/**
 * Classe utility.ArraySet che modella il dato astratto "insieme di interi" con una rappresentazione basata su vettore booleano.
 * <p>Viene utilizzato per individuare un insieme di tuple all'interno di data:
 * <li>gli indici di posizione degli elementi con valore True, corrispondo agli indici di riga delle tuple in data</li>
 * </p>
 */
public class ArraySet {
	/**
	 * vattore booleano
	 */
	private boolean set[];
	/**
	 * tiene traccia del numero di valori true nel vettore set, rappresenta la dimesnione dell'insieme
	 */
	private int size=0;

	/**
	 * Costruttore di classe, inizializza il vettore booleano set a tutti falso
	 */
	public ArraySet (){
		set=new boolean[50];
		for(int i=0;i<set.length;i++)
			set[i]=false;
	}

	/**
	 * Setta a True il valore di posizione i nel vettore booleano set (se quest'ultimo è false),
	 * raddoppia la dimensione di set se l'indice è maggiore della sua dimensione
	 * @param i indice di posizione (in set) del valore da settare true
	 * @return True se si è cambiato l'arraySet (ovvero si è settato un valore da false a true)
	 */
	//return true if add is changing the arraySet
	public boolean add(int i){
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
		// questa condizione equivale a dire: se ho fatto un'aggiunta, incrementa size
		if(i>=size)
			size=i+1;
		return !added;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public boolean delete(int i){
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
	public boolean get(int i){
		return set[i];
	}

	/**
	 * Converte l'oggetto ArraySet in un array di interi da utilizzare per indicizzare il dataset.
	 * Ogni elemento nell'array di interi sarà l'indice di posizione degli elementi che nel vettore booleano
	 * di riferimento avevano valore True
	 * @return array di interi
	 */
	public int[] toArray(){
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

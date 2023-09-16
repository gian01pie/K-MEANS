package database;

import java.util.ArrayList;
import java.util.List;


/**
 * Modella una transazione letta dalla base di dati
 */
public class Example implements Comparable<Example>{
	/**
	 * Rappresenta una riga della tabella nel DB
	 */
	private List<Object> example=new ArrayList<Object>();

	/**
	 * Aggiunge l'elemento in input in coda all'esempio
	 * @param o elemento da aggiungere
	 */
	public void add(Object o){
		example.add(o);
	}

	/**
	 * Restituisce l'i-esimo riferimento collezionato in example
	 * @param i indice dell'elemento da restituire
	 * @return riferimento all'elemento nella posizione i
	 */
	public Object get(int i){
		return example.get(i);
	}
	public int compareTo(Example ex) {
		
		int i=0;
		for(Object o:ex.example){
			if(!o.equals(this.example.get(i)))
				// faccio un cast a Comparable per poter utilizzare il compareTo
				// dell'elemento contenuto in example, posso farlo perché so che l'elemento
				// che troverò li sarà un oggetto di una classe che implementa Comparable
				return ((Comparable)o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}
	public String toString(){
		String str="";
		for(Object o:example)
			str+=o.toString()+ " ";
		return str;
	}
}
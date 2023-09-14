package mining;

import data.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe mining.Cluster che modella un Cluster ovvero un raggruppamento di data.Item
 */
class Cluster {
	/**
	 * centroide del cluster
	 */
	private Tuple centroid;
	/**
	 * insieme degli indici degli elementi che appartengono al cluster
	 */
	private Set<Integer> clusteredData;

	/**
	 * Costruttore che inizializza il centroide con il parametro in input e crea l'oggetto clusteredData
	 * @param centroid centroide del cluster
	 */
	Cluster(Tuple centroid){
		this.centroid = centroid;
		this.clusteredData = new HashSet<Integer>();
		
	}

	/**
	 * @return il centroide del cluster
	 */
	Tuple getCentroid(){
		return centroid;
	}

	/**
	 * Aggiorna il valore del centroide, ovvero aggiorna i valori dei singoli item, che componongono il centroide,
	 * sulla base delle altre tuple presenti nel cluster di riferimento
	 * @param data insieme di transazioni
	 */
	void computeCentroid(Data data) {
		for (int i = 0; i < centroid.getLength(); i++) {
			centroid.get(i).update(data, clusteredData);
		}
	}

	//return true if the tuple is changing cluster

	/**
	 * Aggiunge la tupla di indice id al cluster e restituisce true se questa ha cambiato cluster
	 * @param id indice di posizione di una tupla nel dataset
	 * @return True se la tupla cambia cluster, false altrimenti
	 */
	boolean addData(int id){
		return clusteredData.add(id);
	}

	/**
	 * Verifica se una transazione è clusterizzata nell'array corrente
	 *
	 * @param id indice di posizione della transazione nel dataset
	 * @return True se la transazione appartiene al cluster, false altrimenti
	 */
	boolean contain(int id){
		return clusteredData.contains(id);
	}
	

	//remove the tuple that has changed the cluster

	/**
	 * Rimuove la transazione che ha cambiato cluster
	 * @param id indice di posizione della transazione nel dataset
	 */
	void removeTuple(int id){
		clusteredData.remove(id);
	}

	@Override
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;
		
	}


	public String toString(Data data) {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += centroid.get(i) + " ";
		str += ")\nExamples:\n";
		for (int i : clusteredData) {
			str += "[";
			for (int j = 0; j < data.getNumberOfAttributes(); j++)
				str += data.getAttributeValue((Integer) i, j) + " ";
			str += "] dist=" + getCentroid().getDistance(data.getItemSet(i)) + "\n";
		}
		str += "AvgDistance=" + getCentroid().avgDistance(data, clusteredData) + "\n";
		return str;
	}
}

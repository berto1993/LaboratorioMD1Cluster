package packJerarquico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Iteration 
{
	private int iterationName;
	private ArrayList<Cluster> clusterList;
	private LinkedList<Distance> distanceList;
	
	public Iteration (int pItName, ArrayList<Cluster> pClusterList)
	{
		iterationName = pItName;
		clusterList = pClusterList;
		distanceList = new LinkedList<Distance>();
	}

	public int getClusterSize() 
	{
		return clusterList.size();
	}
	
	public ArrayList<Cluster> getClusterList()
	{
		return clusterList;
	}
	
	public int getIterationName()
	{
		return iterationName;
	}
	
	/**
	 * Calcula cuales son los cluster mas cercanos, los une 
	 * y devuelve un arraylist con el cluster unido sin los cluster
	 * originales
	 * @param pDistancia Tipo de distancia (Complete, Single, Average)
	 * @param pMetrica Tipo de metrica (Minkowski, Manhattan, Euclidean)
	 * @param k k de Minkoswki
	 * @param clustN numero de cluster
	 * @return
	 */
	public ArrayList<Cluster> mergeBestCluster(int pDistancia,int pMetrica,int k, int clustN)
	{
		calculateDistances(pDistancia, pMetrica, k);
		Distance dist = distanceList.getFirst();
		ArrayList<Cluster> clustArray;
		clustArray = (ArrayList<Cluster>) clusterList.clone();
		clustArray.remove(dist.getCluster1());
		clustArray.remove(dist.getCluster2());
		
		Cluster clust = new Cluster(clustN, dist.mergerInstances() , dist.getCluster1(), dist.getCluster2(), iterationName);
		//Añadimos el nuevo cluster (El unido) a la nuevalista de clusters
		clustArray.add(clustArray.size(),clust);
		
		return clustArray;
	}
	
	public ArrayList<Cluster> divideBestCluster(int pMetrica,int k, int clustN)
	{
		calculateDistanteInstances(pMetrica, k);
		Distance dist = distanceList.getLast();
		ArrayList<Cluster> clustArray = (ArrayList<Cluster>) clusterList.clone();
		clustArray.add(dist.getCluster2());
		
		return clustArray;
	}
	
	// Con este metodo calculamos las 
	// distancias entre todos los cluster
	// que hay en la iteracion y las ordenamos
	// de mayor a menor
	
	/**
	 * Calcula las distancias entre todos los clusters
	 * y los guarda en distanceList, despues ordena 
	 * distanceList de menor a mayor
	 * @param pDistancia Tipo de distancia (Complete, Single, Average)
	 * @param pMetrica Tipo de metrica (Minkowski, Manhattan, Euclidean)
	 * @param k k de Minkoswki
	 */
	private void calculateDistances(int pDistancia,int pMetrica,int k)
	{
		Distance dist = null;
		
		for (int i = 0; i < clusterList.size(); i++)
		{
			for (int j = i+1; j < clusterList.size(); j++)
			{
				dist = new Distance(clusterList.get(i), clusterList.get(j));
				dist.calculateDistance(pDistancia, pMetrica, k);
				distanceList.addLast(dist);
			}
		}
		Collections.sort(distanceList);
	}
	
	
	private void calculateDistanteInstances(int pMetrica,int k)
	{
		Distance dist = null;
		Cluster clusterAux = null;
				
		for (int i = 0; i < clusterList.size(); i++)
		{
			clusterAux = clusterList.get(i);
			if (clusterAux.getTamano() > 1)
			{
				dist = new Distance(clusterAux);
				for (int a=0; a < clusterAux.getTamano(); a++)
				{
					for (int b=1; b < clusterAux.getTamano(); b++)
					{
						dist.calculateDistanceDivisive(a, b, pMetrica, k);
						distanceList.addLast(dist);
					}
				}
			}
		}
		Collections.sort(distanceList);
	}
	
	public float getNearestDist()
	{
		return distanceList.getFirst().getDistance();
	}
	
	public float getFurthestDist()
	{
		return distanceList.getLast().getDistance();
	}
}
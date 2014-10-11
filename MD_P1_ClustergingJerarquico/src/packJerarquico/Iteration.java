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
	
	// Con este metodo calculamos las 
	// distancias entre todos los cluster
	// que hay en la iteracion y las ordenamos
	// de mayor a menor
	private void calculateDistances(int pDistancia,int pMetrica,int k)
	{
		Distance dist = null;
		
		for (int i = 0; i < clusterList.size(); i++)
		{
			for (int j = i+1; j < clusterList.size(); j++)
			{
				System.out.println("Calculo distancia cluster "+ clusterList.get(i).getTamano() +"\t cluster" + clusterList.get(j).getTamano());
				dist = new Distance(clusterList.get(i), clusterList.get(j));
				dist.calculateDistance(pDistancia, pMetrica, k);
				distanceList.addLast(dist);
			}
		}
		Collections.sort(distanceList);
	}
	public int getClusterSize() 
	{
		return clusterList.size();
	}
	
	
	public ArrayList<Cluster> getClusterList() {
		return clusterList;
	}
	
	public int getIterationName()
	{
		return iterationName;
	}
	
	public ArrayList<Cluster> mergeBestCluster(int pDistancia,int pMetrica,int k, int clustN)
	{
		calculateDistances(pDistancia, pMetrica, k);
		Distance dist = distanceList.getFirst();
		ArrayList<Cluster> clustArray;
		clustArray = (ArrayList<Cluster>) clusterList.clone();
		clustArray.remove(dist.getCluster1());
		clustArray.remove(dist.getCluster2());
		
		Cluster clust = new Cluster(clustN, dist.mergerInstances() , dist.getCluster1(), dist.getCluster2());
		//Añadimos el nuevo cluster (El unido) a la nuevalista de clusters
		clustArray.add(clust);
		
		return clustArray;
	}
	
	public ArrayList<Cluster> divideBestCluster(int pDistancia,int pMetrica,int k, int clustN)
	{
		calculateDistances(pDistancia, pMetrica, k);
		Distance dist = distanceList.getLast();
		ArrayList<Cluster> clustArray = (ArrayList<Cluster>) clusterList.clone();
		clustArray.remove(dist.getCluster1());
		//clustArray.remove(dist.getCluster2());
		
		return clustArray;
	}
	
	// Con este metodo calculamos las 
	// distancias entre todos los cluster
	// que hay en la iteracion y las ordenamos
	// de mayor a menor
	private void calculateDistanceInstances(int pDistancia,int pMetrica,int k)
	{
		Distance dist = null;
		
		for (int i = 0; i < clusterList.size(); i++)
		{
			System.out.println("Calculo distancias del cluster "+ clusterList.get(i).getNumber());
			dist = new Distance(clusterList.get(i));
			dist.calculateDistance(pDistancia, pMetrica, k);
			distanceList.addLast(dist);
		}
		Collections.sort(distanceList);
	}
}
package packJerarquico;

import java.util.LinkedList;

public class Distance implements Comparable<Distance>
{
	
	private Cluster cluster1, cluster2;
	private float distance;
	
	public Distance (Cluster pCluster1,Cluster pCluster2)
	{
		cluster1 = pCluster1;
		cluster2 = pCluster2;
		distance = 0;
	}
	
	public float getDistance()
	{
		return distance;
	}
	public Cluster getCluster1()
	{
		return cluster1;
	}
	public Cluster getCluster2()
	{
		return cluster2;
	}
	
	public void calculateDistance(int pDistancia, int pMetrica , int k)
	{	//	1 - Simple
		//	2 - Complete
		//	3 - Average

		
		if (pDistancia == 3)
			distance = Distancia.getDistancia().calculateAverage(cluster1, cluster2, pMetrica , k);
		if (pDistancia == 1)
			distance = Distancia.getDistancia().calculateSingle(cluster1, cluster2, pMetrica , k);	
		if (pDistancia == 2)
			distance = Distancia.getDistancia().calculateComplete(cluster1, cluster2, pMetrica , k);
	}
	
	@Override
	public int compareTo(Distance o) {
		return Float.compare(distance, o.distance);
	}

	public LinkedList<Instance> mergerInstances() {
		// Unimos las listas de instancias
		// de ambos clusters (la usaremos para
		// generar el cluster de union)
		LinkedList<Instance> lisInstance = (LinkedList<Instance>) cluster1.getInstances().clone();
		lisInstance.addAll(cluster2.getInstances());
				
		return lisInstance;
	}
}
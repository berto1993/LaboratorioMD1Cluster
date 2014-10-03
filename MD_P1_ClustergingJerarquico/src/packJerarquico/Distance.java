package packJerarquico;

public class Distance 
{
	
	private Cluster cluster1, cluster2;
	private float distance;
	
	public Distance (Cluster pCluster1,Cluster pCluster2)
	{
		cluster1 = pCluster1;
		cluster2 = pCluster2;
		distance = 0;
	}
	
	public float getDistance() {
		return distance;
	}
	public Cluster getCluster1() {
		return cluster1;
	}
	public Cluster getCluster2() {
		return cluster2;
	}
	
	public calculateDistance(Enum<Distancia> typeD, Enum<Metrica>typeM)
	{
		
		if (typeD)
			distance = Distancia.getDistancia().calculateAverage(cluster1, cluster2, typeM);
		if (typeD)
			distance = Distancia.getDistancia().calculateSingle(cluster1, cluster2, typeM);	
		if (typeD)
			distance = Distancia.getDistancia().calculateComplete(cluster1, cluster2, typeM);
	}
}

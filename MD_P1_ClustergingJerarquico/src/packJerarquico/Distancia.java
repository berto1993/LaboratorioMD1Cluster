package packJerarquico;

import java.util.LinkedList;

public class Distancia 
{
	private static Distancia myDistancia = null;
	
	private Distancia()
	{	}
	
	public static Distancia getDistancia()
	{
		if (myDistancia == null)
			myDistancia = new Distancia();
		
		return myDistancia;
	}
	
	public float calculateAverage(Cluster cluster1,Cluster cluster2, int pMetrica, int k)
	{
		if (cluster1.getMiddle() == null || cluster2.getMiddle() == null)
		{
			cluster1.calculateMiddle();
			cluster2.calculateMiddle();
		}
		
		return this.calculateMetric(cluster1.getMiddle(), cluster2.getMiddle(),pMetrica , k);
	}
	
	// Si no lo pasamos
	// como array de coordenadas
	// no podemos hacer un metodo para todo
	private float calculateMetric(float[] coordinate1, float[] coordinate2, int pMetrica , int k)
	{
		//	1 - Euclidean
		//	2 - Manhattan
		//	3 - Minkowski
		float dist = 0;
	
		if (pMetrica == 1)
			dist = Metrica.getMetrica().calculateEuclidean(coordinate1, coordinate2);
		if (pMetrica == 2)
			dist = Metrica.getMetrica().calculateManhattan(coordinate1, coordinate2);
		if (pMetrica == 3)
			dist = Metrica.getMetrica().calculateMikowski(coordinate1, coordinate2, k);
		
		return dist;
		
	}

	public float calculateComplete(Cluster cluster1, Cluster cluster2,	int pMetrica , int k)
	{
		LinkedList<Instance> inslist1 = cluster1.getInstances();
		LinkedList<Instance> inslist2 = cluster2.getInstances();
		float distanceMax = -1;
		float distanceAux = -1;
		
		//Hallamos las distancias entre instancias
		//comparando la mayor obtenida hasta
		//el momento con la ultima distancia obtenida
		//hasta comprobar todas las instancias
		for(int i=0;i<cluster1.getTamano();i++)
		{
			for(int j=0;j<cluster2.getTamano();j++)
			{
				distanceAux = this.calculateMetric(inslist1.get(i).getAtributes(), inslist2.get(j).getAtributes() , pMetrica, k);
				if(distanceAux > distanceMax)
				{
					distanceMax = distanceAux;
				}
			}
		}
		
		return distanceMax;
	}

	public float calculateSingle(Cluster cluster1, Cluster cluster2,int pMetrica , int k)
	{
		LinkedList<Instance> inslist1 = cluster1.getInstances();
		LinkedList<Instance> inslist2 = cluster2.getInstances();
		float distanceMin = Float.MAX_VALUE;
		float distanceAux = -1;
		//Hallamos las distancias entre instancias
		//comparando la menor obtenida hasta
		//el momento con la ultima distancia obtenida
		//hasta comprobar todas las instancias
		for(int i = 0; i < inslist1.size(); i++)
		{
			for(int j = 0; j < inslist2.size(); j++)
			{
				distanceAux = this.calculateMetric(inslist1.get(i).getAtributes(), inslist2.get(j).getAtributes(), pMetrica ,k);
				if(distanceAux < distanceMin)
				{
					distanceMin = distanceAux;
				}
			}
		}
		
		return distanceMin;
	}
}
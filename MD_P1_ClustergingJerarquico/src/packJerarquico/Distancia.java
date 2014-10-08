package packJerarquico;

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
	
	public float calculateAverage(Cluster cluster1,Cluster cluster2, Enum<Metrica>typeM)
	{
		if (cluster1.getMiddle() == null || cluster2.getMiddle() == null)
		{
			cluster1.calculateMiddle();
			cluster2.calculateMiddle();
		}
		
		return this.calculateMetric(cluster1.getMiddle(), cluster2.getMiddle(),typeM);
	}
	
	
	// Si no lo pasamos
	// como array de coordenadas
	// no podemos hacer un metodo para todo
	private float calculateMetric(float[] coordinate1, float[] coordinate2, Enum<Metrica>typeM)
	{
		if (typeM)
			return Metrica.getMetrica().calculateEuclidean(coordinate1, coordinate2);
		if (typeM)
			return Metrica.getMetrica().calculateManhattan(coordinate1, coordinate2);
	}

	public float calculateComplete(Cluster cluster1, Cluster cluster2,	Enum<Metrica> typeM)
	{
		LinkedList<Instance> inslist1 = cluster1.getInstances();
		LinkedList<Instance> inslist1 = cluster2.getInstances();
		float distanceMax = -1;
		float distanceAux = -1;
		
		//Hallamos las distancias entre instancias
		//comparando la mayor obtenida hasta
		//el momento con la ultima distancia obtenida
		//hasta comprobar todas las instancias
		for(int i=1;i<cluster1.getTamano();i++)
		{
			for(int j=1;i<cluster2.getTamano();j++)
			{
				distanceAux = this.calculateMetric(inslist1(i).getAttributes(), inslist2(j).getAttributes(), typeM);
				if(distanceAux > distanceMax)
				{
					distanceMax. = distanceAux;
				}
			}
		}
		
		return distanceMax;
	}

	public float calculateSingle(Cluster cluster1, Cluster cluster2,Enum<Metrica> typeM)
	{
		LinkedList<Instance> inslist1 = cluster1.getInstances();
		LinkedList<Instance> inslist1 = cluster2.getInstances();
		float distanceMin = -1;
		float distanceAux = -1;
		
		//Hallamos las distancias entre instancias
		//comparando la menor obtenida hasta
		//el momento con la ultima distancia obtenida
		//hasta comprobar todas las instancias
		for(int i=1;i<cluster1.getTamano();i++)
		{
			for(int j=1;i<cluster2.getTamano();j++)
			{
				distanceAux = this.calculateMetric(inslist1(i).getAttributes(), inslist2(j).getAttributes(), typeM);
				if(distanceAux < distanceMax)
				{
					distanceMin = distanceAux;
				}
			}
		}
		
		return distanceMin;
	}
}
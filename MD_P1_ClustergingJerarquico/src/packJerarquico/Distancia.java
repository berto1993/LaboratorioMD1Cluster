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

	public float calculateComplete(Cluster cluster1, Cluster cluster2,	Enum<Metrica> typeM) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float calculateSingle(Cluster cluster1, Cluster cluster2,Enum<Metrica> typeM) {
		// TODO Auto-generated method stub
		return 0;
	}
}

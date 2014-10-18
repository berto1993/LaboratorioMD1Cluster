package packDistancias;

public class Metrica 
{
private static Metrica myMetrica = null;
	
	private Metrica()
	{	}
	
	
	public static Metrica getMetrica() 
	{
		if (myMetrica == null)
			myMetrica = new Metrica();
		
		return myMetrica;
	}


	public float calculateEuclidean(float[] coordinate1, float[] coordinate2) {
		return minkowski(coordinate1, coordinate2, 2);
	}

	/**
	 * Calcula la distancia Minkoswki entre dos coordenadas 
	 * @param coordinate1 
	 * @param coordinate2 
	 * @param k la k que se va a emplear
	 * @return la distancia entre las dos coordenadas
	 */
	private float minkowski(float[] coordinate1, float[] coordinate2, int k) {
		
		float[]aux = new float[coordinate1.length];
		
		for (int i = 0; i < coordinate1.length ; i++)
			aux[i] = (float) Math.pow(coordinate1[i] + coordinate2[i], k);
				
		return (float) Math.pow(this.sumValues(aux), (1)/((float)k));
	}

	/**
	 * Suma los valores del array
	 * @param aux array con numeros
	 * @return devuelve la suma de los valores
	 */
	private float sumValues(float[] aux) 
	{
		float sum = 0;
		
		for (int i = 0; i < aux.length; i++)
			sum = sum + aux[i];
		
		return sum;
	}

	/**
	 * Calcula la distancia Manhattan entre dos coordenadas 
	 * @param coordinate1 
	 * @param coordinate2 
	 * @return la distancia entre las dos coordenadas
	 */
	public float calculateManhattan(float[] coordinate1, float[] coordinate2) {
		
		float[]aux = new float[coordinate1.length];
		
		for (int i = 0; i < coordinate1.length ; i++)
			aux[i] = (float) Math.abs(coordinate1[i] + coordinate2[i]);
				
		return (float) this.sumValues(aux);
	}
	
	/**
	 * Calcula la distancia Minkowski entre dos coordenadas 
	 * @param coordinate1 
	 * @param coordinate2 
	 * @return la distancia entre las dos coordenadas
	 */
	public float calculateMikowski(float[] coordinate1, float[] coordinate2, int k)
	{
		return minkowski(coordinate1, coordinate2, k);
	}
}
package packJerarquico;

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
		
		float[]aux = new float[coordinate1.length];
		
		for (int i = 0; i < coordinate1.length ; i++)
			aux[i] = (float) Math.pow(coordinate1[i] + coordinate2[i], 2);
				
		return (float) Math.sqrt(this.sumValues(aux));
	}


	private double sumValues(float[] aux) 
	{
		float sum = 0;
		
		for (int i = 0; i < aux.length; i++)
			sum = sum + aux[i];
		
		return sum;
	}


	public float calculateManhattan(float[] coordinate1, float[] coordinate2) {
		
		float[]aux = new float[coordinate1.length];
		
		for (int i = 0; i < coordinate1.length ; i++)
			aux[i] = (float) Math.abs(coordinate1[i] + coordinate2[i]);
				
		return (float) this.sumValues(aux);
	}
	
	public float calculateMinchowski(float[] coordinate1, float[] coordinate2) {
		
		float[]aux = new float[coordinate1.length];
		
		for (int i = 0; i < coordinate1.length ; i++)
			aux[i] = (float) Math.pow(coordinate1[i] + coordinate2[i], 1);
				
		return (float) Math.sqrt(this.sumValues(aux));
	}
}
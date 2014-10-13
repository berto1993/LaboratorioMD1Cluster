package packJerarquico;

public class Instance
{
	private int name;
	private float[] atributes;
	
	public Instance(int pName ,String[] att, int[] numeric)
	{
		name = pName;
		// Utilizamos numeric para evitar
		// los atributos no numericos, en numeric
		// tenemos la posición de los atributos que
		// nos interesan guardadas
		atributes = new float[numeric.length];
		for (int i = 0; i < numeric.length; i++)
			// Convierte el numero almacenado
			// en el String y lo añade
			atributes[i] = Float.valueOf(att[numeric[i]]);
			
	}
	
	public int getName()
	{
		return name;
	}

	/**
	 * 
	 * @param i posicion del atributo que se
	 * quiere conseguir
	 * @return el atributo en posicion i
	 */
	public float getAtribute(int i)
	{
		return this.atributes[i];
	}
	
	public float[] getAtributes()
	{
		return atributes;
	}
	
	public int getAtributeSize()
	{
		return atributes.length;
	}
}
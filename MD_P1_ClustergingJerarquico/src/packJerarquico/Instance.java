package packJerarquico;

public class Instance
{
	private float[] atributos;
	private float x;
	private float y;
	
	public Instance(float pX, float pY)
	{
		this.x = pX;
		this.y = pY;
		this.atributos[0] = x;
		this.atributos[1] = y;
	}
	
	//No entiendo la funcionalidad de este método
	/*public int getSize()
	{
		return 0;
	}*/

	public float getAttribute(int i)
	{
		return this.atributos[i];
	}
	
	public float[] getAttributes()
	{
		return atributos;
	}
	
	//De este tampoco :P
	/*public int getAttributeSize()
	{
		return 0;
	}*/
}
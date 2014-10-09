package packJerarquico;

public class Instance
{
	private float[] atributes;
	
	public Instance(float pX, float pY)
	{
	
	}
	

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
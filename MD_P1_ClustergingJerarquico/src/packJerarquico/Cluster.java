package packJerarquico;

import java.util.Iterator;
import java.util.LinkedList;

public class Cluster 
{
	// Aqui guardaremos el centroide
	// de cada cluster en caso de 
	// necesitarlo
	private float[] middle;
	// El numero del cluster
	private int number;
	// Las instancias que pertenecen 
	// al cluster
	private LinkedList<Instance> instances;

	public Cluster (int clusterName)
	{
		number = clusterName;
		instances = new LinkedList<Instance>();
		middle = null;
	}
	
	public LinkedList<Instance> getInstances() {
		return instances;
	}

	public float[] getMiddle() {
		return middle;
	}

	public void calculateMiddle()
	{
		// Existe un preproceso
		// al cargar los datos 
		// donde se evitan los
		// datos no numericos
		if (instances.size() > 0)
		{
			int total = instances.size();
			Iterator<Instance> it = instances.iterator();
			
			Instance aux = it.next();
			middle = new float[aux.getAttributeSize()];
			this.initializeMiddle();
			this.sumMiddleValues(aux);
			
			while (it.hasNext())
			{
				aux = it.next();				
				this.sumMiddleValues(aux);
			}
			
			this.middleValuesMean(total);
			
		}
		
		else
			System.out.println("No hay instancias en el cluster");
	}

	private void middleValuesMean(int total) {
		// Calcula la media
		// de los valores 
		// almacenados en 
		// middle y del nº
		// de instancias
		for (int i = 0 ; i < middle.length; i++)
		{
			middle[i] = middle[i] / total;
		}
	}

	private void sumMiddleValues(Instance aux) 
	{
		// Suma a middle
		// los valores de
		// la una instancia
		for (int i = 0 ; i < middle.length; i++)
		{
			middle[i] = middle[i] + aux.getAttribute(i);
		}
		
	}

	private void initializeMiddle() 
	{
		// Pone a 0 todas
		// las posiciones
		// del array Middle(centroide)
		
		for (int i = 0; i < middle.length; i++ )
		{
		middle[i] = 0;
		}
		
	}
}

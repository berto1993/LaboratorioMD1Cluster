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
	// Los dos cluster que forman el nuevo cluster
	// En la primera iteración estan a null;
	private Cluster leftParent = null;
	private Cluster righttParent = null;
	private int iteracionCreacion;
	
	public Cluster (int clusterName ,LinkedList<Instance> pList , Cluster pLeft, Cluster pRight, int pCreac)
	{
		number = clusterName;
		instances = pList;
		middle = null;
		leftParent = pLeft;
		righttParent = pRight;
		iteracionCreacion = pCreac;
	}
	
	
	public LinkedList<Instance> getInstances()
	{
		return instances;
	}
	
	public int getTamano()
	{
		return instances.size();
	}

	public float[] getMiddle()
	{
		return middle;
	}

	public int getNumber()
	{
		return number;
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
			middle = new float[aux.getAtributeSize()];
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
			middle[i] = middle[i] + aux.getAtribute(i);
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


	public Cluster getLeftParent() {
		return leftParent;
	}


	public Cluster getRighttParent() {
		return righttParent;
	}


	public int getIteration() {
		return iteracionCreacion;
	}
}
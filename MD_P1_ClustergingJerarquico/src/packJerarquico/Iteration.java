package packJerarquico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Iteration 
{
	private int iterationName;
	private ArrayList<Cluster> clusterList;
	private LinkedList<Distance> distanceList;
	
	public Iteration (int pItName)
	{
		iterationName = pItName;
		clusterList = new ArrayList<Cluster>();
		distanceList = new LinkedList<Distance>();
	}
	// Con este metodo calculamos las 
	// distancias entre todos los cluster
	// que hay en la iteracion y las ordenamos
	// de mayor a menor
	private void calculateDistances(int pDistancia,int pMetrica,int k)
	{
		Distance dist = null;
		
		for (int i = 0; i < clusterList.size(); i++)
		{
			for (int j = i +1; j < clusterList.size(); j++)
			{
				dist = new Distance(clusterList.get(i), clusterList.get(j));
				dist.calculateDistance(pDistancia, pMetrica, k);
				distanceList.addLast(dist);
			}
		}
		Collections.sort(distanceList);
	}

}

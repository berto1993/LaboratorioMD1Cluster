package packJerarquico;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListOfIterations 
{
	private static ListOfIterations myListOfIterations = null;
	private LinkedList<Iteration> list;
	
	private ListOfIterations() {
		list = new LinkedList<Iteration>();
	} 
	
	public static ListOfIterations getListOfIterations()
	{
		if (myListOfIterations == null)
			myListOfIterations = new ListOfIterations();
		return myListOfIterations;
	}
	
	public void aglomerativeClustering(String path, int distance, int metric, int k)
	{
		ListOfInstances.getListOfInstances().loadInstances(path);
		int i = 0;
		Iteration iterat = new Iteration(0, initializeClusterList());
		
		for (i = 1; iterat.getClusterSize() == 1; i++)
		{// Le añadimos al numero total de instancias el numero de 
		// iteraciones para denominar asi al nuevo cluster
			iterat = new Iteration (i, iterat.mergeBestCluster(distance, metric, k, (ListOfInstances.getListOfInstances().getSize() + i)));
			list.addLast(iterat);
			Printer.getPrinter().byScreenIteration(iterat);
		}
		
		System.out.println("Clustering finalizado");
		
	}

	private ArrayList<Cluster> initializeClusterList() {
		Cluster aux;
		ArrayList<Cluster> clusLis = new ArrayList<Cluster>();
		Instance[] lis = ListOfInstances.getListOfInstances().getInstances();
		LinkedList<Instance> auxLis;
		
		for (int i = 0; i < lis.length; i++)
		{
			auxLis = new LinkedList<Instance>();
			auxLis.add(lis[i]);
			aux = new Cluster(i,auxLis , null, null);
			clusLis.add(aux);
		}
		return clusLis;
	}
	

}

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

	public void divisiveClustering(String path, int distance, int metric, int k)
	{
		ArrayList<Cluster> divised;
		System.out.println("Cargando instancias");
		ListOfInstances.getListOfInstances().loadInstances(path);
		Iteration iterat = new Iteration(0, createCluster());
		System.out.println("Trabajando");
		int n = ListOfInstances.getListOfInstances().getSize();
		
		for (int i = 1; iterat.getClusterSize() != n; i++)
		{
			divised = iterat.divideBestCluster(distance, metric, k, (n + i));
			iterat = new Iteration (i, divised);
			list.addLast(iterat);
		}
	}

	private ArrayList<Cluster> createCluster()
	{
		Instance[] lis = ListOfInstances.getListOfInstances().getInstances();
		LinkedList<Instance> auxLis = null;

		for (int i = 0; i < lis.length; i++)
		{
			auxLis = new LinkedList<Instance>();
			auxLis.add(lis[i]);
		}
		
		ArrayList<Cluster> clusLis = new ArrayList<Cluster>();
		clusLis.add(new Cluster(1, auxLis, null, null));
		
		return clusLis;
	}

	public void aglomerativeClustering(String path, int distance, int metric, int k)
	{	
		ArrayList<Cluster> merged;
		System.out.println("Cargando instancias");
		ListOfInstances.getListOfInstances().loadInstances(path);
		int i = 0;
		int n = ListOfInstances.getListOfInstances().getSize();
		Iteration iterat = new Iteration(0, initializeClusterList());
		System.out.println("Trabajando");
		for (i = 1; iterat.getClusterSize() != 1; i++)
		{// Le añadimos al numero total de instancias el numero de 
		// iteraciones para denominar asi al nuevo cluster
			System.out.println(i);
			merged = iterat.mergeBestCluster(distance, metric, k, (n + i));
			iterat = new Iteration (i, merged);
			list.addLast(iterat);
			//Printer.getPrinter().byScreenIteration(iterat);
			//Printer.getPrinter().byTxtFile(iterat, path);
		}
		Printer.getPrinter().byScreenIteration(list);
		Printer.getPrinter().byTxtFile(list, path);
		System.out.println("Clustering finalizado");
	}

	private ArrayList<Cluster> initializeClusterList()
	{
		Cluster aux = null;
		ArrayList<Cluster> clusLis = new ArrayList<Cluster>();
		Instance[] lis = ListOfInstances.getListOfInstances().getInstances();
		LinkedList<Instance> auxLis = null;
		
		//El problema esta aqui, no parece darle tiempo a hacer la nueva lista y la deja en blanco
		for (int i = 0; i < lis.length; i++)
		{
			auxLis = new LinkedList<Instance>();
			auxLis.add(lis[i]);
			aux = new Cluster(i, auxLis, null, null);
			clusLis.add(aux);
		}
		return clusLis;
	}
}
package packJerarquico;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import packIO.Printer;

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

	/**
	 * Algoritmo de clustering jerarquico TopDown
	 * @param path Ruta del fichero de datos
	 * @param metric Metrica a utilizar
	 * @param k K a utilizar con Minkowski (opcional)
	 */
	public void divisiveClustering(String path, int metric, int k)
	{
		boolean divisive = true;
		ArrayList<Cluster> divised;
		System.out.println("Cargando instancias");
		ListOfInstances.getListOfInstances().loadInstances(path);
		Iteration iterat = new Iteration(0, createCluster());
		System.out.println("Trabajando");
		int n = ListOfInstances.getListOfInstances().getSize();
		
		for (int i = 1; i < n; i++)
		{
			divised = iterat.divideBestCluster(metric, k, (n + i));
			iterat = new Iteration (i, divised);
			list.addLast(iterat);
		}
		
		path = path + new Date().getTime();
		Printer.getPrinter().byScreenIteration(list,divisive);
		Printer.getPrinter().byTxtFile(list, path, divisive);
		Printer.getPrinter().byPDF(list, path, divisive);
		System.out.println("Clustering finalizado");
	}
	/**
	 * Crea un cluster con todas las instancias
	 * @return Devuelve una lista de clusters con un solo cluster
	 */
	private ArrayList<Cluster> createCluster()
	{
		Instance[] lis = ListOfInstances.getListOfInstances().getInstances();
		LinkedList<Instance> auxLis = new LinkedList<Instance>();

		for (int i = 0; i < lis.length; i++)
		{
			auxLis.add(lis[i]);
		}
		
		ArrayList<Cluster> clusLis = new ArrayList<Cluster>();
		Cluster aux = new Cluster(1, auxLis, null, null, 0);
		clusLis.add(aux);
		
		return clusLis;
	}

	/**
	 * Algoritmo de clustering jerarquico Bottom-Up
	 * @param path Ruta del fichero de datos
	 * @param distance Tipo de distancia (Simple, Complete, Average)
	 * @param metric Metrica a utlizar (Euclidea, Minkoswki, Manhattan)
	 * @param k K para minkowski
	 */
	public void aglomerativeClustering(String path, int distance, int metric, int k)
	{	boolean divisive = false;
		ArrayList<Cluster> merged;
		System.out.println("Cargando instancias");
		ListOfInstances.getListOfInstances().loadInstances(path);
		int i = 0;
		int n = ListOfInstances.getListOfInstances().getSize();
		Iteration iterat = new Iteration(0, initializeClusterList());
		System.out.println("Trabajando");
		
		for (i = 1; i < n /*iterat.getClusterSize() != 1*/; i++)
		{// Le añadimos al numero total de instancias el numero de 
		// iteraciones para denominar asi al nuevo cluster
			merged = iterat.mergeBestCluster(distance, metric, k, (n + i));
			iterat = new Iteration (i, merged);
			list.addLast(iterat);
		}
		
		path = path + new Date().getTime();
		Printer.getPrinter().byScreenIteration(list, divisive);
		Printer.getPrinter().byTxtFile(list, path,divisive);
		Printer.getPrinter().createDendogram(path, list);
		Printer.getPrinter().byPDF(list, path,divisive);
		System.out.println("Clustering finalizado");
	}

	/**
	 * Convierte las instancias en un arraylist
	 * de clusters de una unica instancia
	 * @return
	 */
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
			aux = new Cluster(i+1, auxLis, null, null, 0);
			clusLis.add(aux);
		}
		return clusLis;
	}
}

package packJerarquico;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) 
	{
		if (args.length == 4 || args.length == 5)
		{	
		//	0 - error	
		//	1 - Divisivo (conseguir un cluster para cada instancia)
		//	2 -	Aglomerativo (conseguir solo un cluster)
		int top = aglomerative(args[1]);
		//	0 - error 
		//	1 - Simple
		//	2 - Complete
		//	3 - Average
		int distance = setDistance(args[2]);
		//	0 - error
		//	1 - Euclidean
		//	2 - Manhattan
		//	3 - Minkowski
		int metric = setMetric(args[3]);
		
		int k = 0;
		if (args.length == 5)
			k = setK(args[4]);
		if (top == 0 || distance == 0 || metric == 0)
			error();
		else
			startClustering(args[0] ,top, distance, metric, k);
		}
		else
			error();
		
		
	}

	private static void startClustering(String path, int top, int distance,
			int metric, int k) 
	{
		if (top == 2)
			ListOfIterations.getListOfIterations().aglomerativeClustering(path, distance, metric, k);
		else
			ListOfIterations.getListOfIterations().divisiveClustering(path, distance, metric, k);		
	}

	private static int setK(String string) {
		return Integer.getInteger(string);
	}

	private static int aglomerative(String string) 
	{
	int aux = 0;
		if (string.equals("-Top"))
			aux = 1;
		if (string.equals("-Aglom"))
			aux= 2;
		
		return aux;
	}
	

	private static int setDistance(String string) 
	{
	int aux = 0;
		if (string.equals("-Simple"))
			aux = 1;
		if (string.equals("-Complete"))
			aux = 2;
		if (string.equals("-Average"))
			aux = 3;
	
		return aux;
	}

	private static int setMetric(String string) 
	{
		int aux = 0;
		if (string.equals("-Euclidean"))
			aux = 1;
		if (string.equals("-Manhattan"))
			aux = 2;
		if (string.equals("-Minkowski"))
			aux = 3;
	
		return aux;
	}

	private static void error() 
	{
		System.out.println("Usage:\n java -jar <path> <arg1> <arg2> <arg3> [needed for Minkowski]");
		System.out.println("where the available	options are:");
		System.out.println("Arg1:\n \t \"-Top\" for Top-Down \n "
				+ "\t \"-Aglom\" for Aglomerative ");
		System.out.println("Arg2:\n \t \"-Simple\" \n \t \"-Complete\" \n \t \"-Average\" ");
		System.out.println("Arg2:\n \t \"-Euclidean\" \n \t \"-Manhattan\" \n \t \"-Minkowski\"(wiht this metric is necessary to insert the k higher than 0) ");

	}

}

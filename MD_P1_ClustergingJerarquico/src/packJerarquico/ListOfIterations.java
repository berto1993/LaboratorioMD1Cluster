package packJerarquico;

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
		Iteration iterat;
		
		for (i = 1; iterat.getClusterSize; i++)
		{
			iterat = new Iteration ()
		}
		
	}
	

}

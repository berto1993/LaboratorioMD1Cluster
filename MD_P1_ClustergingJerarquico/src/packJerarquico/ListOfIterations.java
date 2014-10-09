package packJerarquico;

import java.util.LinkedList;

public class ListOfIterations 
{
	private static ListOfIterations myListOfIterations = null;
	private LinkedList<Iteration> list;
	private ListOfInstances instances;
	
	private ListOfIterations() {
		list = new LinkedList<Iteration>();
	} 
	
	public static ListOfIterations getListOfIterations()
	{
		if (myListOfIterations == null)
			myListOfIterations = new ListOfIterations();
		return myListOfIterations;
	}
	
	
	

}

package packJerarquico;

import java.rmi.server.LoaderHandler;
import java.util.LinkedList;

public class ListOfInstances 
{
	private static ListOfInstances myListOfInstances = null;
	private Instance[] list;
	
	private ListOfInstances()
	{	}
	
	public static ListOfInstances getListOfInstances()
	{
		if (myListOfInstances == null)
			myListOfInstances = new ListOfInstances();
		return myListOfInstances;
	}
	
	public void loadInstances(String path)
	{
		list = Loader.getLoader().loadInstances(path);
	}
	
	public Instance[] getInstances()
	{
		return list;
	}

	public int getSize() {
		return list.length;
	}

}

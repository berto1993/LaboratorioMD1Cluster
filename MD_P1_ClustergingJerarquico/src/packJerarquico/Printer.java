package packJerarquico;

import java.util.ArrayList;
import java.util.Iterator;

public class Printer {
	private static Printer myPrinter = null;
	
	private Printer()
	{}
	
	public static Printer getPrinter() {
		
		if (myPrinter == null)
			myPrinter = new Printer();
		return myPrinter;
	}

	public void byScreenIteration(Iteration iterat) 
	{
		//Preparamos el texto que vamos
		// a sacar por pantalla
		String printable = prepareString(iterat);
		System.out.println(printable);
		
	}

	private String prepareString(Iteration iterat) 
	{	
		Instance ins;
		Iterator<Instance> it;
		Cluster clus;
		ArrayList<Cluster> clusterL = iterat.getClusterList();
		String out = "Iteración nº " + iterat.getIterationName() + "\n";
		
		out = out +"\t Instance \t Cluster \t Iteración\n";
		for (int i = 0; i < clusterL.size(); i++)
		{
			clus = clusterL.get(i);
			it = clus.getInstances().iterator();
			while (it.hasNext())
			{
				ins = it.next();
				out = out + "\t Instancia "+ ins.getName() + " \t Cluster " + clus.getNumber() +" \t  Iteración " + iterat.getIterationName() + "\n";
			}
		}
	//	out = out + "\n \n" + "Se han unido los clusters " + iterat.g +
		return out;
	}

}

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
		System.out.println("He entrado en printer");
		System.out.println(printable);
		
	}

	private String prepareString(Iteration iterat) 
	{	System.out.println("He entrado en el metodo");
		Instance ins;
		Iterator<Instance> it;
		Cluster clus;
		ArrayList<Cluster> clusterL = iterat.getClusterList();
		String out = new String();
		out.concat("Iteración nº " + iterat.getIterationName() + "\n");
		
		out.concat("\t Instance \t Cluster \t Iteración\n");
		System.out.println(clusterL.size());
		for (int i = 0; i < clusterL.size(); i++)
		{System.out.println(" i =" + i);
			clus = clusterL.get(i);
			it = clus.getInstances().iterator();
			System.out.println("Cluster"+clus.getNumber() +"\t" + clus.getTamano());
			while (it.hasNext())
			{System.out.println("entro en while");
				ins = it.next();
				System.out.println(ins.getAtributeSize()+"Hola ke ase");
				//out.concat("\t Instancia "+ ins.getName() + " \t Cluster " + clus.getNumber() +" \t  Iteración " + iterat.getIterationName());
				System.out.println("\t Instancia "+ ins.getName() + " \t Cluster " + clus.getNumber() +" \t  Iteración " + iterat.getIterationName());
			}
			
		}
		System.out.println(out);
		
		return out;
	}

}

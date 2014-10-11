package packJerarquico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class Printer {
	private static Printer myPrinter = null;
	
	private Printer()
	{}
	
	public static Printer getPrinter() {
		
		if (myPrinter == null)
			myPrinter = new Printer();
		return myPrinter;
	}

	public void byScreenIteration(LinkedList<Iteration> iteratList) 
	{
		//Preparamos el texto que vamos
		// a sacar por pantalla
		Iterator<Iteration> it = iteratList.iterator();
		Iteration iterat = null;
		String text = null;
		String printable = null;
		while (it.hasNext())
		{
			iterat = it.next();
			text = prepareString(iterat);
			System.out.println(text);
		}
	}

	public void byTxtFile(LinkedList<Iteration> iteratList, String path) 
	{
		Iteration iterat = null;
		//File output = new File(path).getParentFile();
		File directory = new File(path + new Date().getTime());
		directory.mkdirs();
		Iterator<Iteration> it = iteratList.iterator();
		FileWriter output = null;
		PrintWriter printer = null;
		String text = null;
		try {
			while (it.hasNext())
			{
				iterat = it.next();
				output = new FileWriter(directory.getPath() + "\\Iteration" + iterat.getIterationName() + ".txt");
				text = prepareString(iterat);
				printer = new PrintWriter(output);
				printer.println(text);
				printer.close();
			}
	
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

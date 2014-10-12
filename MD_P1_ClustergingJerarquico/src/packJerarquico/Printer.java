package packJerarquico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDashPattern;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
		byPDF(iteratList,directory);
	}

	private String prepareString(Iteration iterat) 
	{	
		Instance ins;
		Iterator<Instance> it;
		Cluster clus;
		ArrayList<Cluster> clusterL = iterat.getClusterList();
		String out = "Iteración nº " + iterat.getIterationName() + "\n";
		
		out = out +"\t\t Instance \t\t Cluster \t\t  Iteración\n";
		for (int i = 0; i < clusterL.size(); i++)
		{
			clus = clusterL.get(i);
			it = clus.getInstances().iterator();
			while (it.hasNext())
			{
				ins = it.next();
				out = out + "\t\t Instancia "+ ins.getName() + "\t\t Cluster " + clus.getNumber() +"\t\t  Iteración " + clus.getIteration() + "\n";
			}
		}
		out = out + "\n \n" + "Se han unido los clusters " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getLeftParent().getNumber() + " y " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getRighttParent().getNumber() + " en el cluster " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getNumber() + "\n"; 
		return out;
	}

	public void byPDF(LinkedList<Iteration> list, File directory) 
	{
		Iterator<Iteration> it = list.iterator();
		Document document = new Document();
		Iteration aux;
		    // step 2
	        PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(document, new FileOutputStream(directory.getAbsolutePath() + ".pdf"));
			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        document.open();
	        Iterator<Cluster> itC = null;
	        Iterator<Instance> itI = null;
	        Cluster clus = null;
	        Instance ins = null;
	        PdfOutline root = writer.getRootOutline();
	        PdfOutline marcador = null;
	        Paragraph title;
	        PdfPTable table = null;
	        document.addTitle(directory.getName());
	        PdfPCell cell = null;
	        
	        
		while (it.hasNext())
		{
			aux = it.next();
			marcador = new PdfOutline(root, new PdfDestination(PdfDestination.FITH), "Iteración " + aux.getIterationName());
			title = new Paragraph("Iteración " + aux.getIterationName(),  FontFactory.getFont("arial", 16, Font.BOLDITALIC, BaseColor.BLUE));
			try {
				document.add(title);
				document.add(new Paragraph(" "));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table = new PdfPTable(3); 
			
			/*cell = new PdfPCell(new Paragraph("Instancia",FontFactory.getFont("arial",Font.BOLD)));
			cell.setHorizontalAlignment(50);

			table.addCell(cell);*/
			table.addCell(new Paragraph("Instancia",FontFactory.getFont("arial",12, Font.BOLD)));
			table.addCell(new Paragraph("Cluster",FontFactory.getFont("arial",12, Font.BOLD)));
			table.addCell(new Paragraph("Iteración",FontFactory.getFont("arial",12, Font.BOLD)));
			itC = aux.getClusterList().iterator();
			
			while (itC.hasNext())
			{
				clus = itC.next();
				itI = clus.getInstances().iterator();
		
				while (itI.hasNext())
					{
						ins = itI.next();
					
						table.addCell("Instancia "+ ins.getName());
						table.addCell("Cluster "+ clus.getNumber());
						table.addCell("Iteración " + clus.getIteration());
					}
					
			}
			try {
				document.add(table);
				document.add(new Paragraph("Se han unido los clusters " + clus.getLeftParent().getNumber() + " y " + clus.getRighttParent().getNumber() + " en el cluster " + clus.getNumber()));
				document.newPage();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			document.close();
		
	}

}

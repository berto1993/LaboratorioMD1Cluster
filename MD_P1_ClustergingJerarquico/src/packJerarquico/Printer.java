package packJerarquico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
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
	/**
	 * Saca por pantalla el resultado de
	 * cada iteracion
	 * @param iteratList Lista de iteracines
	 * @param divisive 
	 */
	public void byScreenIteration(LinkedList<Iteration> iteratList, boolean divisive) 
	{
		//Preparamos el texto que vamos
		// a sacar por pantalla
		Iterator<Iteration> it = iteratList.iterator();
		Iteration iterat = null;
		String text = null;
		while (it.hasNext())
		{
			iterat = it.next();
			text = prepareString(iterat, divisive);
			System.out.println(text);
		}
	}
	/**
	 * Escribe en ficheros dentro de un directorio
	 * los resultados de cada iteracion
	 * @param iteratList Lista de iteraciones
	 * @param path Ruta donde generar el directorio
	 * @param divisive 
	 */
	public void byTxtFile(LinkedList<Iteration> iteratList, String path, boolean divisive) 
	{
		Iteration iterat = null;
		//File output = new File(path).getParentFile();
		File directory = new File(path);
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
				text = prepareString(iterat, divisive);
				printer = new PrintWriter(output);
				printer.println(text);
				printer.close();
			}
	
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prepara un string para sacar por 
	 * pantalla o fichero
	 * @param iterat Una iteracion
	 * @param divisive 
	 * @return
	 */
	private String prepareString(Iteration iterat, boolean divisive) 
	{	
		Instance ins;
		Iterator<Instance> it;
		Cluster clus;
		ArrayList<Cluster> clusterL = iterat.getClusterList();
		String out = "Iteración nº " + iterat.getIterationName() + "\n";
		
		out = out +"\t\t Instance \t\t Cluster \t\t  Iteración\n";
		for (int i = 0; i < clusterL.size(); i++)
		{
			System.out.println(i + " de " +clusterL.size());
			clus = clusterL.get(i);
			it = clus.getInstances().iterator();
			while (it.hasNext())
			{
				ins = it.next();
				out = out + "\t\t Instancia "+ ins.getName() + "\t\t Cluster " + clus.getNumber() +"\t\t  Iteración " + clus.getIteration() + "\n";
			}
		}
		if (!divisive)
		out = out + "\n \n" + "Se han unido los clusters " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getLeftParent().getNumber() + " y " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getRighttParent().getNumber() + " en el cluster " + iterat.getClusterList().get(iterat.getClusterList().size()-1).getNumber() + "\n"; 
		return out;
	}

	/**
	 * 
	 * @param list Lista de iteraciones
	 * @param path Ruta donde se va a guardar el fichero
	 * @param divisive 
	 */
	@SuppressWarnings("deprecation")
	public void byPDF(LinkedList<Iteration> list, String path, boolean divisive) 
	{
		Iterator<Iteration> it = list.iterator();
		Document document = new Document();
		File file = new File (path + ".pdf");
		Iteration aux;
		    // step 2
	        PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(document, new FileOutputStream(file));
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
	        Paragraph title;
	        PdfPTable table = null;
	        document.addTitle(file.getName());
	      
		while (it.hasNext())
		{
			aux = it.next();
			new PdfOutline(root, new PdfDestination(PdfDestination.FITH), "Iteración " + aux.getIterationName());
			title = new Paragraph("Iteración " + aux.getIterationName(),  FontFactory.getFont("arial", 16, Font.BOLDITALIC, BaseColor.BLUE));
			try {
				document.add(title);
				document.add(new Paragraph(" "));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table = new PdfPTable(3); 
			
			
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
				if(!divisive)
				document.add(new Paragraph("Se han unido los clusters " + clus.getLeftParent().getNumber() + " y " + clus.getRighttParent().getNumber() + " en el cluster " + clus.getNumber()));
				document.newPage();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(!divisive)
		{
		  new PdfOutline(root, new PdfDestination(PdfDestination.FITH), "Dendograma" );
			title = new Paragraph("Dendograma\n",  FontFactory.getFont("arial", 16, Font.BOLDITALIC, BaseColor.BLUE));
			try {
				document.add(title);
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Image img = null;
			try {
				img = Image.getInstance(new File(path + "/dendograma.jpg").toURL());
				document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
				document.newPage();
				document.add(img);
			} catch (BadElementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			document.close();	
	}
	
	/**
	 * 
	 * @param path ruta donde crear el fichero del dendograma
	 * @param list lista de iteraciones
	 */
	public void createDendogram(String path, LinkedList<Iteration> list)
	{
		int instaCoor = 18;
		File fichero = new File(path +"/Dendograma.jpg");
		String formato = "jpg";
		int nInstancias = ListOfInstances.getListOfInstances().getSize();
		int width = nInstancias * instaCoor + 100 ;
		int height = nInstancias  * instaCoor +100;
		int maxHeight = height - 100;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Iteration iter = null;
		Iterator<Iteration> itI = list.descendingIterator();
		Cluster clus = null;
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		//dibujar (g, itI, iter, width, height, nInstancias,instaCoor, 0);
		if(itI.hasNext())
			{
			iter = itI.next();
			clus = iter.getClusterList().get(iter.getClusterSize()-1);
			}
		draw(g, clus, width, maxHeight
				, instaCoor, 0 , 0, false);
	
		try {
			ImageIO.write(image, formato, fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param g El objeto que dibuja
	 * @param clus El cluster a dibujar
	 * @param width Anchura maxima 
	 * @param maxHeight Altura maxima
	 * @param instaCoor Separacion entre cluster
	 * @param lW Posicion anterior X
	 * @param lD Posicion anterior Y
	 * @param izq booleano que indica si se dibuja a la izquierda o derecha
	 */
private void draw(Graphics g, Cluster clus, int width, int maxHeight,
			int instaCoor, int lW, int lD, boolean izq) {
		// TODO Auto-generated method stub
		if (lW == 0 && lD == 0)
		{
			lW = lW + instaCoor;
			lD = lD + instaCoor;
			g.drawLine(lW, 0 , lW, lD);
			if(clus.getLeftParent() != null)
				draw(g, clus.getLeftParent(), width, maxHeight, instaCoor, lW, lD, true);
			if(clus.getRighttParent() != null)
				draw(g, clus.getRighttParent(), width, maxHeight, instaCoor, lW, lD, false);
			}
		else
		{
			if (izq)
			{
				g.drawLine(lW, lD, lW - instaCoor, lD);
				lW = lW - instaCoor;
				if (clus.getLeftParent() == null && clus.getRighttParent() == null)
				{

					g.drawLine(lW, lD, lW, maxHeight);
					 // Create a rotation transformation for the font.
				    AffineTransform fontAT = new AffineTransform();
				    
					 // get the current font
				    java.awt.Font theFont = g.getFont();

				    // Derive a new font using a rotatation transform
				   // fontAT.rotate(45);
				    fontAT.rotate(90 * java.lang.Math.PI/180);
				    java.awt.Font theDerivedFont = theFont.deriveFont(fontAT);

				    // set the derived font in the Graphics2D context
				    g.setFont(theDerivedFont);

				    // Render a string using the derived font
				    g.drawString("Cluster"+clus.getNumber(), lW, maxHeight+5);

				    // put the original font back
				    g.setFont(theFont);
				}
				else
				{
					g.drawLine(lW, lD, lW, lD + instaCoor);
					lD = lD + instaCoor;
					if(clus.getLeftParent() != null)
						draw(g, clus.getLeftParent(), width, maxHeight, instaCoor, lW, lD, true);
					if(clus.getRighttParent() != null)
						draw(g, clus.getRighttParent(), width, maxHeight, instaCoor, lW, lD, false);
					
				}
			}
			else
			{
				g.drawLine(lW, lD, lW + instaCoor, lD);
				lW = lW + instaCoor;
				if (clus.getLeftParent() == null && clus.getRighttParent()==null)
					{
					g.drawLine(lW, lD, lW, maxHeight);
					g.drawLine(lW, lD, lW, maxHeight);
					 // Create a rotation transformation for the font.
				    AffineTransform fontAT = new AffineTransform();
				    
					 // get the current font
				    java.awt.Font theFont = g.getFont();

				    // Derive a new font using a rotatation transform
				   // fontAT.rotate(45);
				    fontAT.rotate(90 * java.lang.Math.PI/180);
				    java.awt.Font theDerivedFont = theFont.deriveFont(fontAT);

				    // set the derived font in the Graphics2D context
				    g.setFont(theDerivedFont);

				    // Render a string using the derived font
				    g.drawString("Cluster"+clus.getNumber(), lW, maxHeight+5);

				    // put the original font back
				    g.setFont(theFont);
					}
				
				else
				{
					g.drawLine(lW, lD, lW, lD + instaCoor);
					lD = lD + instaCoor;
					if(clus.getLeftParent() != null)
						draw(g, clus.getLeftParent(), width, maxHeight, instaCoor, lW, lD, true);
					if(clus.getRighttParent() != null)
						draw(g, clus.getRighttParent(), width, maxHeight, instaCoor, lW, lD, false);
				}
			}
		}
	}


}
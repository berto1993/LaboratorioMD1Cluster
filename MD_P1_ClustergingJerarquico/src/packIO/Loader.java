package packIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import packJerarquico.Instance;

public class Loader 
{
	private static Loader myLoader = null;
	
	private Loader()
	{}
	
	public static Loader getLoader()
	{
		if (myLoader == null)
			myLoader = new Loader();
		return myLoader;
	}
	/**
	 * Carga las instancias (Solo carga los atributos numericos)
	 * @param path Ruta del fichero
	 * @return Array de instacias
	 */
	public Instance[] loadInstances(String path)
	{
		// Vamos a almacenar aqui
		// las instancias cargadas
		LinkedList<Instance> instances = new LinkedList<Instance>();
		// Vamos a crear en esta
		// variable las instancias
		Instance instan = null;
		File arffFile = null;
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			arffFile = new File (path);
			fr = new FileReader (arffFile);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
			String att [];
			// Vamos a almacenar solamente los 
			// atributos con un valor numerico
			// por eso guardamos si son en su posicion
			LinkedList<Boolean> numericosAux = new LinkedList<Boolean>();
			
			// Numero de atributos con el
			// que vamos a trabajar
			int attNumber = 0;
			attNumber = headerExtractor(br, numericosAux);
			
			// Para agilizar el proceso
			// trabajamos con un array
			// en vez de linkedList
			int[] numeric = numericExtractor(numericosAux, attNumber);
		
			// Para vaciar memoria
			// nos deshacemos de la
			//lista
			numericosAux.clear();
			int i = 1;
			while ((linea=br.readLine())!=null)
			{
				att = linea.split(",");
				instan = new Instance(i, att, numeric);
				instances.addLast(instan);
				i++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try
			{
				if( null != fr )
				{
					fr.close();
				}
			}catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		// lo convertimos a array para agilizar
		// el acceso a los atributos
		return instances.toArray(new Instance[instances.size()]);
	}
	
	/**
	 * Convierte la lista de que atributos son numericos en un 
	 * array con las posiciones de los mismos
	 * @param numericosAux
	 * @param attNumber
	 * @return 
	 */
	private int[] numericExtractor(LinkedList<Boolean> numericosAux,
			int attNumber) {
		//Introduce en un array de attNumber (numero de atributos numericos)
		// posiciones las posiciones de los atributos numericos
		int[] numeric = new int[attNumber];
		int i = 0;
		for(int j = 0; j < numericosAux.size(); j++)			
		{
			if(numericosAux.get(j) == true)
				{
				numeric[i] = j;
				i++;
				}
		}
		return numeric;
	}
/**
 * Extrae en una lista ligada que atributos son numericos y devuelve
 * el numero de los mismos
 * @param br 
 * @param numericosAux lista de booleanos indicando con true los atributos numericos
 * @return numero de atributos numericos
 * @throws IOException
 */
	private int headerExtractor(BufferedReader br,
			LinkedList<Boolean> numericosAux) throws IOException {
		int attNumber = 0;
		String linea;
		linea = br.readLine();
		linea = linea.toUpperCase();
		while(!linea.contains("@data") && !linea.contains("@DATA"))
		{
			linea = linea.toUpperCase();
			if (linea.contains("@ATTRIBUTE"))
			{
				if (!linea.contains("STRING") && !linea.contains("CLASS") && !linea.contains("{"))
				{
					attNumber++;
					numericosAux.addLast(true);
				}
				else
				{	
					numericosAux.addLast(false);
				}
			}
		linea = br.readLine();
		}
		return attNumber; 
	}	
		

}

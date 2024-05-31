package jcolonia.daw2023.junio;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Colección de entradas de tipo «{@link País}».
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class RelaciónPaíses implements Iterable<País> {
	/** El almacén subyacente. */
	private List<País> lista;

	/**
	 * Inicializa el almacén.
	 */
	public RelaciónPaíses() {
		lista = new Vector<País>();
	}

	/**
	 * Añade un nuevo país. No se realiza ningún control de contenido ni
	 * duplicidades.
	 * 
	 * @param elemento el país a añadir
	 */
	public void añadir(País elemento) {
		lista.add(elemento);
	}

	/**
	 * Exporta todos los países en una nueva lista/colección inmutable.
	 * 
	 * @return la nueva lista exportada
	 */
	public List<País> getListaElementos() {
		List<País> listaInmutable = Collections.unmodifiableList(lista);
		return listaInmutable;
	}

	/**
	 * Facilita el número de países almacenados.
	 * 
	 * @return el valor correspondiente
	 */
	public int getNúmElementos() {
		return lista.size();
	}

	/**
	 * Facilita un «iterador» sobre una lista inmutable generada con todos los
	 * elementos. Permite recorrer todos los elementos almacenados con una
	 * estructura de bucle tipo «for-each».
	 */
	@Override
	public Iterator<País> iterator() {
		List<País> clon;
		clon = getListaElementos();
		return clon.iterator();
	}
}

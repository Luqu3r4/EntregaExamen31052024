package jcolonia.daw2023.junio;

import java.io.IOException;
import java.util.List;

/**
 * Controlador de apoyo a la ventana {@link VisorWorldBD3} para consultar una
 * base de datos de países.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class ControlWorldBD {
	/** El nombre/ruta del archivo con los datos de configuración. */
	private static final String NOMBRE_RUTA_CONFIG = "worldconfig.xml";

	/** La colección de países. */
	private RelaciónPaíses conjunto;

	/** Inicializa el controlador sin datos. */
	public ControlWorldBD() {
		conjunto = null;
	}

	/**
	 * Facilita el número de países presentes en la colección.
	 * 
	 * @return el valor correspondiente.
	 */
	public int consultarTamaño() {
		return conjunto.getNúmElementos();
	}

	/**
	 * Carga la colección de paises desde la base de datos.
	 * 
	 * @see AccesoBD#consultarPaíses(String)
	 * @throws AccesoBDExcepción si se produce alguna incidencia en todo el proceso,
	 *                           especialmente al intentar cerrar el acceso.
	 */
	public void cargarDatos() throws AccesoBDExcepción {
		try (AccesoBD bd = new AccesoBD(NOMBRE_RUTA_CONFIG)) {
			conjunto = bd.consultarPaíses(AccesoBD.SELECT_TOTAL_POR_PAÍS);
			// conjunto = bd.consultarPaíses(AccesoBD.SELECT_TOTAL_POR_IDIOMA);
		} catch (AccesoBDExcepción ex) { // Cerrar y propagar
			throw ex;
		} catch (IOException ex) { // Encapsular
			throw new AccesoBDExcepción("Error de acceso a BD", ex);
		}
	}

	/**
	 * Completa el modelo de la tabla con la colección de paises. Elimina el
	 * contenido previo.
	 * 
	 * @param modelo el modelo a rellenar
	 * 
	 * @see RelaciónPaíses#getListaElementos()
	 */
	public void sincronizarListaPaíses(ModeloTablaPaíses modelo) {
		List<País> lista = conjunto.getListaElementos();

		modelo.vaciar();
		for (País elemento : lista) {
			modelo.addRow(elemento);
		}
	}
}

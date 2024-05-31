package jcolonia.daw2023.junio;

/**
 * Excepción usada por {@link AccesoBD} para cualquier incidencia relacionada
 * con el acceso a la configuración o al propio archivo de la base de datos.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 3.0 (20240129)
 * @see AccesoBD#consultarPaíses(String) consultarPaíses(String)
 */
public class AccesoBDExcepción extends RuntimeException {
	/** Número de serie, asociado a la versión de la clase. */
	private static final long serialVersionUID = 202040530001L;

	/**
	 * Crea una excepción sin ninguna información adicional.
	 */
	public AccesoBDExcepción() {
	}

	/**
	 * Crea una excepción con un texto descriptivo.
	 * 
	 * @param mensaje el texto correspondiente
	 */
	public AccesoBDExcepción(String mensaje) {
		super(mensaje);
	}

	/**
	 * Crea una excepción secundaria almacenando otra excepción de referencia.
	 * 
	 * @param causa la excepción –o {@link java.lang.Throwable Throwable}– original
	 */
	public AccesoBDExcepción(Throwable causa) {
		super(causa);
	}

	/**
	 * Crea una excepción secundaria almacenando otra excepción de referencia y un
	 * texto descriptivo.
	 * 
	 * @param mensaje el texto correspondiente
	 * @param causa   la excepción –o {@link java.lang.Throwable Throwable}–
	 *                original
	 */
	public AccesoBDExcepción(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	/**
	 * Crea una excepción secundaria almacenando otra excepción de referencia y un
	 * texto descriptivo. Permite deshabilitar ̣—de manera predeterminada habilitada
	 * en los demás constructores— la posibilidad de almacenar una lista de otras
	 * excepciones simultáneas suprimidas.
	 * 
	 * @param mensaje           el texto correspondiente
	 * @param causa             la excepción –o {@link java.lang.Throwable
	 *                          Throwable}– original
	 * @param permitirSupresión para habilitar/deshabilitar la lista de suprimidas
	 * @param trazaEditable     para permitir/prohibir la edición de la pila de
	 *                          rastreo
	 */
	public AccesoBDExcepción(String mensaje, Throwable causa, boolean permitirSupresión, boolean trazaEditable) {
		super(mensaje, causa, permitirSupresión, trazaEditable);
	}
}

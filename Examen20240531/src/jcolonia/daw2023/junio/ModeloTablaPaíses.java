package jcolonia.daw2023.junio;

import javax.swing.table.DefaultTableModel;

/**
 * Modelo de datos asociado a la tabla de listado de países.
 * 
 * @see País
 */
class ModeloTablaPaíses extends DefaultTableModel {
	/** Número de serie, asociado a la versión de la clase. */
	private static final long serialVersionUID = 202040530001L;
	/** Tipos de datos de las columnas. */
	private static final Class<?>[] TIPOS_COLUMNAS = { Integer.class, String.class, String.class, String.class };
	/** Editabilidad de las columnas. */
	private static final boolean[] COLUMNAS_EDITABLES = { false, false, false, false };
	/** Nombres visibles en el encabezado de cada columna, */
	private static final String[] NOMBRES_COLUMNAS = { "#", "País", "Capital", "Idioma" };

	/**
	 * Crea un modelo de tipo predeterminado sin ninguna fila.
	 */
	public ModeloTablaPaíses() {
		super(NOMBRES_COLUMNAS, 0);
	}

	/**
	 * Facilita el tipo de datos de una columna.
	 * 
	 * @param columna el número de columna [0..n]
	 */
	@Override
	public Class<?> getColumnClass(int columna) {
		return TIPOS_COLUMNAS[columna];
	}

	/**
	 * Facilita la editabilidad de una columna.
	 * 
	 * @param columna el número de columna [0..n]
	 */
	@Override
	public boolean isCellEditable(int fila, int columna) {
		return COLUMNAS_EDITABLES[columna];
	}

	/**
	 * Agrega una nueva fila con los datos de un país.
	 * 
	 * @param nuevoPaís el país a incorporar
	 */
	public void addRow(País nuevoPaís) {
		addRow(new Object[] { Integer.valueOf(0), nuevoPaís.nombre(), nuevoPaís.capital(), nuevoPaís.idioma() });
	}

	/**
	 * Vacía, elimina todas las filas de la tabla.
	 */
	public void vaciar() {
		setRowCount(0);
	}
}

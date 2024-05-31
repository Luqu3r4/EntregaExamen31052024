package jcolonia.daw2023.junio;

/**
 * Ejecutable simple para crear un archivo de configuración.
 * 
 * @see AccesoBD#grabarConfiguración(String, String, boolean)
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class EjemploGuardarConfiguraciónSimple {

	/**
	 * Crea el archivo de configuración «worldconfig.xml» para la base de datos
	 * SQLite «world2.db».
	 * 
	 * @param argumentos opciones en línea de órdenes –no se usa–.
	 */
	public static void main(String[] argumentos) {
		try {
			AccesoBD.grabarConfiguración("worldconfig.xml", "world2.db", false);
			System.out.println("Archivo creado");
		} catch (AccesoBDExcepción e) {
			e.printStackTrace();
		}
	}
}

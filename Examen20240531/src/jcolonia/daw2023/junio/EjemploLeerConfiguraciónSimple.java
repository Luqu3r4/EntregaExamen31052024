package jcolonia.daw2023.junio;

import static java.lang.System.out;

import java.util.Properties;

/**
 * Ejecutable simple para mostrar el contenido de un archivo de configuración.
 * 
 * @see AccesoBD#leerConfiguración(String)
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class EjemploLeerConfiguraciónSimple {

	/**
	 * Envía a la salida estándar la configuración contenida en el archivo
	 * «worldconfig.xml».
	 * 
	 * @param argumentos opciones en línea de órdenes –no se usa–.
	 */
	public static void main(String[] argumentos) {
		Properties configuración;
		try {
			configuración = AccesoBD.leerConfiguración("worldconfig.xml");
			configuración.list(out);
		} catch (AccesoBDExcepción e) {
			e.printStackTrace();
		}
	}
}

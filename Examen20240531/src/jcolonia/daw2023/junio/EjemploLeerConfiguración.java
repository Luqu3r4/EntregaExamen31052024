package jcolonia.daw2023.junio;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Ejecutable simple para mostrar el contenido de un archivo de configuración.
 * Versión autónoma no dependiente de {@link AccesoBD}. Versión autónoma
 * simplificada -«esquiva» excepciones, no recurre a {@link AccesoBD}.
 * 
 * @see AccesoBD#leerConfiguración(String)
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class EjemploLeerConfiguración {

	/**
	 * Envía a la salida estándar la configuración contenida en el archivo
	 * «worldconfig.xml».
	 * 
	 * @param argumentos opciones en línea de órdenes –no se usa–.
	 * @throws Exception si se produce cualquier incidencia
	 */
	public static void main(String[] argumentos) throws Exception {
		Properties configuración;

		Path rutaConfig;
		rutaConfig = Path.of("worldconfig.xml");

		if (!Files.exists(rutaConfig)) {
			throw new Exception("Archivo de configuración no existe: " + rutaConfig.toString());
		}

		configuración = new Properties();

		FileInputStream in = new FileInputStream(rutaConfig.toFile());
		configuración.loadFromXML(in);
		in.close();

		configuración.list(out);
	}
}

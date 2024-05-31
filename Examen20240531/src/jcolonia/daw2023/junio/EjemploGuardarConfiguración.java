package jcolonia.daw2023.junio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Ejecutable simple para crear un archivo de configuración. Versión autónoma
 * simplificada -«esquiva» excepciones, no recurre a {@link AccesoBD}.
 * 
 * @see AccesoBD#grabarConfiguración(String, String, boolean)
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class EjemploGuardarConfiguración {

	/**
	 * Crea el archivo de configuración «worldconfig.xml» para la base de datos
	 * SQLite «world2.db».
	 * 
	 * @param argumentos opciones en línea de órdenes –no se usa–.
	 */
	public static void main(String[] argumentos) {
		Properties configuraciónModelo;

		Path rutaConfig, rutaBD;
		rutaConfig = Path.of("worldconfig.xml");
		rutaBD = Path.of("world2.db");

		if (Files.exists(rutaConfig)) {
			throw new RuntimeException("Archivo de configuración ya existe: " + rutaConfig.toString());
		}

		configuraciónModelo = new Properties();

		configuraciónModelo.setProperty("jdbc.url", "jdbc:sqlite:" + rutaBD.toString());
		configuraciónModelo.setProperty("jdbc.user", "");
		configuraciónModelo.setProperty("jdbc.password", "🙁");
		configuraciónModelo.setProperty("jdbc.codificación", "UTF-8");

		try (FileOutputStream out = new FileOutputStream(rutaConfig.toFile())) {
			configuraciónModelo.storeToXML(out, "Configuración BD", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Archivo creado");
	}
}

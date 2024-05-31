package jcolonia.daw2023.junio;

/**
 * Datos de un país.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 * @param número  el número
 * @param nombre  el nombre
 * @param capital la capital
 * @param idioma  el idioma
 */
public record País(int número, String nombre, String capital, String idioma) {
}

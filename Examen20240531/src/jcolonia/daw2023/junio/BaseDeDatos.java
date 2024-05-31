package jcolonia.daw2023.junio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {
	private String fuente;
	private String nombre;
	private String capital;
	private String idioma = "";
	
	private ResultSet loteDatos;
	private int contador;
	
	public BaseDeDatos(String fuente, int contador) {
		this.fuente = fuente;
		this.contador = contador;
	}

	public void ejecutarConsulta(){
		String consultaNueva = "SELECT * FROM Country where name = ?";
		try (Connection conexión = DriverManager.getConnection(fuente);
			Statement sentenciaSQL = conexión.createStatement();
			PreparedStatement consultaSQL = conexión.prepareStatement(consultaNueva);)
		{
			consultaSQL.setQueryTimeout(5);
			
			consultaSQL.setString(1, getNombre());
			
			loteDatos = consultaSQL.executeQuery();
			
			setResultados();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public País getPais() {
		País pais;
		
		pais = new País(contador,nombre, capital, idioma);
		
		return pais;
	}
	public País getPais(int contador) {
		País pais;
		
		pais = new País(contador,nombre, capital, idioma);
		
		return pais;
	}
	
	private void setResultados() {
		idioma = "";
		nombre = "";
		capital = "";
		try {
			while (loteDatos.next()) {
				nombre = loteDatos.getString("Name");
				capital = loteDatos.getString("Capital");
				idioma += loteDatos.getString("Language");
				idioma += (" ");
				}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}

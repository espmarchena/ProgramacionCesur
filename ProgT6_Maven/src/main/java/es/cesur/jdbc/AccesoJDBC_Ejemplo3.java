package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoJDBC_Ejemplo3 {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456");

			// 1. Ejecutamos el UPDATE
			String updateQuery = "UPDATE peliculas SET GENERO = ? WHERE TITULO LIKE ?"; //cada interrogacion que aparece en una instruccion sql es un parametro
			PreparedStatement pstUpdate = dbcon.prepareStatement(updateQuery); //instruccion sql parametrizada
			pstUpdate.setString(1, "Teatro"); //establece un String para el primer parametro (primera interrogacion de la instruccion)
			pstUpdate.setString(2, "NUEVA PELI"); //establece un String para el segundo parametro (segunda interrogacion de la instruccion)
			pstUpdate.executeUpdate(); //ejecutar la actualizacion 
			pstUpdate.close();

			// 2. Ejecutamos un SELECT para ver los cambios y tenemos que crear otro objeto
			// PreparedStatement
			String selectQuery = "SELECT TITULO, GENERO, PRECIO FROM peliculas WHERE TITULO LIKE ?";
			PreparedStatement pstSelect = dbcon.prepareStatement(selectQuery);
			pstSelect.setString(1, "NUEVA PELI");
			ResultSet resultado = pstSelect.executeQuery();

			// Mostrar los datos
			while (resultado.next()) {
				String titulo = resultado.getString("TITULO");
				String genero = resultado.getString("GENERO");
				Float precio = resultado.getFloat("PRECIO");

				System.out.println(titulo + " - " + genero + " - " + precio + "€");
			}

			resultado.close();
			pstSelect.close();
			dbcon.close();

		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			
		} catch (SQLException sqle) {
			System.out.println("Error de SQL " + sqle);
			System.out.println(sqle.getMessage());
		}
	}
}
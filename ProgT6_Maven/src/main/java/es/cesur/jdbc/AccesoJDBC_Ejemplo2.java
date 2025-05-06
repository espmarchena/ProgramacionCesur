package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC_Ejemplo2 {

	public static void main(String[] args) {

		try {

//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");

//			String sourceURL = "jdbc:mysql://localhost:3306/videoclub";
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub";

			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456");

			Statement sentencia = dbcon.createStatement();
			
			String consulta = "SELECT TITULO, GENERO, ANIO, PRECIO FROM peliculas";
			
			ResultSet resultado = sentencia.executeQuery(consulta);

			// Mostrar los datos
			while (resultado.next()) {
				String titulo = resultado.getString(1);// Valor de la 1ª columna del ResultSet TITULO // .getString() porque es un varchar
				String genero = resultado.getString(2);
				int anio = resultado.getInt(3);
				Float precio = resultado.getFloat(4);// Valor de la 4ª columna del RESULTSET PRECIO

				System.out.println(titulo + " " + precio + " €");
			}
			resultado.close();
			sentencia.close();
			dbcon.close();
			
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			
		} catch (SQLException sqle) {
			System.out.println("Error de SQL " + sqle);
		}
	}
}

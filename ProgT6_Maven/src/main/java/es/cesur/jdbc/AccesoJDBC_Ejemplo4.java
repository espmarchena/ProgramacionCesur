package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC_Ejemplo4 {
	
	public static void main(String[] args) {

		try {

//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");

//			String sourceURL = "jdbc:mysql://localhost/videoclub";
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456");

			Statement sentencia = dbcon.createStatement();
			String consulta = "SELECT TITULO, GENERO, PRECIO FROM peliculas";
			ResultSet rs = sentencia.executeQuery(consulta);

			ResultSetMetaData rsmdt = rs.getMetaData();
			System.out.println("Numero de columnas del ResultSet: " + rsmdt.getColumnCount()); // devuelve numero de columnas
			System.out.println("\n");
			
			
			for (int i=1; i<rsmdt.getColumnCount(); i++) {	
				System.out.println(rsmdt.getColumnName(i));
				System.out.println(rsmdt.getColumnTypeName(i));
				System.out.println(rsmdt.getColumnLabel(i));
				System.out.println("\n");			
			}

			rs.close();
			sentencia.close();
			dbcon.close();
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			
		} catch (SQLException sqle) {
			System.out.println("Error de SQL " + sqle);
		}
	}

}

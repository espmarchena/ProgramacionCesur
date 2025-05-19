package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej3_CrearBD {

	// Conexión a la base de datos
//  static String driver = "com.mysql.cj.jdbc.Driver";
	static String driver = "org.mariadb.jdbc.Driver";

//  static String url = "jdbc:mysql://localhost:3306/";
	static String url = "jdbc:mariadb://localhost:3306/";

	static String usuario = "root";
	static String clave = "123456";

	public static void main(String[] args) {
		Connection conexion = null;
		Statement statement = null;
		try {
			// Registro el Driver
			Class.forName(driver);

			// Abir la conexión
			System.out.println("Conectando a la Base de Datos...");
			conexion = DriverManager.getConnection(url, usuario, clave);

			// Crear la base de datos
			System.out.println("Creando la Base de Datos...");
			statement = conexion.createStatement();

			String sql = "CREATE DATABASE comercial2 CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci";
			statement.executeUpdate(sql);
			System.out.println("Base de Datos creada correctamente...");
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			// Cerrar statement y la conexión a la base de datos
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException se2) {
			}

			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}

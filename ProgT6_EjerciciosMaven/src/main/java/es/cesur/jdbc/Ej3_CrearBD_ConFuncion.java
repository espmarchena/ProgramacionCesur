package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej3_CrearBD_ConFuncion {

	// Datos de conexión
	static final String DRIVER = "org.mariadb.jdbc.Driver";
	static final String URL = "jdbc:mariadb://localhost:3306/";
	static final String USUARIO = "root";
	static final String CLAVE = "123456";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce el nombre de la base de datos a crear: ");
		String nombreBD = scanner.nextLine();
		scanner.close();

		// Llamada a la función
		crearBaseDatos(nombreBD);
	}

	public static void crearBaseDatos(String nombreBD) {
		Connection conexion = null;
		Statement statement = null;

		try {
			// Registrar el driver
			Class.forName(DRIVER);

			// Establecer conexión con el servidor de bases de datos
			System.out.println("Conectando al servidor de bases de datos...");
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

			// Crear la base de datos
			System.out.println("Creando la base de datos '" + nombreBD + "'...");
			statement = conexion.createStatement();

			String sql = "CREATE DATABASE " + nombreBD + " CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci"; //instruccion para crear la bbdd

			statement.executeUpdate(sql); //ejecuta la instruccion
			System.out.println("Base de datos '" + nombreBD + "' creada exitosamente!");

		} catch (ClassNotFoundException e) {
			System.err.println("Error: No se encontró el driver JDBC");
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Error SQL al crear la base de datos");
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
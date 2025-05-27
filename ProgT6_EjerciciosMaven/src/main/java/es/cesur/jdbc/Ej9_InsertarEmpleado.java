package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class Ej9_InsertarEmpleado {
 
	// private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
 
	// private static final String URL = "jdbc:mysql://localhost:3306/gestion";
	private static final String URL = "jdbc:mariadb://localhost:3306/gestion2";
 
	private static final String LOGIN = "root";
	private static final String PASSWORD = "123456";
 
	private static Connection con = null;
	private static Statement st = null;
 
	public static void main(String[] args) {
 
		Scanner sc = new Scanner(System.in);
		// Cargar el Driver
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = con.createStatement();
 
			System.out.println("INDICA LOS DATOS DEL NUEVO EMPLEADO");
 
			System.out.print("Nombre del empleado: ");
			String nombre = sc.nextLine();
 
			System.out.print("Apellidos del empleado: ");
			String apellidos = sc.nextLine();
 
			System.out.print("Cargo del empleado: ");
			String cargo = sc.nextLine();
 
			System.out.print("Salario del empleado: ");
			double salario = sc.nextDouble();
 
			System.out.print("ID Departamento del empleado: ");
			int idDepartamentoFK = sc.nextInt();
 
			String query = "INSERT INTO empleados VALUES (null, '" + nombre + "', '" + apellidos + "', '" + cargo
					+ "', '" + salario + "', '" + idDepartamentoFK + "')";
 
			System.out.println(query);
 
			int filas = st.executeUpdate(query);
 
			if (filas > 0) {
				System.out.println("Empleado insertado correctamente.");
			} else {
				System.out.println("No se insertó ningún registro.");
			}
			con.close();
			st.close();
			sc.close();
 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el Driver: " + e.getMessage());
 
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
 
		}
 
	}
 
}
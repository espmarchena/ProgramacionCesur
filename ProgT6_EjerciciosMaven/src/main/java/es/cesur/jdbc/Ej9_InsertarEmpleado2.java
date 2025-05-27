package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
public class Ej9_InsertarEmpleado2 {
	// private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
 
	// private static final String URL = "jdbc:mysql://localhost:3306/gestion";
	private static final String URL = "jdbc:mariadb://localhost:3306/gestion2";
 
	private static final String LOGIN = "root";
	private static final String PASSWORD = "123456";
 
	private static Connection con = null;
 
	public static void main(String[] args) {
 
		Scanner sc = new Scanner(System.in);
		// Cargar el Driver
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
 
			System.out.println("INDICA LOS DATOS DEL NUEVO EMPLEADO");
 
			System.out.print("Nombre del empleado: ");
			String nombre = sc.nextLine();
 
			System.out.print("Apellidos del empleado: ");
			String apellidos = sc.nextLine();
 
			System.out.print("Cargo del empleado: ");
			String cargo = sc.nextLine();
 
			System.out.print("Salario del empleado: ");
			double salario = sc.nextDouble();
			sc.nextLine();
 
			System.out.print("Nombre del Departamento del empleado: ");
			String nombreDepartamento = sc.nextLine();
 
			/*
			 * Lo primero que tenemos que hacer es seleccionar de la tabla departamentos el
			 * id que corresponde al nombreDepartamento que el usuario ha introducido por
			 * teclado
			 */
			System.out.println("El nombre del departamento indicado por teclado es: " + nombreDepartamento);
			String selccionarIdDepartamento = "SELECT idDepartamento FROM departamentos WHERE nombreDepartamento = ?";
 
			/* Instrucción SQL para insertar el nuevo empleado */
			String insertar = "INSERT INTO empleados (nombreEmpleado, apellidosEmpleado, cargoEmpleado, salarioEmpleado, idDepartamentoFK) VALUES (?, ?, ?, ?, ?)";
 
			PreparedStatement seleccionarDepartamento = con.prepareStatement(selccionarIdDepartamento);
			seleccionarDepartamento.setString(1, nombreDepartamento);
 
			ResultSet rs = seleccionarDepartamento.executeQuery();
 
			PreparedStatement psInsert = con.prepareStatement(insertar);
 
			if (rs.next()) {
				int idDepartamento = rs.getInt("idDepartamento");
				System.out.println("El idDepartamento seleccionado es: " + idDepartamento);
				/* Insertamos el nuevo empleado con el idDepartamento obtenido */
				psInsert.setString(1, nombre);
				psInsert.setString(2, apellidos);
				psInsert.setString(3, cargo);
				psInsert.setDouble(4, salario);
				psInsert.setInt(5, idDepartamento);
 
				int numeroFilas = psInsert.executeUpdate();
				if (numeroFilas > 0) {
					System.out.println("El nuevo emplado se ha insertado bien.");
				} else {
					System.out.println("No se ha hecho el insert");
				}
 
			} else {
				System.out.println("No existe el departamento indicado");
			}
			psInsert.close();
			rs.close();
			seleccionarDepartamento.close();
			con.close();
 
			sc.close();
 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el Driver: " + e.getMessage());
 
		} catch (SQLException e) {
			System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
 
		}
 
	}
 
}

package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class Ej10_ActualizarEmpleado {
 
	// Conexión a la base de datos
//  String driver = "com.mysql.cj.jdbc.Driver";
	String driver = "org.mariadb.jdbc.Driver";
 
//  String url = "jdbc:mysql://localhost:3306/gestion";
	String url = "jdbc:mariadb://localhost:3308/gestion2";
 
	String login = "root";
	String password = "123456";
 
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
 
	public Ej10_ActualizarEmpleado() {
 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, login, password);
			st = con.createStatement();
			/*
			 * Consulta para mostrar los datos de los empleados y el nombre del departamento
			 * en lugar del idDepartamentoFK
			 */
			String consulta = """
					    SELECT e.idEmpleado, e.nombreEmpleado, e.apellidosEmpleado, e.cargoEmpleado, e.salarioEmpleado, d.nombreDepartamento AS nombre_departamento
					    FROM empleados e
					    LEFT JOIN departamentos d ON e.idDepartamentoFK = d.idDepartamento
					""";
 
			rs = st.executeQuery(consulta);
 
			System.out.println("Empleados disponibles:");
			System.out.println("ID - NOMBRE y APELLIDOS - CARGO - SALARIO - DEPARTAMENTO");
 
			while (rs.next()) {
				int id = rs.getInt("idEmpleado");
				String nombre = rs.getString("nombreEmpleado");
				String apellidos = rs.getString("apellidosEmpleado");
				String cargo = rs.getString("cargoEmpleado");
				double salario = rs.getDouble("salarioEmpleado");
				String departamento = rs.getString("nombre_departamento");
 
				System.out.println(id + " - " + nombre + " - " + apellidos + " - " + cargo + " - " + salario + " - "
						+ departamento);
			}
 
			/*
			 * Una vez que tenemos los empleados y sus departamentos. Actualizamos uno
			 * seleccionándolo por su ID, que se indica por teclado
			 */
 
			/*
			 * Para actualizar el empleado dando el nombre del departamento y no el
			 * idDepartamentoFK, primero tenemos que obtener el idDepartamento del
			 * nombreDepartamento dado por teclado
			 */
			Scanner sc = new Scanner(System.in);
			System.out.println("\nSelecciona el ID del empleado que quieres modificar:");
			int idSeleccionado = sc.nextInt();
			sc.nextLine(); // Limpiar buffer
 
			/*
			 * Pedimos al usuario por teclado, los nuevos datos que vamos a actualizar dando
			 * el nombre del departamento en el que queremos incluir el empleado
			 */
			System.out.println("Nuevo nombre del empleado:");
			String nuevoNombre = sc.nextLine();
 
			System.out.println("Nuevos apellidos del empleado:");
			String nuevosApellidos = sc.nextLine();
 
			System.out.println("Nuevo cargo del empleado:");
			String nuevoCargo = sc.nextLine();
 
			System.out.println("Nuevos salario del empleado:");
			double nuevoSalario = sc.nextDouble();
			sc.nextLine();
 
			System.out.println("Nuevo departamento del empleado:");
			String nuevoDepartamento = sc.nextLine();
 
			System.out.println("El nombre del departamento indicado por teclado es: " + nuevoDepartamento);
			String selccionarIdDepartamento = "SELECT idDepartamento FROM departamentos WHERE nombreDepartamento = ?";
 
			/* Instrucción SQL para insertar el nuevo empleado */
			String actualizar = "UPDATE empleados SET nombreEmpleado = ?, apellidosEmpleado = ?, cargoEmpleado = ?, salarioEmpleado = ?, idDepartamentoFK = ? WHERE idEmpleado = ?";
 
			PreparedStatement seleccionarDepartamento = con.prepareStatement(selccionarIdDepartamento);
			seleccionarDepartamento.setString(1, nuevoDepartamento);
 
			ResultSet rs = seleccionarDepartamento.executeQuery();
 
			PreparedStatement psInsert = con.prepareStatement(actualizar);
 
			if (rs.next()) {
				int idDepartamento = rs.getInt("idDepartamento");
				System.out.println("El idDepartamento seleccionado es: " + idDepartamento);
				/* Insertamos el nuevo empleado con el idDepartamento obtenido */
				psInsert.setString(1, nuevoNombre);
				psInsert.setString(2, nuevosApellidos);
				psInsert.setString(3, nuevoCargo);
				psInsert.setDouble(4, nuevoSalario);
				psInsert.setInt(5, idDepartamento);
				psInsert.setInt(6, idSeleccionado);
 
				int numeroFilas = psInsert.executeUpdate();
				if (numeroFilas > 0) {
					System.out.println("El nuevo emplado se ha actualizado bien.");
				} else {
					System.out.println("No se ha hecho lla actualización");
				}
 
			} else {
				System.out.println("No existe el departamento indicado");
			}
 
			rs.close();
			st.close();
			con.close();
			sc.close();
 
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el driver: " + e);
		} catch (SQLException sql) {
			System.out.println("Error al conectar con la base de datos: " + sql);
		}
 
	}
 
	public static void main(String[] args) {
		new Ej10_ActualizarEmpleado();
	}
 
}

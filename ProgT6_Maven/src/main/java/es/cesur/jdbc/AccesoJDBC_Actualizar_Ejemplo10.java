package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccesoJDBC_Actualizar_Ejemplo10 { // Actualizar bbdd
	// Conexión a la base de datos
//  String driver = "com.mysql.cj.jdbc.Driver";
	String driver = "org.mariadb.jdbc.Driver";

//  String url = "jdbc:mysql://localhost:3306/gestion";
	String url = "jdbc:mariadb://localhost:3306/gestion2";

	String login = "root";
	String password = "123456";

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public AccesoJDBC_Actualizar_Ejemplo10() {

		try {
			// Cargamos el Driver
			Class.forName(driver);

			// Establecemos la conexión con la BD
			con = DriverManager.getConnection(url, login, password);

			// Creaos el objeto de tipo Statement indicando el tipo de objeto ResultSet que
			// se creará
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			/*
			 * Creamos el objeto de tipo ResultSet para recorrerlo y mostrar los
			 * departamentos
			 */
			rs = st.executeQuery("SELECT * FROM departamentos");

			System.out.println("Departamentos disponibles:");

			while (rs.next()) {
				int id = rs.getInt("idDepartamento");
				String nombre = rs.getString("nombreDepartamento");
				String localidad = rs.getString("localidadDepartamento");

				System.out.println(id + " - " + nombre + " - " + localidad);
			}

			/*
			 * Pedimos al usuario, por teclado, que seleccione el ID del departamento que
			 * quiere actualizar:
			 */
			Scanner sc = new Scanner(System.in);
			System.out.println("\nSelecciona el ID del departamento que deseas modificar:");
			int idSeleccionado = sc.nextInt();
			sc.nextLine(); // Limpiar buffer

			/* Pedimos al usuario por teclado, los nuevos datos que vamos a actualizar */
			System.out.println("Nuevo nombre del departamento:");
			String nuevoNombre = sc.nextLine();

			System.out.println("Nueva localidad del departamento:");
			String nuevaLocalidad = sc.nextLine();

			String query = "UPDATE departamentos SET nombreDepartamento = ?, localidadDepartamento = ? WHERE idDepartamento = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, nuevoNombre);
			preparedStatement.setString(2, nuevaLocalidad);
			preparedStatement.setInt(3, idSeleccionado);

			int filasActualizadas = preparedStatement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Departamento actualizado correctamente.");
			} else {
				System.out.println("No se encontró el departamento con el ID " + idSeleccionado);
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
		new AccesoJDBC_Actualizar_Ejemplo10();
	}

}
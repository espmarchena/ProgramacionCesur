package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccesoJDBC_Eliminar_Ejemplo11 {
	
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

	public AccesoJDBC_Eliminar_Ejemplo11() {

		try {
			// Cargamos el Driver, que debe estar como dependencia en el proyecto
			Class.forName(driver);

			// Establecemos la conexión con la BBDD
			con = DriverManager.getConnection(url, login, password); // getConnection es un metodo estatico de la clase DriverManager

			// Creaos el objeto de tipo Statement indicando el tipo de objeto ResultSet que se creará
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //podemos movernos hacia delante y hacia detras en el ResultSet

			//Creamos el objeto de tipo ResultSet para recorrerlo y mostrar los departamentos
			rs = st.executeQuery("SELECT * FROM departamentos");

			System.out.println("Departamentos disponibles:");

			while (rs.next()) {
				int id = rs.getInt("idDepartamento");
				String nombre = rs.getString("nombreDepartamento");
				String localidad = rs.getString("localidadDepartamento");

				System.out.println(id + " - " + nombre + " - " + localidad);
			}

			/*
			 * Pedimos al usuario, por teclado, que seleccione el ID del departamento que quiere borrar:
			 */
			Scanner sc = new Scanner(System.in);
			System.out.println("\nSelecciona el ID del departamento que deseas borrar:");
			int idSeleccionado = sc.nextInt();
			sc.nextLine(); // Limpiar buffer

			/* Verificamos si el departamento existe */
			String query = "SELECT * FROM departamentos WHERE idDepartamento = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, idSeleccionado); //el 1 hará referencia al primer parametro
			rs = pst.executeQuery();

			pst.close();

			if (rs.next()) {
				System.out.println(
						"¿Está seguro de que desea eliminar el departamento con ID " + idSeleccionado + "? (sí/no)");
				// sc.nextLine(); // Limpiar buffer
				String respuesta = sc.nextLine();
				if ("sí".equalsIgnoreCase(respuesta)) {

					String query2 = "DELETE FROM departamentos WHERE idDepartamento = ?";
					PreparedStatement pst2 = con.prepareStatement(query2);
					pst2.setInt(1, idSeleccionado);
					int filasAfectadas = pst2.executeUpdate();
					
					if (filasAfectadas > 0) {
						System.out.println("Departamento con ID " + idSeleccionado + " ha sido borrado exitosamente.");
					} else {
						System.out.println("No se pudo borrar el departamento.");
					}
					pst2.close();
				} else {
					System.out.println("Operación cancelada.");
				}
			} else {
				System.out.println("El departamento con ID " + idSeleccionado + " no existe.");
			}

			sc.close();
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el driver: " + e);
			
		} catch (SQLException sql) {
			System.out.println("Error al conectar con la base de datos: " + sql);
		}

	}

	public static void main(String[] args) {
		new AccesoJDBC_Eliminar_Ejemplo11(); //llamo al constructor vacio para ejecutar todo el codigo que tengo implementado en él
	}

}
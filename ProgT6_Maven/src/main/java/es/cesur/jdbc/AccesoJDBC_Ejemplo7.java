package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC_Ejemplo7 {

// String driver = "com.mysql.cj.jdbc.Driver"; 
	String driver = "org.mariadb.jdbc.Driver";

// String url = "jdbc:mysql://localhost:3306/gestion"; 
	String url = "jdbc:mariadb://localhost:3306/gestion2";

	String login = "root";
	String password = "123456";

	String sentencia = "SELECT * FROM departamentos";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public AccesoJDBC_Ejemplo7() {

		// Cargar el Driver
		try {
			Class.forName(driver);

			// Establecer la conexión con la base de datos
			connection = DriverManager.getConnection(url, login, password);

			// Ejecutar consulta
			statement = connection.createStatement(); //cuando está vacio significa que por defecto se le estan pasando dos parametros de la interfaz de ResultSet. Sería como poner createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			/*ResultSet.TYPE_FORWARD_ONLY -> El cursor del ResultSet solo puede moverse hacia adelante
			 *ResultSet.CONCUR_READ_ONLY -> El ResultSet es de solo lectura*/

			rs = statement.executeQuery(sentencia); //el cursor se coloca antes del primer registro

			while (rs.next()) { //el cursor empieza desde el primer registro hasta el ultimo. Saldrá del bucle cuando llegue a un registro que no tenga datos
				int id = rs.getInt("idDepartamento");
				String nombre = rs.getString("nombreDepartamento");
				String localidad = rs.getString("localidadDepartamento");

				System.out.println("ID: " + id);
				System.out.println("Nombre: " + nombre);
				System.out.println("Localidad: " + localidad);
				System.out.println("----------------------");
			}
		} catch (ClassNotFoundException e) { //si no tenemos el Driver en el proyecto, lanzará esta excepcion

			System.out.println("Se ha producido un error al cargar el Driver");
			
		} catch (SQLException e) { //si cualquier linea del programa falla por cualquier fallo de sintaxis sql, lanzará esta excepcion
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			
		} finally { //se ejecuta aunque haya un error (aunque pase por el catch)
			// Cerramos los recursos dentro del finally por ello
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
				
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new AccesoJDBC_Ejemplo7();
	}
}
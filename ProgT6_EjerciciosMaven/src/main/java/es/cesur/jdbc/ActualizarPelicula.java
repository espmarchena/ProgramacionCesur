package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class ActualizarPelicula {
 
	private static String driver = "org.mariadb.jdbc.Driver"; //refactor/convert local variable to field/field declaration
	private static String url = "jdbc:mariadb://localhost:3306/videoclub"; //refactor/convert local variable to field/field declaration
	private static String user = "root"; // refactor/convert local variable to field/field declaration
	private static String password = "123456"; // refactor/convert local variable to field/field declaration

	public static void main(String[] args) {

		try {
			actualizarPelicula();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void actualizarPelicula() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el id de la película que quieres actualizar: ");
		int id = sc.nextInt();

		Class.forName(driver);

		Connection con = DriverManager.getConnection(url, user, password);

		String query = "UPDATE peliculas SET titulo = 'Nueva Película' WHERE id = " + id;

		Statement st = con.createStatement();

		int registrosActualizados = st.executeUpdate(query);

		if (registrosActualizados > 0) {
			System.out.println("La actualización se ha realizado correctamente.");
		} else {
			System.out.println("La actualización ha fallado.");
		}
		st.close();
		con.close();
		sc.close();
	}

}

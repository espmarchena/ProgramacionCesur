package es.cesur.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class AccesoJDBC_Insertar_Ejemplo9 { //Insertar departamento con lo introducido por teclado, metiéndolo dentro de la instruccion sql y añadiendose a la bbdd
 
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
 
			System.out.print("Nombre del departamento: ");
			String nombre = sc.nextLine();
 
			System.out.print("Localidad del departamento: ");
			String localidad = sc.nextLine();
 
			String query = "INSERT INTO departamentos VALUES (null, '" + nombre + "', '" + localidad + "')"; //rellenamos la instruccion sql con las variables que guardan lo que se introduce por teclado
			
			int filas = st.executeUpdate(query);
			
			if (filas > 0) {
				System.out.println("Departamento insertado correctamente.");
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
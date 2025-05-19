package es.cesur.jdbc;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej2_EncriptarClave {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/cesur2";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "123456";

	private static Connection con = null;
	private static Statement st = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = con.createStatement();

			System.out.print("Nombre del usuario: ");
			String nombre = sc.nextLine();

			System.out.print("Clave del usuario: ");
			String clave = sc.nextLine();

			// Verificar si el usuarioi existe
			if (usuarioExiste(con, nombre)) {
				System.out.println("Error: El usuario '" + nombre + "' ya existe en la base de datos.");
				return;
			}

			// Encriptar la contraseña
			String claveEncriptada = getSHA256(clave);

			String query = "INSERT INTO usuarios VALUES (null, '" + nombre + "', '" + claveEncriptada + "')";

			int filas = st.executeUpdate(query);

			if (filas > 0) {
				System.out.println("Usuario insertado correctamente.");
				System.out.println("Contraseña encriptada: " + claveEncriptada);
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

	// Método para verificar si el usuario ya existe
	private static boolean usuarioExiste(Connection con, String nombre) {
		String query = "SELECT COUNT(*) FROM usuarios WHERE nombreUsuario = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, nombre);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método para encriptar la contraseña con SHA-256
	public static String getSHA256(String data) {

		StringBuilder sb = new StringBuilder(); //permite trabajar de forma eficiente con objetos de tipo String (con las cadenas de caracteres)

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			md.update(data.getBytes());
			byte[] byteData = md.digest();

			for (byte b : byteData) {
				sb.append(String.format("%02x", b));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

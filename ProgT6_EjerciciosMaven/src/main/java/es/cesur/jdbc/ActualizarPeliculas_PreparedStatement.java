package es.cesur.jdbc;

public class ActualizarPeliculas_PreparedStatement {

	static String url = "jdbc:mariadb://localhost:3306/videoclub";
	static String user = "root";
	static String password = "123456";
	
	public static void main(String[] args) {
		
		Metodos.actualizarPeliculas_PreparedStatement("videoclub", "Nuevo título", 18);
	}

}

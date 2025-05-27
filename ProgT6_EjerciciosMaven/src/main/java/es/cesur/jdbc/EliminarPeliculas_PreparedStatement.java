package es.cesur.jdbc;

import java.sql.SQLException;

public class EliminarPeliculas_PreparedStatement {
	
	public static void main(String[] args) throws SQLException {
		
		Metodos.eliminarPeliculas_PreparedStatement("videoclub", 6);
		
	}

}

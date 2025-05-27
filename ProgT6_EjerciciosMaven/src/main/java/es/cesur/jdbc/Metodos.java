package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos {
	
	static String url = "jdbc:mariadb://localhost:3306/videoclub";
	static String user = "root";
	static String password = "123456";
	
	public static Connection conexion(String bd) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void consultarPeliculas_PreparedStatement(String bd) throws SQLException {
		Connection con = conexion(bd);
	
		String query = "SELECT * FROM PELICULAS WHERE TITULO= ? AND GENERO = ?"; //es parametizada porque tiene '?' y con el preparedstatement les damos valores
		
		PreparedStatement ps = con.prepareStatement(query); // nos permitirá ejecutar la instruccion/query en la bbdd
		ps.setString(1, "Nueva Peli"); //1er parametro es el orden de la '?' a la que le quiero dar el valor(titulo). 2o parametro es el valor que quiero darle
		ps.setString(2, "Teatro"); //(teatro)
		
		ResultSet rs = ps.executeQuery(); //nos devuelve el resultset. executeQuery ejecuta la query
		
		while(rs.next()) {
			rs.getInt(1); //primera columna del resultset, en este caso es el ID y como es un entero se pone getInt. Porque es la linea que hemos indicado en nuestra query
			rs.getString(2); //segunda columna, en este caso titulo. Al ser un varchar/String, se pone getString. Tb se podria pasar asi: rs.getString("TITULO);
			rs.getString("GENERO"); //otra forma de pasar el parametro
			rs.getInt(4);
			rs.getDouble(5);
			rs.getDouble(6);
			
			System.out.println("ID: " + rs.getInt(1) + ", título: " + rs.getString(2) + ", género: " + rs.getString(3) + ", año: " + rs.getInt(4) + ", precio: " + rs.getDouble(5) + " y precio de alquiler: " + rs.getDouble(6));
		}
	}


	public static void insertarPelicula_PreparedStatement(String titulo, String genero, int anio, double precio, double precioAlquiler, String bd) throws SQLException {
		Connection con = conexion(bd);
	
		String query = "INSERT INTO PELICULAS (TITULO, GENERO, ANIO, PRECIO, PRECIOALQUILER) VALUES (?, ?, ?, ?, ?)"; //es parametizada porque tiene '?'
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, titulo); //1er parametro es el orden de la '?' a la que le quiero dar el valor(titulo). 2o parametro es el valor que quiero darle
		ps.setString(2, genero); //(genero)
		ps.setInt(3, anio); //(año)
		ps.setDouble(4, precio); //precio
		ps.setDouble(5, precioAlquiler); //precioalquiler
		
		int registroInsertado = ps.executeUpdate(); 
		
		if (registroInsertado > 0) {
			System.out.println("Pelicula creada correctamente");
		}
		else {
			System.out.println("Pelicula no insertada");
		}
	}

	public static void actualizarPeliculas_PreparedStatement(String bd, String titulo, int id) {
		try {
			Connection con= conexion(bd);
			String update= "UPDATE PELICULAS SET TITULO = ? WHERE ID = ?";
			
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, titulo);
			ps.setInt(2, id);
			
			int registroModificado = ps.executeUpdate(); //como es una actualizacion, se usa executeUpdate
			
			if (registroModificado > 0) { //si tiene un resultado positivo
				System.out.println("Actualización realizada correctamente"); //avisamos que todo ha ido bien
			}
			else {
				System.out.println("Ha fallado la actualización"); //sino, avisamos que ha ido mal
			}
			
		}
		catch(SQLException sql){
			sql.printStackTrace();			
		}
	}

	public static void eliminarPeliculas_PreparedStatement(String bd, int id) throws SQLException {
		Connection con = conexion(bd);
		
		String delete = "DELETE FROM PELICULAS WHERE ID = ?";
		
		PreparedStatement ps = con.prepareStatement(delete);
		ps.setInt(1,id);
		
		int RegistroEliminado = ps.executeUpdate(); //si devuelve 1 es que se ha eliminado. Si devuelve 0 es que ha fallado
		
		if (RegistroEliminado > 0) {
			System.out.println("Eliminación realizada correctamente"); //avisamos que todo ha ido bien
		}
		else {
			System.out.println("Ha fallado la eliminación"); //sino, avisamos que ha ido mal
		}
	}

}

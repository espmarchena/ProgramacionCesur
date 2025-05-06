package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC_Ejemplo5 {

	public static void main(String[] args) {
		
		try {

//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");

//			String sourceURL = "jdbc:mysql://localhost/videoclub";
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub";

			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456");

			Statement stmt = dbcon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			//estamos indicando que el Resulset se pueda recorrer hacia alante y hacia atras, y que sea actualizable (cursores dinamicos)
			
			ResultSet rst = stmt.executeQuery("select * from peliculas");
			// ...

			// Actualizar una fila:
			rst.absolute(4); // mueve el cursor a la fila 4 desde el principio
			System.out.println("Nº de la fila en la que está el cursor: " + rst.getRow()); // mostramos el numero de la fila
			rst.updateString("GENERO", "Prueba"); // cambio vslor del genero de la fila
			rst.updateRow(); // actualiza la base de datos
			System.out.println("Nº de la fila en la que está el cursor: " + rst.getRow()); // mostramos el nº de la fila en la que estamos

			// Crear una fila nueva:
			rst.moveToInsertRow(); // mueve el cursor a la fila de inserción
			rst.updateInt("ID", 22); // damos valores a las columnas
			rst.updateString("TITULO", "Prueba");
			rst.updateString("GENERO", "Teatro");
			rst.updateInt("ANIO", 2022);
			rst.updateFloat("PRECIO", 2.88f);
			rst.updateFloat("PRECIOALQUILER", 12.63f);
			rst.insertRow(); // insertamos la fila en la base de datos
			rst.moveToCurrentRow(); // volvemos a la fila que estábamos
			System.out.println("Nº de la fila en la que está el cursor: " + rst.getRow()); // mostramos el n� de la fila en la que estamos
			// ...
			rst.absolute(3); // mueve el cursor a la fila 3 desde el principio
			System.out.println("Nº de la fila en la que está el cursor: " + rst.getRow()); // mostramos el n� de la fila en la que estamos
			rst.deleteRow(); // la borramos

			rst.close();
			stmt.close();
			dbcon.close();
			
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			
		} catch (SQLException sqle) {
			System.out.println("Error de SQL " + sqle);
		}
	}

}

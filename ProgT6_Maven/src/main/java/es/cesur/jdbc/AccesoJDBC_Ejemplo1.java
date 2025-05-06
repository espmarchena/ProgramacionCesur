package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoJDBC_Ejemplo1 {

	public static void main(String[] args) {

		try {
			// Class.forName("com.mysql.cj.jdbc.Driver"); ESTE ES PARA MYSQL
			//Asegura o comprueba si tenemos el archivo .jar (la dependencia externa) que hemos creado al escribir en el pom.xml
			//No es una linea de codigo imprescindible
			Class.forName("org.mariadb.jdbc.Driver"); 
			
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub"; //URL para conectarnos a la bbddd. Hay que poner el nombre de la bbdd (en este caso es videoclub)
			//Ruta donde se encuentr el servidor: localhost por defecto. Por lo que no es necesario indicarlo si no queremos
			
			//Pedimos conexión con la base de datos a la clase DriverManager.
			//Connection nos permitirá conectarnos a la bbdd. Es una interfaz
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456"); //DriverManager es una clase que permite manejar los drivers en mi programa (tiene el metodo getConnection)
			
			//Con el objeto Connection creamos el objeto Statement
			//Statement nos permitirá ejecutar las instrucciones sql en la bbdd. Es una interfaz
			Statement sentencia = dbcon.createStatement(); //este metodo te devuelve un Statement que será el que necesitamos para ejecutar las instrucciones sql
 
			//variable donde tenemos guardada la instrucción SQL que queremos ejecutar y enviar a la base de datos.
			String ins = "INSERT INTO PELICULAS (TITULO,GENERO,ANIO,PRECIO,PRECIOALQUILER) VALUES ('NUEVA PELI', 'ACCIÓN', 2010, 5, 15)";
 
			/* Ejecutamos la instrucción SQL. En este caso estamos añadiendo un nuevo registro */
			sentencia.executeUpdate(ins); //executeUpdate() para realizar actualizaciones dml (insert, delete, update) o ddl (create table, alter table, drop table). Le paso la instruccion
 
			ResultSet resultado = sentencia.executeQuery("select * from peliculas"); // metodo executeQuery() ejecuta la instruccion sql que tiene entre parentesis. Siempre va a ser un Select
																					// ResultSet es una interfaz de la api que representa el conjunto de resultados de una consulta a una base de datos. Permite acceder y manipular los datos devueltos por consultas SQL (select).
			// Mostrar los datos
			while (resultado.next()) { //next() mueve el cursor a la siguiente fila. Devuelve un booleano: Si hay más filas, avanza y procesa la siguiente (true) sino sale del bucle ResultSet (false)
				System.out.println(resultado.getInt("ID") + " "
						+ resultado.getString("TITULO") + " "
						+ resultado.getString("GENERO") + " "
						+ resultado.getInt("ANIO") + " "
						+ resultado.getFloat("PRECIO") + " "
						+ resultado.getFloat("PRECIOALQUILER"));
			}
			//cerramos los recursos en orden inverso
			resultado.close();
			sentencia.close();
			dbcon.close();
			
			
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			System.out.println(cnf.getMessage());
			
		} 
		catch (SQLException sqle) {
			System.out.println("Error de SQL" + sqle);
			System.out.println(sqle.getMessage());
		}
	}
}

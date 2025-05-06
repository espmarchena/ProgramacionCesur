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
			Class.forName("org.mariadb.jdbc.Driver"); 
			
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub"; //localhost por defecto. Por lo que no es necesario indicarlo si no queremos
			
			//Pedimos conexión con la base de datos a la clase DriverManager.
			//Connection nos permitirá conectarnos a la bbdd
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456"); //DriverManager es una clase que permite manejar los drivers en mi programa
			
			//Con el objeto Connection creamos el objeto Statement
			//Statement nos permitirá ejecutar las instrucciones sql en la bbdd
			Statement sentencia = dbcon.createStatement(); //este metodo te devuelve un Statement que será el que necesitamos para ejecutar las instrucciones sql
 
			//Instrucción SQL que queremos ejecutar y enviar a la base de datos.
			String ins = "INSERT INTO PELICULAS (TITULO,GENERO,ANIO,PRECIO,PRECIOALQUILER) VALUES ('NUEVA PELI', 'ACCIÓN', 2010, 5, 15)";
 
			/* Ejecutamos la instrucción SQL */
			sentencia.executeUpdate(ins); //executeUpdate() para realizar actualizaciones dml (insert, delete, update) o ddl (create table, alter table, drop table)
 
			ResultSet resultado = sentencia.executeQuery("select * from peliculas"); // metodo executeQuery() ejecuta la instruccion que tiene entre parentesis
			
			// Mostrar los datos
			while (resultado.next()) {
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

package es.cesur.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class AccesoJDBC_Ejemplo6 {
 
	public static void main(String[] args) {
		try {
 
//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
 
//			String sourceURL = "jdbc:mysql://localhost/videoclub";
			String sourceURL = "jdbc:mariadb://localhost:3306/videoclub";
 
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "123456");
 
			Statement stm = dbcon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //parametros para que el resulset sea dinamico. Para que recorra hacia delante y hacia detras (sensitive significa que si otra persona modifica, se guarda el cambio) y que solo sea de lectura
			ResultSet rs = stm.executeQuery("SELECT TITULO, PRECIO FROM peliculas");
 
			/*
			 * Comprobamos si el cursor del ResultSet está después de la ultima fila y si no
			 * lo esta, lo movemos a esa posion para recorrer el ResultSet al reves
			 */
			if (rs.isAfterLast() == false) { //comprobamos si el cursor del ResultSet esta despues de la ultima fila
				rs.afterLast(); //si no lo esta, lo coloco detras de la ultima linea, por fuera
			}
			// Recorremos el ResultSet de abajo hacia arriba
			while (rs.previous()) { //para que recorra desde el ultimo registro hasta el primero. Es lo contrario del next()
				String name = rs.getString("TITULO");
				float price = rs.getFloat("PRECIO");
				System.out.println(name + " = " + price + "€"); //y me lo acaba mostrando del reves
			}
 
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver erróneo " + cnf);
			
		} catch (SQLException sqle) {
			System.out.println("Error de SQL " + sqle);
		}
 
	}
 
}
package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Ej7_LeerEmpleado {
 
//	String driver = "com.mysql.cj.jdbc.Driver";
	static String driver = "org.mariadb.jdbc.Driver";
 
//	String url = "jdbc:mysql://localhost:3306/gestion";
	static String url = "jdbc:mariadb://localhost:3306/gestion2";
 
	static String login = "root";
	static String password = "123456";
 
	String sentencia = "SELECT * FROM departamentos";
 
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
 
	public static void main(String[] args) {
		try {
			Class.forName(driver);
 
			con = DriverManager.getConnection(url, login, password);
 
			st = con.createStatement();
 
			String query = "Select * from empleados";
 
			rs = st.executeQuery(query);
 
			System.out.println("------LISTADO DE EMPLEADOS -----");
			System.out.println("ID\tNOMBRE y APELLIDOS\tCARGO\t\t\tSALARIO\tidDepartamentoFK");
 
			while (rs.next()) {
				int id = rs.getInt("idEmpleado");
				String nombre = rs.getString("NOMBREEMPLEADO");
				String apellidos = rs.getString("apellidosEmpleado");
				String cargo = rs.getString("cargoEmpleado");
				double salario = rs.getDouble("salarioEmpleado");
				int idFK = rs.getInt("idDepartamentoFK");
 
				System.out.println(
						id + "\t" + nombre + "\t" + apellidos + "\t\t" + cargo + "\t\t" + salario + "\t\t" + idFK);
			}
 
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

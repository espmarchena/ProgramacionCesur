package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadosMetadatos_Ej8 {
// String driver = "com.mysql.cj.jdbc.Driver"; 
	String driver = "org.mariadb.jdbc.Driver";

// String url = "jdbc:mysql://localhost:3306/empresa"; 
	String url = "jdbc:mariadb://localhost:3306/empresa2";

	String login = "root";
	String password = "123456";

	String sentencia = "SELECT * FROM empleados"; //instruccion sql, en este caso de tipo select (consulta)

	Connection connection = null; //conectarnos a la bbdd
	Statement statement = null; //ejecutar las instrucciones sql en la bbdd
	ResultSet rs = null; //para ver o consultar lo que tenemos en la bbdd

	public EmpleadosMetadatos_Ej8() { //constructor vacio

		try {
			// Cargamos el Driver
			Class.forName(driver);

			// Establecemos la conexión con la base de datos
			connection = DriverManager.getConnection(url, login, password);
			statement = connection.createStatement(); //cuando está vacio significa que por defecto se le estan pasando dos parametros de la interfaz de ResultSet. Sería como poner createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			/*ResultSet.TYPE_FORWARD_ONLY -> El cursor del ResultSet solo puede moverse hacia adelante
			 *ResultSet.CONCUR_READ_ONLY -> El ResultSet es de solo lectura*/

			// Accedemos a los metadatos de la base de datos
			DatabaseMetaData dmd = connection.getMetaData(); //creo el objeto de la interfaz DatabaseMetaData que me facilita los siguientes metodos:
			System.out.println("Base de Datos:" + dmd.getDatabaseProductName());// Da el nombre del producto de la base de datos
			System.out.println("Versión:" + dmd.getDatabaseProductVersion());// Da la versión del producto de la base de datos
			System.out.println("Usuario:" + dmd.getUserName());// Da el nombre del usuario conectado a la BD
			System.out.println("Driver:" + dmd.getDriverName());// Da el nombre del driver

			// Trabajamos con una Tabla
			System.out.println("\nTRABAJAMOS CON LA TABLA EMPLEADOS");
			rs = statement.executeQuery(sentencia); //Metodo que me va a permitir ejecutar la instruccion de tipo select

			ResultSetMetaData rsm = rs.getMetaData(); //contiene la informacion de los objetos de una tabla

			int n = rsm.getColumnCount();// Da el numero de columnas de una tabla

			System.out.println("Número de columnas: " + n);
			// Recorremos las columnas de la tabla empleados para acceder a sus metadatos
			for (int i = 1; i <= n; i++) {
				System.out.println("Los metadatos de la columna " + i + " de la tabla empleados son: ");
				System.out.println("Campo: " + rsm.getColumnName(i));
				System.out.println("Tipo: " + rsm.getColumnTypeName(i));
				System.out.println("Tamaño: " + rsm.getColumnDisplaySize(i));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}

	}

	public static void main(String[] args) {
		new EmpleadosMetadatos_Ej8();
	}
}

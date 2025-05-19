package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej4_CrearTabla {

    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String URL = "jdbc:mariadb://localhost:3306/comercial2";
    static final String USUARIO = "root";
    static final String CLAVE = "123456";

    public static void main(String[] args) {
        Connection conexion = null;
        Statement statement = null;
        
        try {
            //Registrar el driver
            Class.forName(DRIVER);
            
            //Establecer conexión con la base de datos
            System.out.println("Conectando a la base de datos 'comercial2'...");
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            
            //Crear la tabla clientes
            System.out.println("Creando tabla 'clientes'...");
            statement = conexion.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "nombre VARCHAR(50) NOT NULL, " +
                         "apellidos VARCHAR(100) NOT NULL, " +
                         "dni VARCHAR(20) NOT NULL UNIQUE, " +
                         "telefono VARCHAR(20)" +
                         ")";
            
            statement.executeUpdate(sql); //ejecuto la instruccion para crear la tabla
            
            System.out.println("Tabla 'clientes' creada exitosamente!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.err.println("Error SQL al crear la tabla");
            e.printStackTrace();
            
        } finally {
			// Cerrar statement y la conexión a la base de datos
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException se2) {
			}

			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
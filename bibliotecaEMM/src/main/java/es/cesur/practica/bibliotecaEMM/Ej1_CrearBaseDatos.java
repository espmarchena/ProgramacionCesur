package es.cesur.practica.bibliotecaEMM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Ej1_CrearBaseDatos {
	
	static String driver = "org.mariadb.jdbc.Driver";
    static String url = "jdbc:mariadb://localhost:3306/";
    static String user = "root";
    static String password = "123456";
	
    public static void main(String[] args) {
   
        try {
			// Registro el Driver
			Class.forName(driver);
			
			// Abir la conexión
			System.out.println("Conectando a la Base de Datos...");
        	Connection con = DriverManager.getConnection(url, user, password);

			// Crear la base de datos
			System.out.println("Creando la Base de Datos...");
			Statement stmt = con.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS bibliotecaEMM"; // Instrucción SQL para crear la bd
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada correctamente...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

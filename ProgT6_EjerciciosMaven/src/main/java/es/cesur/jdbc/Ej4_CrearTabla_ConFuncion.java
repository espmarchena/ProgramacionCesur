package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej4_CrearTabla_ConFuncion {

    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String USUARIO = "root";
    static final String CLAVE = "123456";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce el nombre de la base de datos: ");
        String nombreBD = scanner.nextLine();
        
        System.out.print("Introduce el nombre de la tabla a crear: ");
        String nombreTabla = scanner.nextLine();
        
        scanner.close();

        // Llamada a la función
        crearTabla(nombreBD, nombreTabla);
    }

 
    public static void crearTabla(String nombreBD, String nombreTabla) {
        String url = "jdbc:mariadb://localhost:3306/" + nombreBD;
        
        try (Connection conexion = DriverManager.getConnection(url, USUARIO, CLAVE);
             Statement statement = conexion.createStatement()) {
            
            // Registrar el driver 
            Class.forName(DRIVER);
            
            System.out.println("Creando tabla '" + nombreTabla + "' en la base de datos '" + nombreBD + "'...");
            
            String sql = "CREATE TABLE IF NOT EXISTS " + nombreTabla + " (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "nombre VARCHAR(50) NOT NULL, " +
                         "apellidos VARCHAR(100) NOT NULL, " +
                         "dni VARCHAR(20) NOT NULL UNIQUE, " +
                         "telefono VARCHAR(20)" +
                         ")";
            
            statement.executeUpdate(sql); //ejecuta instruccion sql
            
            System.out.println("Tabla '" + nombreTabla + "' creada exitosamente en '" + nombreBD + "'!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.err.println("Error SQL al crear la tabla");
            e.printStackTrace();
        }
    }
}
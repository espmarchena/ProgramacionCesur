package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej3_CrearBD_NombrePorTeclado {

    static String driver = "org.mariadb.jdbc.Driver";
    static String url = "jdbc:mariadb://localhost:3306/";
    static String usuario = "root";
    static String clave = "123456";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de la base de datos a crear: ");
        String nombreBD = scanner.nextLine();
        scanner.close();

        Connection conexion = null;
        Statement statement = null;
        try {
            // Registro el Driver
            Class.forName(driver);

            // Abrir la conexión
            System.out.println("Conectando al servidor de bases de datos...");
            conexion = DriverManager.getConnection(url, usuario, clave);

            // Crear la base de datos
            System.out.println("Creando la Base de Datos '" + nombreBD + "'...");
            statement = conexion.createStatement();

            String sql = "CREATE DATABASE " + nombreBD + " CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci";
            statement.executeUpdate(sql);
            System.out.println("Base de Datos '" + nombreBD + "' creada correctamente...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Cerrar statement y la conexión a la base de datos
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {}

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
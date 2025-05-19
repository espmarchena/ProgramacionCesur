package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej5_InsertarDatos_ConFuncion {

    static final String DRIVER = "org.mariadb.jdbc.Driver";
    static final String URL = "jdbc:mariadb://localhost:3306/comercial2";
    static final String USUARIO = "root";
    static final String CLAVE = "123456";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- INGRESO DE NUEVO CLIENTE ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        scanner.close();

        // Llamada a la función de inserción
        boolean exito = insertarCliente(nombre, apellidos, dni, telefono);
        
        if (exito) {
            System.out.println("Cliente insertado correctamente!");
            
        } else {
            System.out.println("No se pudo insertar el cliente");
        }
    }

    /**
     * Función para insertar un nuevo cliente en la base de datos
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param dni DNI del cliente
     * @param telefono Teléfono del cliente (puede ser null o vacío)
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public static boolean insertarCliente(String nombre, String apellidos, String dni, String telefono) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement ps = conexion.prepareStatement(
                 "INSERT INTO clientes (nombre, apellidos, dni, telefono) VALUES (?, ?, ?, ?)")) {
            
            // Registrar el driver (no necesario en versiones recientes de JDBC)
            Class.forName(DRIVER);
            
            // Establecer parámetros
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, dni);
            
            if (telefono == null || telefono.isEmpty()) {
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, telefono);
            }
            
            // Ejecutar inserción
            int filasAfectadas = ps.executeUpdate();
            
            return filasAfectadas > 0;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.err.println("Error SQL al insertar cliente");
            e.printStackTrace();
            return false;
        }
    }
}
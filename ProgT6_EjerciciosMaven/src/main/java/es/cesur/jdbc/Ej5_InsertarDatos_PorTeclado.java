package es.cesur.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej5_InsertarDatos_PorTeclado {

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

	        Connection conexion = null;
	        PreparedStatement ps = null;
	        
	        try {
	            //Registrar el driver
	            Class.forName(DRIVER);
	            
	            //Establecer conexión
	            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
	            
	            //Preparar la sentencia SQL
	            String sql = "INSERT INTO clientes (nombre, apellidos, dni, telefono) VALUES (?, ?, ?, ?)";
	            ps = conexion.prepareStatement(sql);
	            
	            //Establecer parámetros
	            ps.setString(1, nombre);
	            ps.setString(2, apellidos);
	            ps.setString(3, dni);
	            
	            if (telefono.isEmpty()) {
	                ps.setNull(4, java.sql.Types.VARCHAR);
	                
	            } else {
	                ps.setString(4, telefono);
	            }
	            
	            //Ejecutar inserción
	            int filasAfectadas = ps.executeUpdate();
	            
	            if (filasAfectadas > 0) {
	                System.out.println("Cliente insertado correctamente!");
	                
	            } else {
	                System.out.println("No se pudo insertar el cliente");
	            }
	            
	        } catch (ClassNotFoundException e) {
	            System.err.println("Error: No se encontró el driver JDBC");
	            e.printStackTrace();
	            
	        } catch (SQLException e) {
	            System.err.println("Error SQL al insertar cliente");
	            e.printStackTrace();
	            
	        } finally {
	            //Cerrar recursos
	            try {
	                if (ps != null) ps.close();
	                
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar PreparedStatement");
	            }
	            
	            try {
	                if (conexion != null) conexion.close();
	                
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar Connection");
	            }
	        }
	    }
	}

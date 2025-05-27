package es.cesur.practica.bibliotecaEMM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Ej5_ActualizarLibro {
	
    static final String driver = "org.mariadb.jdbc.Driver";
    static final String url = "jdbc:mariadb://localhost:3306/bibliotecaEMM";
    static final String user = "root";
    static final String password = "123456";
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            //Registrar el driver
            Class.forName(driver);
            
            //Establecer conexión con la base de datos
            System.out.println("Conectando a la base de datos 'bibliotecaEMM'...");
        	Connection con = DriverManager.getConnection(url, user, password);
        	
            System.out.print("Introduce el ID del libro que deseas actualizar: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nuevo título: ");
            String nuevoTitulo = sc.nextLine();

            String sql = "UPDATE librosEMM SET titulo = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevoTitulo);
            pstmt.setInt(2, id);

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Libro actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún libro con ese ID.");
            }
            
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


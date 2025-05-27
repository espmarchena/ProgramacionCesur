package es.cesur.practica.bibliotecaEMM;

import java.sql.*;
import java.util.Scanner;

public class Ej3_InsertarLibro {
	
    static final String driver = "org.mariadb.jdbc.Driver";
    static final String url = "jdbc:mariadb://localhost:3306/bibliotecaEMM";
    static final String user = "root";
    static final String password = "123456";
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        try {
            //Registrar el driver
            Class.forName(driver);
            
            // Establecer conexión con la base de datos
            System.out.println("Conectando a la base de datos 'bibliotecaEMM'...");
        	Connection con = DriverManager.getConnection(url, user, password);
        	
            // Preparar la sentencia SQL
            String sql = "INSERT INTO librosEMM (titulo, editorial, numero_paginas, anio_publicacion) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);


            System.out.println("--- INGRESO DE NUEVO LIBRO ---");
            
            for (int i = 1; i <= 5; i++) {
                System.out.println("\n--- Libro " + i + " ---");

                System.out.print("Título: ");
                String titulo = sc.nextLine();

                System.out.print("Editorial: ");
                String editorial = sc.nextLine();

                System.out.print("Número de páginas: ");
                int numero_paginas = Integer.parseInt(sc.nextLine()); // parsear para evitar problemas con nextInt()

                System.out.print("Año de publicación: ");
                int anio_publicacion = Integer.parseInt(sc.nextLine()); // parsear para evitar problemas con nextInt()

                // Establecer parámetros para la sentencia
                pstmt.setString(1, titulo);
                pstmt.setString(2, editorial);
                pstmt.setInt(3, numero_paginas);
                pstmt.setInt(4, anio_publicacion);
            
            // Ejecutar inserción
                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Libro insertado correctamente!");
                } else {
                    System.out.println("No se pudo insertar el libro");
                }
            }

            pstmt.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.err.println("Error SQL al insertar el libro");
            e.printStackTrace();
        }
    }
}


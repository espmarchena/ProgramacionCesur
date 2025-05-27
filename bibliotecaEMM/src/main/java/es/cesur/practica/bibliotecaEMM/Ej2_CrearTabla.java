package es.cesur.practica.bibliotecaEMM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Ej2_CrearTabla {

    static final String driver = "org.mariadb.jdbc.Driver";
    static final String url = "jdbc:mariadb://localhost:3306/bibliotecaEMM";
    static final String user = "root";
    static final String password = "123456";
	
	    public static void main(String[] args) {

	        try {
	            //Registrar el driver
	            Class.forName(driver);
	            
	            //Establecer conexión con la base de datos
	            System.out.println("Conectando a la base de datos 'bibliotecaEMM'...");
	        	Connection con = DriverManager.getConnection(url, user, password);
	        	
	            //Crear la tabla librosEMM
	            System.out.println("Creando tabla 'librosEMM'...");
	            Statement stmt = con.createStatement();

	            String sql = "CREATE TABLE IF NOT EXISTS librosEMM (" +
	                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
	                        "titulo VARCHAR(100), " +
	                        "editorial VARCHAR(100), " +
	                        "numero_paginas INT, " +
	                        "anio_publicacion INT" +
	                        ")";

	            stmt.executeUpdate(sql); // Ejecuto la instruccion para crear la tabla
	            System.out.println("Tabla creada correctamente.");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


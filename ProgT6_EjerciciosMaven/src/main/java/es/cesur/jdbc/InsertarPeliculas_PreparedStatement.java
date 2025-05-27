package es.cesur.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

public class InsertarPeliculas_PreparedStatement {
	
	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner (System.in);
		System.out.println("Dime el título de la película:");
		String titulo = sc.nextLine();
		
		System.out.println("Dime el género de la película:");
		String genero = sc.nextLine();
		
		System.out.println("Dime el año de la película:");
		int anio = sc.nextInt();
		sc.nextLine(); //limpio el scanner
		
		System.out.println("Dime el precio de la película:");
		double precio = sc.nextDouble();
		sc.nextLine(); //limpio el scanner
		
		System.out.println("Dime el precio de alquiler de la película:");
		double precioAlquiler = sc.nextDouble();
		sc.nextLine(); //limpio el scanner
		
		sc.close();
		
		Metodos.insertarPelicula_PreparedStatement(titulo, genero, anio, precio, precioAlquiler, "videoclub");
			
	}

}

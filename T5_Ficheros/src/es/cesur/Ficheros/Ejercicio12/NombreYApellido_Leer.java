package es.cesur.Ficheros.Ejercicio12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NombreYApellido_Leer {

	/*
	 * 12. Nombre y apellidos – Realiza un programa Java que escriba en un fichero
	 * de texto nombres.txt un Array de String con el nombre y apellido de tres
	 * alumnos, separados por coma. Ejemplo: María José, Martínez. Completa la
	 * funcionalidad del programa, leyendo del fichero nombres.txt, los datos que
	 * hemos escrito y mostrándolos por consola.
	 */
		
	public static void main(String[] args) {
		// Leer del archivo y mostrar por consola
		System.out.println("Leyendo datos del archivo nombres.txt:");
		
		try { 
			FileReader fr = new FileReader("nombres.txt");
			BufferedReader br = new BufferedReader (fr);
				
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] palabra = linea.split(",");
					
				String nombre = palabra[0].trim();
				String apellido = palabra[1].trim();
					
				System.out.println("Nombre " + nombre + ", Apellido: " + apellido);
			}
		} catch (IOException e) {
				System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}
}

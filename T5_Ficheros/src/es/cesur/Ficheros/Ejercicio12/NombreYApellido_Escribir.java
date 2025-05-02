package es.cesur.Ficheros.Ejercicio12;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NombreYApellido_Escribir {

	/*
	 * 12. Nombre y apellidos – Realiza un programa Java que escriba en un fichero
	 * de texto nombres.txt un Array de String con el nombre y apellido de tres
	 * alumnos, separados por coma. Ejemplo: María José, Martínez. Completa la
	 * funcionalidad del programa, leyendo del fichero nombres.txt, los datos que
	 * hemos escrito y mostrándolos por consola.
	 */
	public static void main(String[] args) {
		// Array con nombres y apellidos de tres alumnos
		String[] alumnos = {
				"María José, Martínez",
				"Juan Carlos, García",
				"Ana María, López"
		};

		// Escribir en el archivo
		try {
			FileWriter fw = new FileWriter("nombres.txt");
			BufferedWriter bw = new BufferedWriter (fw);
				
			for (String alumno : alumnos) {
				bw.write(alumno);
				bw.newLine(); // Escribe un salto de línea después de cada alumno
			}
				
			System.out.println("Datos escritos correctamente en nombres.txt");
				
		}
		catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}
}


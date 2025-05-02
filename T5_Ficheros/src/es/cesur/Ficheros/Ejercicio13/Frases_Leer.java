package es.cesur.Ficheros.Ejercicio13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Frases_Leer {

	public static void main(String[] args) {

		// Leer del archivo, contar palabras y mostrar resultados
		System.out.println("\nLeyendo frases del archivo y contando palabras:");

		try {
			FileReader fr = new FileReader("frases.txt"); // lee caracteres
			BufferedReader br = new BufferedReader(fr);

			String linea;
			int numeroFrase = 1;

			while ((linea = br.readLine()) != null) { //mientras no esté vacío, lee la línea de texto del flujo de entrada (br) y la asigna a la variable linea
				
				// Contar palabras
				int contadorPalabras = 0;
				
				if (linea != null && linea.length() > 0) {
					
					String[] palabras = linea.trim().split(" "); // divide una cadena en un array de palabras,
					//eliminando primero los espacios en blanco al principio y al final de la cadena .TRIM()
					//y luego usando el espacio en blanco como separador para crear el array de palabras .SPLIT(" ")
					
					contadorPalabras = palabras.length;

					// Ajuste para múltiples espacios consecutivos
					for (String palabra : palabras) {
						if (palabra.length() == 0) {
							contadorPalabras--;
						}
					}
				}

				System.out.println("Frase " + numeroFrase + ": \"" + linea + "\"");
				System.out.println("Número de palabras: " + contadorPalabras + "\n");
				numeroFrase++;

			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}
}

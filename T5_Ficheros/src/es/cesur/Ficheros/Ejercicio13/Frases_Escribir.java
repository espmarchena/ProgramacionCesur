package es.cesur.Ficheros.Ejercicio13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Frases_Escribir {

    public static void main(String[] args) {
        // Array con frases de ejemplo
        String[] frases = {
            "Hola, ¿qué tal?",
            "Ya huele a Feriaaaaa.",
            "Java es un lenguaje de programación orientado a objetos.",
            "La práctica constante es esencial."
        };

        // Escribir en el archivo
		try {
			FileWriter fw = new FileWriter("frases.txt"); //escribe caracteres
			BufferedWriter bw = new BufferedWriter(fw);
			
            for (String frase : frases) {
                bw.write(frase); // escribe la cadena frase en el flujo de escritura que representa bw
                bw.newLine(); // Escribe un salto de línea después de cada frase
            }
            System.out.println("Frases escritas correctamente en frases.txt");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

package ejercicio1;

import java.io.IOException;

public class Principal {
	
    public static void main(String[] args) {
    	
        String[] frases = {
            "El sol brilla intensamente hoy.",
            "Me gusta aprender cosas nuevas cada día.",
            "La programación es una habilidad muy útil.",
            "A veces, los errores enseñan más que los aciertos.",
            "Leer libros expande la mente."
        };
        
        String nombreFichero = "esperanzaMarchena.txt";
        
        try {
            // Escribir el fichero
            ejercicio1.escribirFichero(nombreFichero, frases);
            
            // Leer el fichero
            String contenido = ejercicio1.leerFichero(nombreFichero);
            System.out.println("Contenido del fichero:");
            System.out.println(contenido);
            
            // Contar palabras
            int totalPalabras = ejercicio1.contarPalabras(contenido);
            System.out.println("Total de palabras: " + totalPalabras);
            
        } catch (IOException e) {
            System.out.println("Error al manejar el fichero: " + e.getMessage());
        }
    }
}
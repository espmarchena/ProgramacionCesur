package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio1 {
    
    public static void escribirFichero(String nombreFichero, String[] contenido) {
    	
        try {
        	FileWriter fw = new FileWriter(nombreFichero);
        	BufferedWriter bw = new BufferedWriter(fw);
        	
            for (String linea : contenido) {
                bw.write(linea);
                bw.newLine();
            }
            
            bw.close();
            fw.close();
        }
        catch (IOException e){
        	System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
    
    public static String leerFichero(String nombreFichero) throws IOException {
    	
        StringBuilder contenido = new StringBuilder();
        try{
        	FileReader fr = new FileReader(nombreFichero);
        	BufferedReader br = new BufferedReader(fr);
        
            String linea;
            
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            
            br.close();
            fr.close();    
        }
        catch (IOException e){
        	System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        return contenido.toString();
        
    }
    
    public static int contarPalabras(String texto) {
    	
        if (texto == null || texto.isEmpty()) {
            return 0;
        }
        String[] palabras = texto.split("\\s+");
        
        return palabras.length;
    }
}


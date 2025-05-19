package ejercicio2;

import java.io.IOException;

public class Principal {
	
    public static void main(String[] args) throws IOException {
        // Crear array de 2 contactos 
        Listin[] contactos = {
            new Listin("Juan Pérez", "Calle Mayor 1", "600111222"),
            new Listin("Pilar López", "Avenida Central 5", "600333444")
        };
        
        String nombreFichero = "esperanzaMarchenaListin.bin";
        
        // Guardar los contactos en fichero binario
		manejarListin.guardarListin(contactos, nombreFichero);
		
		// Leer los contactos del fichero binario
		Listin[] contactosLeidos = manejarListin.cargarListin(nombreFichero);
		
		// Mostrar los contactos por consola
		System.out.println("Contactos guardados:");
		
		for (Listin contacto : contactosLeidos) {
		    System.out.println(contacto);
		}
        
    }
}

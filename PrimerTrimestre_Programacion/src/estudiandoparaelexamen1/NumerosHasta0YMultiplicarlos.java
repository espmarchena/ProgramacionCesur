package estudiandoparaelexamen1;

import java.util.Scanner;

public class NumerosHasta0YMultiplicarlos {

	public static void main(String[] args) {
        // Pedir números hasta un 0 y mostrar una vez que se sale del bucle, el resultado de multiplicar todos los números introducidos
        
        Scanner sc = new Scanner(System.in);
        int numero = 1;
        int producto = 1; // Inicializamos en 1 para que no afecte la multiplicación
        
        while (numero != 0) {
            System.out.println("Número:");
            numero = sc.nextInt();
            
            // Si el número no es 0, multiplicamos
            if (numero != 0) {
                producto *= numero;
            }
        }
        
        System.out.println("El producto de todos los números introducidos es: " + producto);
        
        
        sc.close();
    }
}
		
		
		

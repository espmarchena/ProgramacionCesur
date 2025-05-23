package deberesenclase;

import java.util.Scanner;

public class PedirLongitudTablaFuncionFibonacci {
	static Scanner sc;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		//pedir numero que sera la longitud de mi tabla
		//el algoritmo de fibonacci llevarlo a una funcion que devuelve la tabla
		
		int longitud= pedirLongitud("Dame longitud de la tabla:"); // guardo en la variable lo que hace la funcion
		int tabla[] = fibonacci(longitud); //guardar en una tabla lo que hace(o devuelve) la funcion fibonacci
		
		pintarTabla(tabla);
	}

	public static int pedirLongitud(String mensaje) { //FUNCION PARA PEDIR EL NUMERO
		System.out.println(mensaje);
		int longitud = sc.nextInt();
		return longitud;
	}
	
	public static int[] fibonacci (int longitud) { //FUNCION PARA HACER ALGORITMO FIBONACCI
		int []fibonacci = new int[longitud];
		
		fibonacci[0] = 0;
		fibonacci[1] = 1;
		for (int i=2; i<fibonacci.length;i++) {
			fibonacci[i]= fibonacci[i-1] + fibonacci[i-2];
		}
		
		return fibonacci;
	}
	
	public static void pintarTabla(int tabla[]) {
		for( int i=0; i<tabla.length;i++) {
			System.out.println(i + "->" + tabla[i]);
		}	
	}
	
	
	
	
	
}
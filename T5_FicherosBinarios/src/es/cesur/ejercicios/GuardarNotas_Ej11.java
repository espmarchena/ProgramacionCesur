package es.cesur.ejercicios;

import java.util.Scanner;

public class GuardarNotas_Ej11 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Notas_Ej11[] notas = new Notas_Ej11[3];
		
		Metodos_Ej11y12.crearEscribirFicheroBinario(notas, scanner); //metodo con el try/catch. Se pone el nombre de la clase delante pq es estatico y esta desarollado en otra clase
	}

	
}

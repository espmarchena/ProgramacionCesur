package es.cesur.ejercicios;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Notas_Ej11[] notas = new Notas_Ej11[3];
		Metodos_Ej11y12.crearEscribirFicheroBinario(notas, scanner); // GuardarNotas_Ej11
		
		Metodos_Ej11y12.leerFicheroBinario(); // clase LeerNota_Ej12

	}

}

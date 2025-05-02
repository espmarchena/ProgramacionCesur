package es.cesur.ejercicios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Metodos_Ej11y12 {

	//Clase GuardarNotas_Ej11
	public static void crearEscribirFicheroBinario(Notas_Ej11[] notas, Scanner sc) {

		System.out.println("Introduce los datos de 3 notas:");
		for (int i = 0; i < 3; i++) { //recorro array
			System.out.println("\nNota " + (i + 1) + ":"); //muestro la palabra nota, aumentando la posicion en cada iteracion
			
			System.out.print("Código: ");
			String codigo = sc.nextLine();
			
			System.out.print("Nombre: ");
			String nombre = sc.nextLine();
			
			System.out.print("Curso: ");
			String curso = sc.nextLine();
			
			System.out.print("Calificación: ");
			double calificacion = sc.nextDouble();
			
			sc.nextLine(); // para regular el escaner
			

			notas[i] = new Notas_Ej11(codigo, nombre, curso, calificacion); //relleno y guardo los datos del array en cada iteracion con los atributos de la clase Notas gracias al constructor, que son dados por el usuario
		}
		
		try { 
			
			FileOutputStream fos = new FileOutputStream("notas.bin"); //para escribir byte a byte el fichero binario
			ObjectOutputStream oos = new ObjectOutputStream(fos); //para escribir el objeto (el array) en el fichero
			oos.writeObject(notas); //metodo para escribir el objeto (la tabla notas en este caso)
			
			System.out.println("Datos de notas guardados correctamente en notas.bin");

			//cerramos los recursos abiertos, en orden inverso
			sc.close();
			oos.close();

		}
		catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}
		
	}
	
	
	
	//Clase LeerNota_Ej12
	public static void leerFicheroBinario() {
		try {
			FileInputStream fis = new FileInputStream("notas.bin"); // para leer el fichero binario con el nombre que de
																	// el usuario por pantalla (notas.bin)
			ObjectInputStream ois = new ObjectInputStream(fis); // para crear el objeto
			Notas_Ej11[] notas = (Notas_Ej11[]) ois.readObject(); // para leer el objeto. Lo he casteado para que
																	// compile

			System.out.println("Datos de las notas:");
			for (int i = 0; i < notas.length; i++) {
				System.out.println("\nNota " + (i + 1) + ":"); // muestro la palabra nota, aumentando la posicion en
																// cada iteracion
				System.out.println("Código: " + notas[i].getCodigo());
				System.out.println("Nombre: " + notas[i].getNombre());
				System.out.println("Curso: " + notas[i].getCurso());
				System.out.println("Calificación: " + notas[i].getCalificacion());
			}

			// cerramos los recursos abiertos, en orden inverso
			ois.close();
			fis.close();

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer el fichero: " + e.getMessage());
		}
	}
	
}

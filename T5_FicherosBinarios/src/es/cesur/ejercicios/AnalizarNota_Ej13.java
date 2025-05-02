package es.cesur.ejercicios;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class AnalizarNota_Ej13 {

	/*13. Analizar Nota - Realizar un programa en JAVA que lea el fichero binario  “notas.bin”
	 * y obtenga por pantalla la nota media, la mejor nota y la peor nota.*/
	
	public static void main(String[] args) {

		try {
			FileInputStream fis = new FileInputStream("notas.bin"); // para leer el fichero binario con el nombre del fichero notas.bin
			ObjectInputStream ois = new ObjectInputStream(fis); // para leer el objeto
			Notas_Ej11[] notas = (Notas_Ej11[]) ois.readObject(); // para leer el objeto. Lo he casteado para que compile

			double suma = 0;
			double mejorNota = notas[0].getCalificacion();
			double peorNota = notas[0].getCalificacion();

			for (int i = 0; i < notas.length; i++) {
				suma += notas[i].getCalificacion(); // sumo todas las notas del array notas con cada iteracion
                if (notas[i].getCalificacion() > mejorNota) { //comparo cada nota en cada iteracion
                    mejorNota = notas[i].getCalificacion(); //guardo la mejor nota
                }
                if (notas[i].getCalificacion() < peorNota) { //comparo cada nota en cada iteracion
                    peorNota = notas[i].getCalificacion(); //guardo la peor nota
                }
			}

			double media = suma / notas.length; //calculo la media dividiendo la suma de las calificaciones entre el nº de asignaturas

			System.out.println("Estadísticas de las notas:");
			System.out.println("Nota media: " + media);
			System.out.println("Mejor nota: " + mejorNota);
			System.out.println("Peor nota: " + peorNota);
			
			
			// cerramos los recursos abiertos, en orden inverso
			ois.close();
			fis.close();


		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer el fichero: " + e.getMessage());
		}
	}
}

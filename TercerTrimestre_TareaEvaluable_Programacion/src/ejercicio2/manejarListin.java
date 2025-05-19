package ejercicio2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class manejarListin {

	public static boolean guardarListin(Listin[] contactos, String nombreFichero) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(nombreFichero);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(contactos);
			return true;
			
		} catch (IOException e) {
			System.err.println("Error al guardar el fichero: " + e.getMessage());
			return false;
			
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar ObjectOutputStream: " + e.getMessage());
			}

			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar FileOutputStream: " + e.getMessage());
			}
		}
	}

	public static Listin[] cargarListin(String nombreFichero) {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(nombreFichero);
			ois = new ObjectInputStream(fis);
			return (Listin[]) ois.readObject();
			
		} catch (IOException e) {
			System.err.println("Error al leer el fichero: " + e.getMessage());
			return null;
			
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Clase no encontrada: " + e.getMessage());
			return null;
			
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar ObjectInputStream: " + e.getMessage());
			}

			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar FileInputStream: " + e.getMessage());
			}
		}
	}
}


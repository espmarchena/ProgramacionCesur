package ejercicio2;

public class Persona {

	// atributos protegidos para que las clases hijas puedan acceder a ellos (accesibles dentro de la misma clase, subclases y dentro del mismo paquete)
	protected String nombre;
	protected String direccion;

	// constructor vacío
	public Persona() {
		this.nombre = "";
		this.direccion = "";
	}

	// constructor con parámetros
	public Persona(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}

	// método conducir
	public void conducir() {
		System.out.println(nombre + " está conduciendo.");
	}

	// getters and setters (para controlar el acceso a atributos privados)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}

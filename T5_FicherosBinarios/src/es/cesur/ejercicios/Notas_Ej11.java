package es.cesur.ejercicios;

import java.io.Serializable;

public class Notas_Ej11 implements Serializable {
	
	//atributos
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String curso;
	private double calificacion;

	/*
	 * 11. Nota - Realizar un programa en JAVA que dada la clase Nota, con un
	 * código, un nombre, un curso y una calificación, lea por teclado una tabla de
	 * 3 elementos de la clase Nota. El programa guardará dicha tabla en un fichero
	 * binario llamado “notas.bin”.
	 */

	//constructor por parametros
	public  Notas_Ej11 (String codigo, String nombre, String curso, double calificacion) {
	        this.codigo = codigo;
	        this.nombre = nombre;
	        this.curso = curso;
	        this.calificacion = calificacion;
	}
	
	// getters y setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	
}


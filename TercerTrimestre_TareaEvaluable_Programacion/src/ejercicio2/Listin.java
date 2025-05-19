package ejercicio2;

import java.io.Serializable;

public class Listin implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String direccion;
    private String telefono;
    
    public Listin(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Dirección: " + direccion + ", Teléfono: " + telefono;
    }
}


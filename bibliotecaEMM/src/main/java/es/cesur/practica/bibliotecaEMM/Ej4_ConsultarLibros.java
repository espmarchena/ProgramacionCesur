package es.cesur.practica.bibliotecaEMM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ej4_ConsultarLibros {
	
    static final String driver = "org.mariadb.jdbc.Driver";
    static final String url = "jdbc:mariadb://localhost:3306/bibliotecaEMM";
    static final String user = "root";
    static final String password = "123456";
	
    public static void main(String[] args) {

        try {
            //Registrar el driver
            Class.forName(driver);
            
            //Establecer conexión con la base de datos
            System.out.println("Conectando a la base de datos 'bibliotecaEMM'...");
        	Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM librosEMM";
            PreparedStatement pstmt = con.prepareStatement(query); // nos permitirá ejecutar la instruccion/query en la bbdd
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.printf("ID: %d | Título: %s | Editorial: %s | Páginas: %d | Año: %d%n",
                        rs.getInt(1), //ID
                        rs.getString(2), // titulo
                        rs.getString(3), // editorial
                        rs.getInt(4), // numero de paginas
                        rs.getInt(5)); // año de publicacion
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

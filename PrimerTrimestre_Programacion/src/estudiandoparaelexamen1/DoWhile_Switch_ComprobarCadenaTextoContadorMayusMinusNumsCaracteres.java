package estudiandoparaelexamen1;

import java.util.Scanner;

public class DoWhile_Switch_ComprobarCadenaTextoContadorMayusMinusNumsCaracteres {

	public static void main(String[] args) {
		// 1 cuantas mayus 2 cuantas minus 3 cuantos numeros 4 cuaantos caracteres especiales
		
		Scanner sc = new Scanner (System.in);
		System.out.println("Cadena:");
		String s = sc.nextLine();
		
		String opcion;
	
		
		do {
			System.out.println("Opcion:");
			opcion = sc.nextLine();
			int contador= 0;
			
			switch(opcion){
			
				case "1":
					for (int i=0;i<s.length();i++){ //recorro el largo de la cadena
						char c = s.charAt(i); //cojo cada caracter de la cadena
						if (c>= 'A' && c<= 'Z') { //si está entre la A y la Z
							contador++; //le digo que los sume
						}
					}
					System.out.println(contador + " mayusculas"); // y que me los muestre
				break;
					
				case "2":
					for (int i=0;i<s.length();i++){ //recorro el largo de la cadena
						char c = s.charAt(i); //cojo cada caracter de la cadena
						if (c>= 'a' && c<= 'z') { //si está entre la a y la z
							contador++; //le digo que los sume
						}
					}
					System.out.println(contador + " minusculas"); // y que me los muestre
				break;
				
				case "3":
					for (int i=0;i<s.length();i++){ //recorro el largo de la cadena
						char c = s.charAt(i); //cojo cada caracter de la cadena
						if (c>= '0' && c<= '9') { //si está entre el 0 y el 9
							contador++; //le digo que los sume
						}
					}
					System.out.println(contador + " numeros"); // y que me los muestre
				break;
					
				case "4":
					for (int i=0;i<s.length();i++){ //recorro el largo de la cadena
						char c = s.charAt(i); //cojo cada caracter de la cadena
						if (c=='?' || c=='!' || c=='-' || c=='*') { //si contiene esos caracteres
							contador++; //le digo que los sume
						}
					}
					System.out.println(contador + " caracteres especiales"); // y que me los muestre
				break;
			
				case "5":
					System.out.println("Adiós!");
				break;
					
				default:
					System.out.println("Error en la opcion seleccionada");
			}	

		}
		while(!opcion.equals("5"));
		
		
		
		sc.close();
	}

}

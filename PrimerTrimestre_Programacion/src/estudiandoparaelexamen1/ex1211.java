package estudiandoparaelexamen1;

import java.util.Scanner;

public class ex1211 {

	public static void main(String[] args) {
		 // Lee un número por teclado y comprueba que este numero es mayor o igual que cero, si no lo es lo volverá a pedir (do while),
		//después muestra ese número por consola.
        
        int num;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Introduce un numero mayor que 0");
            num=sc.nextInt();
        }while(num>0);
 
        System.out.println(num);
    }
}

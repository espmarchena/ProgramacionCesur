package tarea1;

public class TestBanco {

	public static void main(String[] args) {

		/* Antonio y Beatriz se hacen clientes del banco */
		Cliente antonio = new Cliente("123456789Z", "Antonio Alonso", "Av. Pueblo Saharaui, s/n");
		Cliente beatriz = new Cliente("987654321A", "Beatriz Benítez", "Calle Sol, 4");
		
		/* Por defecto, todas las cuentas nuevas tienen 100€ */
		Cuenta cuentaAntonio = new Cuenta(48151, 100, antonio);
		Cuenta cuentaBeatriz = new Cuenta(62342, 100, beatriz);
		
		/* Antonio y Beatriz consultan el saldo */
		System.out.println("La cuenta de " + cuentaAntonio.getCliente().getNombre() + " tiene " + cuentaAntonio.getSaldo() + " euros.");
		System.out.println("La cuenta de " + cuentaBeatriz.getCliente().getNombre() + " tiene " + cuentaBeatriz.getSaldo() + " euros.");
		
		/* Beatriz transfiere 50€ a Antonio */
		cuentaBeatriz.setSaldo(cuentaBeatriz.getSaldo() - 50);
		cuentaAntonio.setSaldo(cuentaAntonio.getSaldo() + 50);
		
		/* Antonio y Beatriz vuelven a consultar su saldo para comprobar que todo ha ido bien */

		System.out.println("La cuenta de " + cuentaAntonio.getCliente().getNombre() + " tiene " + cuentaAntonio.getSaldo() + " euros.");
		System.out.println("La cuenta de " + cuentaBeatriz.getCliente().getNombre() + " tiene " + cuentaBeatriz.getSaldo() + " euros.");
		
		/* Antonio gana 100€ en una rifa y lo ingresa en su cuenta */
		cuentaAntonio.setSaldo(cuentaAntonio.getSaldo() + 100);
		
		/* Beatriz tiene que pagar 30€ a hacienda y retira el dinero */
		cuentaBeatriz.setSaldo(cuentaBeatriz.getSaldo() - 30);
		
		/* Antonio y Beatriz consultan de nuevo el saldo de su cuenta. */
		System.out.println("La cuenta de " + cuentaAntonio.getCliente().getNombre() + " tiene " + cuentaAntonio.getSaldo() + " euros.");
		System.out.println("La cuenta de " + cuentaBeatriz.getCliente().getNombre() + " tiene " + cuentaBeatriz.getSaldo() + " euros.");
		
		/* Antonio transfiere 50€ a Beatriz y vuelven a consultar el saldo de su cuenta */
		
		cuentaAntonio.setSaldo(cuentaAntonio.getSaldo() - 50);
		cuentaBeatriz.setSaldo(cuentaBeatriz.getSaldo() + 50);
		System.out.println("La cuenta de " + cuentaAntonio.getCliente().getNombre() + " tiene " + cuentaAntonio.getSaldo() + " euros.");
		System.out.println("La cuenta de " + cuentaBeatriz.getCliente().getNombre() + " tiene " + cuentaBeatriz.getSaldo() + " euros.");
		
	}
}

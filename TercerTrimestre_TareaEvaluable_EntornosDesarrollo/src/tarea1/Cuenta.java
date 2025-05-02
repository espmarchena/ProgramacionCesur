package tarea1;

public class Cuenta {

	private int cuenta;
	private int saldo;
	private Cliente cliente;
	
	public Cuenta(int cuenta, int saldo, Cliente cliente) {
		this.cuenta = cuenta;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
}

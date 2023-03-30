 package cuentaCorriente;

import java.util.List;
import java.util.Scanner;

public class CuentaCorriente {

	/*
	 * Si no definimos ningún constructor por campos,
	 * se puede utilizar el constructor vacío por defecto.
	 * En el momento que se defina un constructor por campos
	 * específico, si se quiere usar el constructor por defecto
	 * será necesario definirlo en la clase de forma explícita.
	 */
	
	public CuentaCorriente(String dni, String nombreTitular, double saldo) {
		super();
		this.dni = dni;
		this.nombreTitular = nombreTitular;
		this.saldo = saldo;
	}

	public CuentaCorriente() {
		super();
	}

	//Atributos
	/*
	 * Modificador private: solo la misma clase podrá acceder a los
	 * atributos de forma directa.
	 */
	
	private String dni;
	private String nombreTitular;
	private double saldo;
	private int contador = 0;
	//Getters y Setters
	/*
	 * Se utilizar para que software externo a la clase pueda 
	 * tener acceso a los atributos.
	 */
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	//Sobre escritura toString
	@Override
	public String toString() {
		return "CuentaCorriente [dni=" + dni + ", nombreTitular=" + nombreTitular + ", saldo=" + saldo + "]";
	}
	
	//Métodos
	
	//Crear una cuenta
	public CuentaCorriente crearCuenta () {
		
		System.out.println("CREAR CUENTA: ");
		Scanner entradaDni = new Scanner(System.in);	
		Scanner entradaNombreTitular = new Scanner(System.in);	
		String opcionEntradaDni;
		String opcionEntradaNombreTitular;
		System.out.println("Introduza el DNI del titular: ");
		opcionEntradaDni = entradaDni.next();
		System.out.println("Introduza el nombre completo del titular: ");
		opcionEntradaNombreTitular = entradaNombreTitular.next();
		
		//Opción 1, constructor vacío
		CuentaCorriente cccVacio = new CuentaCorriente();
		cccVacio.setDni(opcionEntradaDni);
		cccVacio.setNombreTitular(opcionEntradaNombreTitular);
		cccVacio.setSaldo(0);
		return cccVacio;

		//Opción 2, constructor con todos los campos
		/*CuentaCorriente cccInicializado = new CuentaCorriente(opcionEntradaDni, opcionEntradaNombreTitular, 0);
		return cccInicializado;*/
	}
	//Ingresar dinero a la cuenta
	public List<CuentaCorriente> ingresoCuenta(List<CuentaCorriente> bd) {
		System.out.println("INGRESO CUENTA: ");
		//pedir dni
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		
			

		if(buscarcuenta(opcionEntradaDni,bd)) 
		{
			System.out.println("Indique saldo a ingresar: ");
			Scanner entradaIngreso = new Scanner(System.in);
			double ingreso = entradaIngreso.nextDouble();
			double saldoActual = bd.get(contador).getSaldo();
			double saldoNuevo = saldoActual+ingreso;
			bd.get(contador).setSaldo(saldoNuevo);
			System.out.println("Saldo anterior: "+saldoActual+" Saldo nuevo: "+saldoNuevo);
			return bd;
		}
		else 
		{
			System.out.println("No existe cuenta para el dni indicaco: "+opcionEntradaDni);
			return bd;
		}
	}
	//Mostrar Cuenta
	public void mostrarCuentasUsuario(List<CuentaCorriente> bd)
	{
		System.out.println("MOSTRAR CUENTA: ");
		//pedir dni
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		//buscar la cuenta
			

		if(buscarcuenta(opcionEntradaDni,bd)) 
		{
			System.out.println("Informacion del clienta: ");
			double saldoActual = bd.get(contador).getSaldo();
			String nombre=bd.get(contador).getNombreTitular();
			System.out.println("Dni: "+opcionEntradaDni+" Nombre: "+nombre+" Salsdo: "+saldoActual);
		}
		else 
		{
			System.out.println("No existe cuenta para el dni indicaco: "+opcionEntradaDni);
			
		}
		
	}
	public List<CuentaCorriente> retiroCuenta(List<CuentaCorriente> bd) 
	{
		System.out.println("Retirar CUENTA: ");
		//pedir dni
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		
			

		if(buscarcuenta(opcionEntradaDni,bd)) 
		{
			System.out.println("Indique saldo a retirar: ");
			Scanner entradaRetiro = new Scanner(System.in);
			double retirar = entradaRetiro.nextDouble();
			double saldoActual = bd.get(contador).getSaldo();
			double saldoNuevo = saldoActual-retirar;
			if(saldoNuevo>=0)
			{
				bd.get(contador).setSaldo(saldoNuevo);
			}
			else
			{
				System.out.println("El salo que quieres retirar es de "+retirar+" el inferior al que tienes en la cuenta [Acualmente tienes: "+saldoActual+" ]");
			}
			
			
			System.out.println("Saldo anterior: "+saldoActual+" Saldo nuevo: "+saldoNuevo);
			
		}
		else 
		{
			System.out.println("No existe cuenta para el dni indicaco: "+opcionEntradaDni);

		}
		return bd;
	}
	//buscar la cuenta
	public boolean buscarcuenta(String dniUsuario,List<CuentaCorriente> bd)
	{
		contador=0;
		for(CuentaCorriente cuenta: bd) {			
			String dniBd = cuenta.getDni();
			if(dniBd.equals(dniUsuario)) 
			return true;
			
			contador++;			
		}
		
		return false;
	}

}
